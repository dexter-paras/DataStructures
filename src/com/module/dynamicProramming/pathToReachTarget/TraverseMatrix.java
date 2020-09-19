/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.dynamicProramming.pathToReachTarget;

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

    public int uniquePathsUsingDP(int rows, int cols) {
        int matrix[][] = new int[rows][cols];

        // marking row1 as 1
        for (int i = 0; i < matrix.length; i++) {
            matrix[0][i] = 1;
        }

        // marking col1 as 1
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[i][0] = 1;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
            }
        }

        return matrix[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(new TraverseMatrix().uniquePaths(10, 10));
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken using Recursion: "+ (endTime-startTime));

        System.out.println(new TraverseMatrix().uniquePathsUsingDP(10, 10));
        long endTime2 =System.currentTimeMillis();
        System.out.println("Time Taken using DP: "+ (endTime2-endTime));
    }
}