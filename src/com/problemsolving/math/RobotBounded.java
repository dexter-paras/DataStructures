/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.math;

/**
 * @author paras.chawla
 * @version $Id: RobotBounded.java, v 0.1 2021-03-11 3:21 PM paras.chawla Exp $$
 */

// APproach - https://leetcode.com/problems/robot-bounded-in-circle/discuss/850536/Clear-Explanation-with-images-For-Beginners-like-me
// https://leetcode.com/problems/robot-bounded-in-circle/discuss/290856/JavaC%2B%2BPython-Let-Chopper-Help-Explain
public class RobotBounded {

    /*
    (x,y) is the location of chopper.
    d[i] is the direction he is facing.
    i = (i + 1) % 4 will turn right
    i = (i + 3) % 4 will turn left
    */

    public boolean isRobotBounded(String instructions) {
        int[] currPos = new int[] {0, 0};
        int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int d = 0; // 0: up (north) , 1: right, 2: down, 3: left

        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            if (ch == 'G') {
                currPos[0] += dirs[d][0];
                currPos[1] += dirs[d][1];
            } else if (ch == 'L') {
                d = (d + 3) % 4;
            } else {
                d = (d + 1) % 4;
            }
        }
        return currPos[0] == 0 && currPos[1] == 0 || d != 0;
    }
}