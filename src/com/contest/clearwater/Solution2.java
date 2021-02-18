/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.clearwater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Solution1.java, v 0.1 2021-01-27 7:35 PM paras.chawla Exp $$
 */
public class Solution2 {

    public int calculateProfit(List<Integer> arr, int x, int y, int z) {

        Collections.sort(arr);

        // find sum for x times of size 1
        int xSum = 0;
        while (x-- > 0) {
            xSum += arr.get(arr.size() - 1);
        }

        int ySum = 0;
        while (y-- > 0) {
            ySum = ySum + arr.get(arr.size() - 1) + arr.get(arr.size() - 2);
        }

        int zSum = 0;
        while (z-- > 0) {
            zSum = zSum + arr.get(arr.size() - 1) + arr.get(arr.size() - 2) + arr.get(arr.size() - 3);
        }

        return Math.max(xSum, Math.max(ySum, zSum));
    }

    public static void main(String[] args) {
        Solution2 obj = new Solution2();
        List<Integer> arr = new ArrayList<>();
        arr.add(-6);
        arr.add(-3);
        arr.add(-3);
        arr.add(10);
        System.out.println(obj.calculateProfit(arr,0,0,1));
    }
}