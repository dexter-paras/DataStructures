/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.matrix;

/**
 * @author paras.chawla
 * @version $Id: Matrix01Max1Subsequence.java, v 0.1 2021-03-25 4:41 PM paras.chawla Exp $$
 */

public class Matrix01Max1Subsequence {

    // To Find maximum Substring of 1 when it is allowed to change k 0s to 1s
    // 1 0 0 1 1 0 1 0 0 1 1 1 0 0 1 1 1
    private int max1SubString(int[] array, int k) {

        int i = 0, j = 0, length = array.length;

        int maxLength = 0;

        while (i < length) {

            while (j < length && (array[j] == 1 || k > 0)) {

                if(array[j]==0){
                    k--;
                }
                j++;
            }

            // window not following property
            maxLength = Math.max(maxLength, j - i - 1);

            if(array[i]==0){

            }

        }
        return maxLength;
    }

    public static void main(String[] args) {
        Matrix01Max1Subsequence obj = new Matrix01Max1Subsequence();

        System.out.println(obj.max1SubString(new int[] {1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1}, 3));

    }

}