/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: KClosestPointToOrigin.java, v 0.1 2021-01-12 9:40 AM paras.chawla Exp $$
 *
 * https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
 */
public class KClosestPointToOrigin {

    // Approach 1- Using MaxHeap
    // TC - NlogK
    public int[][] kClosest(int[][] points, int K) {

        // Base case
        if (points == null || points.length == 0) {
            return null;
        }

        // Create a Max Heap with size K , top element with max distance...Need to remove Entry with Max Distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double result = Math.sqrt(Math.pow(o2[0], 2) + Math.pow(o2[1], 2)) - Math.sqrt(Math.pow(o1[0], 2) + Math.pow(o1[1], 2));
                if (result > 0) {
                    return (int) Math.ceil(result);
                } else if (result < 0) {
                    return (int) Math.floor(result);
                } else {
                    return 0;
                }
            }
        });

        // No need to do Sqrt of the distance from Origin
        // PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] point : points) {
            maxHeap.add(point);
            // add first and then check for removal , if done vice-versa - it won't poll() correctly
            if (maxHeap.size() == K + 1) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[maxHeap.size()][2];
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            int[] entry = maxHeap.poll();
            result[idx++] = entry;
        }

        return result;
    }

    // Approach 2 - Sorting points based on Distance, no need to do sqrt on both
    // TC - NlogN
    public int[][] kClosestSort(int[][] points, int K) {

        Arrays.sort(points, (o1, o2) -> o1[0] * o1[0] + o1[1] * o1[1] - o2[0] * o2[0] - o2[1] * o2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void main(String[] args) {
        KClosestPointToOrigin obj = new KClosestPointToOrigin();
        System.out.println(obj.kClosestSort(new int[][] {{3, 3}, {5, -1}, {-2, 4}, {1, 3}, {10, 10}}, 3));
        System.out.println(obj.kClosest(new int[][] {{1, 3}, {-2, 2}}, 1));
    }

}