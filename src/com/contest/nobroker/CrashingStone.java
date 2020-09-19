/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.nobroker;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: CrashingStone.java, v 0.1 2020-09-13 20:29 paras.chawla Exp $$
 */
public class CrashingStone {

    public static int lastStoneWeight(List<Integer> weights) {
        // Insert all the stones into a Max-Heap.
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone: weights) {
            heap.add(stone);
        }

        // While there is more than one stone left, we need to remove the two largest
        // and smash them together. If there is a resulting stone, we need to put into
        // the heap.
        while (heap.size() > 1) {
            int stone1 = heap.remove();
            int stone2 = heap.remove();
            if (stone1 != stone2) {
                heap.add(stone1 - stone2);
            }
        }

        // Check whether or not there is a stone left to return.
        return heap.isEmpty() ? 0 : heap.remove();
    }
}