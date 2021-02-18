/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.expedia;

/**
 * @author paras.chawla
 * @version $Id: Solution2.java, v 0.1 2021-02-08 1:17 PM paras.chawla Exp $$
 */
public class Solution2 {

    public static void main(String[] args) {

    }

    /*
    [1] = 1
    [1 7] = 4
    [1 7 4] = 4
    [1 7 4 3]

    [1 7 4 10] =5.5

    maxHeap             minHeap
    1                   7           1+7/2 =4

    4                   7           if(minheap.size() == maxHeap.size()) = minheap.peek()+maxheap.peek/2
   /                                else maxheap.peek();
  1

     4                   7
    / \
   1  3

   3                     4
   /                    /
  1                     7

    // 1. if both heaps are empty and if both heap size are equal , directly add to maxHeap
    // 2. if both heap size are not equal, if(addNum > minHeap.peek() - minHeap  ~ sorted property will remain there
    // 3. if both heap size are not equal, if(addNum < maxHeap.peek() - maxHeap ~ sorted property will remain there
    // 4. if diff btw minHeap.size - maxHeap.size >=2 reBalance both heaps -> transfering element from larger heap size to smaller heap size
    */


}