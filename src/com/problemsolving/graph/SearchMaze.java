/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

/**
 * @author paras.chawla
 * @version $Id: SearchMaze.java, v 0.1 2020-09-12 23:43 paras.chawla Exp $$
 * https://leetcode.com/problems/the-maze/
 * https://leetcode.com/problems/the-maze/discuss/132857/Java-DFS-solution-beats-98
 * A ball keep rolling, to find if a path exist from start to dest in maze[][] matrix
 */
public class SearchMaze {

    // move right, move left, move up, move down
    int[][] steps = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int count=0;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return dfs(maze, start, destination, visited, maze.length, maze[0].length);
    }

    //start = [0,3], destination = [2,0]
    public boolean dfs(int[][] maze, int[] start, int[] dest, boolean[][] visited, int rows, int cols) {

        // if cell already visited before
        if (visited[start[0]][start[1]]) {
            return false;
        }

        // base condition , if currentPosition reached to destination position
        if (start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }

        // mark current cell as visited
        visited[start[0]][start[1]] = true;

        // traverse all 4 directions
        // steps[][]= {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
        // start {0,4}
        for (int[] step : steps) {
            int currX = start[0]; // 0
            int currY = start[1]; // 4

            // if choose 1 direction - keep moving to next direction till wall comes , hence while loop is required
            // Ball keep rolling in 1 particular step, keep incrementing step[0] and step[1] to currX and currY position
            // make sure ball don't hit the wall , if hit wall..stop
            // Intuition - Ensures that the ball keeps rolling in the current direction until it hits the wall.
            // Only when the ball hits the wall does it enters the next call on dfs()
            while (currX + step[0] >= 0 && currX + step[0] < rows && // 1>=0 && 1<5
                    currY + step[1] >= 0 && currY + step[1] < cols && // 4>=0 && 4<5
                    maze[currX + step[0]][currY + step[1]] != 1) {
                currX += step[0]; //(0,4) -> (1,4) -> (2,4)
                currY += step[1];
                count++;
            }

            // check dfs on new rolled position after step
            if (dfs(maze, new int[] {currX, currY}, dest, visited, rows, cols)) {
                System.out.println(count);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchMaze obj = new SearchMaze();
        int[][] maze = new int[][] {{1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 1}};
        //int[][] maze2 = new int[][] {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        obj.hasPath(maze, new int[] {0, 3}, new int[] {2, 0});
    }

}