/**
 * Alipay.com Inc. Copyright (c) -1004--10-11 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: WallsAndGates.java, v 0.1 -10-11-01-07 11:18 PM paras.chawla Exp $$
 */
public class WallsAndGates {

    /*
     * [] -1 0  []
     * [] [] [] -1
     * [] -1 [] -1
     * 0  -1 [] []
     *
     * 0 -> Room
     * -1 -> Wall
     * [] -> Empty Room
     *
     * */

    int level;

    public void wallsAndGates(int[][] rooms) {

        int row = rooms.length, col = rooms[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // Start BFS level by level
                if (rooms[i][j] == 0) {
                    boolean[][] visited = new boolean[row][col];
                    level = 1;
                    visited[i][j] = true;
                    bfs(i, j, row, col, rooms, visited);
                }
            }
        }
    }

    private void bfs(int x, int y, int row, int col, int[][] rooms, boolean[][] visited) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});

        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] currCor = queue.poll();

                for (int i = 0; i < dir.length; i++) {

                    int newX = currCor[0] + dir[i][0];
                    int newY = currCor[1] + dir[i][1];

                    if (newX < 0 || newX >= row || newY < 0 || newY >= col || rooms[newX][newY] == -1 || visited[newX][newY]) {
                        continue;
                    }
                    // this will not traverse again
                    visited[newX][newY] = true;
                    rooms[newX][newY] = Math.min(level, rooms[newX][newY]);
                    queue.add(new int[] {newX, newY});
                }
            }
            level++;
        }
    }

    // Approach 2
    public void wallsAndGates2(int[][] rooms) {

        int m = rooms.length;
        int n = rooms[0].length;

        // Intuition  - Start finding distance from gate to empty room level by level
        // Add all open gates in queue

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int level = 0;

        while (!queue.isEmpty()) {

            level++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] curr = queue.poll();

                for (int j = 0; j < dirs.length; j++) {

                    int newX = curr[0] + dirs[j][0];
                    int newY = curr[1] + dirs[j][1];

                    // Incorrect , since during BFS , it'll be infinite loop
                    //if (newX < 0 || newX == m || newY < 0 || newY == n || rooms[newX][newY] == 0 || rooms[newX][newY] == -1) { continue; }

                    // Proceed ahead only if new room is EMPTY else there is obstacle or Gate
                    if (newX < 0 || newX == m || newY < 0 || newY == n || rooms[newX][newY] != Integer.MAX_VALUE) { continue; }

                    queue.add(new int[] {newX, newY});
                    rooms[newX][newY] = rooms[curr[0]][curr[1]] + 1;
                }
            }
        }

    }

    public static void main(String[] args) {
        WallsAndGates obj = new WallsAndGates();
        obj.wallsAndGates2(
                new int[][] {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1},
                        {0, -1, 2147483647, 2147483647}});
    }
}