/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.hardlc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: LongestConsecutiveSequence.java, v 0.1 2020-12-26 12:53 AM paras.chawla Exp $$ https://leetcode
 * .com/problems/longest-consecutive-sequence/ Intuition - check if lower number doesn't exist and upper number exist - it means that
 * sequence start from current number and keep traversing and find longest consecutive sequence
 * <p>
 * Eg. for 1 , 2 exist but 0 doesn't exist - hence it should be taken and further calculation needs to be processed
 * <p>
 * if lower number and upper number both exist - that means that same number will encounter to lower number sequence and this number can be
 * skipped
 * <p>
 * Eg. for 2, since 1 and 3 both exist , hence can be skipped - because 2,3,4 seq is smaller than 1,2,3,4
 */
public class LongestConsecutiveSequence {

    //nums[]=[100,4,200,1,3,2]
    public int longestConsecutive(int[] nums) {

        if(nums==null || nums.length==0){
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 1;

        Iterator<Integer> setIterator = set.iterator();
        while (setIterator.hasNext()) {

            int num = setIterator.next();
            // case 1 - for num=1 , set contains 2 but doesn't contain 0
            if (set.contains(num + 1) && !set.contains(num - 1)) {
                int maxSeq = maxLength(set, num, 1);
                maxLength = Math.max(maxLength, maxSeq);
            }

        }
        return maxLength;

    }

    // num=1
    private int maxLength(Set<Integer> set, int num, int count) {

        /*
        if (set.contains(num + 1)) {
            return maxLength(set, num + 1, ++count);
        }
        */

        while (set.contains(num + 1)) {
            count++;
            num++;
        }

        return count;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
        System.out.println(obj.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
        System.out.println(obj.longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }
}