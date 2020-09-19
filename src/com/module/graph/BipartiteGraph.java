/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: BipartiteGraph.java, v 0.1 2020-06-09 07:26 paras.chawla Exp $$
 * <p>
 * A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every edge (u, v) either connects
 * a vertex from U to V or a vertex from V to U
 * <p>
 * There is no edge that connects vertices of same set
 * <p>
 * A Bipartite graph Can only have even edge length Cycle
 * <p>
 * A bipartite graph is possible if the graph coloring is possible using two colors such that vertices in a set are colored with the same
 * color
 */

/* Pseudo Code
  1. Color all vertices with no-color i.e. -1
  2. To handle disconnected graph -if graph is disconnected, then need to restart bfs from all vertices which are not visited
  3. Color initial vertex with 1 and put in queue
  4. Loop Queue till it is empty
       4.1 if self loop from current vertex , then return false
       4.2 color neighbor with opposite color of current color.Add neighbors in Queue as well
 */
public class BipartiteGraph {

    public boolean isBipartite(int[][] graph) {

        // if graph is connected, then can pass source vertex
        //return isBipartite(graph, 0);

        // Step 1.
        int[] color = new int[graph.length];
        Arrays.fill(color,-1);

        // Step 2.
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1 && !isBipartiteUtil(graph, i, color)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartiteUtil(int[][] graph, int vertex, int[] color) {

        // Step 3.
        Queue<Integer> queue = new LinkedList<>();
        color[vertex] = 1;
        queue.add(vertex);

        // Step 4.
        while (!queue.isEmpty()) {
            int u = queue.poll();

            // Step 4.1
            if (graph[u][u] == 1) {
                return false;
            }

            // Step 4.2
            for (int v = 0; v < graph.length; v++) {

                // if u-> v is connected and v is not colored yet
                if (graph[u][v] == 1 && color[v] == -1) {
                    color[v] = 1 - color[u];
                    queue.add(v);
                }

                // if u-> v is connected and v is same color as that of u
                else if (graph[u][v] == 1 && color[v] == color[u]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new BipartiteGraph().isBipartite(new int[][] {{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}}));
    }
}