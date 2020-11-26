/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author paras.chawla
 * @version $Id: Graph.java, v 0.1 2019-08-24 10:54 paras.chawla Exp $$
 */

// a- >{d} | d ->{b,e} | b->{c,e} | e ->{c,f} | c->null | f-> null

class Vertex {
    String name;

    // first Neighbor
    Neighbor neighbor;

    Vertex(String name, Neighbor neighbors) {
        this.name = name;
        this.neighbor = neighbors;
    }
}

// Neighbor is a linked list

class Neighbor {
    int      vertexNum;
    Neighbor next;

    Neighbor(int vertexNum, Neighbor nbr) {
        this.vertexNum = vertexNum;
        this.next = nbr;
    }
}

public class Graph {

    // array of linkedList i.e. Neighbor
    Vertex[] vertices;

    Graph() {

    }

    public Graph(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));

        String graphType = sc.next();
        boolean undirected = true;

        if (graphType.equals("directed")) {
            undirected = false;
        }

        vertices = new Vertex[sc.nextInt()];

        // read and instantiate vertices
        // a-0 ; b-1 ; c-2 ; d-3 ; e-4 ; f-5

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(sc.next(), null);
        }

        // read edges
        while (sc.hasNext()) {

            // read vertex names and translate to vertex numbers
            int v1 = indexForName(sc.next()); //a ->0
            int v2 = indexForName(sc.next()); //d ->3

            vertices[v1].neighbor = new Neighbor(v2, vertices[v1].neighbor);
            if (undirected) {
                vertices[v2].neighbor = new Neighbor(v1, vertices[v2].neighbor);
            }
        }
    }

    int indexForName(String name) {
        for (int v = 0; v < vertices.length; v++) {
            if (vertices[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }

    private void print() {
        for (int i = 0; i < vertices.length; i++) {
            for (Neighbor nbr = vertices[i].neighbor; nbr != null; nbr = nbr.next) {
                System.out.println(vertices[i].name + " - - - >" + vertices[nbr.vertexNum].name);
            }
        }
    }

    // 0, visited[]=False
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        System.out.println("Visiting---->" + vertices[v].name);
        for (Neighbor nbr = vertices[v].neighbor; nbr != null; nbr = nbr.next) {
            if (!visited[nbr.vertexNum]) {
                System.out.println(vertices[v].name + "---->" + vertices[nbr.vertexNum].name);
                dfs(nbr.vertexNum, visited);
            }
        }
    }

    // this is required to restart dfs from all vertices which are not visited
    private void dfs() {
        boolean[] visited = new boolean[vertices.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println("Restart starting on dfs" + vertices[i].name);
                dfs(i, visited);
            }
        }
    }

    // this is required to restart dfs from all vertices which are not visited
    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                bfs(i, visited, queue);
            }
        }
    }

    private void bfs(int v, boolean[] visited, Queue<Integer> queue) {
        visited[v] = true;
        System.out.println("Visting--->" + vertices[v].name);
        queue.offer(v);
        while (!queue.isEmpty()) {
            int pollVertex = queue.poll();
            for (Neighbor nbr = vertices[pollVertex].neighbor; nbr != null; nbr = nbr.next) {
                if (!visited[nbr.vertexNum]) {
                    System.out.println("Visting --->" + vertices[nbr.vertexNum].name);
                    visited[nbr.vertexNum] = true;
                    queue.offer(nbr.vertexNum);
                }
            }
        }
    }

    // this is required to restart dfsTopSort from all vertices which are not visited
    private void dfsTopSort() {
        boolean[] visited = new boolean[vertices.length];
        int[] topSort = new int[visited.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println("Restart starting on dfs" + vertices[i].name);
                dfsTopSort(i, visited, topSort, visited.length - 1);
            }
        }
    }

    private int dfsTopSort(int v, boolean[] visited, int[] topNum, int n) {
        visited[v] = true;

        // To find non-visited edges in dfs manner
        for (Neighbor nbr = vertices[v].neighbor; nbr != null; nbr = nbr.next) {
            if (!visited[nbr.vertexNum]) {
                n = dfsTopSort(nbr.vertexNum, visited, topNum, n);
            }
        }
        // about to back to previous vertex
        topNum[v] = n;
        return n - 1;
    }

    private List<Integer> topologicalSort() {

        // Add vertices with 0 indegree in a Set
        int[] indegree = new int[vertices.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices.length; i++) {
            for (Neighbor nbr = vertices[i].neighbor; nbr != null; nbr = nbr.next) {
                int count = indegree[nbr.vertexNum];
                indegree[nbr.vertexNum] = count + 1;
            }
        }

        // loop all indegree and add all vertices with 0 indegree in Queue to start the process
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                // add vertexNum i with indegree 0 in queue
                queue.add(i);
            }
        }

        List<Integer> topSort = new ArrayList<>();

        while (!queue.isEmpty()) {
            int vertexWithIndegreeZero = queue.poll();
            topSort.add(vertexWithIndegreeZero);
            // check all neighbors and reduce indegree to 1 for all adjacent neighbors
            for (Neighbor nbr = vertices[vertexWithIndegreeZero].neighbor;
                 nbr != null; nbr = nbr.next) {
                indegree[nbr.vertexNum]--;
                if(indegree[nbr.vertexNum]==0){
                    queue.add(nbr.vertexNum);
                }
            }
        }
        return topSort;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter graph input file");
        String file = scanner.nextLine();
        Graph graph = new Graph(file);
        graph.print();

        /* dfs/bfs shouldn't be call from any particular vertex, so handler included outside and
         restart happens from all vertices so as to cover full traversal */
        graph.dfs();

        graph.bfs();

        graph.dfsTopSort();

        graph.topologicalSort();

    }
}
