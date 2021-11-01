package com.medolia.spring.demo.util;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.lang.reflect.AnnotatedType;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * @author lbli
 * @date 2021/9/14
 */
@Slf4j
public class FileMapper {

    private static final String PATH = "/demo.properties";

    public static String getValue(String key) {
        Map<String, String> map = getMap();
        return map.getOrDefault(key, StringUtils.EMPTY);
    }

    /**
     * java 8 lambda 其实是一个特殊的匿名内部类，实际上也会实例化，而且对于一个特定的 lambda jvm 会固定其为一个单例实现复用
     * 实例完全限定名为 [classFullName]$$lambda$[code]/[id]@[address]
     * e.p.: com.medolia.lab.util.FileMapper$$Lambda$262/1704064279@731f8236
     * <p>
     * guava 的方法 memoize(Supplier) 返回一个 get() 方法带双重检查锁逻辑的 supplier，
     * 从源码看，每次调用 get() 都会显式创建一个新的对象重新计算，所以带双重检查只是为了线程安全；
     * memoization 只会让某个调用更快地返回，而并没有缓存复用结果；
     */
    private static Map<String, String> getMap() {
        Supplier<Map<String, String>> mapSupplier = () -> {
            Resource resource = new ClassPathResource(PATH);
            EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
            Properties properties = null;
            try {
                properties = PropertiesLoaderUtils.loadProperties(encodedResource);
            } catch (Exception e) {
                //
            }
            if (Objects.isNull(properties)) {
                return new HashMap<>(16);
            }
            return properties.entrySet().stream().collect(toMap(
                    e -> String.valueOf(e.getKey()),
                    e -> String.valueOf(e.getValue()),
                    (e1, e2) -> e2));
        };
        Supplier<Map<String, String>> delegate = Suppliers.memoize(mapSupplier);
        // delegate = memoizeWithLog(mapSupplier);
        return delegate.get();
    }

    private static Supplier<Map<String, String>> memoizeWithLog(Supplier<Map<String, String>> mapSupplier) {
        // 反射看到这个类实现了 Supplier 接口
        AnnotatedType[] annotatedInterfaces = mapSupplier.getClass().getAnnotatedInterfaces();
        String interfaceDec = Stream.of(annotatedInterfaces)
                .map(e -> e.getType().getTypeName())
                .collect(Collectors.joining(" "));
        log.info("lambda instance: {}, 实现接口: {}", mapSupplier, interfaceDec);
        log.info("type check 1: {}", mapSupplier.getClass().getTypeName());
        Supplier<Map<String, String>> delegate = Suppliers.memoize(mapSupplier);
        log.info("static delegate instance: {}", delegate);
        log.info("type check 2: {}", delegate.getClass().getTypeName());
        return delegate;
    }
}
