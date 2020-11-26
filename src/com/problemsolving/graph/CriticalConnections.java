/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CriticalConnections.java, v 0.1 2020-11-25 14:06 paras.chawla Exp $$
 */
public class CriticalConnections {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        // List of critical connections
        List<List<Integer>> criticalConnections = new ArrayList<>();

        // Create Graph
        Graphh graph = new Graphh(n);

        // create adjList
        for (List<Integer> connection : connections) {
            graph.addEdge(connection.get(0), connection.get(1));
        }

        // traverse removing all connections one by one and check if graph is disconnected
        // if disconnected, remove that particular connection

        for (List<Integer> connection : connections) {

            // update graph based on connection removed
            graph.removeEdge(connection.get(0), connection.get(1));

            // check if graph is disconnected or not, if disconnected, add in List<Critical connections>
            boolean[] visited = new boolean[n];

            // check for all connections
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    count++;
                    dfs(visited, graph, i);
                }
            }

            // if network breaks,add traverse connection into critical connection list
            if (count > 1) {
                criticalConnections.add(connection);
            }

            // undo everything
            count = 0;
            visited = new boolean[n];

            // update graph based on connection removed
            graph.addEdge(connection.get(0), connection.get(1));
        }
        return criticalConnections;
    }

    private void dfs(boolean[] visited, Graphh graph, int i) {
        visited[i] = true;
        for (int childVertex : graph.getAdjList().get(i)) {
            if (!visited[childVertex]) {
                dfs(visited, graph, childVertex);
            }
        }
    }

    public static void main(String[] args) {
        CriticalConnections obj = new CriticalConnections();
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> d = new ArrayList<>();

        a.add(0);
        a.add(1);
        b.add(1);
        b.add(2);
        c.add(2);
        c.add(0);
        d.add(1);
        d.add(3);

        connections.add(a);
        connections.add(b);
        connections.add(c);
        connections.add(d);

        obj.criticalConnections(4, connections);
    }
}