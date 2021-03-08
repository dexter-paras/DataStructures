/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: ShortestBridge.java, v 0.1 2020-12-21 11:19 paras.chawla Exp $$
 * https://leetcode.com/problems/shortest-bridge/discuss/190421/Java-extremely-easy-to-understand-with-comment-
 * (bfs-to-find-answer-and-dfs-to-paint-the-matrix)
 */

// bfs to find answer and dfs to paint the matrix
public class ShortestBridge {

    public int shortestBridge(int[][] A) {
        // Step 1 - paint one island to 2 to differentiate both islands
        floodFill(A);

        // Step 2 - Apply BFS from all points having 2
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[A.length][A[0].length];

        // Initialize queue with all cordinates having 2
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 2) {
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        int level = 0;
        int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {

                int[] curr = queue.poll();
                int oldX = curr[0];
                int oldY = curr[1];
                if (A[oldX][oldY] == 1) {//found, then return
                    return level - 1;
                }

                for (int[] dir : directions) {
                    int x = curr[0] + dir[0]; //0,1
                    int y = curr[1] + dir[1];

                    if (x < 0 || x >= A.length || y < 0 || y >= A[0].length || visited[x][y]) {
                        continue;
                    }

                    // this will not traverse again
                    visited[x][y] = true;
                    queue.add(new int[] {x, y});
                }
            }
            level++;// next level
        }
        return level;
    }

    // Paint or flood fill one island to 2
    private void floodFill(int[][] A) {

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    dfs(i, j, A);
                    return;
                }
            }
        }

    }

    // Traverse in all 4 directions and flood fill with 2
    private void dfs(int x, int y, int[][] A) {

        // base condition
        if (x < 0 || x > A.length - 1 || y < 0 || y > A[0].length - 1 || A[x][y] != 1) {
            return;
        }

        A[x][y] = 2;
        dfs(x - 1, y, A);
        dfs(x + 1, y, A);
        dfs(x, y - 1, A);
        dfs(x, y + 1, A);
    }

    public static void main(String[] args) {
        ShortestBridge obj = new ShortestBridge();
        //System.out.println(obj.shortestBridge(new int[][] {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
        //System.out.println(obj.shortestBridge(new int[][] {{0, 1}, {1, 0}}));
        System.out.println(obj.shortestBridge(new int[][] {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));
    }

}