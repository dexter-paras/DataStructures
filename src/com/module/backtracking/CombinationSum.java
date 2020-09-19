/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[] {2, 3, 5}, 9));
    }
}