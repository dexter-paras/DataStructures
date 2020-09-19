/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

/**
 * @author paras.chawla
 * @version $Id: PalindromeSequence.java, v 0.1 2020-09-16 22:45 paras.chawla Exp $$
 */
public class PalindromeSequence {

    public static int maxScore(String s) {

        int n=s.length();

        int[][] dp = new int[n][n];

        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }

        for(int k=1;k<n;k++){
            for(int i=0;i<n-k;i++){
                int j = i + k;
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                }
            }
        }

        int maxProd = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                maxProd = Math.max(maxProd,dp[i][j]*dp[j+1][n-1]);
            }
        }
        return maxProd;
    }
}