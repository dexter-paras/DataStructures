/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.cache.storage.impl;

import org.gojek.cachelib.cache.exceptions.KeyNotFoundException;
import org.gojek.cachelib.cache.exceptions.StorageFullException;
import org.gojek.cachelib.cache.storage.Storage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: HashMapBasedStorage.java, v 0.1 2020-11-23 19:59 paras.chawla Exp $$
 */
public class HashMapBasedStorage<K, V> implements Storage<K, V> {

    Map<K, V> map;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.map = new HashMap<K, V>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (map.size() < capacity) {
            map.put(key, value);
        } else {
            throw new StorageFullException();
        }
    }

    public V get(K key) {
        if (map.get(key) != null) {
            return map.get(key);
        } else {
            throw new KeyNotFoundException();
        }
    }

    public void remove(K key) {
        map.remove(key);
    }
}