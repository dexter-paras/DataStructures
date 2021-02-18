/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: NumberOfConnectedComponents.java, v 0.1 2021-01-16 8:14 PM paras.chawla Exp $$
 * <p>
 * // Approach 2 - https://www.youtube.com/watch?v=ymxPZk7TesQ&t=2s
 */
public class NumberOfConnectedComponents {

    /* Approach 1 - Use DFS
    n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

        0->1
        1->0,2
        2->1
        3->4
    */
    public int countComponents(int n, int[][] edges) {

        // create a Graph
        Graph graph = new Graph(n);

        // add edges in graph
        // Its a undirected Graph , hence need to add both directionals
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
            graph.addEdge(edge[1], edge[0]);
        }

        boolean[] visited = new boolean[n];
        int connectedComponent = 0;
        // Run dfs on all vertices if not visited
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connectedComponent++;
                visited[i] = true;
                dfs(i, graph.adjList, visited);
            }
        }

        return connectedComponent;
    }

    private void dfs(int vertex, List<List<Integer>> adjList, boolean[] visited) {
        List<Integer> childrens = adjList.get(vertex);

        for (int children : childrens) {
            if (!visited[children]) {
                visited[children] = true;
                dfs(children, adjList, visited);
            }
        }
    }

    class Graph {
        int                 V;
        List<List<Integer>> adjList;

        public Graph(int V) {
            this.V = V;
            this.adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }
    }

    /* Approach 2 - Use Disjoint Sets
    n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

    [1, 2, 2, 4, 4] <- parent
    [0, 1, 2, 3, 4] <- parent
    [0, 1, 2, 3, 4]

    */
    public int countComponents2(int n, int[][] edges) {

        // create an array which contains parent of every index
        int[] parent = new int[n];

        // initially parent of all index is itself , -1 represent parent of itself
        // Initially add edges are independent subset and while traversing edges, we'll keep union them
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // traverse edges
        for (int[] edge : edges) {

            int fromEdge = edge[0];//0
            int toEdge = edge[1];//1

            union(fromEdge, toEdge, parent);
        }

        // find total connected Components
        Set<Integer> set = new HashSet<>();
        //parent[] = [1,2,2,4,4]
        //           [0,1,2,3,4]

        // Doing path compression
        for (int i = 0; i < parent.length; i++) {
            set.add(find(i, parent));
        }

        return set.size();
    }

    private void union(int fromEdge, int toEdge, int[] parent) {
        int parentFromEdge = find(fromEdge, parent); //0
        int parentToEdge = find(toEdge, parent); // 1

        parent[parentFromEdge] = parentToEdge;
    }

    private int find(int edge, int[] parent) {
        // if Absolute parent of edge is not equal to current edge
        if (parent[edge] != edge) {
            parent[edge] = find(parent[edge], parent);
        }
        return parent[edge];
    }

    public static void main(String[] args) {
        NumberOfConnectedComponents obj = new NumberOfConnectedComponents();
        System.out.println(obj.countComponents2(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
    }

}


