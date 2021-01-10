/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CycleInUnDirectedGraph.java, v 0.1 2021-01-03 10:43 PM paras.chawla Exp $$
 */
public class CycleInUnDirectedGraph {

    // Find Cycle using Disjoint Set
    boolean hasCycle(List<int[]> edges, int V) {

        int[] parent = new int[V];

        // 1. All Vertices are parent of itself
        Arrays.fill(parent, -1);

        // 2. loop every edge one by one and return false if particular edge creating a cycle if added
        for (int i = 0; i < V; i++) {

            int[] edge = edges.get(i);

            int from = edge[0];
            int to = edge[1];

            int fromParentVertex = findParent(from, parent);
            int toParentVertex = findParent(to, parent);

            if (fromParentVertex == toParentVertex) {
                return true;
            }

            union(fromParentVertex, toParentVertex, parent);
        }
        return false;
    }

    private int findParent(int vertex, int[] parent) {

        if (parent[vertex] == -1) {
            return vertex;
        }
        return findParent(parent[vertex], parent);
    }

    private void union(int fromParentVertex, int toParentVertex, int[] parent) {
        parent[fromParentVertex] = toParentVertex;
    }

}