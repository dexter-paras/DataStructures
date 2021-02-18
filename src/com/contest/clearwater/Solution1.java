/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.clearwater;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: Solution1.java, v 0.1 2021-01-27 7:35 PM paras.chawla Exp $$
 */
public class Solution1 {

    public int sumAndCost(List<Integer> num) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(num);
        int sum = priorityQueue.poll();
        int cost = 0;
        while (!priorityQueue.isEmpty()) {
            int currentElement = priorityQueue.poll();
            if (currentElement < sum) {
                priorityQueue.add(sum);
                sum = currentElement;
            } else {
                sum += currentElement;
                cost += sum;
                continue;
            }

            sum += priorityQueue.poll();
            cost += sum;
        }

        return cost;
    }

    public static void main(String[] args) {
        Solution1 obj = new Solution1();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(8);
        System.out.println(obj.sumAndCost(list));
    }
}