/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: RotateArray.java, v 0.1 2020-02-08 13:12 paras.chawla Exp $$
 */
public class RotateArray {

    public static void main(String[] args) {
        int array[] = {1, 2, 3, 4, 5};
        int k = 2;
        int rotateArray[] = rotateArray(array, k);
    }

    private static int[] rotateArray(int[] array, int k) {
        int temp = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < array.length; j++) {
                temp = array[array.length - i];
            }
        }
        return null;
    }
}