package com.medolia.arch.game;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.util.VisibleForTesting;

/**
 * @author lbli
 * @date 2022/5/1
 */
public interface Character {


    @VisibleForTesting
    static Character testOne() {
        return new Character() {
            @Override
            public String toString() {
                return "test-character-" + RandomStringUtils.randomAscii(4);
            }
        };
    }
}
