/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Nov;

/**
 * @author paras.chawla
 * @version $Id: N4_ConsecutiveCharacters.java, v 0.1 2020-11-04 11:57 paras.chawla Exp $$
 * Intuition - Think Backward and Forward while iterating an array. SOmetimes backward iteration helps
 */
public class N4_ConsecutiveCharacters {

    // Approach 1- Thinking Forward
    public int maxPower(String s) {

        if (s.length() == 1) {
            return 1;
        }
        int max = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                int j = i + 1;
                int currCount = 1;
                for (; j < s.length(); j++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        break;
                    }
                    currCount++;
                    max = Math.max(currCount, max);
                }
                i = j - 1;
            }
        }
        return max;
    }

    // Approach 2- Thinking Backward

    public int maxPower2(String s) {
        if (s.length() == 1) { return 1; }

        int max = 1;
        int currMax = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currMax++;
            } else {
                currMax = 1;
            }

            if (currMax > max) { max = currMax; }
        }
        return max;
    }

    public static void main(String[] args) {
        N4_ConsecutiveCharacters obj = new N4_ConsecutiveCharacters();
        System.out.println(obj.maxPower("triplepillooooow"));
        System.out.println(obj.maxPower("hooraaaaaaaaaaay"));
        System.out.println(obj.maxPower("tourist"));
    }

}