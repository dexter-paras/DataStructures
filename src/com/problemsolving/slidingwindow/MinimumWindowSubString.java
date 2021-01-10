/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.slidingwindow;

/**
 * @author paras.chawla
 * @version $Id: MinimumWindowSubString.java, v 0.1 2020-10-22 13:22 paras.chawla Exp $$
 */
public class MinimumWindowSubString {

    public String minWindow(String S, String T) {
        int[] curr_count = new int[256];

        // prefill curr_count to -1 with characters in T string
        // -1 acting as a flag ,tells which character to take care

        int dist_count = 0;
        for (char ch : T.toCharArray()) {
            curr_count[ch] = -1;
            dist_count++;
        }

        // start traversal
        int start = 0, start_index = -1;
        boolean startFound = true;
        int min_len = Integer.MAX_VALUE;

        int count = 0;
        int length = S.length();
        for (int j = 0; j < length; j++) {
            char ch = S.charAt(j);

            if (curr_count[ch] >= 1) {
                curr_count[ch]++;
            }

            // when SOUTH char matches to char in T
            if (curr_count[ch] == -1) {
                curr_count[ch] += 2;
                count++;
                // set start index to first character which matches
                if (startFound) {
                    start = j;
                    startFound = false;
                }
            }

            // check if count==dist_count, if yes perform pointer movement
            if (count == dist_count) {
                while (curr_count[S.charAt(start)] > 1 || curr_count[S.charAt(start)] == 0) {
                    if (curr_count[S.charAt(start)] > 1) { curr_count[S.charAt(start)]--; }
                    start++;
                }

                // Update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }
        if (count < dist_count) {
            return "";
        }
        return S.substring(start_index, start_index + min_len);
    }

    public static void main(String[] args) {
        MinimumWindowSubString obj = new MinimumWindowSubString();
        //System.out.println(obj.minWindow("AA", "AA"));
        System.out.println(obj.minWindow("ADOBECODEBANCC","ABC"));
        //System.out.println(obj.minWindow("ADOBECODEBANC","BEN"));
    }

}