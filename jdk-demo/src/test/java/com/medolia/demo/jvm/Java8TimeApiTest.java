package com.medolia.demo.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 * <div>
 *  TIME API explain
 *  2021-10-10T21:45:59.182-04:00[America/New_York]
 *  ZonedDateTime/OffsetDateTime/Instant
 *  |_ LocalDateTime ZoneId/ZoneOffset/ZoneRegion
 *       |_ LocalDate LocalTime
 * </div>
 * <p>interval: Duration(all units) Period(Year, Month, Day)
 * opt/com: plus,minus; isBefore,isAfter
 * </div>
 *
 *  @author lbli
 *  @date 2021/10/10
 */
@Slf4j
public class Java8TimeApiTest {

    @Test
    void timeZoneTest2() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("");
    }

    @Test
    void scaleTest() throws Exception {
        int i = 0;
        while (i++ < 2000) {
            LocalDateTime now = LocalDateTime.now();
            Thread.sleep(0, 37);
            System.out.println(now.getNano());
        }
    }

    @Test
    void timeZoneTest() {
        Timestamp timestamp = new Timestamp(1638967356000L);


        Instant instant = timestamp.toInstant();
        Instant instant1 = instant.minusSeconds(29 * 60);
        Date from = Date.from(instant1);
        Instant instant2 = from.toInstant();
        System.out.println("");
    }

    @Test
    public void getCurrentDate() {
        LocalDate today = LocalDate.now();
        log.info("today: {}", today);

        log.info("today: year{} month{} day{}", today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        LocalDate birthday = LocalDate.of(1997, 1, 3);
        log.info("birthday: {}", birthday);

    }

    /**
     * LocalTime 不可变，操作时间偏移后需要赋值给一个新实例
     * 偏移操作：plus minus
     * 比较操作：isBefore isAfter
     */
    @Test
    public void localTimeTest() {
        LocalTime now = LocalTime.now();
        LocalTime twoMinsLater = now.plusMinutes(2);
        LocalTime twoHoursBefore = now.minus(2, ChronoUnit.HOURS);
        log.info("now: {}, 2minsLater: {}, 2hbefore:{}", now, twoMinsLater, twoHoursBefore);
        Duration duration = Duration.between(now, twoMinsLater);
        assertEquals(2, duration.toMinutes());

        duration = Duration.between(now, twoHoursBefore);
        assertEquals(-2, duration.toHours());
        assertTrue(now.isAfter(twoHoursBefore));
        assertTrue(now.isBefore(twoMinsLater));
    }

    @Test
    public void clockTest() {
        Clock utc = Clock.systemUTC();
        Clock defaultClock = Clock.systemDefaultZone();

        log.info("utc:{}, defaultClock:{}", utc.millis(), defaultClock.millis());
    }

    /**
     * 注意，ZoneDateTime.of(LocalDateTime, ZoneId) 只能设置时区，内部不负责转变为正确时间；
     * 如果需要准确时间，需要调用 now(ZoneId)
     */
    @Test
    public void testTimeZone() {
        ZoneId america = ZoneId.of("America/New_York");
        // 获取指定时区的当前日期时间
        LocalDateTime now = LocalDateTime.now(america);
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(now, america);
        log.info("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    @Test
    public void testFormat() {
        String dayAfterTomorrow = "20211012";
        LocalDate localDate = LocalDate.parse(dayAfterTomorrow, DateTimeFormatter.BASIC_ISO_DATE);
        log.info("dayAfterTomorrow: {}", localDate.toString());
    }

    @Test
    public void testInstant() {
        Instant now = Instant.now();
        ZonedDateTime ctt = now.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        log.info("now: {}", ctt);
    }

    @Test
    public void testFormatBuilder() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String text = now.format(formatter);
        log.info("yyyyMMddHHMMss: {}", text);
        LocalDateTime parseDate = LocalDateTime.parse(text, formatter);
        log.info("parseDate: {}", parseDate);

        Duration duration = Duration.between(now, parseDate).abs();
        assertTrue(duration.toMillis() < 1000);
    }
}
