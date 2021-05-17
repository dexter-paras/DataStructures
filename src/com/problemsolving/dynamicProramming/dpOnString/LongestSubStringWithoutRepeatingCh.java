/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: LongestSubStringWithoutRepeatingCh.java, v 0.1 2020-04-29 00:50 paras.chawla Exp $$
 */
public class LongestSubStringWithoutRepeatingCh {

    public int lengthOfLongestSubstring(String s) {

        // base conditions
        if (s == null || s.isEmpty()) { return 0; }

        int ans = 1;

        int left = 0, right = 0, len = s.length();
        Set<Character> set = new HashSet<>();

        while (right < len) {

            char ch = s.charAt(right);
            // if not duplicate , start the longest substring from here
            if (!set.contains(ch)) {
                set.add(ch);
                right++;
            } else {
                set.remove(s.charAt(left++));
            }
            ans = Math.max(ans, set.size());
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int[] index = new int[128];
            int count = 0;
            for (int j = i; j < s.length(); j++) {
                if (index[s.charAt(j)] != 0) {
                    break;
                }
                index[s.charAt(j)]++;
                count++;
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStringWithoutRepeatingCh().lengthOfLongestSubstring(" "));
    }
}