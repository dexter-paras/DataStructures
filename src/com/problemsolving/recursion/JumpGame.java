/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: JumpGame.java, v 0.1 2020-04-26 11:30 paras.chawla Exp $$
 * Greedy Approach - https://www.youtube.com/watch?v=2HnlGToCdCc&feature=youtu.be
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        return jump(0, nums);
    }

    //           0,1,2,3,4
    //(i=0,nums={2,3,1,1,4});
    /*      jump(0,nums)
        /                         \
       j(1,n)                   j(2,n)
        /   \       \           /
      j(2,n)j(3,n)  j(4,n)     j(3,n)
                     BREAK      /
                              j(4,n)
                              BREAK
    */
    private boolean jump(int i, int[] nums) {

        int element = nums[i];

        if (i > nums.length) {
            return false;
        }

        // base conditions
        if (i == nums.length - 1) {
            return true;
        }
        if (element == 0 && i < nums.length - 1) {
            return false;
        }

        for (int j = 1; j <= element; j++) {
            return jump(i + j, nums);
        }
        return false;
    }

    //[2,3,1,1,4]
    public boolean canJumpBFS(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }

        //index,value
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, nums[0]});

        //(0,2)
        //2->(1,3),(2,1)
        //3->(2,1),(3,1),(4,4)
        while (!queue.isEmpty()) {

            int curr[] = queue.poll();
            int currIdx = curr[0];
            int element = curr[1];

            // base condition
            if (currIdx == nums.length - 1) {
                return true;
            }

            for (int i = 1; i <= element && currIdx + i < nums.length; i++) {
                queue.add(new int[] {currIdx + i, nums[currIdx + i]});
            }
        }
        return false;
    }

    // 0,2
    // 1,1
    public static void main(String[] args) {
        System.out.println(new JumpGame().canJumpBFS(new int[] {2, 0, 0}));
        System.out.println(new JumpGame().canJumpBFS(new int[] {3, 2, 1, 0, 4}));

        System.out.println(new JumpGame().canJumpBFS(new int[] {2, 0}));
        System.out.println(new JumpGame().canJumpBFS(new int[] {2, 3, 1, 1, 4}));
        System.out.println(new JumpGame().canJumpBFS(new int[] {3, 2, 1, 0, 4}));
    }
}