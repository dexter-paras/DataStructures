/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.sorting;

/**
 * @author paras.chawla
 * @version $Id: DutchFlag.java, v 0.1 2020-12-01 11:16 paras.chawla Exp $$ https://www.youtube.com/watch?v=sEQk8xgjx64
 * Taking low, high as 2 pointers and mid as pointer between 0s and 1s
 * Intent is to have 0s to left and 2s to right
 * when a[mid]==0 , swap(mid,low) so as to fix 0 to the left and move low++
 * when a[mid]==2 , swap(mid,high) so as to fix 2 to the right and move high--
 */
public class DutchFlag {

    public void sortColors(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }

        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {

            if (nums[mid] == 0) {
                swap(mid, low, nums);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(mid, high, nums);
                high--;
            }
        }
    }

    private void swap(int low, int high, int[] inputArray) {

        int temp = inputArray[low];
        inputArray[low] = inputArray[high];
        inputArray[high] = temp;
    }
}