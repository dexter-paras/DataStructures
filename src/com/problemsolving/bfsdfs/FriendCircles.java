/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import com.problemsolving.graph.Graphh;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: FriendCircles.java, v 0.1 2020-11-24 22:56 paras.chawla Exp $$
 */
public class FriendCircles {

    public int findCircleNum(int[][] M) {

        if (M == null || M.length == 0) {
            return 0;
        }

        int friendCircles = 0;
        int row = M.length;
        int col = M[0].length;

        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 1 && !visited[i][j]) {
                    friendCircles++;
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                    bfs(M, visited, queue);
                }
            }
        }
        return friendCircles;
    }

    private void bfs(int[][] M, boolean[][] visited, Queue<int[]> queue) {

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[1];

            for (int i = 0; i < M[0].length; i++) {
                if (M[y][i] != 0 && !visited[y][i]) {
                    visited[y][i] = true;
                    queue.add(new int[] {y, i});
                }
            }
        }
    }

    // Approach 2 - Using DFS , This is same as adjacency list of a graph
    public int findCircleNumdfs(int[][] M) {

        if (M == null || M.length == 0) {
            return 0;
        }

        int friendCircles = 0;

        // Creating Graph out of Matrix
        Graphh graph = new Graphh(M.length);

        // Creating Adjacency list
        int row = M.length;
        int col = M[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i != j && M[i][j] == 1) {
                    graph.addEdge(i, j);
                }
            }
        }

        boolean[] visited = new boolean[M.length];
        // Traversing graph all vertices and apply dfs
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                friendCircles++;
                dfs(visited, graph, i);
            }
        }

        return friendCircles;
    }

    private void dfs(boolean[] visited, Graphh graph, int i) {
        visited[i] = true;
        for (int childVertex : graph.getAdjList().get(i)) {
            if(!visited[childVertex]){
                dfs(visited,graph,childVertex);
            }
        }
    }

    public static void main(String[] args) {
        int grid1[][] = new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int grid2[][] = new int[][] {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        FriendCircles obj = new FriendCircles();
        //System.out.println(obj.findCircleNum(grid));
        System.out.println(obj.findCircleNumdfs(grid1));
        System.out.println(obj.findCircleNumdfs(grid2));
    }
}