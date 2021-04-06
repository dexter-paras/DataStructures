/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: MaxMinPath.java, v 0.1 2021-03-29 8:40 PM paras.chawla Exp $$
 */

/*
 * [[5,4,5]
 *  [1,2,6]
 *  [7,4,6]
 * ]
 * */

public class MaxMinPath {

    int dirs[][] = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // Approach 1 - Brute Force - To find all paths possible in matrix from S to End
    public int maximumMinimumPath(int[][] A) {

        List<List<Integer>> allPaths = new ArrayList<>();

        boolean[][] visited = new boolean[A.length][A[0].length];

        ArrayList<Integer> currPath = new ArrayList<>();
        visited[0][0] = true;
        currPath.add(A[0][0]);

        helper(A, 0, 0, currPath, allPaths, visited);

        System.out.println(allPaths);

        return 0;
    }

    private void helper(int[][] grid, int i, int j, ArrayList<Integer> currPath, List<List<Integer>> allPaths, boolean[][] visited) {

        // base condition
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            allPaths.add(new ArrayList<>(currPath));
            return;
        }

        // recurse in all 4 directions
        for (int k = 0; k < dirs.length; k++) {

            int newX = i + dirs[k][0];
            int newY = j + dirs[k][1];

            if (validCell(grid, newX, newY) && !visited[newX][newY]) {

                // make a choice
                currPath.add(grid[newX][newY]);

                visited[newX][newY] = true;

                // recurse
                helper(grid, newX, newY, currPath, allPaths, visited);

                // remove the choice(backtrack)
                currPath.remove(currPath.size() - 1);

                visited[newX][newY] = false;
            }
        }
    }

    // Approach 2 - Extending Approach 1
    int max = Integer.MIN_VALUE;
    public int maximumMinimumPath2(int[][] A) {

        List<List<Integer>> allPaths = new ArrayList<>();

        boolean[][] visited = new boolean[A.length][A[0].length];

        ArrayList<Integer> currPath = new ArrayList<>();
        visited[0][0] = true;
        currPath.add(A[0][0]);

        helper(A, 0, 0, currPath, allPaths, A[0][0], visited);

        System.out.println(allPaths);

        return max;
    }

    /*
     * [[5,4,5]
     *  [1,2,6]
     *  [7,4,6]
     * ]
     * */
    private void helper(int[][] grid, int i, int j, ArrayList<Integer> currPath, List<List<Integer>> allPaths, int score,
                        boolean[][] visited) {

        // base condition
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            allPaths.add(new ArrayList<>(currPath));
            max = Math.max(max, score);
            return;
        }

        // recurse in all 4 directions
        for (int k = 0; k < dirs.length; k++) {

            int newX = i + dirs[k][0];
            int newY = j + dirs[k][1];

            if (validCell(grid, newX, newY) && !visited[newX][newY]) {

                // make a choice
                currPath.add(grid[newX][newY]);

                visited[newX][newY] = true;

                // recurse
                helper(grid, newX, newY, currPath, allPaths, Math.min(score, grid[newX][newY]), visited);

                // remove the choice(backtrack)
                currPath.remove(currPath.size() - 1);

                visited[newX][newY] = false;
            }
        }
    }

    // Approach 3 - https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/323927/Java-BFS-%2B-PQ
    // https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/457525/JAVA-A-Summery-of-All-Current-Solutions
    // TODO

    private boolean validCell(int[][] grid, int i, int j) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MaxMinPath obj = new MaxMinPath();

        int[][] grid = new int[][] {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};

        obj.maximumMinimumPath(grid);
    }

}