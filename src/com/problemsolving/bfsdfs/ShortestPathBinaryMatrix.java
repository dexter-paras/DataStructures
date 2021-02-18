/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: ShortestPathBinaryMatrix.java, v 0.1 2021-02-15 12:08 PM paras.chawla Exp $$
 */
public class ShortestPathBinaryMatrix {

    /**
     * grid[][] 0 0 0 1 1 0 1 1 0
     */

    // Shortest path from top-left to bottom-right
    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // 1. Base check , if top-left or bottom-right ==1 , return right away
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        // carry out bfs
        grid[0][0] = 1;

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {0, 0});

        int[][] dirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return grid[row][col];
            }

            for (int[] dir : dirs) {
                int newX = curr[0] + dir[0];
                int newY = curr[1] + dir[1];

                // skip
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }

                grid[newX][newY] = grid[curr[0]][curr[1]] + 1;
                queue.add(new int[] {newX, newY});
            }
        }

        // Target unreachable, lots of obstacles
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix obj = new ShortestPathBinaryMatrix();
        System.out.println(obj.shortestPathBinaryMatrix(new int[][] {{0, 1}, {1, 0}}));
        System.out.println(obj.shortestPathBinaryMatrix(new int[][] {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

}