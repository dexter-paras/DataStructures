/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author paras.chawla
 * @version $Id: TopKFrequentLC347.java, v 0.1 2019-09-21 21:43 paras.chawla Exp $$
 * description : Given a non-empty array of integers, return the k most frequent elements.
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 */

public class TopKFrequentLC347 {

    public List<Integer> topKFrequent(int[] nums, int k) {

        /* create a HashMap with element and count*/
        Map<Integer, Integer> unsortedMap = createUnsortedMap(nums);
        System.out.println(unsortedMap);

        /* sort hashMap with values , Collections.sort only accept list
         * Step 1 :Convert hashMap to a List - entrySet include full entry*/
        List<Map.Entry<Integer, Integer>> unsortedList = new LinkedList<>(unsortedMap.entrySet());

        /* Step 2 :Sort List using COllections.sort with asc or desc order*/
        Collections.sort(unsortedList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        /* List is sorted now */
        List<Integer> topKFrequestList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topKFrequestList.add(unsortedList.get(i).getKey());
        }
        return topKFrequestList;
    }

    public List<Integer> topKFrequentSolution2(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    //use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
    public List<Integer> topKFrequentUsingHeap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    // use treeMap. Use freqncy as the key so we can get all freqencies in order
    public List<Integer> topKFrequentUsingTreeMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (!freqMap.containsKey(freq)) {
                freqMap.put(freq, new LinkedList<>());
            }
            freqMap.get(freq).add(num);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

    private Map<Integer, Integer> createUnsortedMap(int[] num) {
        Map<Integer, Integer> unsortedMap = new HashMap<>();
        for (int i : num) {
            unsortedMap.put(i, unsortedMap.getOrDefault(i, 0) + 1);
        }
        return unsortedMap;
    }

    public static void main(String[] args) {
        int[] unsortedArray = new int[]{1, 1, 2, 2, 2, 2, 2, 8, 6, 6, 8, 8, 8};
        System.out.println(new TopKFrequentLC347().topKFrequent(unsortedArray, 1));
        System.out.println(new TopKFrequentLC347().topKFrequentSolution2(unsortedArray, 1));
        System.out.println(new TopKFrequentLC347().topKFrequentUsingHeap(unsortedArray, 2));
        // creating a TreeMap<Frequency, List<Integer>>
        System.out.println(new TopKFrequentLC347().topKFrequentUsingTreeMap(unsortedArray, 2));
    }
}