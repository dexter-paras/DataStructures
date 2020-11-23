/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib;

import org.gojek.cachelib.cache.Cache;
import org.gojek.cachelib.factory.CacheFactory;

/**
 * @author paras.chawla
 * @version $Id: Main.java, v 0.1 2020-11-23 23:25 paras.chawla Exp $$
 */
public class Main {

    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new CacheFactory().defaultCache(3);

        // h<->1<->t
        cache.put(1, 1);

        // h<->1<->2<->t
        cache.put(2, 2);

        // h<->2<->1<->t
        System.out.println(cache.get(1));

        // h<->2<->1<->3<->t
        cache.put(3, 3);
        System.out.println(cache.get(3));

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.

        // h<->1<->3<->4<->t
        cache.put(4, 4);

        cache.get(2); // This should throw exception "Tried to access non-existing key."
    }

}