/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: RomanToInteger.java, v 0.1 2021-02-22 8:47 AM paras.chawla Exp $$
 */
public class RomanToInteger {

    // s="VI"
    //

    // Move Left to Right to solve this problem
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int num = 0;
        for (int i = 0; i < s.length(); i++) {

            if (i == s.length() - 1 || map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)) ) {
                num += map.get(s.charAt(i));
            } else {
                num = num + map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i++;
            }

        }

        return num;
    }

    // Approach 2 - O(1) Space Complexity
    public int romanToInt2(String s) {
        int sum = 0;

        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'I':
                    sum += sum >= 5 ? -1 : 1;
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    sum += sum >= 50 ? -10 : 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    sum += sum >= 500 ? -100 : 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;

            }
        }

        return sum;
    }

    public static void main(String[] args) {
        RomanToInteger obj = new RomanToInteger();
        System.out.println(obj.romanToInt2("III"));
        System.out.println(obj.romanToInt2("IV"));
        System.out.println(obj.romanToInt2("IX"));
        System.out.println(obj.romanToInt2("LVIII"));
        System.out.println(obj.romanToInt2("MCMXCIV"));
    }

}