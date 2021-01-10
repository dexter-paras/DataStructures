/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.cache;

import org.gojek.cachelib.cache.exceptions.KeyNotFoundException;
import org.gojek.cachelib.cache.exceptions.StorageFullException;
import org.gojek.cachelib.cache.policies.EvictionPolicy;
import org.gojek.cachelib.cache.storage.Storage;

/**
 * @author paras.chawla
 * @version $Id: Cache.java, v 0.1 2020-11-23 19:55 paras.chawla Exp $$ Generic Cache storing key as K and value as V
 */
public class Cache<K, V> {

    private final Storage<K, V>     storage;
    private final EvictionPolicy<K> evictionPolicy;

    public Cache(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    /**
     * Put K-V in Cache Storage Update Eviction policy regarding any access of key
     */
    public void put(K key, V value) {
        try {
            this.storage.put(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            K keyEvict = this.evictionPolicy.evictKey();
            this.storage.remove(keyEvict);
            System.out.println("Creating space by evicting item " + keyEvict);
            put(key, value);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        try {
            V value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (KeyNotFoundException e) {
            throw e;
        }
    }
}