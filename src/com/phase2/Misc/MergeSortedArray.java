/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.phase2.Misc;

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
        if (p1 < m)
            System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        System.out.println(nums1Copy);
    }

    public static void main(String[] args) {
        new MergeSortedArray().merge(new int[] {1, 3, 7, 9, 11, 0, 0}, 7, new int[] {2, 5}, 2);
    }
}