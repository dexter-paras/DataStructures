/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

/**
 * @author paras.chawla
 * @version $Id: MaxProductSubArray.java, v 0.1 2020-03-27 21:23 paras.chawla Exp $$ [-1,6,2,0,7,9] To find max product of contiguous
 * subArray 7,9 is continguous subArray with max product =63
 */
public class MaxProductSubArray {

    //[-1,6,2,0,7,9]
    private int maxProductSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int prevMaxProduct = nums[0];// -1
        int prevMinProduct = nums[0];// -1
        int currentMaxProduct = nums[0];
        int currentMinProduct = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {

            /* currentMax is Max of either take prevMax multiply with new number or prevMin multiply with new number
            or number itself(after 0 case)*/
            currentMaxProduct = Math.max(nums[i], Math.max(prevMaxProduct * nums[i], prevMinProduct * nums[i]));

            /* currentMin is Min of either take prevMax multiply with new number or prevMin multiply with new number
            or number itself(after 0 case)*/
            currentMinProduct = Math.min(nums[i], Math.min(prevMaxProduct * nums[i], prevMinProduct * nums[i]));

            // may be currentMaxProduct at index i becomes smaller than existing result(max product)
            result = Math.max(result, currentMaxProduct);

            prevMaxProduct = currentMaxProduct;
            prevMinProduct = currentMinProduct;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProductSubArray().maxProductSubArray(new int[]{-1,6,2,0,7,9}));
    }
}