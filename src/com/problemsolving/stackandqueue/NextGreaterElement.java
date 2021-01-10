/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.stackandqueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: NextGreaterElement.java, v 0.1 2020-11-21 21:00 paras.chawla Exp $$
 * https://leetcode.com/problems/next-greater-element-ii/discuss/98262/Typical-ways-to-solve-circular-array-problems.-Java-solution.
 */
public class NextGreaterElement {

    // Variant 1 - No Circular array
    public static int[] nextGreaterElements(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack();

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int idx = 0; idx < nums.length; idx++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                result[stack.pop()] = nums[idx];
            }
            stack.push(idx);
        }
        return result;
    }

    // Variant 2 - Circular array
    //array[] = [60,40,20,90,10,50, 60,40,20,90,10,50]

    public static int[] nextGreaterElementsCircularArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        Stack<Integer> stack = new Stack();

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                result[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return result;
    }

    public static int[] nextGreaterElements2(int[] nums) {
        int[] res = new int[nums.length];
        int[] doublenums = new int[nums.length * 2];
        System.arraycopy(nums, 0, doublenums, 0, nums.length);
        System.arraycopy(nums, 0, doublenums, nums.length, nums.length);
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = i + 1; j < doublenums.length; j++) {
                if (doublenums[j] > doublenums[i]) {
                    res[i] = doublenums[j];
                    break;
                }
            }
        }
        return res;
    }

    //nums[] = 60, 40, 20, 90, 10, 50
    //stack = 5 , 4,  3 , 2,  1 , 0
    public static int[] nextGreaterElements3(int[] nums) {
        int[] results = new int[nums.length];

        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(i);
            results[i] = -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int index = stack.peek();
                if (nums[index] > nums[i]) {
                    results[i] = nums[index];
                    break;
                } else {
                    stack.pop();
                }
            }
            stack.push(i);
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements3(new int[] {60, 40, 20, 90, 10, 50})));
        //output 100, 140, -1, 100, 100, 60
    }
}