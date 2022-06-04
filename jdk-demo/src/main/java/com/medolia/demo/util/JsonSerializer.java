package com.medolia.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Json 序列化工具
 *
 * @author lbli@trip.com
 * @date 2021/7/16
 */
@Slf4j
public class JsonSerializer {

    private final static ObjectMapper MAPPER;

    static {
        MAPPER = Jackson2ObjectMapperBuilder
                .json()
                .featuresToEnable(
                        MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
                        JsonParser.Feature.IGNORE_UNDEFINED,
                        JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                        DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                        DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .featuresToDisable(
                        SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS)
                .build();
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> String serialize(T bean) {
        try {
            Objects.requireNonNull(bean);
            return MAPPER.writeValueAsString(bean);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static <T> List<T> deserializeToList(String json, Class<T> valueType) {
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(json));
            JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, valueType);
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

    public static <T> T deserialize(String value, Class<T> type) {
        try {
            return MAPPER.readValue(value, type);
        } catch (Exception e) {
            log.warn("deserialize", e);
            return null;
        }
    }

    public static <T> T deserialize(String value, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(value, typeReference);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
