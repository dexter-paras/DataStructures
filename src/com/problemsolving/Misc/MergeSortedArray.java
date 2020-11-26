/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

/**
 * @author paras.chawla
 * @version $Id: MergeSortedArray.java, v 0.1 2020-07-25 11:43 paras.chawla Exp $$
 */
public class MergeSortedArray {

    // Approach 1 - Use another DataStructure
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //int[] nums1Copy = Arrays.copyOf(nums1, m);

        // Make a copy of nums1.
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            nums1[p++] = nums1Copy[p1] < nums2[p2] ? nums1Copy[p1++] : nums2[p2++];
        }

        // if there are still elements to add
        if (p1 < m) { System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 - p2); }
        if (p2 < n) { System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2); }
        System.out.println(nums1Copy);
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {

        int p1 = 0;
        int p2 = 0;

        int[] temp = new int[m + n];
        int idx = 0;

        while (p1 < m && p2 < n) {

            if (nums1[p1] < nums2[p2]) {
                temp[idx++] = nums1[p1];
                p1++;
            } else {
                temp[idx++] = nums2[p2];
                p2++;
            }
        }

        // concatenate rest of elements
        while (idx < temp.length && p1 < m) {
            temp[idx++] = nums1[p1++];
        }
        while (idx < temp.length && p2 < n) {
            temp[idx++] = nums2[p2++];
        }

        System.arraycopy(temp, 0, nums1, 0, temp.length);

    }

    // Approach 3- O(1) Space Compexity <- Clever solution
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
        // compare two elements from nums1 and nums2
        // and add the largest one in nums1
        { nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--]; }

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        //new MergeSortedArray().merge2(new int[] {1, 3, 7, 9, 11, 0, 0}, 7, new int[] {2, 5}, 2);
        new MergeSortedArray().merge2(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
    }
}