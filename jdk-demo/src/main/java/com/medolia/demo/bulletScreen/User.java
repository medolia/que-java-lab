package com.medolia.demo.bulletScreen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class User {

    private final String id;

    private final String name;

    public User(String name) {
        this(RandomStringUtils.randomAscii(8), name);
    }

    public User() {
        this("user-" + RandomStringUtils.randomAlphabetic(6));
    }
}
