/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LongestSubstring2DistinctChar.java, v 0.1 2020-12-03 20:25 paras.chawla Exp $$
 * <p>
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "eceba" Output: 3 Explanation: t is "ece" which its length is 3. Example 2:
 * <p>
 * Input: "ccaabbb" Output: 5 Explanation: t is "aabbb" which its length is 5.
 */
public class LongestSubstring2DistinctChar {

    // s=ccaabbb
    public static int lengthOfLongestSubstringTwoDistinct(String s) {

        if (s.length() < 3) { return s.length(); }

        int start = 0;
        int distinctCount = 0;
        int maxLength = 2;

        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            // if new unique character, add as distinctCount
            if (!countMap.containsKey(s.charAt(i))) {
                distinctCount++;
            }
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);

            if (distinctCount == 2) { maxLength = Math.max(maxLength, i - start + 1); }

            // Shrink from left side
            while (distinctCount > 2) {
                if (countMap.get(s.charAt(start)) > 1) {
                    int currCount = countMap.get(s.charAt(start));
                    countMap.put(s.charAt(start), --currCount);
                } else if (countMap.get(s.charAt(start)) == 1) {
                    countMap.remove(s.charAt(start));
                    distinctCount--;
                }
                start++;
            }
        }

        return distinctCount == 1 ? s.length() : maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("aaaaa"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("aabb"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("aabbcccc"));
    }
}