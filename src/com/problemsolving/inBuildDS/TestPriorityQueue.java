/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: TestPriorityQueue.java, v 0.1 2019-09-24 22:10 paras.chawla Exp $$
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(15);
        priorityQueue.add(5);
        priorityQueue.add(1);
        System.out.println(priorityQueue);
        priorityQueue.remove();
        priorityQueue.remove();
        priorityQueue.remove();
    }
}