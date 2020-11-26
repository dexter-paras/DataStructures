/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: SquareSorted.java, v 0.1 2020-02-12 16:40 paras.chawla Exp $$
 */
public class SquareSorted {

    public static void main(String[] args) {
        int[] array = {-6, -3, -1, 2, 4, 5};
        System.out.println(Arrays.toString(sortByAbs(array)));
        System.out.println(Arrays.toString(sortedSquareUsingHeap(array)));
    }

    // result = [-1,2,-3,4,5,-6]
    //O(n^2) - poor complexity
    private static int[] sortByAbs(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (Math.abs(A[i]) > Math.abs(A[j])) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
            A[i] = A[i] * A[i];
        }
        A[A.length - 1] = A[A.length - 1] * A[A.length - 1];
        return A;
    }

    private static int[] sortedSquareUsingHeap(int[] A) {
        PriorityQueue queue = new PriorityQueue();
        for (int i : A) {
            queue.add(i * i);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            A[i++] = (int) queue.poll();
        }
        return A;
    }
}