/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: SubArraySum.java, v 0.1 2020-04-22 22:59 paras.chawla Exp $$
 */
public class SubArraySum {

    // Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k.
    // Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.
    public int subarraySum(int[] nums, int k) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j]. So if we know SUM[0, i - 1]
                  and SUM[0, j], then we can easily get SUM[i, j]. To achieve this, we just need to go through the array,
                 calculate the current sum and save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).
     */
    public int subarraySum2(int[] nums, int k) {

        int count = 0;

        int prefixSum = 0;

        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int num : nums) {
            prefixSum += num;

            if (prefixSum == k) {
                count++;
            }

            if (prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum-k);
            }

            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    // Improvement
    public int subarraySum3(int[] nums, int k) {

        int count = 0;

        int prefixSum = 0;

        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0,1);
        for (int num : nums) {
            prefixSum += num;

            if (prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum-k);
            }

            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SubArraySum().subarraySum(new int[] {2, 4, 3, 1, 2, 1, -3, 10, 7}, 7));
        System.out.println(new SubArraySum().subarraySum(new int[] {1, 3, 2, 1, 5, 4, 2}, 6));
        System.out.println(new SubArraySum().subarraySum(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
    }
}