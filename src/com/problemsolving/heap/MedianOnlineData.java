/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: MedianOnlineData.java, v 0.1 2020-03-11 08:56 paras.chawla Exp $$
 */
public class MedianOnlineData {

    PriorityQueue<Integer> lowerHalf;
    PriorityQueue<Integer> upperHalf;

    /**
     * initialize your val structure here.
     */
    public MedianOnlineData() {
        // lowerHalf is a Max Heap
        lowerHalf = new PriorityQueue(Collections.reverseOrder());

        // upperHalf is a Min Heap
        upperHalf = new PriorityQueue();
    }

    public void addNum(int num) {
        // if Inserted Element is lesser than peek of lowerHalf, then insert into LowerHalf
        if (lowerHalf.size() == 0 || num < lowerHalf.peek()) {
            lowerHalf.add(num);
        } else {
            upperHalf.add(num);
        }
        rebalance(lowerHalf, upperHalf);
        System.out.println(findMedian());
    }

    // 1. Rebalance is required only when size diff between two heaps exceed more than 2
    // 2. It is required to balance both heap and to find Median of online data at the earliest
    private void rebalance(PriorityQueue<Integer> lowerHalf, PriorityQueue<Integer> upperHalf) {
        PriorityQueue<Integer> biggerHeap = lowerHalf.size() > upperHalf.size() ? lowerHalf : upperHalf;
        PriorityQueue<Integer> smallerHeap = lowerHalf.size() > upperHalf.size() ? upperHalf : lowerHalf;
        if (biggerHeap.size() - smallerHeap.size() >= 2) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    // if both heaps are similar size then median is max of lowerHalf and min of UpperHalf else highest of biggerHeap
    public double findMedian() {
        if (lowerHalf.size() == upperHalf.size()) {
            return ((double) lowerHalf.peek() + (double) upperHalf.peek()) / 2;
        } else {
            PriorityQueue<Integer> biggerHeap = lowerHalf.size() > upperHalf.size() ? lowerHalf : upperHalf;
            return biggerHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianOnlineData obj = new MedianOnlineData();
        obj.addNum(5);
        obj.addNum(7);
        obj.addNum(1);
        obj.addNum(2);
        obj.addNum(6);
        obj.addNum(3);
        obj.addNum(4);
        obj.addNum(9);
    }
}