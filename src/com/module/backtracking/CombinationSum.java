/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: CombinationSum.java, v 0.1 2020-08-05 21:43 paras.chawla Exp $$
 */
public class CombinationSum {

    //candidates~[2,3,5], target = 9
    // [2,2,2,2](b) ->[2,2,2,3](A) ->[2,2,2,5](b)
    // [2,2,3]->[2,2,3,3](b)
    // [2,2,5](A)
    // [2,3] ->[2,3,3] ->[2,3,3,3](b) ->[2,3,5](b)
    // [2,5] ->[2,5,5](b)
    // [3]->[3,3]->[3,3,3](A) ->[3,3,5](b)
    // [3,5] ->[3,5,5](b)
    // [5] -> 5>4(b)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        //1. Initialize list as result
        List<List<Integer>> finalResult = new ArrayList<>();

        if (candidates == null || candidates.length == 0) {
            return finalResult;
        }

        //2. Sort candidates
        Arrays.sort(candidates);

        // 3. curr list
        List<Integer> curRes = new ArrayList();

        // 4. Recursion as dfs
        findCombinationSumToTarget(candidates, target, curRes, finalResult, 0);

        return finalResult;
    }

    private void findCombinationSumToTarget(int[] candidates, int target, List<Integer> curRes, List<List<Integer>> finalResult,
                                            int idx) {

        //1. base condition
        if (target == 0) {
            // create deep copy of result,
            // if you directly add curRes, what you actually add is the address of curRes.
            // In the following calculation, curRes will continue change, so the content in the address will change.
            // But if you add a new ArrayList, it won't change anymore.
            finalResult.add(new ArrayList<>(curRes));
            return;
        }

        //2. candidates[] [2,3,5] target=9
        for (int i = idx; i < candidates.length; i++) {

            //if you dont do this , your target will get into negatives, and will also go over the entire array list
            if (candidates[i] > target) {
                break;
            }
            curRes.add(candidates[i]);
            findCombinationSumToTarget(candidates, target - candidates[i], curRes, finalResult, i);

            // 3. To go back to previous level , need to remove last element added in currList
            curRes.remove(curRes.size() - 1);
        }
    }

    private void findCombinationSumToTarget2(int[] candidates, int target, List<Integer> curRes, List<List<Integer>> finalResult,
                                             int idx) {

        //1. base condition
        if (target == 0) {
            // create deep copy of result,
            // if you directly add curRes, what you actually add is the address of curRes.
            // In the following calculation, curRes will continue change, so the content in the address will change.
            // But if you add a new ArrayList, it won't change anymore.
            finalResult.add(new ArrayList<>(curRes));
            return;
        }

        //2. candidates[] [2,3,5] target=9

        //if you dont do this , your target will get into negatives, and will also go over the entire array list
        if (idx >= candidates.length || candidates[idx] > target) {
            return;
        }
        curRes.add(candidates[idx]);
        findCombinationSumToTarget2(candidates, target - candidates[idx], curRes, finalResult, idx);

        // 3. To go back to previous level , need to remove last element added in currList
        curRes.remove(curRes.size() - 1);

        findCombinationSumToTarget2(candidates, target, curRes, finalResult, idx + 1);
    }

    // Approach 3 - Choosing same element only once
    // Resultant ans is unique
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // storing actual result
        List<List<Integer>> result = new ArrayList<>();

        // add only unique list in actual result
        Set<List<Integer>> set = new HashSet<>();

        // base condition
        if (candidates.length == 0 && target == 0) {
            return result;
        }

        Arrays.sort(candidates);

        combinationSum2(candidates, new ArrayList<>(), result, 0, target, set);

        return result;
    }

    private void combinationSum2(int[] candidates, ArrayList currList, List<List<Integer>> result, int idx, int target, Set set) {

        // base condition
        if (target == 0) {
            //convert currList into Set and save in set to check if list already exist, don't add in result
            if (!set.contains(currList)) {
                set.add(currList);
                result.add(new ArrayList<>(currList));
            }
            return;
        }

        //if you dont do this , your target will get into negatives, and will also go over the entire array list
        if (idx >= candidates.length || candidates[idx] > target) {
            return;
        }

        // Make a choice
        currList.add(candidates[idx]);

        // recurse, can't choose the same index , so move to next idx
        combinationSum2(candidates, currList, result, idx + 1, target - candidates[idx], set);

        // remove the choice
        currList.remove(currList.size() - 1);

        // recurse further after removing the choice which didn't fit in
        combinationSum2(candidates, currList, result, idx + 1, target, set);

    }

    public static void main(String[] args) {
        //System.out.println(new CombinationSum().combinationSum2(new int[] {2, 3, 5}, 9));
        // System.out.println(new CombinationSum().combinationSum2(new int[] {2, 5, 2,1,2}, 5));
        System.out.println(new CombinationSum().combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
    }
}