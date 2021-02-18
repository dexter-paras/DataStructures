/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: SquirrelSimulation.java, v 0.1 2021-02-07 9:32 PM paras.chawla Exp $$
 */
public class SquirrelSimulation {

    /*
    * Input:
        Height : 5
        Width : 7
        Tree position : [2,2]
        Squirrel : [4,4]
        Nuts : [[3,0], [2,5]]
        Output: 12
    * */

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {

        // 1. find min nut to move by squirrel
        int minDistance = Integer.MAX_VALUE;
        int[] nearestNut = new int[2];

        for (int[] nut : nuts) {
            int nearestDistance = Math.abs(nut[0] - squirrel[0]) + Math.abs(nut[1] - squirrel[1]);
            if (nearestDistance < minDistance) {
                nearestNut = nut;
                minDistance = nearestDistance;
            }
        }

        // 2. traverse all nuts one by one and reach to Tree then
        Queue<int[]> queue = new LinkedList<>();
        int totalDistance = minDistance;
        for (int[] nut : nuts) {
            if (nut != nearestNut) { queue.add(nut); }
        }

        // 3. squirel has reached nearestNut with distance=minDistance
        totalDistance += Math.abs(nearestNut[0] - tree[0]) + Math.abs(nearestNut[1] - tree[1]);

        // 4. traverse till all nuts are finished
        while (!queue.isEmpty()) {

            int[] nut = queue.poll();
            // traverse to nut and back to tree
            totalDistance = totalDistance + (2 * (Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1])));
        }

        return totalDistance;
    }

    public int minDistance2(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0, maxDiff = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            int dist = Math.abs(tree[0] - nut[0]) + Math.abs(tree[1] - nut[1]);
            sum += 2*dist;
            maxDiff = Math.max(maxDiff, dist - Math.abs(squirrel[0] - nut[0]) - Math.abs(squirrel[1] - nut[1]));
        }
        return sum - maxDiff;
    }

    public static void main(String[] args) {
        SquirrelSimulation obj = new SquirrelSimulation();
        System.out.println(obj.minDistance(5, 7, new int[] {2, 2}, new int[] {4, 4}, new int[][] {{3, 0}, {2, 5}}));
    }

}