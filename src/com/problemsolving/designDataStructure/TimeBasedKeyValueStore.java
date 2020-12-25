/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import com.problemsolving.genericProgramming.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: TimeBasedKeyValueStore.java, v 0.1 2020-12-25 10:20 PM paras.chawla Exp $$
 * https://leetcode.com/problems/time-based-key-value-store/discuss/244229/Java-beats-100
 *
 *   20 <- root
 *  /  \
 * 10 30
 *
 * tree.floorKey(timeStamp)
 *
 */
public class TimeBasedKeyValueStore {

    Map<String, List<Pair<String, Integer>>> timeMap;

    /**
     * Initialize your data structure here.
     */
    public TimeBasedKeyValueStore() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!timeMap.containsKey(key)) {
            timeMap.put(key, new ArrayList<>());
        }
        timeMap.get(key).add(new Pair(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }
        return binarySearch(timestamp, timeMap.get(key));
    }

    /*
    // To find the timeStamp which either matches or to find just greatest to this timestamp in ArrayList
    // 10 -> 20-> 30-> 40-> 50->60->70
    private String binarySearch(int timestamp, List<Pair<String, Integer>> pairs, int low, int high) {

        int i = Collections.binarySearch(pairs, new Pair<String, Integer>("{", timestamp),
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));

        if (i >= 0) {
            return pairs.get(i).getKey();
        } else if (i == -1) {
            return "";
        } else {
            return pairs.get(-i - 2).getKey();
        }
    }
    */

    protected String binarySearch(int timeStamp, List<Pair<String, Integer>> list) {
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (list.get(mid).getValue() == timeStamp) { return list.get(mid).getKey(); }
            if (list.get(mid).getValue() < timeStamp) {
                if (list.get(mid + 1).getValue() > timeStamp) { return list.get(mid).getKey(); }
                low = mid + 1;
            } else { high = mid - 1; }
        }
        return list.get(low).getValue() <= timeStamp ? list.get(low).getKey() : "";
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore kv = new TimeBasedKeyValueStore();
        kv.set("love", "high", 10); // store the key "foo" and value "bar" along with timestamp = 1
        kv.set("love", "low", 20);
        System.out.println(kv.get("love", 5));  // output "bar"
        System.out.println(kv.get("love",
                10)); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is
        // at timestamp 1 ie "bar"
        //kv.set("foo", "bar2", 4);
        System.out.println(kv.get("love", 15)); // output "bar2"
        System.out.println(kv.get("love", 20)); //output "bar2"
        System.out.println(kv.get("love", 25));
    }

}