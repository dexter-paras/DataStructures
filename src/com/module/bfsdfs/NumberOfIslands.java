/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.bfsdfs;

/**
 * @author paras.chawla
 * @version $Id: NumberOfIslands.java, v 0.1 2020-04-29 22:49 paras.chawla Exp $$
 */
public class NumberOfIslands {

    /*
    * 11000
      11000
      00100
      00011
    If i'm at any piece of land,then that includes an Island
    What we need to do is that recurring of that piece of land
    we need to mark all visited lands

    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    BFS solution - https://leetcode.com/problems/number-of-islands/discuss/56338/Java-DFS-and-BFS-solution
    1->11000
       11000
       00100  <- 2nd
       00011  <- 3rd
    */

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numOfIslands = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    markVisited(i, j, grid, row, col);
                }
            }
        }
        return numOfIslands;
    }

    // mark all piece of lands as visited top/bottom/up/down
    private void markVisited(int i, int j, char[][] grid, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != '1') {
            return;
        }

        //Mark current piece of land as visited and then merge to 1 common island for all adjacent land
        grid[i][j] = '2';
        markVisited(i + 1, j, grid, row, col); // DOWN grid[0][0] -> grid[1][0] -> grid[2][0]
        markVisited(i, j + 1, grid, row, col); // RIGHT grid[0][1]
        markVisited(i, j - 1, grid, row, col); // LEFT
        markVisited(i - 1, j, grid, row, col); // UP
    }

    public static void main(String[] args) {
        char grid[][] = new char[][] {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        new NumberOfIslands().numIslands(grid);
    }
}