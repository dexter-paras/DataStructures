/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: Search2dSortedMatrix1.java, v 0.1 2020-10-28 11:08 paras.chawla Exp $$
 * https://leetcode.com/problems/search-a-2d-matrix/
 * Approach 1 Time Complexity - m * O(log(n)) <- performing binary search on all rows
 * Approach 2 Time Complexity - O(log(m*n)) <- performing 1 binary search on full 1D matrix i.e. m*n
 *
 *[ 1    3   5   7
 * 10   11  14  20
 * 25   27  28  30]
 * Every new row first element is greater than last col last value
 */
public class Search2dSortedMatrix1 {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int lastIdx = matrix.length * matrix[0].length - 1;
        return helper(matrix, target, 0, lastIdx, matrix[0].length);
    }

    // Convert 2D to 1D and apply binary search on 1D array as usual
    // row = idx/numCols
    // col = idx%numCols
    private boolean helper(int[][] matrix, int target, int low, int high, int numCols) {

        if (low > high) {
            return false;
        }
        int mid = low + ((high - low) / 2);

        int row = mid / numCols;
        int col = mid % numCols;

        if (matrix[row][col] == target) {
            return true;
        }

        if (matrix[row][col] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
        return helper(matrix, target, low, high, col);
    }

    public static void main(String[] args) {
        Search2dSortedMatrix1 obj = new Search2dSortedMatrix1();
        //int[][] matrix = new int[][] {};
        int[][] matrix2 = new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 15;
        obj.searchMatrix(matrix2, target);
    }

}