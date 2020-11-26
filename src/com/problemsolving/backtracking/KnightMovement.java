/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

/**
 * @author paras.chawla
 * @version $Id: KnightMovement.java, v 0.1 2020-08-23 12:48 paras.chawla Exp $$
 */
public class KnightMovement {

    int minCount;
    int currCount;

    // 8 moves
    //x=2,y=1
    public int minKnightMoves(int x, int y) {

        if (x == 0 && y == 0) {
            return 0;
        }
        helper(0, 0, x, y, minCount, currCount);
        return minCount;
    }

    private void helper(int cx, int cy, int rx, int ry, int minCount, int currCount) {

        // Base Condition
        if (cx > rx && cy > ry) {
            return;
        }
        // final condition
        if (cx == rx && cy == ry) {
            minCount = Math.min(currCount, minCount);
        }

        // move to all 8 possible directions
        for (int i = 0; i < 8; i++) {
            currCount++;
            helper(cx + 1, cy + 2, rx, ry, minCount, currCount);
            helper(cx + 2, cy + 1, rx, ry, minCount, currCount);
            helper(cx - 1, cy + 2, rx, ry, minCount, currCount);
            helper(cx - 2, cy + 1, rx, ry, minCount, currCount);
            helper(cx - 2, cy - 1, rx, ry, minCount, currCount);
            helper(cx - 1, cy - 2, rx, ry, minCount, currCount);
            helper(cx + 1, cy - 2, rx, ry, minCount, currCount);
            helper(cx + 2, cy - 1, rx, ry, minCount, currCount);
            currCount--;
        }
    }
}