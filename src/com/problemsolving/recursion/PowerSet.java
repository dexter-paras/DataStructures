/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PowerSet.java, v 0.1 2020-09-15 21:44 paras.chawla Exp $$
 */
public class PowerSet {

    // final result storing all powerSet
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        if (nums.length == 0) {
            return result;
        }

        List<Integer> partialSubSet = new ArrayList();
        generatePowerSet(0, nums, result, partialSubSet);
        return result;
    }

    /*
     *                      1
     *            NORTH /     \ Y
     *                        2
     *                NORTH /    \ Y
     *                            3
     *                    NORTH /   \ Y
     *                               4
     *                        NORTH /    \ Y
     *                       {1,2,3}  {1,2,3,4}
     * */

    /* Intuition - At every Index, we've 2 choices whether to choose or whether not to choose particular item in array
    1. Make a choice
    2. Recurse
    3. Undo the choice
    */

    void generatePowerSet(int idx, int[] nums, List<List<Integer>> result, List<Integer> partialSubSet) {

        // base condition
        if (idx == nums.length) {
            // add deep copy of partialSubset in final result
            result.add(new ArrayList<Integer>(partialSubSet));
            return;
        }

        // choosing element at index idx in partialSubSet
        partialSubSet.add(nums[idx]);

        // after choosing element at idx i , moving to next idx and calling recursion
        generatePowerSet(idx + 1, nums, result, partialSubSet);

        // Not choosing element at index idx in partialSubSet
        partialSubSet.remove(partialSubSet.size() - 1);

        // after not choosing element at idx i , moving to next idx and calling recursion
        generatePowerSet(idx + 1, nums, result, partialSubSet);

    }

    // Approach 2- the method below is adding elements and constructing subsets
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        result.add(tmp); // add empty set
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int n = result.size();
            for (int j = 0; j < n; j++) {
                // NOTE: must create a new tmp object, and add element to it.
                tmp = new ArrayList<Integer>(result.get(j));
                tmp.add(nums[i]);
                result.add(new ArrayList<Integer>(tmp));
            }
        }
        return result;
    }

    // Approach 3- Using BitManipulation

    public List<List<Integer>> subsetsUsingBitManipulation(int[] nums) {
        Arrays.sort(nums);
        int totalNumber = 1 << nums.length; // left shift 1 -> 3 times   0001(1) -> 1000(8)
        List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
        for (int i = 0; i < totalNumber; i++) {
            List<Integer> set = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(nums[j]);
                }
            }
            collection.add(set);
        }
        return collection;
    }

    public List<List<Integer>> subsetsSol3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    /*
    Order of list
    []
    [1]
    [1,2]
    [1,2,3]
    [1,3]
    [2]
    [2,3]
    [3]
    */

    /*
    *            ---
    *   i=0 / i=1  \   i=2  \
    *      1       2        3
    * i=1/i=2 \     /
    *   1,2  1,3  2,3
    *i=2/
    *  1,2,3
    * */

    private void backtrack(List<List<Integer>> list, List<Integer> partialSubset, int[] nums, int idx) {
        list.add(new ArrayList<>(partialSubset));
        for (int i = idx; i < nums.length; i++) {
            partialSubset.add(nums[i]);
            backtrack(list, partialSubset, nums, i + 1);
            partialSubset.remove(partialSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        PowerSet obj = new PowerSet();
        System.out.println(obj.subsetsUsingBitManipulation(new int[] {1, 2, 3}));
    }
}