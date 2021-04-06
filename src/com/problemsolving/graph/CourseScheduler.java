/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: CourseScheduler.java, v 0.1 2020-09-17 20:45 paras.chawla Exp $$ https://leetcode.com/problems/course-schedule/
 */
public class CourseScheduler {

    // https://www.youtube.com/watch?v=Zuwp40mT66c
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);

        // fulfil graph
        for (int i = 0; i < prerequisites.length; i++) {
            graph.addEdge(prerequisites[i][0], prerequisites[i][1]);
        }

        // global visited array - to check if cyclic from all vertices
        // store already visited nodes with no cycle found last time if true
        boolean[] visited = new boolean[numCourses];
        // current visited array in current dfs traversal - to check if current traversal from vertex V involves cycle or not
        boolean[] recStack = new boolean[numCourses];

        // detect cycle from each vertex/course
        for (int i = 0; i < numCourses; i++) {
            if (is_cyclic(i, visited, recStack, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean is_cyclic(int i, boolean[] visited, boolean[] recStack, Graph graph) {

        // traversal node already exist in recStack, that means cycle exist
        if (recStack[i]) {
            return true;
        }

        // node already visited and no cycle found last time, so no use of further processing
        if (visited[i]) {
            return false;
        }

        visited[i] = true;
        recStack[i] = true;

        //Childrens of current traversal node
        List<Integer> childrens = graph.adjList.get(i);
        // traverse all childrens of current traversal node
        for (int j : childrens) {
            if (is_cyclic(j, visited, recStack, graph)) {
                return false;
            }
        }
        recStack[i] = false;
        return false;
    }

    class Graph {
        int                 V;
        List<List<Integer>> adjList;

        public Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);

            for (int i = 0; i < V; i++) {
                adjList.add(new LinkedList<Integer>());
            }
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }
    }

    // Approach 2 - BFS solution
    public boolean canFinishbfs(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];

        // number of in-degree arrows coming to vertex
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList();
        int count = 0;

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        while (queue.size() != 0) {
            int course = queue.poll();
            for (int i = 0; i < graph[course].size(); i++) {
                int pointer = (int) graph[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if (count == numCourses) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        CourseScheduler obj = new CourseScheduler();
        //System.out.println(obj.canFinishbfs(3, new int[][] {{0, 1}, {2, 1}, {2, 0}}));
        //System.out.println(obj.canFinish(3, new int[][] {{0, 1}, {2, 1}, {2, 0}}));
        //System.out.println(obj.canFinish(2, new int[][] {{0, 1},{1, 0}}));
        System.out.println(obj.canFinishbfs(5, new int[][] {{0, 1}, {2, 1}, {4, 0}, {4, 2}, {4, 3}, {2, 3}}));
    }

}