/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: OneEditDistance.java, v 0.1 2021-04-21 10:14 AM paras.chawla Exp $$
 */
public class OneEditDistance {

    // Writing code in a way that s.length is always lesser than t.length
    // https://leetcode.com/problems/one-edit-distance/discuss/50107/Accepted-clean-Java-solution-with-explanation-(two-pointers)
    public boolean isOneEditDistance(String s, String t) {

        int i = 0, j = 0;

        int slen = s.length();
        int tlen = t.length();

        // case 0.1
        if (s.isEmpty() && t.isEmpty()) { return false; }

        // case 0.2
        if ((slen == 1 && t.isEmpty()) || (tlen == 1 && s.isEmpty())) { return true; }

        if (slen > tlen) { return isOneEditDistance(t, s); }

        // s="abcd" && t="abecd" || s="abcd" && t ="abed"

        while (i < slen && j < tlen) {

            // if unmatched -> either replace or insert
            if (s.charAt(i) != t.charAt(j)) {

                // insert t[j] to s[i] & compare rest of the string
                return s.substring(i).equals(t.substring(j + 1)) ||
                        // replace t[j] to s[i] & compare rest of the string
                        s.substring(i + 1).equals(t.substring(j + 1));

            }
            i++;
            j++;
        }

        // Make sure there is only 1 character left in t, if more characters then return false
        return t.length() - j == 1;
    }

    // NOT WORKING
    public boolean isOneEditDistance2(String s, String t) {

        int i = 0, j = 0;

        int slen = s.length();
        int tlen = t.length();

        // case 0.1
        if (s.isEmpty() && t.isEmpty()) { return false; }

        // case 0.2
        else if ((slen == 1 && t.isEmpty()) || (tlen == 1 && s.isEmpty())) { return true; }

        // case 1
        else if (slen < tlen) {

            while (i < slen && j < tlen) {

                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return s.substring(i).equals(t.substring(j + 1));
                }
            }
        }

        // case 2

        else if (slen == tlen) {

            while (i < slen && j < tlen) {

                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                } else {
                    // skipping unmatched characters
                    return s.substring(i + 1).equals(t.substring(j + 1));
                }
            }
        }

        // case 3

        else {

            while (j < tlen && i < slen) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return s.substring(i + 1).equals(t.substring(j));
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OneEditDistance obj = new OneEditDistance();
        System.out.println(obj.isOneEditDistance("a", "ab"));
        System.out.println(obj.isOneEditDistance("a", ""));
        System.out.println(obj.isOneEditDistance("", ""));
        System.out.println(obj.isOneEditDistance("", "A"));
    }
}