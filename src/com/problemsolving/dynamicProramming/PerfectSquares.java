/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 *//*

package com.problemsolving.dynamicProramming;

*/
/**
 * @author paras.chawla
 * @version $Id: PerfectSquares.java, v 0.1 2020-04-01 08:07 paras.chawla Exp $$
 *//*

public class PerfectSquares {

    //numSquares(n)= min(numSquares(n-k) + 1)∀k∈{square numbers}
    public int numSquares(int n) {

        int sqrRoot = (int) Math.sqrt(n);
        return helper(n, sqrRoot);
    }

   */
/* // (12,3)
    private int helper(int n, int options) {

        for(int i=options;i>0;i--){
            int sqr=options*options;
            helper(n-sqr)
        }
    }*//*


    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(12));
    }
}*/
