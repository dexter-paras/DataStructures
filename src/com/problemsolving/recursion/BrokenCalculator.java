/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.recursion;

/**
 * @author paras.chawla
 * @version $Id: BrokenCalculator.java, v 0.1 2021-02-22 10:38 AM paras.chawla Exp $$
 * https://leetcode.com/problems/broken-calculator/discuss/234484/JavaC%2B%2BPython-Change-Y-to-X-in-1-Line
 */
public class BrokenCalculator {

    /*  X=5, Y=8
        Convert X to Y
        Operations allowed : Subtract X to 1 or Multiply X by 2

        Backward flow
        Convert Y to X
        Operations allowed : Add Y to 1 if Y is odd or Divide Y by 2 if Y is even
    */
    public int brokenCalc(int X, int Y) {

        int result = 0;
        while (Y > X) {
            result++;
            Y = Y % 2 == 0 ? Y / 2 : Y + 1;
        }

        return result + X - Y;
    }

    public static void main(String[] args) {
        BrokenCalculator obj = new BrokenCalculator();
        obj.brokenCalc(5,8);
        obj.brokenCalc(3,10);
        obj.brokenCalc(1024,1);
    }

}