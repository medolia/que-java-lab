package com.medolia.spring.demo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class DateMarshallTest {

    /**
     * 默认序列化为时间戳 1970.01.01
     */
    @Test
    public void whenSerializingDateWithJackson_thenSerializedToTimestamp()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = df.parse("01-01-1970 01:00");
        Event event = new Event("party", date);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(event);
        assertThat(json, Matchers.is("{\"name\":\"party\",\"eventDate\":3600000}"));
    }

    @Test
    public void whenSerializingDateToISO8601_thenSerializedToText()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "01-01-1970 02:30";
        Date date = df.parse(toParse);
        Event event = new Event("party", date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // StdDateFormat is ISO8601 since jackson 2.9
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("1970-01-01T02:30:00.000+00:00"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Event {
        @JsonProperty("name")
        private String name;
        @JsonProperty("eventDate")
        private Date eventDate;
    }
}
