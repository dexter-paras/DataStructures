/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: KSmallestElementSortedMatrix.java, v 0.1 2020-08-19 21:54 paras.chawla Exp $$
 * <p>
 * matrix =
 * [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 *
 * k = 8,
 * return 13
 * Sorted 1,5,9,10,11,12,13,13,15
 * Index  0 1 2 3 4 5 6  7  8  9
 *
 */
public class KSmallestElementSortedMatrix {

    // To find kth smallest Element, use MaxHeap , doesn't care about max elements
    public int kthSmallest(int[][] matrix, int k) {

        // Create Max Heap and remove max elements since we're concerned only of smallest element
        PriorityQueue<Integer> maxQueue = new PriorityQueue(Collections.reverseOrder());

        // Intuition is to left with heap with k smallest elements with kth element at top
        // here we're looping full matrix even if lets say after 5th row we know all rows have higher element
        // whose addition is useless
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxQueue.add(matrix[i][j]);
                // As soon as heap size becomes more than k , remove top element
                if (maxQueue.size() == k +1) {
                    // removing max elements because max elements aren't required and concern is only on min elements
                    maxQueue.poll();
                }
            }
        }
        return maxQueue.peek();
    }

    // Use Min-Heap - Approach 2 - Not traversing full matrix
    public int kthSmallestSol2(int[][] matrix, int k) {

        // Initialize min-heap i.e. PQ of size min(NORTH,K) where NORTH - number of sorted rows, K - find kth smallest element
        // Comparator is min-Heap based on matrix value
        PriorityQueue<HeapData> pq = new PriorityQueue<>(Math.min(matrix.length, k), (hd1, hd2) -> hd1.value - hd2.value);

        // Add matrix elements till min-heap size and put heapData
        for (int r = 0; r < Math.min(matrix.length, k); r++) {
            pq.offer(new HeapData(r, 0, matrix[r][0]));
        }

        // iterate till k(smallest element) -> removing 1,2,3,..k-1 smallest element and return kth element
        HeapData element = pq.peek();
        while (k-- > 0) {
            element = pq.poll();
            int r = element.row;
            int c = element.col;

            // If we have any new elements in the current row, add them
            if (c < matrix[0].length-1) {
                pq.offer(new HeapData(r, c + 1, matrix[r][c + 1]));
            }
        }
        return element.value;
    }

    // We need a class which is having row,col so as to decide where to move next
    class HeapData {
        int row;
        int col;
        int value;

        public HeapData(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println(new KSmallestElementSortedMatrix().kthSmallestSol2(new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
    }
}