/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.matrix;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: SortMatrixDiagonally.java, v 0.1 2021-01-28 3:30 PM paras.chawla Exp $$ https://leetcode
 * .com/problems/sort-the-matrix-diagonally/solution/
 */
public class SortMatrixDiagonally {

    /*
     * [3,3,1,1]
     * [2,2,1,2]
     * [1,1,1,2]
     *
     * */

    // Approach 1
    // TC - O(m*n*log(min(m,n)); Max diagonal size = min(m,n);
    // SC - O(m*n) Storing all elements in priority Queue
    public int[][] diagonalSort(int[][] mat) {

        // 1. Create a HashMap<Integer,Integer>
        // K -> row - col , V - PriorityQueue<Integer> matrix[row][col]
        HashMap<Integer, Queue<Integer>> map = new HashMap<>();

        // 2. All elements in same diagonals have same i-j property, hence it can be taken as key in map

        int rows = mat.length;
        int cols = mat[0].length;

        // 3. Traverse all rows and cols and add it in a Map
        // Map looks like below
        // 0->1,2,3
        // 1->1,2,3
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map.putIfAbsent(i - j, new PriorityQueue<Integer>());
                map.get(i - j).add(mat[i][j]);
            }
        }

        // 4. Traverse again and put vales from map to matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }

        return mat;
    }

    // Approach - 2
    // Instead of saving all diagonals in Map, we'll be saving single diagonal, sort it and put it back in matrix
    public int[][] diagonalSortApprch2(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        // diagonals start from first row index
        // in the lower left corner
        for (int row = 0; row < m; row++) {
            sortDiagonal(row, 0, mat);
        }

        // diagonals start from first col index
        // in the upper right corner
        for (int col = 0; col < n; col++) {
            sortDiagonal(0, col, mat);
        }

        return mat;
    }

    // TODO - Tricky, Need to understand again
    private void sortDiagonal(int row, int col, int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        Queue<Integer> queue = new PriorityQueue<>();

        int diagonalLength = Math.min(m - row, n - col);
        for (int i = 0; i < diagonalLength; i++) {
            queue.add(mat[row + i][col + i]);
        }
        for (int i = 0; i < diagonalLength; i++) {
            mat[row + i][col + i] = queue.remove();
        }
    }

}