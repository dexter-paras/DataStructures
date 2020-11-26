/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: KLargestElement.java, v 0.1 2020-03-01 00:38 paras.chawla Exp $$
 */

/*
If we want the k smallest items, we don't care for large items. So max heap, we can remove top elements
If we want the k largest items, we don't care for small items. So min heap, we can remove top elements
*/

public class KLargestElement {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue();
        for (int i : nums) {
            minQueue.add(i);
            if (minQueue.size() == k + 1) {
                minQueue.poll();
            }
        }
        return minQueue.peek();
    }

    public static void main(String[] args) {
        int array[] = {3, 2, 3, 1, 2, 3};
        System.out.println(findKthLargest(array, 4));
    }
}