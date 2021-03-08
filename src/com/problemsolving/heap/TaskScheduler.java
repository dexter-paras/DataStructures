/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: TaskScheduler.java, v 0.1 2021-02-25 9:13 AM paras.chawla Exp $$
 * https://leetcode.com/problems/task-scheduler/discuss/104511/Java-Solution-PriorityQueue-and-HashMap
 */
public class TaskScheduler {

    /*
     * tasks[] ={A,A,A,A,A,A,B,B,B,C,C,D,E,F}
     * n=2(CoolDown period
     * Intuition :
     *   1. Calculate freq of each char array and put it in map
     *   2. Create a PQ having freq in descending order
     *   3. Create a CoolDown Map which puts polled task in cooldown till cooldown is over,
     *      Once Cool down is over, add that task freq again in queue
     *   4. Start processing task till queue is empty
     * */

    public int leastInterval(char[] tasks, int n) {

        // 1. Calculate freq of each char array and put it in map
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        for (char c : tasks) {
            taskFreqMap.put(c, taskFreqMap.getOrDefault(c, 0) + 1);
        }

        // 2. Create a PQ having freq in descending order
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int taskFreq : taskFreqMap.values()) {
            queue.offer(taskFreq);
        }

        // 3. Create a CoolDown Map which puts polled task in cooldown till cooldown is over
        // K - currTime, V- FreqLeft
        Map<Integer, Integer> coolDownMap = new HashMap<>();
        int currTime = 0;

        // 4. CPU will start processing tasks
        while (!queue.isEmpty() || !coolDownMap.isEmpty()) {
            if (coolDownMap.containsKey(currTime - n - 1)) {
                queue.offer(coolDownMap.remove(currTime - n - 1));
            }
            if (!queue.isEmpty()) {
                int left = queue.poll() - 1;
                if (left != 0) { coolDownMap.put(currTime, left); }
            }
            currTime++;
        }
        return currTime;
    }

    public static void main(String[] args) {
        TaskScheduler obj = new TaskScheduler();
        System.out.println(obj.leastInterval(new char[] {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'D', 'E', 'F'}, 2));
        // A->B->C->A->B->C->A->B->D->A->E->F->A->Idle->Idle->A
        // 5  2  1  4  1  0  3  0  0  2  0  0  1              0
    }

}