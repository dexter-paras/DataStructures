/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.test;

import org.gojek.cachelib.cache.policies.LRUEvictionPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author paras.chawla
 * @version $Id: LRUEvictionPolicyTest.java, v 0.1 2020-11-23 23:46 paras.chawla Exp $$
 */
public class LRUEvictionPolicyTest {

    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @BeforeEach
    void setUp() {
        lruEvictionPolicy = new LRUEvictionPolicy<Integer>();
    }

    @Test
    void test1_NoKeyDuringInitialization() {
        assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    void test2_EvictKeyShouldReturnInSameOrderOfKeyAccessed() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        assertEquals(1, lruEvictionPolicy.evictKey());
        assertEquals(2, lruEvictionPolicy.evictKey());
        assertEquals(3, lruEvictionPolicy.evictKey());
        assertEquals(4, lruEvictionPolicy.evictKey());
    }


}