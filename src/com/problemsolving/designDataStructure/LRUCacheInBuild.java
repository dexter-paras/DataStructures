/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LRUCacheInBuild.java, v 0.1 2020-10-07 00:32 paras.chawla Exp $$
 */
public class LRUCacheInBuild extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheInBuild(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}