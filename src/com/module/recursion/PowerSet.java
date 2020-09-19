/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PowerSet.java, v 0.1 2020-09-15 21:44 paras.chawla Exp $$
 */
public class PowerSet {

    // final result storing all powerSet
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        if(nums.length ==0){
            return result;
        }

        List<Integer> partialSubSet = new ArrayList();
        generatePowerSet(0,nums,result,partialSubSet);
        return result;
    }

    void generatePowerSet(int idx, int[] nums ,List<List<Integer>> result,List<Integer> partialSubSet){

        // base condition
        if(idx==nums.length){
            // add deep copy of partialSubset in final result
            result.add(new ArrayList<Integer>(partialSubSet));
            return;
        }

        // choosing element at index idx in partialSubSet
        partialSubSet.add(nums[idx]);

        // after choosing element at idx i , moving to next idx and calling recursion
        generatePowerSet(idx+1,nums,result,partialSubSet);

        // Not choosing element at index idx in partialSubSet
        partialSubSet.remove(partialSubSet.size()-1);

        // after not choosing element at idx i , moving to next idx and calling recursion
        generatePowerSet(idx+1,nums,result,partialSubSet);

    }

    // the method below is adding elements and constructing subsets
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        result.add(tmp); // add empty set
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++){
            int n =  result.size();
            for(int j=0; j<n; j++){
                // NOTE: must create a new tmp object, and add element to it.
                tmp = new ArrayList<Integer>(result.get(j));
                tmp.add(nums[i]);
                result.add(new ArrayList<Integer>(tmp));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        PowerSet obj = new PowerSet();
        System.out.println(obj.subsets2(new int[]{1,2,3}));
    }
}