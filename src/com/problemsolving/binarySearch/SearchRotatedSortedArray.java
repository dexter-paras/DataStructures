/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: SearchRotatedSortedArray.java, v 0.1 2020-04-19 21:41 paras.chawla Exp $$
 */
public class SearchRotatedSortedArray {

    // array =[7,8,1,2,3,4,5,6]
    //           ^
    // if target =8 and 8> 7(nums[0]) then target is on leftSubarray
    // else if target =5 and 5<7 , then target is on rightSubArray
    public int search(int[] nums, int target) {
        int pivot = findPivot(nums, 0, nums.length - 1);
        if (pivot == -1) {
            return -1;
        }
        if (target >= nums[0]) {
            return binarySearch(nums, 0, pivot, target);
        } else {
            return binarySearch(nums, pivot + 1, nums.length - 1, target);
        }
    }

    private int binarySearch(int[] nums, int low, int high, int target) {

        if (low > high) {
            return -1;
        }

        int mid = low + ((high - low) / 2);

        if (nums[mid] == target) {
            return mid;
        } else if (target > nums[mid]) {
            return binarySearch(nums, mid + 1, high, target);
        }
        return binarySearch(nums, low, mid - 1, target);
    }

    //array =[4,5,6,1,2,3]
    //            ^
    private int findPivot(int[] nums, int low, int high) {

        // Base condition when element not found
        if (low > high) {
            return -1;
        }

        // if array=[1],target = 1
        if (low == high) {
            return low;
        }

        int mid = low + ((high - low) / 2);

        // case 1. pivot where next element is smaller meaning new subArray is going to start
        // [4,5,6,1,2,3]
        // l    m     h
        if (mid < high && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        // case 2. previous element greater than current element, which means pivot is previous element
        //  [4,5,6,1,2,3] 1 as mid
        //   l     ^   h
        else if (mid > low && nums[mid - 1] > nums[mid]) {
            return mid - 1;
        }
        // case 3. [4,5,6,1,2,3]
        //          l       ^ h
        else if (nums[low] > nums[mid]) {
            return findPivot(nums, low, mid - 1);
        }
        // next element is still greater than current element, no hope , keep checking forward
        // case 4. [4,5,6,1,2,3]
        //          l ^ h
        return findPivot(nums, mid + 1, high);
    }

    public static void main(String[] args) {
        System.out.println(new SearchRotatedSortedArray().search(new int[] {1, 2}, 1));
        //System.out.println(new SearchRotatedSortedArray().search(new int[] {7, 8, 9, 11, 12, 13, 17, 21, 23, 24, 26, 3, 4, 5, 6}, 5));
        //System.out.println(new SearchRotatedSortedArray().search(new int[] {7, 8, 9, 11, 12, 13, 17, 21, 23, 24, 26}, 5));
        //System.out.println(new SearchRotatedSortedArray().search(new int[] {7, 8, 9, 11, 12, 13, 17, 21, 23, 24, 26,3,6}, 5));
    }
}