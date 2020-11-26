/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.matrix;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: RotateImage.java, v 0.1 2020-09-03 20:43 paras.chawla Exp $$
 * https://javabypatel.blogspot.com/2016/11/rotate-matrix-by-90-degrees-inplace.html
 */
public class RotateImage {

    /* Rotate clockwise
     * 1 2 3             7 8 9               7 4 1
     * 4 5 6  => Reverse 4 5 6 Transpose =>  8 5 2
     * 7 8 9             1 2 3               9 6 3
     *
     * 1 2 3               1 4 7               7 4 1
     * 4 5 6  => Transpose 2 5 8 Reverse =>    8 5 2
     * 7 8 9               3 6 9               9 6 3
     *
     * Time complexity : O(N^2)
     * Space Complexity: O(1) , in-place
     */
    public void rotateClockwise(int[][] matrix) {
        int n = matrix.length;

        // 1. Transpose matrix , change row to column, could use XOR operator to remove temp dependency
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        // 2. Reverse elements of matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new RotateImage().rotateClockwise(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}