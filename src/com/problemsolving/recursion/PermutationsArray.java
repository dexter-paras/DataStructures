/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author paras.chawla
 * @version $Id: PermutationsArray.java, v 0.1 2020-04-12 19:57 paras.chawla Exp $$
 *
 * Recursion tree
 * https://www.youtube.com/watch?v=KukNnoN-SoY
 */
public class PermutationsArray {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> originalList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> currList = new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

        if (nums.length == 0) {
            return result;
        }

        permute(originalList, currList, result, originalList.size());
        return result;
    }

    private void permute(List<Integer> possiblitiesList, List<Integer> choosenList, List<List<Integer>> result, int size) {

        // Base condition, when possiblitiesList is empty that means no more elements to choose and all elements choosen in choosenList
        if (choosenList.size()==size) {
            result.add(new ArrayList<>(choosenList));
            return;
        }

        for (int i = 0; i < possiblitiesList.size(); i++) {

            choosenList.add(possiblitiesList.get(i));
            possiblitiesList.remove(i);

            permute(possiblitiesList, choosenList, result, size);

            possiblitiesList.add(i,choosenList.get(choosenList.size()-1));
            choosenList.remove(choosenList.size()-1);
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationsArray obj = new PermutationsArray();
        obj.permute(new int[] {1, 2, 3});
    }
}