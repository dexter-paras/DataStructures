/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

/**
 * @author paras.chawla
 * @version $Id: UnionFind.java, v 0.1 2021-02-17 11:23 PM paras.chawla Exp $$
 * https://leetcode.com/problems/is-graph-bipartite/discuss/923027/Generic-Union-Find-Templete-Solution
 */
public class UnionFind {

    private int size;

    private int numComponents;

    private int[] parent;
    private int[] rank;

    // [0,1,2,3]    < parent
    // [0,1,2,3]    < index

    public UnionFind(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("Size <= 0 is not allowed");
        }

        size = numComponents = n;
        parent = new int[n];
        rank = new int[n];

        // all vertices are parent of itself initially
        for (int i = 0; i < n; i++) { parent[i] = i; }
    }

    public int find(int vertex) {
        while (vertex != parent[vertex]) {
            parent[vertex] = parent[parent[vertex]];
            vertex = parent[vertex];
        }
        return vertex;
    }

    public void union(int p, int q) {

        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return;
        }

        // Merge smaller component/set into the larger one.
        if (rank[rootQ] > rank[rootP]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
            if (rank[rootP] == rank[rootQ]) {
                rank[rootP]++;
            }
        }

        // Since the roots found are different we know that the number of components/sets has decreased by one
        numComponents--;
    }

    // Return whether or not the elements 'p' and 'q' are in the same components/set.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Returns the number of remaining components/sets
    public int components() {
        return numComponents;
    }

    // Return the number of elements in this UnionFind/Disjoint set
    public int size() {
        return size;
    }

}