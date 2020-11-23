/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.factory;

import org.gojek.cachelib.cache.Cache;
import org.gojek.cachelib.cache.policies.LRUEvictionPolicy;
import org.gojek.cachelib.cache.storage.impl.HashMapBasedStorage;

/**
 * @author paras.chawla
 * @version $Id: CacheFactory.java, v 0.1 2020-11-23 23:26 paras.chawla Exp $$
 */
public class CacheFactory<K, V> {

    public Cache<K, V> defaultCache(int capacity) {
        return new Cache<K, V>(new HashMapBasedStorage<K, V>(capacity), new LRUEvictionPolicy<K>());
    }
}