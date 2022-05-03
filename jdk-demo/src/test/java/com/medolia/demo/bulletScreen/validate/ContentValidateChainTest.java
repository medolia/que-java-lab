package com.medolia.demo.bulletScreen.validate;

import com.medolia.demo.bulletScreen.BulletScreen;
import com.medolia.demo.bulletScreen.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Slf4j
class ContentValidateChainTest {

    @Test
    void testFireChain() {

        User sender = new User();
        BulletScreen bulletScreen = BulletScreen.builder()
                .id(RandomStringUtils.randomNumeric(20))
                .isValid(true)
                .orgContent("marvelous! havent seen this before")
                .sendTime(LocalDateTime.now())
                .user(sender)
                .videoInstant(Duration.ofSeconds(304L))
                .build();
        log.info("bullet screen: {}", bulletScreen);


        ContentValidateChain chain = new ContentValidateChain(ContentValidateEleFactory.contentValidElementList());
        chain.fireChain(bulletScreen);

    }
}