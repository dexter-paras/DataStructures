/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DetectCycleInGraph.java, v 0.1 2020-09-12 23:43 paras.chawla Exp $$
 */
public class DetectCycleInGraph {

    static class Graph {
        private final int                 V;
        private final List<List<Integer>> adj;

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);

            for (int i = 0; i < V; i++) {
                adj.add(new LinkedList<Integer>());
            }
        }

        private void addEdge(int source, int dest) {
            adj.get(source).add(dest);
        }

        private boolean isCyclic() {

            // Mark all the vertices as not visited and
            // not part of recursion stack
            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];

            // Call the recursive helper function to
            // detect cycle in different DFS trees
            for (int i = 0; i < V; i++) {
                if (isCyclicUtil(i, visited, recStack)) {
                    return true; }
            }

            return false;
        }

        // check if from particular vertex, cycle exist or not
        private boolean isCyclicUtil(int i, boolean[] visited,
                                     boolean[] recStack) {

            // if current traversing node is already visited, hence current traversal includes a cycle
            if (recStack[i]) {
                return true;
            }

            // if already visited before , that means no cycle exist ,hence further processing isn't required
            // because it has already been processed before
            if (visited[i]) {
                return false;
            }

            // mark current traversal node as visited in global visited array
            visited[i] = true;
            // mark current traversal node as visited in current traversal visited array
            recStack[i] = true;

            List<Integer> children = adj.get(i);

            for (Integer c : children) {
                if (isCyclicUtil(c, visited, recStack)) {
                    return true;
                }
            }

            // if no cycle from current visited node, mark as false in recStack i.e. traversal visited array
            recStack[i] = false;
            return false;
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);

        System.out.println(graph.isCyclic());
    }
}