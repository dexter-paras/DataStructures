/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: FindSmallestSubSequence.java, v 0.1 2021-03-18 5:21 PM paras.chawla Exp $$
 */
public class FindSmallestSubSequence {

    // WORKING but TLE
    public String minWindow(String S, String T) {

        List<Integer> index = new ArrayList<>();

        // One Pass
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == T.charAt(0)) {
                index.add(i);
            }
        }

        int max = Integer.MAX_VALUE;
        String result = "";

        // Second Pass
        for (int i = 0; i < index.size(); i++) {

            int idx = index.get(i);
            int ptr = 0;

            for (int k = idx; k < S.length(); ) {

                if (S.charAt(k) == T.charAt(ptr)) {
                    k++;
                    ptr++;
                } else {
                    k++;
                }

                // subsequence meet , Save the result
                if (ptr == T.length()) {
                    int subSeqLen = k - idx;

                    if (subSeqLen < max) {
                        result = S.substring(idx, k);
                        max = subSeqLen;
                    }
                    break;
                }
            }
        }

        return result;
    }

    // NOT WORKING
    // Wrong when S= "abcbdeade" , T ="bde"
    // Here bde within bcbde isn't taken care, till previous b isn't finished, next b ignored in previous b
    public String minWindow2(String S, String T) {

        int i = 0;
        int j = 0;
        int start = 0, end = 0;
        boolean startFound = true;
        String word = "";
        int max = S.length() + 1;

        while (i < S.length()) {

            if (S.charAt(i) == T.charAt(j)) {
                if (startFound) {
                    start = i;
                    startFound = false;
                }
                j++;
            }
            i++;

            // subsequence Found
            if (j == T.length()) {
                end = i;

                if (end - start < max) {
                    max = end - start;
                    word = S.substring(start, end);
                }
                j = 0;
                startFound = true;
            }
        }
        return word;
    }

    // Working Solution
    // Very Important : Once Subsequence found, find subsequence going reverse as well to find Smallest subsequence if missed before
    // https://www.youtube.com/watch?v=W2DvQcDPD9A

    public String minWindow3(String S, String T) {
        String window = "";
        int j = 0, min = S.length() + 1;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(j)) {
                j++;
                if (j == T.length()) {
                    int end = i + 1;
                    j--;
                    while (j >= 0) {
                        if (S.charAt(i) == T.charAt(j)) j--;
                        i--;
                    }
                    j++;
                    i++;
                    if (end - i < min) {
                        min = end - i;
                        window = S.substring(i, end);
                    }
                }
            }
        }
        return window;
    }

    // COPIED
    /*.*/
    public String minWindow4(String S, String T) {
        if (S.length() == 0 || T.length() == 0) {
            return "";
        }

        /**
         * we can conduct two steps by using two pointers for this probelm:
         * 1. check feasibility from left to right
         * 2. check optimization from right to left
         * we can traverse from left to right, find a possible candidate until reach the first ending character of T
         * eg: for the string s = abcdebdde and t = bde, we should traverse s string until we find first e,
         * i.e. abcde, then traverse back from current "e" to find if we have other combination of bde with smaller
         * length.
         * @param right: fast pointer that always points the last character of T in S
         * @param left: slow pointer that used to traverse back when right pointer find the last character of T in S
         * @param tIndex: third pointer used to scan string T
         * @param minLen: current minimum length of subsequence
         * */
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        while (right < S.length()) {
            int tIndex = 0;
            // use fast pointer to find the last character of T in S
            while (right < S.length()) {
                if (S.charAt(right) == T.charAt(tIndex)) {
                    tIndex++;
                }
                if (tIndex == T.length()) {
                    break;
                }
                right++;
            }

            // if right pointer is over than boundary
            if (right == S.length()) {
                break;
            }

            // use another slow pointer to traverse from right to left until find first character of T in S
            int left = right;
            tIndex = T.length() - 1;
            while (left >= 0) {
                if (S.charAt(left) == T.charAt(tIndex)) {
                    tIndex--;
                }
                if (tIndex < 0) {
                    break;
                }
                left--;
            }
            // if we found another subsequence with smaller length, update result
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                result = S.substring(left, right + 1);
            }
            // WARNING: we have to move right pointer to the next position of left pointer, NOT the next position
            // of right pointer
            right = left + 1;
        }
        return result;
    }


    public static void main(String[] args) {
        FindSmallestSubSequence obj = new FindSmallestSubSequence();
        System.out.println(obj.minWindow3("abcdeabddebde", "bde"));
        System.out.println(obj.minWindow3("abcbdeade", "bde"));
    }
}