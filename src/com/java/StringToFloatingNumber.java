/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.java;

/**
 * @author paras.chawla
 * @version $Id: StringToFloatingNumber.java, v 0.1 2021-01-07 10:29 AM paras.chawla Exp $$
 */
public class StringToFloatingNumber {

    // String to floating point number

    // str = "oracle
    // str = "12341.1432" -> 123412.1234

    // str = "oac@1"
    // str = null
    // str = ""

    Float convertString(String str) {

        if (str == null || str.isEmpty()) {
            return null; // Exception
        }

        String[] tokens = str.split("\\.");

        float beforeFloat = parseString(tokens[0]);//123
        float afterFloat = parseString(tokens[1]);//412

        float result = beforeFloat + afterFloat / (float) Math.pow(10, tokens[1].length());
        return result;
    }

    float parseString(String str) {
        float ans = 0;
        float multiplicationFactor = 1;

        for (int i = str.length() - 1; i >= 0; i--) {
            ans += (str.charAt(i) - '0') * multiplicationFactor;
            multiplicationFactor *= 10;
        }

        return ans;
    }

    public static void main(String[] args) {
        StringToFloatingNumber obj = new StringToFloatingNumber();
        System.out.println(obj.convertString("1213243.4561412412"));
        System.out.println(Float.parseFloat("1213243.4561412412"));
    }

}