/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.bfsdfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: RottingOranges.java, v 0.1 2020-05-03 01:33 paras.chawla Exp $$
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 * Input: [[2,1,1],
 *         [1,1,0],
 *         [0,1,1]
 *        ]
 * Output: 4
 */

class Cell {

    int x;
    int y;

    int timeFrame;

    public Cell(int x, int y, int timeframe) {
        this.x = x;
        this.y = y;
        this.timeFrame = timeframe;
    }
}

public class RottingOranges {

    int result      = 0;
    int freshOrange = 0;

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        Queue<Cell> queue = new ArrayDeque<>();

        int row = grid.length;
        int col = grid[0].length;

        // Find all '2' and add in timeFrame 0
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // add all rotten tomatoes initially in grid in queue
                if (grid[i][j] == 2) {
                    queue.add(new Cell(i, j, 0));
                }
                if (grid[i][j] == 1) {
                    freshOrange++;
                }
            }
        }

        if (freshOrange == 0) {
            return 0;
        }

        while (!queue.isEmpty()) {
            Cell node = queue.poll();

            // if rotten orange, it will rot all 4 directional rot
            if (grid[node.x][node.y] == 2) {
                rotFreshCellsAndAddInQueue(node.x, node.y, grid, row, col, queue, node.timeFrame);
            }
        }

        return freshOrange > 0 ? -1 : result;
    }

    private boolean checkAnyFreshOrangeLeft(int[][] grid) {
        for (int i = 0; i < grid.length; i++) { for (int j = 0; j < grid[0].length; j++) { if (grid[i][j] == 1) { return true; } } }
        return false;
    }

    private void rotFreshCellsAndAddInQueue(int x, int y, int[][] grid, int row, int col, Queue<Cell> queue, int tf) {

        if (x < 0 || x >= row || y < 0 || y >= col) {
            return;
        }

        if (x + 1 < row && x + 1 >= 0 && grid[x + 1][y] == 1) {
            grid[x + 1][y] = 2;
            queue.add(new Cell(x + 1, y, tf + 1));
            freshOrange--;
        }
        if (x - 1 >= 0 && x - 1 < row && grid[x - 1][y] == 1) {
            grid[x - 1][y] = 2;
            queue.add(new Cell(x - 1, y, tf + 1));
            freshOrange--;
        }
        if (y - 1 >= 0 && y - 1 < col && grid[x][y - 1] == 1) {
            grid[x][y - 1] = 2;
            queue.add(new Cell(x, y - 1, tf + 1));
            freshOrange--;
        }
        if (y + 1 >= 0 && y + 1 < col && grid[x][y + 1] == 1) {
            grid[x][y + 1] = 2;
            queue.add(new Cell(x, y + 1, tf + 1));
            freshOrange--;
        }

        result = Math.max(result, tf);
    }

    public static void main(String[] args) {

        //int grid[][] = new int[][] {{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
        int grid2[][] = new int[][] {{ 1, 2}};
        new RottingOranges().orangesRotting(grid2);
    }

    /*public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    markVisited(i, j, grid, row, col);
                }
            }
        }
        return 0;
    }

    private void markVisited(int x, int y, int[][] grid, int row, int col) {

        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }

        if (x + 1 < row && x + 1 > 0) {
            grid[x + 1][y] = 2;
        }
        if (x - 1 > 0 && x - 1 < row) {
            grid[x - 1][y] = 2;
        }
        if (y - 1 > 0 && y - 1 < col) {
            grid[x][y - 1] = 2;
        }
        if (y + 1 > 0 && y + 1 < col) {
            grid[x][y + 1] = 2;
        }
    }
*/
}