/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: GenerateStringOddCounts.java, v 0.1 2020-03-11 08:10 paras.chawla Exp $$
 */

// Generate a String With Characters That Have Odd Counts
public class GenerateStringOddCounts {

    public String generateTheString(int n) {
        // if n is even , append string with 'a' and for remaining n-1,append string with 'b'
        StringBuilder str = new StringBuilder();
        if (n % 2 == 0) {
            str.append("a");
            n--;
        }
        while (n-- > 0) {
            str.append("b");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new GenerateStringOddCounts().generateTheString(10));
        System.out.println(new GenerateStringOddCounts().generateTheString(9));
    }
}