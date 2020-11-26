/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: DfsBfsTs.java, v 0.1 2020-09-24 10:54 paras.chawla Exp $$
 */
public class DfsBfsTs {

    public static void main(String[] args) {
        DfsBfsTs obj = new DfsBfsTs();
        obj.traversal(7, new int[][] {{0, 1}, {0, 3}, {3, 2}, {2, 1}, {4, 5}, {4, 6}, {5, 6}});
        obj.traversal(8, new int[][] {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 7}, {5, 7}, {6, 7}});
    }

    public void traversal(int numV, int[][] edges) {
        // Crating a graph of vertices
        Graphh graph = new Graphh(numV);

        // Adding edges in graph
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }

        System.out.println(" ******** Starting DFS *********** ");

        // calculating dfs from all vertices
        boolean[] visited = new boolean[numV];
        for (int i = 0; i < numV; i++) {
            if (!visited[i]) {
                System.out.println("Visiting Vertex " + i);
                dfs(i, graph, visited);
            }
        }

        System.out.println(" ******** Starting BFS *********** ");

        // calculating bfs from all vertices
        boolean[] visitedBfs = new boolean[numV];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numV; i++) {
            if (!visitedBfs[i]) {
                System.out.println("Visiting Vertex " + i);
                bfs(i, graph, visitedBfs, queue);
            }
        }
    }

    private void dfs(int vertex, Graphh graph, boolean[] visited) {

        // mark current vertex as visited and start working on the same vertex
        visited[vertex] = true;
        // find list of neighbors
        List<Integer> neighbors = graph.adjList.get(vertex);

        // traverse all neighbors if they've not visited before
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                System.out.println("Visiting vertex " + neighbor);
                visited[neighbor] = true;
                dfs(neighbor, graph, visited);
            }
        }
    }

    private void bfs(int vertex, Graphh graph, boolean[] visitedBfs, Queue<Integer> queue) {

        visitedBfs[vertex] = true;
        queue.offer(vertex);

        while (!queue.isEmpty()) {
            int currVertex = queue.poll();

            // find list of neighbors
            List<Integer> neighbors = graph.adjList.get(currVertex);

            // traverse all neighbors if they've not visited before
            for (int neighbor : neighbors) {
                if (!visitedBfs[neighbor]) {
                    System.out.println("Visiting vertex " + neighbor);
                    visitedBfs[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

}