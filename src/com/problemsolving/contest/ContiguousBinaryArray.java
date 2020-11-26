/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: ContiguousBinaryArray.java, v 0.1 2020-04-14 12:08 paras.chawla Exp $$
 */
public class ContiguousBinaryArray {

    /*input = {0,1,0,1,0,0}
     output = {1,0,0,1}*/

    public int findMaxLength(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }

                if (zeroCount == oneCount) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    public int findMaxLength2(int[] nums) {

        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        System.out.println(maxlen);
        return maxlen;
    }

    public int findMaxLength3(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }


    public static void main(String[] args) {
        new ContiguousBinaryArray().findMaxLength3(new int[]{0,0,1,0,0,0,1,1});
    }

}