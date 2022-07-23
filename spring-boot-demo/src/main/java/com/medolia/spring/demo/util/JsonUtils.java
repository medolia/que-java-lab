package com.medolia.spring.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Slf4j
public class JsonUtils {
    private JsonUtils(){};

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 序列化
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
        // 解序列化
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 解析
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 映射
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> String serialize(T bean) {
        String result = null;
        try {
            result = beanToJson(bean);
        } catch (Exception e) {
            log.warn("serialize", e);
        }
        return result;
    }

    private static <T> String beanToJson(T bean) throws Exception {
        return mapper.writeValueAsString(bean);
    }

    public static <T> T deserialize(String json, Class<T> valueType) {
        T result = null;
        try {
            result = jsonToBean(json, valueType);
        } catch (Exception e) {
            log.warn("deserialize", e);
        }
        return result;
    }

    private static <T> T jsonToBean(String json, Class<T> valueType) throws Exception {
        return mapper.readValue(json, valueType);
    }

    public static <T> List<T> deserializeToList(String json, Class<T> valueType) {
        List<T> result = null;
        try {
            result = jsonToBeanList(json, valueType);
        } catch (Exception e) {
            log.warn("deserializeToList", e);
        }
        return result;
    }

    private static <T> List<T> jsonToBeanList(String json, Class<T> valueType) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
        return mapper.readValue(json, javaType);
    }
}
