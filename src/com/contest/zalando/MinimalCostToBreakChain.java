/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.zalando;

/**
 * @author paras.chawla
 * @version $Id: MinimalCostToBreakChain.java, v 0.1 2021-01-21 7:53 AM paras.chawla Exp $$
 */
public class MinimalCostToBreakChain {

    // int[] A = {5,2,4,6,3,7}
    public int solution(int[] A) {

        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i < A.length - 3; i++) {
            int p = i;
            for (int j = i + 2; j < A.length - 1; j++) {
                int q = j;
                minCost = Math.min(A[p] + A[q], minCost);
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        MinimalCostToBreakChain obj = new MinimalCostToBreakChain();
        System.out.println(obj.solution(new int[] {5, 2, 4, 8, 9}));
        //System.out.println(obj.solution(new int[] {5, 2, 4, 6, 3, 7}));
        //System.out.println(obj.solution(new int[] {5, 2, 4, 6, 3, 7}));
    }
}