/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: CloneGraph.java, v 0.1 2021-02-11 2:56 PM paras.chawla Exp $$
 */
public class CloneGraph {

    // Approach 1- Working DFS

    Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph2(Node node) {

        if (node == null) {
            return node;
        }

        // if node already explored, return visited cloned node
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // create clone node and put it in visited map
        Node cloneNode = new Node(node.val);

        visited.put(node, cloneNode);

        // 1 -> 2,4     1->1'
        // 2 -> 1,3     1->1', 2->2'
        // 1 -> 1'
        // 3 -> 2,4     1->1',2->2',3->3'
        // 4 -> 1'
        for (Node children : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph2(node));
        }

        return cloneNode;
    }

    // Approach 3 - BFS
    // TODO

    // APproach 2- Not Working
    public Node cloneGraph(Node node) {

        // 1. Traverse the graph and create mapping of oldNode and newNode

        Map<Node, Node> map = new HashMap<>();
        // 2. Pass 1 to create mapping of oldNode and newNode
        dfs(node, map);

        // 3. Add neighbor nodes in cloneNodes
        boolean visited[] = new boolean[map.size()];
        dfsCloneNode(node, map, visited);

        return map.get(node);

    }

    private void dfsCloneNode(Node node, Map<Node, Node> map, boolean[] visited) {

        visited[node.val - 1] = true;
        for (Node children : node.neighbors) {
            if (!visited[children.val - 1]) {
                Node clonedNode = map.get(node);
                clonedNode.neighbors.add(map.get(children));
                dfsCloneNode(children, map, visited);
            }
        }
    }

    private void dfs(Node node, Map<Node, Node> map) {

        // if cloneNode not exist, create one
        if (!map.containsKey(node)) {
            Node cloneNode = new Node(node.val);
            map.put(node, cloneNode);
        } else {
            return;
        }

        for (Node children : node.neighbors) {
            dfs(children, map);
        }
    }

}

class Node {
    public int        val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}