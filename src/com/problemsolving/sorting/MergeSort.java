/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.sorting;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: MergeSort.java, v 0.1 2020-10-14 20:35 paras.chawla Exp $$
 * <p>
 * 2 subroutines 1. Split till base case is left with 1 element 2. merge left and right sorted Array
 * https://www.geeksforgeeks.org/merge-sort/
 * Amazing InMemoryDataStructure
 * https://leetcode.com/problems/sort-an-array/discuss/276463/Java-QuickSort-%2B-SelectionSort-%2B-MergeSort-summary
    It is characterized by 2 major subroutines:
    split(T[] arr): The split subroutine splits an array in half and recursively sorts the left and right halves. They are later merged in the merge subroutine
    merge(T[] left, T[] right): The merge subroutine merges two sorted sequences in O(len(left) + len(right)) time (overarchingly O(n) time with respect to the original input size)
 */
public class MergeSort {

    private int[] sortArray(int[] array) {
        split(array, 0, array.length - 1);
        return array;
    }

    // 1. Splitting into n/2 in every recursion
    private void split(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        split(array, left, mid);
        split(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    /* 2. merging 2 base unsorted array into sorted array
     if L[] size=3 , R[] size=3 , max number of comparisons is 3+3-1=5
     Merges two subarrays of arrat[]
     First subarray is arr[left..mid]
     Second subarray is arr[mid+1..right]
     */
    private void merge(int[] array, int left, int mid, int right) {

        // 1. Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 2. Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // 3. Fill both Left & Right temp arrays
        for (int i = 0; i < n1; ++i) { L[i] = array[left + i]; }

        for (int j = 0; j < n2; ++j) { R[j] = array[mid + 1 + j]; }

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int array[] = new int[] {19, 8, -13, 2, -5, 4, 100, 30};
        obj.sortArray(array);
        Arrays.toString(array);
    }

}