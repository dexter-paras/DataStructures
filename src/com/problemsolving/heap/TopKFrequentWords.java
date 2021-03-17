/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: TopKFrequentWords.java, v 0.1 2021-03-11 9:01 PM paras.chawla Exp $$
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {

        // create a map
        Map<String, Integer> map = new HashMap<>();

        // love-2 , leetcode-2, word-1 => k=2
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // if same frequency --> asc order
        PriorityQueue<Pairr> heap = new PriorityQueue<>(k, (p1, p2) -> {
            if (p1.count == p2.count) {
                return p1.str.compareTo(p2.str);
            } else {
                return p2.count - p1.count;
            }
        });

        List<String> result = new ArrayList<>();

        // Create a PQ
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            heap.offer(new Pairr(entry.getKey(), entry.getValue()));
        }

        while (!heap.isEmpty() || k > 0) {
            result.add(heap.poll().str);
            k--;
            if (k == 0) {
                return result;
            }
        }
        return result;
    }

    // Better solution
    public List<String> topKFrequentApproach2(String[] words, int k) {

        // create a map
        Map<String, Integer> map = new HashMap<>();

        // love-2 , leetcode-2, word-1 => k=2
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Creating a min-Heap i.e. top elements with lower count and if count is equal ,have desc order
        PriorityQueue<String> heap = new PriorityQueue<>(k, (p1, p2) -> {
            if (map.get(p1).equals(map.get(p2))) {
                return p2.compareTo(p1);
            } else {
                return map.get(p1) - map.get(p2);
            }
        });

        List<String> result = new ArrayList<>();

        // Create a min PQ and removing from min pq when k exceeds
        for (String str : map.keySet()) {
            heap.offer(str);

            // Store only k elements in HEAP
            if (heap.size() > k) {
                heap.poll();
            }
        }

        while (!heap.isEmpty() ) {
            result.add(heap.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords obj = new TopKFrequentWords();
        obj.topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2);
    }
}

class Pairr {

    String str;
    int    count;

    public Pairr(String str, int count) {
        this.str = str;
        this.count = count;
    }

}