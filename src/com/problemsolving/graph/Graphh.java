/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Graphh.java, v 0.1 2020-11-25 12:03 paras.chawla Exp $$
 */
public class Graphh {
    int                 V;
    List<List<Integer>> adjList;

    public Graphh(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public boolean removeEdge(int u, int v) {
        adjList.get(u).remove((Object) v);
        adjList.get(v).remove((Object) u);
        return true;
    }

    public List<List<Integer>> getAdjList() {
        return adjList;
    }
}
