/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.palindrome;

/**
 * @author paras.chawla
 * @version $Id: ValidPalindromeWithCharacters.java, v 0.1 2020-08-04 10:17 paras.chawla Exp $$
 * <p>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama" Output: true Example 2:
 * <p>
 * Input: "race a car" Output: false
 */
public class ValidPalindromeWithCharacters {

    //s= "A man, a plan, a canal: Panama"
    //s= "a man, a plan, a canal: panama"
    //    ^                            ^
    //   p1                            p2

    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();

        char[] array = s.toCharArray();
        int length = array.length;
        int p1;
        int p2;

        for (p1 = 0, p2 = length - 1; p1 < p2; ) {
            // case 1. if both characters lies within a-z
            if (Character.isLetterOrDigit(array[p1]) && Character.isLetterOrDigit(array[p2])) {
                if (array[p1] == array[p2]) {
                    p1++;
                    p2--;
                } else {
                    return false;
                }
            }
            // case 2.if p1 character lies within a-z but p2 character lies outside
            else if (Character.isLetterOrDigit(array[p1]) && !Character.isLetterOrDigit(array[p2])) {
                p2--;
            }
            // case 3.if p2 character lies within a-z but p1 character lies outside
            else if (Character.isLetterOrDigit(array[p2]) && !Character.isLetterOrDigit(array[p1])) {
                p1++;
            }
            // case 4. if both characters are different
            else if (!Character.isLetterOrDigit(array[p1]) && !Character.isLetterOrDigit(array[p2])) {
                p1++;
                p2--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPalindromeWithCharacters().isPalindrome("0P"));
    }
}