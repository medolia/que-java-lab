package com.medolia.demo.jvm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class PredicateTest {

    List<String> names;

    @BeforeEach
    void beforeAll() {
        names = Arrays.asList("Adam", "Alexander", "John", "Tom");
    }

    @Test
    public void whenFilterListWithCollectionOfPredicatesUsingAnd_thenSuccess() {
        List<Predicate<String>> allPredicates = new ArrayList<>();
        allPredicates.add(str -> str.startsWith("A"));
        allPredicates.add(str -> str.contains("d"));
        allPredicates.add(str -> str.length() > 4);

        List<String> result = names.stream()
                .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        assertThat(result, contains("Alexander"));
    }

    /**
     * Predicate.and() Predicate.or() Predicate.negate()
     */
    @Test
    public void whenFilterListWithCombinedPredicatesUsingOrAndNegate_thenSuccess() {
        Predicate<String> predicate1 = str -> str.startsWith("J");
        Predicate<String> predicate2 = str -> str.length() < 4;

        List<String> result = names.stream()
                .filter(predicate1.or(predicate2.negate()))
                .collect(Collectors.toList());

        assertEquals(3, result.size());
        assertThat(result, contains("Adam", "Alexander", "John"));
    }

    @Test
    public void whenFilterListWithCombinedPredicatesUsingAnd_thenSuccess() {
        Predicate<String> predicate1 = str -> str.startsWith("A");
        Predicate<String> predicate2 = str -> str.length() < 5;

        List<String> result = names.stream()
                .filter(predicate1.and(predicate2))
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        assertThat(result, contains("Adam"));
    }

}
