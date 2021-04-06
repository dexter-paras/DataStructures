/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: WordBreak.java, v 0.1 2020-11-02 22:14 paras.chawla Exp $$ Given a non-empty string s and a dictionary wordDict containing
 * a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * https://www.youtube.com/watch?v=1U4jQusbeJc
 */
public class WordBreak {

    // TLS
    public boolean wordBreakApproach0(String s, List<String> wordDict) {

        // Adding words in a Set

        Set<String> set = new HashSet<>(wordDict);

        return wordBreak(s,set);
    }


    private boolean wordBreak(String s,Set<String> set){

        // base condition
        if(s.isEmpty()){
            return true;
        }

        for(int i=0;i<s.length();i++){
            if(set.contains(s.substring(0,i+1)) && wordBreak(s.substring(i+1), set)){
                return true;
            }
        }
        return false;
    }

    private boolean wordBreak(String s, List<String> wordDict) {

        Map<String, Boolean> map = new HashMap<>();

        if (s.isEmpty()) {
            return true;
        }

        if (wordDict.isEmpty()) {
            return false;
        }

        return helper(s, wordDict, map);
    }

    private boolean helper(String s, List<String> wordDict, Map<String, Boolean> map) {

        if (s.isEmpty()) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.substring(i + 1)) != null) {
                return wordDict.contains(s.substring(0, i + 1)) && map.get(s.substring(i + 1));
            } else if (wordDict.contains(s.substring(0, i + 1)) && helper(s.substring(i + 1), wordDict, map)) {
                map.put(s.substring(i + 1), true);
                return true;
            }
        }
        return false;
    }

    // Approach 2 - Bottom Up Approach
    // Intuition - Solving puzzle putting small pieces together
    private boolean wordBreakDp(String s, List<String> wordDict) {

        if (s.isEmpty()) {
            return true;
        }

        if (wordDict.isEmpty()) {
            return false;
        }

        // convert list to set
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);

        // Create Dp array of length s.length +1
        boolean[] dp = new boolean[s.length() + 1];

        // We can make empty string from wordDict everyTime
        dp[0] = true;

        //TC - O(n^3)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();
        List dictionary = new ArrayList();
        dictionary.add("ab");
        dictionary.add("c");
        dictionary.add("ef");
        System.out.println(obj.wordBreakDp("abcdef", dictionary));
    }

}