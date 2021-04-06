/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

/**
 * @author paras.chawla
 * @version $Id: UniquePath2.java, v 0.1 2021-02-04 8:36 AM paras.chawla Exp $$ https://www.techiedelight
 * .com/find-total-number-unique-paths-maze-source-destination/ TODO - Important to understand count logic here
 */
public class UniquePath2 {

    public int uniquePathsWithObstacles(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        //Map<String, Integer> countMap = new HashMap<>();
        count = helper(grid, 0, 0, rows, cols, count);
        return count;
    }

    private int helper(int[][] grid, int i, int j, int rows, int cols, int count) {

        // satisfy condition
        if (i == rows - 1 && j == cols - 1 && grid[i][j] != 1) {
            count++;
        }

        if (isValidCell(i, j, rows, cols) && grid[i][j] != 1) {

            // mark visited
            grid[i][j] = 2;

            // move right
            count = helper(grid, i, j + 1, rows, cols, count);
            // move down
            count = helper(grid, i + 1, j, rows, cols, count);

            // mark unvisited
            grid[i][j] = 0;
        }
        return count;
    }

    // Check if cell (x, y) is valid or not
    private static boolean isValidCell(int x, int y, int rows, int cols) {
        if (x < 0 || y < 0 || x >= rows || y >= cols) { return false; }

        return true;
    }

    // Approach 2 https://leetcode.com/problems/unique-paths-ii/solution/
    /* TODO - Do it by 4th Feb

    grid[4][4]

     WITH Obstacles
     S->0 0 1 0
        0 0 0 0
        0 1 1 0
        1 0 0 0 <- D

     Make start as 1 since there exist a path from 0,0 to 0,0
     WITH Obstacles
     Step 1 :
     S->1 1 0 0  if grid[0][i]==0, prev value else if grid[0][i]==1(obstacle), mark it 0 . No path
        1 0 0 0
        1 1 1 0
        0 0 0 0 <- D

     Step 2:
     S->1 1 0 0
        1 2 2 2  if grid[i][j]==0, grid[i][j]= grid[i-1][j] + grid[i][j-1]; else if grid[i][j] ==1 , grid[i][j]= 0
        1 0 0 2
        0 0 0 2 <- D


     WITHOUT Obstacles
     S->0 0 0 0
        0 0 0 0
        0 0 0 0
        0 0 0 0 <-D

     S->0 1 1  1
        1 2 3  4
        1 3 6  10
        1 4 10 20 <-D

    0 - empty space , can walk
    1 - Obstacle , can't walk

    */
    public int uniquePathsWithObstaclesDp(int[][] grid) {

        // no unique path exist if start point is Obstacle
        if (grid[0][0] == 1) {
            return 0;
        }

        int R = grid.length;
        int C = grid[0].length;

        // Update Start position as 1 because a unique path exist to reach at grid[0][0]
        grid[0][0] = 1;

        // move row-wise and update unique path at grid[0][j] from j to n if grid[0][j]==0
        // if grid[0][j]==1 , means obstacle ,means no unique path ,hence update to 0

        // Any cell in the first row can only be reached from the cell left to it.
        for (int i = 1; i < C; i++) {
            grid[0][i] = grid[0][i] == 0 ? grid[0][i - 1] : 0;
        }

        // Any cell in the first col can only be reached from the cell above it.
        for (int i = 1; i < R; i++) {
            grid[i][0] = grid[i][0] == 0 ? grid[i - 1][0] : 0;
        }

        // For any other cell in the grid, we can reach it either from the cell to left of it or the cell above it.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                // empty space
                if (grid[i][j] == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                }
                // obstacle ,If any cell has an obstacle, we won't let that cell contribute to any path.
                else {
                    grid[i][j] = 0;
                }
            }
        }

        return grid[R - 1][C - 1];
    }

    public static void main(String[] args) {
        UniquePath2 obj = new UniquePath2();
        System.out.println(obj.uniquePathsWithObstacles(new int[][] {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 0}, {1, 0, 0, 0}}));
        System.out.println(obj.uniquePathsWithObstaclesDp(new int[][] {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 0}, {1, 0, 0, 0}}));
    }
}