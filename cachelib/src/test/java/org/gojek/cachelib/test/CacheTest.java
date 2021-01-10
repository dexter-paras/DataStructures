/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.test;

import org.gojek.cachelib.cache.Cache;
import org.gojek.cachelib.cache.exceptions.KeyNotFoundException;
import org.gojek.cachelib.cache.exceptions.StorageFullException;
import org.gojek.cachelib.factory.CacheFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author paras.chawla
 * @version $Id: CacheTest.java, v 0.1 2020-11-23 23:41 paras.chawla Exp $$
 */
public class CacheTest {

    Cache<Integer, Integer> cache;

    @Mock
    CacheFactory<Integer, Integer> cacheFactory;

    @BeforeEach
    public void setup() {

        /* Approach 1 -
        CacheFactory mockCacheFactory = Mockito.mock(CacheFactory.class);
        cache = mockCacheFactory.defaultCache(3);


        Approach 2 -
        MockitoAnnotations.initMocks(this);
        cache = cacheFactory.defaultCache(3);
        */

        cache = new CacheFactory<Integer, Integer>().defaultCache(3);
    }

    @Test
    public void test_getAndPutIntegerElementsInCache() {
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // Accessing 1 after 2 got inserted which makes 2 the least recently used till now.
        cache.put(3, 3);
        assertEquals(3, cache.get(3));

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.
        cache.put(4, 4);

        cache.get(2); // This should throw exception "Tried to access non-existing key."
    }

    @Test
    @Disabled("Repeat Test case")
    public void test_getAndPutIntegerElementsInCache_Repeat() {
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // Accessing 1 after 2 got inserted which makes 2 the least recently used till now.
        assertTrue(cache.get(1) != null);
        cache.put(3, 3);
        assertEquals(3, cache.get(3));

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.
        cache.put(4, 4);

        cache.get(2); // This should throw exception "Tried to access non-existing key."
    }

    @Test
    public void keyNotExistInCache() throws KeyNotFoundException {
        Assertions.assertThrows(KeyNotFoundException.class, () -> {
            cache.get(10);
        });
    }

    /*
    @Test(expected = Exception.class)
    public void testThrowsException() throws Exception {
        // ...
    }
    */

    @Test
    void testFailWithTimeout() throws InterruptedException {
        Assertions.assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(1000));
    }

    /*
    @Test(timeout = 10)
    public void testFailWithTimeout() throws InterruptedException {
        Thread.sleep(100);
    }*/

    @AfterEach
    public void after() {
        System.out.println("If you allocate external resources in a Before method you need to release them after the test runs.");
    }

}