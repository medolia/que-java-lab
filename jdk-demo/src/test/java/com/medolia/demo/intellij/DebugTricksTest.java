package com.medolia.demo.intellij;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
class DebugTricksTest {
    @Test
    void ObjMaskDemo() {
        Collection<Task> tasks = Arrays.asList(new Task(), new Task());
        tasks.forEach(task -> new Thread(task).start());
    }

    private static void mayBeAdd(Collection<Integer> holder) {
        int i = ThreadLocalRandom.current().nextInt(10);
        if (i % 3 == 0) {
            holder.add(i);
        }
    }

    private static class Task implements Runnable {

        private final Collection<Integer> holder = new ArrayList<>();

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                mayBeAdd(holder);
            }
        }
    }
}
