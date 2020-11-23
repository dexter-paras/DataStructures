/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.cache.policies;

/**
 * @author paras.chawla
 * @version $Id: EvictionPolicy.java, v 0.1 2020-11-23 20:07 paras.chawla Exp $$
 * Generic Eviction policy, impl class implements this class
 */
public interface EvictionPolicy<K> {

    /**
     * change in internal update of key when key is accessed based on Evictionpolicy
     * @param key
     */
    void keyAccessed(K key);

    /*
     Evict key based on eviction policy and return evicted key;
     */
    K evictKey();

}