/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

/**
 * @author paras.chawla
 * @version $Id: Fibonacci.java, v 0.1 2020-03-20 23:49 paras.chawla Exp $$
 */
// 1,1,2,3,5,8,13 ...
public class Fibonacci {

    int result;

    private int fibonacciRecursive(int n) {
        if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
        return result;
    }

    // With Memoization [1,1,2,3,5,8]
    // if n =6  memo=[0,0,0,0]
    private int fibonacciMemoization(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        if (n == 1 || n == 2) {
            result = 1;
        } else {
            result = fibonacciMemoization(n - 1, memo) + fibonacciMemoization(n - 2, memo);
        }
        memo[n] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibonacciRecursive(45));
        int array[] = new int[101];
        System.out.println((double) new Fibonacci().fibonacciMemoization(100, array));
    }
}