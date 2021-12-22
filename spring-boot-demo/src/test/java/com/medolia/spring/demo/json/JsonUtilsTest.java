package com.medolia.spring.demo.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.medolia.spring.demo.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author lbli@trip.com
 * @date 2021/8/27
 */
@Slf4j
class JsonUtilsTest {

    @Test
    public void whenSerializingJava8DateAndReadingFromEntity_thenCorrect() throws IOException {


        String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        EventWithLocalDate result = mapper.readValue(json, EventWithLocalDate.class);
        assertThat(result.getEventDate().toString(), containsString("2014-12-20"));
    }

    @Test
    public void whenSerializingJava8DateAndReadingValue_thenCorrect() throws IOException {
        String stringDate = "\"2014-12-20\"";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        LocalDate result = mapper.readValue(stringDate, LocalDate.class);
        assertThat(result.toString(), containsString("2014-12-20"));
    }

    @Test
    public void whenDeserialisingZonedDateTimeWithDefaults_thenNotCorrect()
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        String converted = objectMapper.writeValueAsString(now);

        ZonedDateTime restored = objectMapper.readValue(converted, ZonedDateTime.class);
        System.out.println("serialized: " + now);
        System.out.println("restored: " + restored);
        // 指示反序列化时忽略时区
        assertThat(now, not(restored));
    }

    @Test
    void testSerializeJava8TimeApi() throws Exception {
        Anchor anchor = new Anchor(
                "medolia",
                LocalDate.of(1995, 12, 10),
                27);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(anchor);
        log.info("json: {}", json);
        Anchor anchor1 = objectMapper.readValue(json, Anchor.class);
        log.info("anchor from json: {}", anchor1);
    }

    @Test
    void serialize() throws Throwable {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        String json1 = JsonUtils.serialize(calendar);
        System.out.println(json1);
        String json2 = JsonUtils.serialize(now);
        System.out.println(json2);
        assertEquals(json1, json2);
    }

    @Test
    void dataSerialize() throws Throwable {
        Map<Date, String> map = new HashMap<>();
        map.put(new Date(), "now");
        System.out.println(JsonUtils.serialize(map));
    }

    @Data
    @AllArgsConstructor
    static class Anchor {
        String name;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate birthday;
        Integer age;
    }

    static class EventWithLocalDate {

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        public LocalDate eventDate;

        public LocalDate getEventDate() {
            return eventDate;
        }

        public void setEventDate(LocalDate eventDate) {
            this.eventDate = eventDate;
        }
    }

}