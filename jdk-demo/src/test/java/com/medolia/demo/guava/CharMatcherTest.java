package com.medolia.demo.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lbli@trip.com
 * @date 2021/9/9
 */
public class CharMatcherTest {
    /**
     * 给定字符集， retainFrom() 只保留字符集字符
     */
    @Test
    public void whenRemoveSpecialCharacters_thenRemoved() {
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.javaLetterOrDigit();
        String result = matcher.retainFrom(input);

        assertEquals("Hello12", result);
    }

    @Test
    public void whenRemoveNonASCIIChars_thenRemoved() {
        String input = "あhello₤";

        String result = CharMatcher.ascii().retainFrom(input);
        assertEquals("hello", result);

        result = CharMatcher.inRange('0', 'z').retainFrom(input);
        assertEquals("hello", result);
    }

    /**
     * 自定义字符集
     */
    @Test
    public void whenRemoveCharsNotInCharset_thenRemoved() {
        Charset charset = Charset.forName("cp437");
        CharsetEncoder encoder = charset.newEncoder();

        Predicate<Character> inRange = encoder::canEncode;

        String result = CharMatcher.forPredicate(inRange)
                .retainFrom("helloは");
        assertEquals("hello", result);
    }

    @Test
    public void whenValidateString_thenValid() {
        String input = "hello";

        boolean result = CharMatcher.javaLowerCase().matchesAllOf(input);
        assertTrue(result);

        result = CharMatcher.is('e').matchesAnyOf(input);
        assertTrue(result);

        result = CharMatcher.javaDigit().matchesNoneOf(input);
        assertTrue(result);
    }
}
