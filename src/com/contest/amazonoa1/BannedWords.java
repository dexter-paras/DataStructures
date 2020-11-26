/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.amazonoa1;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: BannedWords.java, v 0.1 2020-11-24 15:37 paras.chawla Exp $$
 */
public class BannedWords {

    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet();

        for (String str : banned) {
            set.add(str);
        }

        // Replace any letter which is not a-z, A-Z -> replace with empty whitespace
        String[] tokens = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase().split("\\s+");
        Map<String, Integer> map = new HashMap<>();

        for (String token : tokens) {
            if (map.containsKey(token)) {
                int count = map.get(token);
                map.put(token, count + 1);
            } else {
                map.put(token, 1);
            }
        }

        int maxFreq = 0;
        String mostFreqWord = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!set.contains(entry.getKey()) && entry.getValue() > maxFreq) {
                maxFreq = Math.max(maxFreq, entry.getValue());
                mostFreqWord = entry.getKey();
            }
        }
        return mostFreqWord;
    }

    // Approach 2 - Better coding style
    public static String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet();

        for (String str : banned) {
            bannedWords.add(str);
        }

        // Replace any letter which is not a-z, A-Z -> replace with empty whitespace
        String[] tokens = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase().split("\\s+");
        //String[] words = paragraph.toLowerCase().split("[ !?',;.]+");
        Map<String, Integer> map = new HashMap<>();

        for (String token : tokens) {
            if (!bannedWords.contains(token)) {
                map.put(token, map.getOrDefault(token, 0) + 1);
            }
        }

        // 4). return the word with the highest frequency
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

    }

    public static void main(String[] args) {
        mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"});
        mostCommonWord("a, a, a, a, b,b,b,c, c", new String[] {"a"});
    }
}