/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author paras.chawla
 * @version $Id: IntersectionArray.java, v 0.1 2020-12-24 11:11 PM paras.chawla Exp $$
 */
public class IntersectionArray {
    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set = new HashSet<>();
        // Add element in HashSet
        for (int num : nums1) {
            set.add(num);
        }

        // check in nums2 , if exist add in array
        HashSet<Integer> result = new HashSet<>();

        for (int num : nums2) {
            if (set.contains(num)) {
                result.add(num);
            }
        }

        int[] array = new int[result.size()];
        int i = 0;
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }

        return array;
    }

    public static void main(String[] args) {
        IntersectionArray obj = new IntersectionArray();
        obj.intersection(new int[]{1,2,2,1},new int[]{2,2});
    }
}
