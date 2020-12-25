/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.pathToReachTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Triangle.java, v 0.1 2020-12-02 02:28 paras.chawla Exp $$
 */
public class Triangle {

    // Approach 1 - DP using Bottom Up
    public static int minimumTotal(List<List<Integer>> triangle) {

        // base condtion

        if (triangle.size() == 0) {
            return 0;
        }

        // Bottom up
        int rows = triangle.size();

        int minSumCache[] = new int[triangle.size()];
        for (int i = 0; i < minSumCache.length; i++) {
            minSumCache[i] = triangle.get(rows-1).get(i);
        }

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minSumCache[j] = triangle.get(i).get(j) + Math.min(minSumCache[j], minSumCache[j + 1]);
            }
        }
        return minSumCache[0];
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);
        List<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(5);
        c.add(7);
        List<Integer> d = new ArrayList<>();
        d.add(4);
        d.add(1);
        d.add(8);
        d.add(3);
        triangle.add(a);
        triangle.add(b);
        triangle.add(c);
        triangle.add(d);

        System.out.println(minimumTotal2(triangle));
    }
}