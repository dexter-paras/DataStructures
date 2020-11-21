/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.gs;

/**
 * @author paras.chawla
 * @version $Id: FindMinMax.java, v 0.1 2020-11-17 13:41 paras.chawla Exp $$
 */
public class FindMinMax {

    private static float[] findRange(int[] array, float num) {

        // No Range
        if (array.length == 1) {
            return null;
        }

        float max = Float.MAX_VALUE, min = Float.MIN_VALUE;

        // find first greatest element of num
        for (int i = 0; i < array.length; i++) {
            if ((float) array[i] > num) {
                max = array[i];
                break;
            }
        }

        // find first smallest element of num
        for (int i = 0; i < array.length; i++) {
            if ((float) array[i] < num) {
                min = array[i];
                break;
            }
        }

        if (max == Float.MAX_VALUE || min == Float.MIN_VALUE) {
            return null;
        }

        for (int i = 0; i < array.length; i++) {
            if ((float) array[i] > num) {
                max = max - num < array[i] - num ? max : array[i];
            } else {
                min = num - min > num - array[i] ? array[i] : min;
            }
        }
        return new float[] {min, max};

    }

    public static void main(String[] args) {
        float[] result = findRange(new int[] {9, 4, 7, 1, -2, 5, -3}, 2.3f);
        float[] result1 = findRange(new int[] {9, -4, 7, -1, 2, 5}, 2.3f);
        float[] result2 = findRange(new int[] {-9, 4, 7, 1, 2}, 2.3f);
        float[] result3 = findRange(new int[] {9, -4, 7, 1}, 2.3f);
        float[] result4 = findRange(new int[] {9, 4, -7}, 2.3f);
    }

}