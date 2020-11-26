/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: TwoSumLessthanK.java, v 0.1 2020-11-22 18:16 paras.chawla Exp $$
 */
public class TwoSumLessthanK {

    // input A = [34,23,1,24,75,33,54,8], K = 60
    public static int twoSumLessThanK(int[] A, int K) {

        // Sort Array
        // A[]= [1,8,23,24,33,34,54,75]
        Arrays.sort(A);

        int maxSum = -1;
        if (A.length <= 1) {
            return maxSum;
        }
        for (int i = 0; i < A.length; i++) {
            int firstNumber = A[i];
            if (firstNumber >= K) { continue; }
            if (K - firstNumber < A[0]) { break; }
            int secondNumberJustLessorThanK = findNumber(K - firstNumber, A, 0, A.length - 1);

            // if secondNumber found
            if (secondNumberJustLessorThanK != -1) {
                maxSum = Math.max(maxSum, firstNumber + secondNumberJustLessorThanK);
            }
        }
        return maxSum;
    }

    // find number just lessor than num
    // num =59
    private static int findNumber(int target, int[] nums, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + ((high - low) / 2);

        if (nums[mid] == target) {
            return nums[mid - 1];
        } else if (target > nums[mid]) {
            if (mid < nums.length && target > nums[mid + 1]) {
                low = mid + 1;
            } else {
                return nums[mid];
            }
        } else {
            if (mid - 1 >= 0 && target < nums[mid - 1]) {
                high = mid - 1;
            } else {
                return nums[mid - 1];
            }
        }
        return findNumber(target, nums, low, high);
    }

    // Approach - 2 Pointer, Moving toward Target, Work only on Sorted Array
    public static int twoSumLessThanK2(int[] A, int K) {

        // Sort Array , target = 60
        // A[]= [1,8,23,24,33,34,54,75]
        //                 ^^
        //                 lh
        Arrays.sort(A);
        int maxSum = -1;
        int low = 0, high = A.length - 1;

        while (low < high) {
            // moving closer to target, hence moving low pointer
            // satisfy condition , possible candidate
            if (A[low] + A[high] < K) {
                maxSum= Math.max(maxSum,A[low]+ A[high]);
                low++;
            } else {
                high--;
            }
        }
        return maxSum;
    }

    // Approach 3- Counting Sort
    public static int twoSumLessThanK3(int[] A, int K) {
        int S = -1;
        int[] count = new int[1001];
        for (int i: A)
            ++count[i];
        int lo = 1, hi = 1000;
        while (lo <= hi) {
            if (lo + hi >= K || count[hi] == 0)
                --hi;
            else {
                if (count[lo] > (lo < hi ? 0 : 1))
                    S = Math.max(S, lo + hi);
                ++lo;
            }
        }
        return S;
    }

    public static void main(String[] args) {
        System.out.println(twoSumLessThanK3(new int[] {34, 23, 1, 24, 75, 33, 54, 8}, 60));
        //System.out.println(twoSumLessThanK(new int[] {10, 30, 20}, 15));
        /*System.out.println(twoSumLessThanK(
                new int[] {632, 921, 621, 18, 447, 566, 161, 960, 596, 109, 215, 927, 805, 611, 742, 329, 225, 866, 641, 231, 358, 312, 878,
                        251, 924, 167, 840, 695, 712, 283, 982, 884, 2, 695, 17, 949, 167, 320, 242, 65, 167, 608, 129, 652, 720, 604, 927,
                        950, 401, 399}, 600));
                        */
    }

}