package com.medolia.demo.jvm;

import com.google.common.primitives.Bytes;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class HashCollectionTest {

    @Test
    void testMutableSet() {
        Set<CharSequence> set = new HashSet<>();

        ByteBuffer byteBuffer = ByteBuffer.wrap("A".getBytes(StandardCharsets.UTF_8));
        set.add(String.valueOf(byteBuffer.get()));
        byteBuffer.clear();
        byteBuffer.put("B".getBytes(StandardCharsets.UTF_8));
        byteBuffer.clear();
        set.add(String.valueOf(byteBuffer.get()));
        System.out.println("set = " + set);

    }
}
