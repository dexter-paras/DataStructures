/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.vonage;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: LargestPossibleVerticalPath.java, v 0.1 2021-01-17 9:39 PM paras.chawla Exp $$
 */
public class LargestPossibleVerticalPath {

    public static int solution(int[] X, int[] Y) {

        int maxX = 0;
        int maxY = 0;

        Arrays.sort(X);
        System.out.println("Sort X "+Arrays.toString(X));
        Arrays.sort(Y);
        System.out.println("Sort Y "+Arrays.toString(Y));

        for (int i = 1; i < X.length; i++) {
            int diffX = X[i] - X[i - 1];
            maxX = Math.max(maxX, diffX);
        }
        System.out.println("Max X "+ maxX);

        for (int i = 1; i < Y.length; i++) {
            int diffY = Y[i] - Y[i - 1];
            maxY = Math.max(maxY, diffY);
        }

        System.out.println("Max Y "+ maxY);

        return maxX >= maxY ? maxX : maxY;
    }

    public static void main(String[] args) {
        int[] M = {1, 8, 7, 3, 4, 1, 8};
        int[] N = {6, 4, 15, 8, 5, 1, 7};
        System.out.println(solution(M, N));
        System.out.println();

        int[] M1 = {5, 5, 5, 7, 7, 7};
        int[] N1 = {3, 4, 5, 1, 3, 4};
        System.out.println(solution(M1, N1));
        System.out.println();

        int[] M2 = {6, 10, 1, 4, 4};
        int[] N2 = {2, 5, 3, 1, 6};
        System.out.println(solution(M2, N2));
        System.out.println();

        int[] M3 = {4, 1, 5, 4};
        int[] N3 = {4, 5, 1, 3};
        System.out.println(solution(M3, N3));
        System.out.println();
    }

}