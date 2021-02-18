/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.salesforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: MedianElementFinder.java, v 0.1 2021-01-28 7:34 PM paras.chawla Exp $$
 */
public class MedianElementFinder {

    // max Heap
    private static PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
    // min Heap
    private static PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

    private static void addNum(int num) {
        if (lowerHalf.size() == 0 || num < lowerHalf.peek()) {
            lowerHalf.add(num);
        } else {
            upperHalf.add(num);
        }
        reBalance(lowerHalf, upperHalf);
    }

    private static void reBalance(PriorityQueue<Integer> lowerHalf, PriorityQueue<Integer> upperHalf) {
        PriorityQueue<Integer> biggerHeap = lowerHalf.size() > upperHalf.size() ? lowerHalf : upperHalf;
        PriorityQueue<Integer> smallerHeap = lowerHalf.size() > upperHalf.size() ? upperHalf : lowerHalf;
        if (biggerHeap.size() - smallerHeap.size() >= 2) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    public static int findMedian() {
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) / 2;
        } else {
            PriorityQueue<Integer> biggerHeap = lowerHalf.size() > upperHalf.size() ? lowerHalf : upperHalf;
            return biggerHeap.peek();
        }
    }

    public static List<Integer> medians(List<Integer> arr) {
        // Write your code here
        List<Integer> result = new ArrayList<>();

        for (int num : arr) {
            addNum(num);
            result.add(findMedian());
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=44;i++)
            list.add(1);
        for(int i=45;i<=68;i++)
            list.add(2);
        List<Integer> result = MedianElementFinder.medians(list);
        System.out.println(result);
        System.out.println(result.size());
    }
}