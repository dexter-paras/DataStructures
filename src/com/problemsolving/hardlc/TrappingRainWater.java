/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.hardlc;

/**
 * @author paras.chawla
 * @version $Id: TrappingRainWater.java, v 0.1 2020-10-19 14:08 paras.chawla Exp $$
 * https://www.youtube.com/watch?v=VZpJxINSvfs Appraoch 1   T(n^2)
 * https://www.youtube.com/watch?v=HmBbcDiJapY Approach 2   T(n) + SOUTH(n)
 * https://www.youtube.com/watch?v=XqTBrQYYUcc Approach 3   T(n) + SOUTH(1)
 */
public class TrappingRainWater {

    public int trap(int[] height) {

        int size = height.length;

        // base condition
        if (size < 3) {
            return 0;
        }

        int total_water = 0;
        int lmax[] = new int[size];
        int rmax[] = new int[size];

        // construct left max out of elevation map
        // non-decreasing or increasing array
        lmax[0] = height[0];
        for (int i = 1; i < size; i++) {
            lmax[i] = Math.max(height[i], lmax[i - 1]);
        }

        // construct right max out of elevation map
        // non-increasing or decreasing array
        rmax[size - 1] = height[size - 1];
        for (int j = size - 2; j >= 0; j--) {
            rmax[j] = Math.max(height[j], rmax[j + 1]);
        }

        for (int k = 0; k < size; k++) {
            total_water += (Math.min(lmax[k], rmax[k]) - height[k]);
        }
        return total_water;

    }
}