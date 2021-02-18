/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: CountBalanceElements.java, v 0.1 2021-02-03 10:59 PM paras.chawla Exp $$
 * Intuition
 * when you remove a number, the odd elements to the right of it become even elements and vice-versa.
 *
 * We will pre compute the odd and even sum to the left of ith element and store it.
 * Similarly we will compute the odd and even sum to the right of ith element and store it.
 * Finally all we need to check the number of times
 *
 * leftOdd[i] + rightEven[i] == leftEven[i] + rightOdd[i]
 * https://leetcode.com/discuss/interview-question/531404/balancing-elements
 * https://codereview.stackexchange.com/questions/242289/counting-balanced-numbers-in-a-given-array
 */
public class CountBalanceElements {

    public int balance(int[] nums){
        int n = nums.length;
        int[] leftOdd = new int[n], leftEven = new int[n];
        int[] rightOdd = new int[n], rightEven = new int[n];
        int odd = 0, even = 0;
        for(int i=0;i<n;i++){
            leftOdd[i] = odd;
            leftEven[i] = even;
            if(i%2==0) even += nums[i];
            else odd += nums[i];
        }
        odd = 0;
        even = 0;
        for(int i=n-1;i>=0;i--){
            rightOdd[i] = odd;
            rightEven[i] = even;
            if(i%2==0) even += nums[i];
            else odd += nums[i];
        }
        int count = 0;
        for(int i=0;i<n;i++){
            if(leftOdd[i] + rightEven[i] == leftEven[i] + rightOdd[i]){
                System.out.println("id:" + i);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountBalanceElements obj = new CountBalanceElements();
        obj.balance(new int[] {5, 5, 2, 5, 8});
    }
}