/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: DistinctSubString.java, v 0.1 2020-10-19 19:17 paras.chawla Exp $$
 * <p>
 * minString=abccf - traversalMap =4 = ch.length
 * <p>
 * minString -abccf bccffba- traversalMap=4 =ch.length minSTring= Math.min(current.length, minString.length) input_string = “abccffba”  ^^
 * ij a-1 b-1 c-2 f-1 traversalMap - 4 = ch.length distinct_array = [a,b,c,f]
 */
public class DistinctSubString {

    public String distinctSubString(String str, char[] ch) {

        // 2 times traversal and creaing a traversal map
        Set<Character> traversalSet = new HashSet<>();// char[] 26 - a,b,c
        StringBuilder minString = new StringBuilder(new StringBuilder(str).append("X"));
        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(str.charAt(i));//a
            traversalSet.add(str.charAt(i));//a

            for (int j = i + 1; j < str.length(); j++) {
                builder.append(str.charAt(j));//abccf
                traversalSet.add(str.charAt(j));//a,b,c,f
                if (traversalSet.size() == ch.length) {
                    if (builder.length() < minString.length()) {
                        minString = builder;
                        break;
                    }
                }
            }
            traversalSet = new HashSet<>();
        }
        return minString.toString();
    }

    static final int MAX_CHARS = 256;

    // Function to find smallest window containing
    // all distinct characters
    private String findSubString(String str) {
        int n = str.length();

        // Count all distinct characters.
        int dist_count = 0;

        boolean[] visited = new boolean[MAX_CHARS];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (visited[str.charAt(i)] == false) {
                visited[str.charAt(i)] = true;
                dist_count++;
            }
        }

        // Now follow the algorithm discussed in below
        // post. We basically maintain a window of characters
        // that contains all characters of given string.
        int start = 0, start_index = -1;
        int min_len = Integer.MAX_VALUE;

        int count = 0;
        int[] curr_count = new int[MAX_CHARS];
        for (int j = 0; j < n; j++) {
            // Count occurrence of characters of string
            curr_count[str.charAt(j)]++;

            // If any distinct character matched,
            // then increment count
            if (curr_count[str.charAt(j)] == 1) { count++; }

            // if all the characters are matched
            if (count == dist_count) {
                // Try to minimize the window i.e., check if
                // any character is occurring more no. of times
                // than its occurrence in pattern, if yes
                // then remove it from starting and also remove
                // the useless characters.
                while (curr_count[str.charAt(start)] > 1) {
                    if (curr_count[str.charAt(start)] > 1) { curr_count[str.charAt(start)]--; }
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
        // Return substring starting from start_index
        // and length min_len
        return str.substring(start_index, start_index + min_len);
    }

    public static void main(String[] args) {
        DistinctSubString obj = new DistinctSubString();
        //System.out.println(obj.distinctSubString("abccfbaa", new char[] {'a', 'b', 'c', 'f'}));
        obj.findSubString("aabcbcdbca");
    }
}