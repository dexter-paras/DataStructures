/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.hardlc;

/**
 * @author paras.chawla
 * @version $Id: ContainerMostWater.java, v 0.1 2020-12-26 8:01 PM paras.chawla Exp $$
 * https://leetcode.com/problems/container-with-most-water/discuss/6100/Simple-and-clear-proofexplanation
 */
public class ContainerMostWater {

    // 1   8  6  2  5  4  8  3  7
    // Intuition is to find max X and max Y to current coordinate so as to maximize Area
    // Approach 1- O(n^2)
    public int maxArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {

            int x = i + 1, y = height[i];
            int area;
            // for (int j = i+1; j < height.length; j++) <- Better
            for (int j = 0; j < height.length && i != j; j++) {
                int x1 = j + 1, y1 = height[j];
                area = Math.abs(x1 - x) * Math.abs(Math.min(y, y1));
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    /* Approach 2 - Intuition
        Use two pointers, 1 at start and another at end.
        Move pointer with smaller height so as to have better chance of increase max Area

    Idea / Proof:

    The widest container (using first and last line) is a good candidate, because of its width. Its water level is the height of the
    smaller one of first and last line.
    All other containers are less wide and thus would need a higher water level in order to hold more water.
    The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.

    Video solution - proof by contradiction - Amazingly explained
    https://leetcode.com/problems/container-with-most-water/solution/

*/
    public int maxAreaSol2(int[] heights) {
        int maxArea = 0;

        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            // because the length is always decreasing
            // we only need to consider a pair if its height
            // is greater than any pair's height we've used so far

            maxArea = Math.max(maxArea, Math.min(heights[left], heights[right]) * (right - left));

            // move pointers
            if (heights[left] == heights[right]) {
                left++;
                right--;
            } else if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerMostWater obj = new ContainerMostWater();
        System.out.println(obj.maxAreaSol2(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(obj.maxAreaSol2((new int[] {4, 3, 2, 1, 4})));
        System.out.println(obj.maxAreaSol2((new int[] {1, 2, 1})));
        System.out.println(obj.maxAreaSol2((new int[] {1, 1})));
    }

}