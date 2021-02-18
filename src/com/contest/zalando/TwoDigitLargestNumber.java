/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.zalando;

/**
 * @author paras.chawla
 * @version $Id: TwoDigitLargestNumber.java, v 0.1 2021-01-21 7:05 AM paras.chawla Exp $$
 */
public class TwoDigitLargestNumber {

    public int solution(String S) {

        if (S == null || S.length() == 0 || S.length() == 1) {
            return -1;
        }

        // S= "5055297"
        StringBuilder builder = new StringBuilder();
        builder.append(S.charAt(0));

        int maxNumber = -1;
        for (int i = 1; i < S.length(); i++) {
            if (builder.length() != 2) {
                builder.append(S.charAt(i));
            }
            int twoDigitNumber = Integer.parseInt(builder.toString());
            maxNumber = Math.max(twoDigitNumber, maxNumber);
            builder.deleteCharAt(0);
        }

        return maxNumber;
    }

    public static void main(String[] args) {
        TwoDigitLargestNumber obj = new TwoDigitLargestNumber();
        //System.out.println(obj.solution("012345"));
        System.out.println(obj.solution("0010009"));
        //System.out.println(obj.solution("000012"));

    }
}