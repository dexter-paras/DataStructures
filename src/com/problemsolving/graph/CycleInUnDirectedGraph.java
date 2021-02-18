/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CycleInUnDirectedGraph.java, v 0.1 2021-01-03 10:43 PM paras.chawla Exp $$
 */
public class CycleInUnDirectedGraph {

    /*
     *   0-------1
     *   |
     *   |
     *   3-------2
     *
     *
     * edges {{0,1},{0,3},{2,3},{1,2}}
     *
     *
     *-1    -1     -1       -1         parent[]
     * 0     1      2        3
     *
     * */

    // Approach 1 - Find Cycle using Disjoint Set
    boolean hasCycle(List<int[]> edges, int V) {

        int[] parent = new int[V];

        // 1. All Vertices are parent of itself
        Arrays.fill(parent, -1);

        // 2. loop every edge one by one and return false if particular edge creating a cycle if added
        for (int i = 0; i < V; i++) {

            int[] edge = edges.get(i);

            int from = edge[0];
            int to = edge[1];

            int fromParentVertex = findParent(from, parent);//0
            int toParentVertex = findParent(to, parent);//1

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

    // Approach 2 Using UnionByRank

    boolean hasCycle2(List<int[]> edges, int V) {

        int[] parent = new int[V];

        // 1. All Vertices are parent of itself
        Arrays.fill(parent, -1);

        // 2. loop every edge one by one and return false if particular edge creating a cycle if added
        for (int i = 0; i < V; i++) {

            int[] edge = edges.get(i);

            int from = edge[0];
            int to = edge[1];

            int fromRoot = findParent(from, parent);//0
            int toRoot = findParent(to, parent);//1

            if (fromRoot == toRoot) {
                return true;
            }

            // merge two different set into 1 single set
            unionByRank(fromRoot, toRoot, parent);
        }
        return false;
    }
    /* {1 ,-2, -1,  -1}
       {0 , 1,  2,   3}

   A. {0,1}
            1
            ^
            |
            0

   B. {0,3}
     f(0)=1(4)
     f(3)=3(1)

    {1 ,-4, -1, 1}
    {0 , 1,  2, 3}

     1 <---3
     ^
     |
     0

    C. {2,3}

     f(2)=2(1)
     f(3)=1(4)

    Different Parent ,hence union is possible

    {1 ,-4,  1, 1}
    {0 , 1,  2, 3}

      2----> 1 <---3
             ^
             |
             0

     D. {1,2}
         f(1)=1(4)
         f(2)=1(4)

     Same,hence including this edge means cycle will create


    */
    private void unionByRank(int fromRoot, int toRoot, int[] parent) {

        // Same Rank
        if(Math.abs(parent[fromRoot])>= Math.abs(parent[toRoot])) {
            parent[fromRoot] = toRoot;
            parent[toRoot] *= 2;
        }
    }

    public static void main(String[] args) {
        CycleInUnDirectedGraph obj = new CycleInUnDirectedGraph();
        List<int[]> edgeList = new ArrayList<>();
        edgeList.add(new int[] {0, 1});
        edgeList.add(new int[] {0, 3});
        edgeList.add(new int[] {2, 3});
        edgeList.add(new int[] {1, 2});

        obj.hasCycle2(edgeList,4);
    }

}