/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

/**
 * @author paras.chawla
 * @version $Id: ExcelSheetColumnNumber.java, v 0.1 2020-08-11 20:35 paras.chawla Exp $$
 */
public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int result = 0;

        // start from last character and move backward
        // "CAD"
        //    ^
        int length = s.length();
        for (int idx = 0; idx < s.length(); idx++) {
            char ch = s.charAt(idx);
            result = result * 26;

            // In Java, subtracting characters is subtracting ASCII values of characters
            // By doing so, you will get results from 0 (for A) to 25 (for Z).
            // Since we are indexing from 1, we can just add 1 up to the result.
            result += (ch - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnNumber().titleToNumber("ZY"));
    }
}