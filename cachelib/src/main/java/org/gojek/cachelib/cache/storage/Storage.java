/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.cache.storage;

/**
 * @author paras.chawla
 * @version $Id: Storage.java, v 0.1 2020-11-23 19:58 paras.chawla Exp $$
 * Generic Storage with storage related behaviors
 */
public interface Storage<K, V> {

    void put(K key, V value);

    V get(K key);

    void remove(K key);

}