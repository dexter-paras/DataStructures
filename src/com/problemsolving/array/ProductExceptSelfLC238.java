/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: ProductExceptSelfLC238.java, v 0.1 2019-10-01 07:56 paras.chawla Exp $$
 * Product of Array except Self
 * arr[]    = [3, 4, 5 ,2]
 * Output[] = [40,30,24,60]
 */

public class ProductExceptSelfLC238 {

    public int[] productExceptSelf(int[] nums) {

        /* Step 1 - Initialize PL[] and PR[] */
        int[] productLeft = new int[nums.length];
        int[] productRight = new int[nums.length];

        /* Initializing product of left and right of element as 1*/
        productLeft[0] = 1;
        productRight[nums.length - 1] = 1;

        /* Creating Product of Left elements array & product of Right element*/
        for (int i = 1, j = nums.length - 2; i < nums.length && j >= 0; i++, j--) {
            productLeft[i] = productLeft[i - 1] * nums[i - 1];
            productRight[j] = productRight[j + 1] * nums[j + 1];
        }

        /* Multiplying productLeft[i]* productRight[i]*/
        for (int i = 0; i < nums.length; i++) {
            productLeft[i] = productLeft[i] * productRight[i];
        }

        return productLeft;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4};
        int productExceptSelf[] = new ProductExceptSelfLC238().productExceptSelf(array);
        System.out.println(Arrays.toString(productExceptSelf));
    }
}