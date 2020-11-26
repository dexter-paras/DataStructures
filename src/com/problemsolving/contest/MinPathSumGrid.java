/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: MinPathSumGrid.java, v 0.1 2020-04-19 10:30 paras.chawla Exp $$
 */
public class MinPathSumGrid {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int dp[][] = new int[grid.length + 1][grid[0].length + 1];
        for (int[] row : dp) { Arrays.fill(row, -1); }
        dp[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        return cost(0, 0, grid, dp);
    }

    /*
    Approach 1 - Recursion
    For each element, we consider two paths, rightwards and downwards and find the minimum sum out of those two.
    It specifies whether we need to take a right step or downward step to minimize the sum.
    cost(i,j)= grid[i][j] + Min(cost(i+1,j),cost(i,j+1);
    */

    private int cost(int row, int col, int[][] grid, int[][] dp) {
        // Since we're taking minimum into account , so when base condition reach, we're returning max value which has no affect.
        if (row == grid.length || col == grid[0].length) { return Integer.MAX_VALUE; }

        // when we reach at bottom right, This is the exist of the recursion
        if (row == grid.length - 1 && col == grid[0].length - 1) {return grid[row][col];}

        //we consider two paths, rightwards and downwards and find the minimum sum out of those two

        int rightSum = dp[row][col + 1] != -1 ? dp[row][col + 1] : cost(row, col + 1, grid, dp);
        int downSum = dp[row + 1][col] != -1 ? dp[row + 1][col] : cost(row + 1, col, grid, dp);
        int min = Math.min(rightSum, downSum);

        dp[row][col] = grid[row][col] + min;
        return grid[row][col] + min;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1, 3, 1,1}, {1, 5, 7,1}, {4, 2, 1,1}};

        System.out.println(new MinPathSumGrid().minPathSum(grid));
    }
}