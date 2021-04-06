/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: NumberOfIslands.java, v 0.1 2020-04-29 22:49 paras.chawla Exp $$
 * https://leetcode.com/problems/number-of-distinct-islands/discuss/237859/Simple-Java-InMemoryDataStructure!
 */
public class NumberOfDistinctIslands {

    /*
    * 11000
      11000
      00100
      00011
    If i'm at any piece of land,then that includes an Island
    What we need to do is that recurring of that piece of land
    we need to mark all visited lands

    Given a 2d grid map of 1s (land) and 0s (water), count the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    BFS solution - https://leetcode.com/problems/number-of-islands/discuss/56338/Java-DFS-and-BFS-solution
    1->11000
       11000
       00100  <- 2nd
       00011  <- 3rd

       Tricky case where backtracking is imp
                {1, 1, 0},
                {1, 0, 0},
                {0, 0, 0},
                {1, 1, 0},
                {0, 1, 0}
    */

    public int numIslands(int[][] grid) {

        StringBuilder builder;
        Set<String> set = new HashSet<>();
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    builder = new StringBuilder();
                    markVisited("X", builder, i, j, grid, row, col);
                    set.add(builder.toString());
                }
            }
        }
        return set.size();
    }

    // mark all piece of lands as visited top/bottom/up/down
    private void markVisited(String direction, StringBuilder builder, int i, int j, int[][] grid, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != 1) {
            return;
        }

        //Mark current piece of land as visited and then merge to 1 common island for all adjacent land
        grid[i][j] = 2;
        builder.append(direction);
        markVisited("D", builder, i + 1, j, grid, row, col); // DOWN grid[0][0] -> grid[1][0] -> grid[2][0]
        markVisited("U", builder, i - 1, j, grid, row, col); // UP grid[0][1]
        markVisited("L", builder, i, j - 1, grid, row, col); // LEFT
        markVisited("R", builder, i, j + 1, grid, row, col); // UP

        // why backtracking as a moving direction is important
        // https://leetcode.com/problems/number-of-distinct-islands/discuss/150037/DFS-with-Explanations
        builder.append("EAST");
    }

    public static void main(String[] args) {

        NumberOfDistinctIslands obj = new NumberOfDistinctIslands();
        int grid[][] = new int[][] {{1, 1, 0, 0, 1, 1, 0}, {1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 1, 0, 0, 0},
                {1, 0, 1, 1, 0, 1, 1}};
        // both 2 cases require backtracking to append
        int trickyGrid1[][] = new int[][] {{1, 1, 1}, {0, 1, 0}};
        int trickyGrid2[][] = new int[][] {{1, 1, 0}, {0, 1, 1}};
        obj.numIslands(grid);
        obj.numIslands(trickyGrid1);
        obj.numIslands(trickyGrid2);
    }
}