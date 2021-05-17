/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
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
        int[] array0 = {-10, -7, -5, -1};
        int[] array1 = {1, 2, 4, 5};
        int[] array2 = {-6, -3, -1, 2, 4, 5};
        System.out.println(Arrays.toString(sortedSquares(array0)));
        System.out.println(Arrays.toString(sortedSquares(array1)));
        System.out.println(Arrays.toString(sortedSquares(array2)));
        //System.out.println(Arrays.toString(sortByAbs(array2)));
        //System.out.println(Arrays.toString(sortedSquareUsingHeap(array2)));
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

    public static int[] sortedSquares(int[] nums) {

        int[] result = new int[nums.length];

        // Case 1 - all positive numbers
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[i] * nums[i];
            }
        }

        // Case 2 - all negative numbers
        else if (nums[nums.length - 1] < 0) {
            for (int i = 0, j = nums.length - 1; i < nums.length && j >= 0; i++, j--) {
                result[i] = nums[j] * nums[j];
            }
        }

        // case 3 - Include both positive and negative numbers
        else {
            int k = 0;
            // find pivot
            for (; k < nums.length; k++) {
                if (nums[k] >= 0) {
                    break;
                }
            }

            int left = k - 1;
            int right = k;
            int p = 0;

            while (right < nums.length && left >= 0) {
                if (nums[right] < Math.abs(nums[left])) {
                    result[p++] = nums[right] * nums[right];
                    right++;
                } else {
                    result[p++] = nums[left] * nums[left];
                    left--;
                }
            }

            // see if any index is left
            while (right < nums.length) {
                result[p++] = nums[right] * nums[right];
                right++;
            }

            while (left >= 0) {
                result[p++] = nums[left] * nums[left];
                left--;
            }
        }
        return result;
    }

    public int[] sortedSquares2(int[] A) {
        //result array
        int[] res = new int[A.length];
        //pointers for left and right
        int lo = 0; int hi = A.length - 1;
        //iterate from n to 0
        for (int i = A.length - 1; i >= 0; i--) {
            //check if abs left is less than or equal to abs right
            if (Math.abs(A[lo]) >= Math.abs(A[hi])) {
                //add left squared to result array
                res[i] = A[lo] * A[lo];
                //increment left pointer
                lo++;
            } else {
                //add right squared to result array
                res[i] = A[hi] * A[hi];
                //decrement right pointer
                hi--;
            }
        }
        //result
        return res;
    }
}