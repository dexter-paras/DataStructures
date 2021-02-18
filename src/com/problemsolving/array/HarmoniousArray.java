/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: HarmoniousArray.java, v 0.1 2021-02-05 8:10 AM paras.chawla Exp $$ Learnings -
 */
public class HarmoniousArray {

    // Approach1 - O(n^2)
    public int findLHS(int[] nums) {
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {

            boolean diffFound = false;
            int count = 0;

            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                } else if (nums[i] == nums[j] + 1) {
                    diffFound = true;
                    count++;
                }
            }

            if (diffFound) { maxCount = Math.max(count, maxCount); }

        }
        return maxCount;
    }

    // Approach 2 - O(nlogn) Sorting and counting keys

    // APproach 3 - Using HashMap
    // Intuition - if key exist , find if key+1 also exist and add there counts
    public int findLHSUsingMap(int[] nums) {

        // 1. create map
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {

            int count = 0;
            int key = entry.getKey();
            if (countMap.containsKey(key + 1)) {
                count = countMap.get(key) + countMap.get(key + 1);
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        HarmoniousArray obj = new HarmoniousArray();
        System.out.println(obj.findLHS(new int[] {1, 3, 2, 2, 5, 2, 3, 7}));
    }
}