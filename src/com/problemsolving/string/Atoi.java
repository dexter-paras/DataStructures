/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: Atoi.java, v 0.1 2019-08-07 20:23 paras.chawla Exp $$
 */
public class Atoi {

    //"4193 with words" , "words and 987" ,"-91283472332" ,"    -42","+-23"
    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        // remove whitespaces
        str = str.trim();
        double result = 0;

        // handle positive and negative cases
        boolean isNegative = false;
        int startIndex = 0;// index of first non-numerial character

        if(str.length()==0){
            return 0;
        }
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            startIndex++;
        }
        if (str.charAt(0) == '-') {
            isNegative = true;
        }

        for (int i = startIndex; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            int value = (int) (str.charAt(i) - '0');
            result = result * 10 + value;
        }

        if (isNegative) {
            result = -result;
        }

        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi(" "));
    }
}