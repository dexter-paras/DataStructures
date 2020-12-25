/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

/**
 * @author paras.chawla
 * @version $Id: MaximalSquare.java, v 0.1 2020-12-13 10:50 paras.chawla Exp $$

To Find the largest square containing only 1's and return its area.

[["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
]

 */
public class MaximalSquare {

    // Approach 2- Use Dynamic programming to save precomputed largest square matrix at particular index
    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // store largest value in a variable
        int largest = 0;

        // To sustain out of bound values, create dp of size length + 1
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
                largest=Math.max(largest,dp[i][j]);
            }
        }
        return largest*largest;
    }
}