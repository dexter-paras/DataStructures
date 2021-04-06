/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: WordBreak2.java, v 0.1 2020-11-28 12:35 paras.chawla Exp $$
 */
public class WordBreak2 {

    List<List<String>> globalResult = new ArrayList<>();

    public List<List<String>> wordSegmentation(String s, String[] wordDict) {

        List<String> result = new ArrayList<>();

        // base condition
        if (s == null || wordDict.length == 0) {
            return globalResult;
        }

        // add wordDict into Set - To make it O(1)
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }

        helper(s, set, result);

        return globalResult;
    }

    // str= "code" ["c","od","e"]
    private void helper(String str, Set<String> set, List<String> list) {
        if (str.length() == 0) {
            // adding deep copy
            globalResult.add(new ArrayList<>(list));
        }
        //ode i=0;i<3
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.substring(0, i + 1))) {
                list.add(str.substring(0, i + 1));
                helper(str.substring(i + 1), set, list);
                list.remove(list.size() - 1);
            }
        }
    }

    // WORKING properly
    private List<String> wordBreakHelper(String s, List<String> wordDict) {

        List<String> result = new ArrayList<>();
        if (s.isEmpty()) {
            result.add("");
            return result;
        }

        // check each word on String s and if matches, get List<Strings> on rest of the substring
        for (String word : wordDict) {

            // word= "pine" + helper("applepenapple")
            //                  "apple" + helper("penapple")
            //                              "pen" + helper("apple")
            //                                         "apple" + helper("");

            if (s.startsWith(word)) {
                List<String> subStrings = wordBreakHelper(s.substring(word.length()), wordDict);
                for (String subString : subStrings) {
                    String space = subString.isEmpty() ? "" : " ";
                    result.add(word + space + subString);
                }
            }
        }
        return result;
    }

    // https://www.youtube.com/watch?v=uR3RElKnrkU
    public static void main(String[] args) {
        //String[] str = new String[] {"cat", "cats", "and", "sand", "dog"};
        List<String> list = new ArrayList<>();

        list.add("apple");
        list.add("pen");
        list.add("applepen");
        list.add("pine");
        list.add("pineapple");

        WordBreak2 obj = new WordBreak2();
        List<String> globalResult = obj.wordBreakHelper("pineapplepenapple", list);
        System.out.print(globalResult);
    }

}