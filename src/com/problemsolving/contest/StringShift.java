/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

/**
 * @author paras.chawla
 * @version $Id: StringShift.java, v 0.1 2020-04-15 10:02 paras.chawla Exp $$
 */
public class StringShift {

    //s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
    public String stringShift(String s, int[][] shift) {

        int rightShiftCount = 0;
        int leftShiftCount = 0;
        for (int i = 0; i < shift.length; i++) {
            int direction = shift[i][0];
            int amount = shift[i][1];

            if (direction == 1) {
                rightShiftCount += amount;
            } else {
                leftShiftCount += amount;
            }
        }
        if (rightShiftCount > leftShiftCount) {
            rightShiftCount = rightShiftCount - leftShiftCount;
            leftShiftCount = 0;
        } else {
            leftShiftCount = leftShiftCount - rightShiftCount;
            rightShiftCount = 0;
        }
        int length = s.length();
        if (rightShiftCount > 0) {
            if (rightShiftCount > length) {
                rightShiftCount %= length;
            }
            return s.substring(length - rightShiftCount) + s.substring(0, length - rightShiftCount);
        } else if (leftShiftCount > 0) {
            if (leftShiftCount > length) {
                leftShiftCount %= length;
            }
            return s.substring(leftShiftCount) + s.substring(0, leftShiftCount);
        } else {
            return s;
        }
    }

    static String leftrotate(String str, int d) {
        String ans = str.substring(d) + str.substring(0, d);
        return ans;
    }

    // function that rotates s towards right by d
    static String rightrotate(String str, int d) {
        return leftrotate(str, str.length() - d);
    }

    //https://www.youtube.com/watch?v=hrxyXmxQRz4
    //https://www.youtube.com/watch?v=zKabljbMZxw
    public String stringShift2(String s, int[][] shift) {
        int len = s.length();

        for (int[] value : shift) {
            if (value[0] == 0) {
                s = leftShift(s, value[1] % len);
            } else {
                s = rightShift(s, value[1] % len);
            }
        }
        return s;
    }

    public static String leftShift(String s, int num) {
        return s.substring(num) + s.substring(0, num);
    }

    public static String rightShift(String s, int num) {
        return s.substring(s.length() - num) + s.substring(0, s.length() - num);
    }

    public String stringShift3(String s, int[][] shift) {
        int pos = 0, len = s.length();
        for (int[] sh : shift) {
            pos += sh[0] == 0 ? sh[1] : -sh[1];
        }
        pos = (pos % len + len) % len;
        return s.substring(pos) + s.substring(0, pos);
    }

    public static void main(String[] args) {

        System.out.println(new StringShift().stringShift3("mecsk", new int[][] {{0, 4}, {1, 5}, {1, 4}, {0, 1}, {0, 5}}));
        System.out.println(new StringShift().stringShift3("abcdefg", new int[][] {{0, 1}, {0, 1}, {1, 2}, {0, 3}}));
        System.out.println(new StringShift().stringShift3("abc", new int[][] {{0, 1}, {1, 2}}));
        System.out.println(
                new StringShift().stringShift3("yisxjwry", new int[][] {{1, 8}, {1, 4}, {1, 3}, {1, 6}, {0, 6}, {1, 4}, {0, 2}, {0, 1}}));
    }
}