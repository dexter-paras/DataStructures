/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author paras.chawla
 * @version $Id: LFUCache.java, v 0.1 2021-01-02 8:50 PM paras.chawla Exp $$
 */
public class LFUCache<K, V> {

    Map<K, V>                       storage;
    Map<K, Integer>                 freqMap;
    TreeMap<Integer, LinkedList<K>> lfuMap;
    int                             capacity;

    public LFUCache(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
        freqMap = new HashMap<>();
        lfuMap = new TreeMap<>();
    }

    public int get(K key) {

        if (!storage.containsKey(key)) {
            return -1;
        }

        int prevCount = freqMap.get(key);
        freqMap.put(key, prevCount + 1);
        int currCount = freqMap.get(key);

        // update TreeMap
        // Remove key from prevCount LinkedList
        LinkedList<K> list = lfuMap.get(prevCount);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == key) {
                    list.remove(i);
                    break;
                }
            }
            if(list.size()==0){
                lfuMap.pollFirstEntry();
            }
        }

        // add key in new currCount TreeMap
        lfuMap.put(currCount, lfuMap.getOrDefault(currCount, new LinkedList<K>()));
        lfuMap.get(currCount).add(key);
        return (Integer) storage.get(key);
    }

    public void put(K key, V value) {

        if(capacity==0){
            return;
        }

        // 1. If need to evict key
        if (storage.size() >= capacity) {

            Map.Entry<Integer, LinkedList<K>> firstEntry = lfuMap.firstEntry();
            K evictKey = firstEntry.getValue().getFirst();
            if (firstEntry.getValue().size() == 1) {
                lfuMap.pollFirstEntry();
            } else {
                firstEntry.getValue().remove(firstEntry.getValue().getFirst());
            }

            System.out.println("Evicted Key is "+ evictKey);

            freqMap.remove(evictKey);

            storage.remove(evictKey);
        } else if (storage.containsKey(key)) {
            storage.put(key, value);
            return;
        }
        storage.put(key, value);
        freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
        int count = freqMap.get(key);
        lfuMap.put(count, lfuMap.getOrDefault(count, new LinkedList<K>()));
        lfuMap.get(count).add(key);

    }

    public static void main(String[] args) {
        LFUCache<Integer, Integer> lfuCache = new LFUCache<>(2);
        lfuCache.put(1, 10);
        lfuCache.put(2, 20);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 30);
        System.out.println(lfuCache.get(2));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        lfuCache.put(4, 4);   // evicts key 1.
        System.out.println(lfuCache.get(1));      // return -1 (not found)
        System.out.println(lfuCache.get(3));      // return 3
        System.out.println(lfuCache.get(4));      // return 4
    }

}