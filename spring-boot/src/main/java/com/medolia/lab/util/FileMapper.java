package com.medolia.lab.util;


import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
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

import static java.util.stream.Collectors.toMap;

/**
 * @author lbli
 * @date 2021/9/14
 */
public class FileMapper {

    private static final String PATH = "/demo.properties";

    public static String getValue(String key) {
        Map<String, String> map = getMap();
        return map.getOrDefault(key, StringUtils.EMPTY);
    }

    /**
     * java 8 lambda 其实是一个特殊的匿名内部类，实际上也会实例化，
     * 名字为 [lambda 所在类]$lambda[code] e.p. FileMapper$lambda@1734
     * guava 的 memoize(Supplier) 返回一个 get() 方法带双重检查锁逻辑的 supplier
     */
    private static Map<String, String> getMap() {
        Supplier<Map<String, String>> mapSupplier = () -> {
            Resource resource = new ClassPathResource(PATH);
            EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
            Properties properties = null;
            try {
                properties = PropertiesLoaderUtils.loadProperties(encodedResource);
            } catch (IOException e) {
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
        // 反射看到这个类实现了 Supplier 接口
        // AnnotatedType[] annotatedInterfaces = mapSupplier.getClass().getAnnotatedInterfaces();
        return Suppliers.memoize(mapSupplier).get();
    }
}
