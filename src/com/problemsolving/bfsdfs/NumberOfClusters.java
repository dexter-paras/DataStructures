/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

/**
 * @author paras.chawla
 * @version $Id: NumberOfClusters.java, v 0.1 2020-09-03 09:43 paras.chawla Exp $$
 */

/* 1. To find number of clusters - this include diagonal elements as well
// 2. Largest cluster

Input : M[][5] = { 0 0 1 1 0
                   1 0 1 1 0
                   0 1 0 0 0
                   0 0 0 0 1
                 }
Output : 6
Number of regions - 2
Length of region 1 - 6
Length of region 2 - 1

So largest region: 6

*/
public class NumberOfClusters {

    // count of number of 1s from particular index for particular region
    static int currcount    = 0;

    // largest count of number of 1s for matrix
    static int maxCount = 0;

    public static void main(String[] args) {
        int matrix[][] = {
                {0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}};
        int row = matrix.length;
        int col = matrix[0].length;

        int numRegions = 0;

        // 1. traverse matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                // 2. To find cluster of 1, check index whose value is 1, ignore others
                if (matrix[i][j] == 1) {
                    numRegions++;
                    // 3. traverse in all possible directions
                    traverse(matrix, i, j, row, col);
                    maxCount = Math.max(currcount, maxCount);
                    currcount = 0;
                }
            }
        }
        System.out.println("Number of Regions: " + numRegions);
        System.out.println("Largest Region in matrix: " + maxCount);
    }

    private static void traverse(int[][] matrix, int i, int j, int row, int col) {
        // recursion only when current element at i,j is 1, for other cases it shouldn't check
        if (i < 0 || i > row - 1 || j < 0 || j > col - 1 || matrix[i][j] != 1) {
            return;
        }

        // Mark it as visited, 2 means visited
        matrix[i][j] = 2;
        currcount++;

        traverse(matrix, i + 1, j, row, col); // down
        traverse(matrix, i - 1, j, row, col); // up
        traverse(matrix, i, j - 1, row, col); // left
        traverse(matrix, i, j + 1, row, col); // right
        traverse(matrix, i - 1, j + 1, row, col); //right top diagonal
        traverse(matrix, i + 1, j - 1, row, col); // right down diagonal
        traverse(matrix, i - 1, j - 1, row, col); // left top diagonal
        traverse(matrix, i + 1, j + 1, row, col); // left bottom diagonal
    }
}