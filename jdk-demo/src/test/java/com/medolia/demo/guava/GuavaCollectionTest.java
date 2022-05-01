package com.medolia.demo.guava;

import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author lbli@trip.com
 * @date 2021/9/8
 */
public class GuavaCollectionTest {
    @Test
    void biMapException() {
        BiMap<String, String> userId = HashBiMap.create();
        userId.put("medolia", "que");
        userId.put("daicy", "she");

        // 尝试使用其他 key 链接已有的 value 会抛出 IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> userId.put("who", "que"));

        // 使用 forcePut() 删除之前已有的链接
        userId.forcePut("who", "que");
        assertThat(userId.get("who"), is("que"));
        assertThat(userId.inverse().get("que"), is("who"));
    }

    @Test
    void biMapBasic() {
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("medolia", 98789123);

        Integer id = userId.get("medolia");
        String name = userId.inverse().get(98789123);

        assertThat(id, is(98789123));
        assertThat(name, is("medolia"));
    }

    @Test
    void rangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}

    }
}
