/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: ThreeSum.java, v 0.1 2020-11-27 14:20 paras.chawla Exp $$
 * <p>
 * 1. For the main function:
 * <p>
 * Sort the input array nums. Iterate through the array: If the current value is greater than zero, break from the loop. Remaining values
 * cannot sum to zero. If the current value is the same as the one before, skip it. Otherwise, call twoSumII for the current position i.
 * <p>
 * 2. For twoSumII function:
 * <p>
 * Set the low pointer lo to i + 1, and high pointer hi to the last index. While low pointer is smaller than high: If sum of nums[i] +
 * nums[lo] + nums[hi] is less than zero, increment lo. If sum is greater than zero, decrement hi. Otherwise, we found a triplet: Add it to
 * the result res. Decrement hi and increment lo. Increment lo while the next value is the same as before to avoid duplicates in the result.
 * 3. Return the result res.
 */
public class ThreeSum {

    // nums[] ={-1,0,1,2,-1,-4}
    // sorted[]={-4,-3,-1,0,1,2}
    //            ^  ^        ^
    //            i low       high
    public List<List<Integer>> threeSum(int[] nums) {

        //1. Sort input array
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        // 2. Iterate through array && make sure number is less than or equal to zero
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {

            // if curr element is same as previous element, skip processing
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(i, nums, res);
            }
        }
        return res;
    }

    private void twoSum(int idx, int[] nums, List<List<Integer>> res) {

        int low = idx + 1, high = nums.length - 1;
        while (low < high) {
            int sum = nums[idx] + nums[low] + nums[high];
            if (sum < 0) {
                low++;
            } else if (sum > 0) {
                high--;
            } else {
                res.add(Arrays.asList(nums[idx], nums[low++], nums[high--]));
                // Increment lo while the next value is the same as before to avoid duplicates in the result.
                while (low < high && nums[low] == nums[low - 1]) { ++low; }
            }
        }
    }

    // Approach 2 - Nice ,Simple and Working
    //https://leetcode.com/problems/3sum/discuss/143636/Java-with-set
    public List<List<Integer>> threeSumSol2(int[] nums) {

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = nums.length - 1;

            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return new ArrayList<>(set);
    }

}