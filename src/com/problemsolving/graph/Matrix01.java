/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: Matrix01.java, v 0.1 2021-02-26 9:39 AM paras.chawla Exp $$
 * https://leetcode.com/problems/01-matrix/discuss/248525/Java-BFS-solution-with-comments
 */
public class Matrix01 {

    // Start BFS from 0 and update distance to all adjacent 1s

    // Mark all 1s as -1 in order to differentiate from actual distance=1
    public int[][] updateMatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        // 1. Mark all 1s as -1 in order to differentiate from actual distance=1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[] {i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }

        // 2. Starting BFS from all Os existing in Matrix at once
        int level = 0;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            level++;
            // upto queue size, level should be level+1
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int j = 0; j < dirs.length; j++) {
                    int newX = curr[0] + dirs[j][0];
                    int newY = curr[1] + dirs[j][1];

                    if (newX < 0 || newX == m || newY < 0 || newY == n || matrix[newX][newY] != -1) {
                        continue;
                    }

                    matrix[newX][newY] = matrix[curr[0]][curr[1]] + 1;
                    queue.add(new int[] {newX, newY});
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Matrix01 obj = new Matrix01();
        int[][] matrix = new int[][] {{0, 0, 0, 1, 1}, {0, 1, 0, 1, 1}, {1, 1, 1, 1, 0}, {1, 1, 1, 0, 0}};
        obj.updateMatrix(matrix);
    }

}