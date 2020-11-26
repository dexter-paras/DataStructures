/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: FirstBadVersion.java, v 0.1 2020-05-02 11:07 paras.chawla Exp $$
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad
 * version. You should minimize the number of calls to the API.
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * <p>
 * call isBadVersion(3) -> false call isBadVersion(5) -> true call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {

        return helper(1, n);
    }

    private int helper(int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return 1;
        }
        int mid = low + (high - low) / 2;
        if (mid > low && isBadVersion(mid) == true && isBadVersion(mid - 1) == false) {
            return mid;
        } else if (mid < high && isBadVersion(mid) == false && isBadVersion(mid + 1) == true) {
            return mid + 1;
        } else if (mid > low && isBadVersion(mid) == true && isBadVersion(mid - 1) == true) {
            return helper(low, mid - 1);
        }
        return helper(mid + 1, high);
    }

    /*Approach 2 - Better solution, less code*/
    public int firstBadVersionSol2(int n) {
        int low = 1;
        int high = n;
        int mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Dummy Method
    boolean isBadVersion(int version) {
        if (version >= 4) { return true; } else { return false; }
    }

    public static void main(String[] args) {
        System.out.println(new FirstBadVersion().firstBadVersion(5));
    }
}