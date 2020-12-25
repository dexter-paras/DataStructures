/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: IntersectionArray.java, v 0.1 2020-12-24 11:11 PM paras.chawla Exp $$
 */
public class IntersectionArray2 {
    public int[] intersect(int[] nums1, int[] nums2) {

        // traverse nums1 and store in Map<Integer,Count>

        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : nums1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();

        // traverse nums2 and check in countMap
        for (int num : nums2) {
            if (countMap.containsKey(num)) {
                result.add(num);
                int count = countMap.get(num);
                if (count == 1) {
                    countMap.remove(num);
                } else {
                    countMap.put(num, --count);
                }
            }
        }

        int[] array = new int[result.size()];
        int idx = 0;
        for (int i : result) {
            array[idx++] = i;
        }

        return array;
    }

    // 1-2, 2-2

    public static void main(String[] args) {
        IntersectionArray2 obj = new IntersectionArray2();
        obj.intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2});
    }
}
