package com.medolia.demo.jvm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lbli@trip.com
 * @date 2021/9/2
 */
public class LRUCache extends LinkedHashMap {

    private int CACHE_CAP;


    public LRUCache(int CACHE_CAP) {
        this.CACHE_CAP = CACHE_CAP;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > CACHE_CAP;
    }
}
