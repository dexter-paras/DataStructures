/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
        Arrays.fill(color, -1);

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

    private boolean isBipartiteApproach2(int[][] graph) {

        // Step 1.
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        // Step 2. Create Graph using graph[][]
        Graph obj = new Graph(graph.length);
        for (int i = 0; i < graph.length; i++) {
            int[] edges = graph[i];
            for (int j = 0; j < edges.length; j++) {
                obj.addEdge(i, edges[j]);
            }
        }

        // traverse all vertices and use BFS, all neighboring edges must be having different color, if visited and same color-> return false
        // color = -1 i.e. vertex Not Visited yet
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1 && !bfs(i, obj, color)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(int vertex, Graph graph, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        color[vertex] = 1;
        queue.add(vertex);

        while (!queue.isEmpty()) {

            // find neighboring vertices and assign opposite color to that of vertex i
            int currVertex = queue.poll();//0

            List<Integer> childrens = graph.adjList.get(currVertex);

            for (int children : childrens) {
                // unvisted
                if (color[children] == -1) {
                    color[children] = 1 - color[currVertex];
                    queue.add(children);
                } else if (color[children] == color[currVertex]) {
                    return false;
                }
            }
        }
        return true;
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

    // Using Disjoint Sets
    // graph {1, 3}, {0, 2}, {1, 3}, {0, 2}
    // parent [0,1,2,3]
    //        [0,3,2,3] {1,3}
    //        [2,3,2,3] {0,2}
    //
    // https://leetcode.com/problems/is-graph-bipartite/discuss/923027/Generic-Union-Find-Templete-Solution
    // https://leetcode.com/problems/is-graph-bipartite/discuss/204703/18-lines-UnionFind-solution
    // https://leetcode.com/problems/is-graph-bipartite/discuss/176266/Clean-and-easy-unionfind-in-JAVA
    // Explanation - https://leetcode.com/problems/is-graph-bipartite/discuss/221141/C%2B%2B-20-ms-faster-than-98.93-Union-Find-Solution-with-Detailed-explanation
    public boolean isBipartiteApproach3(int[][] graph) {

        int n = graph.length;
        int parent[] = new int[n];

        // fill parent array
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // traverse graph
        // Find the group of the first node
        for (int u = 0; u < n; u++) {

            for (int j = 0; j < graph[u].length; j++) {
                // Find the group of all the connected nodes
                int v = graph[u][j];

                // for a graph to be bipartite ; u & v can't be in same set means can't have same parent
                // If two connected nodes belongs to the same
                // group we do not have Bipartite Graph
                if (find(u, parent) == find(v, parent)) { return false; }

                // Put all the nodes that are connected with the i node
                // in the same group. We choose the first node of
                // the graph[i] to have the correct group each time
                // Perform union here in order not to recompute the
                // find of the current child (graph[i][j])
                int v1 = find(graph[u][0], parent);
                int v2 = find(graph[u][j], parent);

                // make v1,v2 in same set i.e. parent of v1 = v2
                if (v1 != v2) { parent[v1] = v2; }
            }

        }
        return true;
    }

    int find(int vertex, int[] parent) {
        while (vertex != parent[vertex]) {
            parent[vertex] = parent[parent[vertex]];
            vertex = parent[vertex];
        }
        return vertex;
    }

    public static void main(String[] args) {
        BipartiteGraph obj=  new BipartiteGraph();
        System.out.println(obj.isBipartiteApproach3(new int[][] {{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        System.out.println(obj.isBipartiteApproach3(new int[][] {{1, 3,2}, {0, 2}, {1, 3}, {0, 2}}));
    }

}