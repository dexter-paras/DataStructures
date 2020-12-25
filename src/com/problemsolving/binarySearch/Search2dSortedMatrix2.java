/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: Search2dSortedMatrix2.java, v 0.1 2020-10-28 11:08 paras.chawla Exp $$
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *  T  1    3   5   11 <- [0][3] 11 < 14 move down
 *     8    9  14   20 <- [1][3] 20 > 14 move right
 *  B 10   15  21   30
 *    L             R
 * Constraints:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Intuition:
 * 1) When it comes to searching in 1d or 2d space, always think to reduce space Area going further
 * a) if start with 1(Top-Left idx) and need to find 20, moving from L to R and T to B will only increase value , hence no space reduce
 * b) if start with 30(Bottom-Right Idx and need to find 20, moving from R to L and B to T will only decrease value, hence no space reduce
 * c) if start with 11(Top-Right idx) and need to find 20, moving from R to L will reduce and T to B will increase the values, hence space reduce
 * d) if start with 10(Bottom-Left idx) and need to find 20, moving from L to R will increase and B to T will decrease the values, hence space reduce
 *
 * Time Complexity: O(m+n)
 */
public class Search2dSortedMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return helper(matrix, 0, matrix[0].length - 1, target, matrix.length, matrix[0].length);
    }

    private boolean helper(int[][] matrix, int row, int col, int target, int numRows, int numCols) {

        // base condition
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return false;
        }

        // nirvana condition
        if (target == matrix[row][col]) {
            return true;
        }
        // move downwards to reduce space area,hence increase row++
        if (target > matrix[row][col]) {
            row++;
        } else if (target < matrix[row][col]) {
            col--;
        }
        return helper(matrix, row, col, target, numRows, numCols);
    }

    public static void main(String[] args) {
        Search2dSortedMatrix2 obj = new Search2dSortedMatrix2();
        int[][] matrix = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 20;
        obj.searchMatrix(matrix, target);
    }

}