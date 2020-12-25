/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.sorting;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: Sort0sAnd1s.java, v 0.1 2020-12-01 10:07 paras.chawla Exp $$ Approach 2- Count 0s and 1s Approach 3-
 */
public class Sort0sAnd1s {

    // Approach 1- Using Two Pointers
    private static void sortBinaryArray(int[] inputArray) {

        int low = 0, high = inputArray.length - 1;

        // Intent is to keep 0s to the left and 1s to the right
        while (low < high) {

            if (inputArray[low] == 1 && inputArray[high] == 0) {
                swap(low, high, inputArray);
                low++;
                high--;
            } else if (inputArray[low] == 0 && inputArray[high] == 0) {
                low++;
            } else if (inputArray[low] == 1 && inputArray[high] == 1) {
                high--;
            }
        }
        System.out.println(Arrays.toString(inputArray));
    }

    private static void swap(int low, int high, int[] inputArray) {

        int temp = inputArray[low];
        inputArray[low] = inputArray[high];
        inputArray[high] = temp;
    }

    public static void main(String[] args) {
        sortBinaryArray(new int[] {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0});
    }

}