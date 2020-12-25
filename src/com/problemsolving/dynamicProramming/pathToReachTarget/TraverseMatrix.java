/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.pathToReachTarget;

/**
 * @author paras.chawla
 * @version $Id: TraverseMatrix.java, v 0.1 2020-09-05 12:18 paras.chawla Exp $$
 */
public class TraverseMatrix {

    int count = 0;

    public int uniquePaths(int rows, int cols) {
        int matrix[][] = new int[rows][cols];

        helper(matrix, 0, 0);
        System.out.println(count);
        return count;
    }

    private void helper(int[][] matrix, int i, int j) {

        // nirvana condition
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            count++;
        }

        // base condition
        if (i < 0 || i > matrix.length - 1 || j < 0 || j > matrix[0].length - 1) {
            return;
        }

        helper(matrix, i + 1, j);
        helper(matrix, i, j + 1);
    }

    // Bottom up - starting from 00 to finally rows,cols
    // Number of Unique path to reach at i,j = number of unique paths to reach at i-1,j + number of unique paths to reach at i,j-1
    public int uniquePathsUsingDP(int rows, int cols) {
        int matrix[][] = new int[rows][cols];

        // marking row1 as 1
        // number of unique ways to move from 00 to 01 or 01 to 02 or 02 to 03 is 1 only as we can only move right
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i] = 1;
        }

        // marking col1 as 1
        // number of unique ways to move from 00 to 10 or 10 to 20 or 20 to 30 is 1 only as we can only move down
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 1;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                // number of unique ways to reach (i,j) = NOUWTR(i-1,j) + NOUWTR(i,j-1)
                matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
            }
        }

        return matrix[matrix.length - 1][matrix[0].length - 1];
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new TraverseMatrix().uniquePaths(3, 7));
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken using Recursion: " + (endTime - startTime));

        System.out.println(new TraverseMatrix().uniquePathsUsingDP(3, 7));
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time Taken using DP: " + (endTime2 - endTime));
    }
}