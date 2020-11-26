/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: FindSmallestGFG.java, v 0.1 2019-09-25 13:08 paras.chawla Exp $$
 * Find the smallest positive integer value that cannot be represented as sum of any subset of a given array.
 * https://medium.com/dexters-lab/eli5-find-the-smallest-positive-integer-value-that-cannot-be-represented-as-sum-of-any-subset-of-f8ea2488184b
 */

    /*
    res =1 ;  arr[] =[1,3,4]
    i    arr[i]     res     res
    0    1       <= 1       2
    1    1       <= 2       3
    2    3       <= 3       6
    3    4       <= 6       10
    */

public class FindSmallestGFG {

    int findSmallest(int arr[], int n) {
        int res = 1; // Initialize result

        // Traverse the array and increment 'res' if arr[i] is
        // smaller than or equal to 'res'.
        for (int i = 0; i < n && arr[i] <= res; i++)
            res = res + arr[i];

        return res;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4};
        System.out.println(new FindSmallestGFG().findSmallest(arr, arr.length));
    }
}