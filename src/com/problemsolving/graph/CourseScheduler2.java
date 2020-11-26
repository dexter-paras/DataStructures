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
 * @version $Id: CourseScheduler2.java, v 0.1 2020-09-18 01:09 paras.chawla Exp $$
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduler2 {

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

    // numCourses = number of courses/vertices
    // prerequisities = dependency of 1 course on another course = directed edges
    // prerequisities = {{0, 1}, {2, 1}, {4, 0},{4,2},{4,3},{2,3}}
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // 1. Create graph
        Graph graph = new Graph(numCourses);

        // 2. fulfill graph
        for (int i = 0; i < prerequisites.length; i++) {
            // adding childrens
            graph.addEdge(prerequisites[i][0], prerequisites[i][1]);
        }

        // 3. creating in-degree or dependency matrix
        int[] degree = new int[numCourses];

        for (int i = 0; i < graph.adjList.size(); i++) {
            List<Integer> childrens = graph.adjList.get(i);
            for (int idx : childrens) {
                degree[idx]++;
            }
        }

        // 4. instantiating finish count ,signifies how many courses are finished
        int finish = 0;

        // 5. traverse degree matrix and if degree found 0 , add in queue because there is no dependeny on course with in-degree as 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                // added in queue as it has no dependency , hence it is finished
                finish++;
            }
        }

        // 6. keep traversing till all courses are finished
        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int finshedCourse = queue.poll();
            result[numCourses - index - 1] = finshedCourse;
            index++;
            // get all dependencies of finished course and reduce children depencies by 1
            List<Integer> childrens = graph.adjList.get(finshedCourse);
            // traverse all childrens and reduce children indegree by 1, if indegree becomes 0 that means course has no dependency
            for (int idx : childrens) {
                degree[idx]--;
                if (degree[idx] == 0) {
                    queue.add(idx);
                    finish++;
                }
            }
        }

        return finish == numCourses ? result : new int[0];
    }

    public static void main(String[] args) {
        CourseScheduler2 obj = new CourseScheduler2();
        //System.out.println(obj.findOrder(5, new int[][] {{0, 1}, {2, 1}, {4, 0}, {4, 2}, {4, 3}, {2, 3}}));
        //System.out.println(obj.findOrder(5, new int[][] {{0, 1}, {2, 1}, {4, 0}, {4, 2}, {3, 4}, {2, 3}}));
        System.out.println(obj.findOrder(2, new int[][] {{1, 0}}));
    }
}