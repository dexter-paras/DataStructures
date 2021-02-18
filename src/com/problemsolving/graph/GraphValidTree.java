/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: GraphValidTree.java, v 0.1 2021-01-19 9:23 AM paras.chawla Exp $$
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {

        // A tree is valid if it doesn't hold any cycle
        // Need to check if cycle exist or not

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // traverse edges and check if parent of both edges are same, return false

        for (int[] edge : edges) {

            int fromEdge = edge[0];//0
            int toEdge = edge[1];//1

            int parentFromEdge = find(fromEdge, parent);
            int parentToEdge = find(toEdge, parent);

            // if path already exist and new edge will create a cycle
            if (parentFromEdge == parentToEdge) {
                return false;
            }

            parent[parentFromEdge] = parentToEdge;
        }
        // find total connected Components
        Set<Integer> set = new HashSet<>();
        //parent[] = [1,2,2,4,4]
        //           [0,1,2,3,4]

        // Doing path compression
        for (int i = 0; i < parent.length; i++) {
            set.add(find(i, parent));
        }

        return set.size() == 1 ? true : false;
    }

    private int find(int edge, int[] parent) {
        // if Absolute parent of edge is not equal to current edge
        if (parent[edge] != edge) {
            parent[edge] = find(parent[edge], parent);
        }
        return parent[edge];
    }

    public static void main(String[] args) {
        GraphValidTree obj = new GraphValidTree();
        System.out.println(obj.validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        System.out.println(obj.validTree(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }
}