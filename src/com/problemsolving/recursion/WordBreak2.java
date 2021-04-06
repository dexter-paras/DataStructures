/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: WordBreak2.java, v 0.1 2021-03-06 2:01 PM paras.chawla Exp $$
 */
public class WordBreak2 {

    List<String> globalResult = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);

        List<String> result = new ArrayList<>();

        helper(s, set, 0, result, new StringBuilder());

        return result;
    }

    // s= "catsanddog", idx=0, result={}
    private void helper(String s, Set<String> set, int idx, List<String> result, StringBuilder builder) {

        // base condition
        if (idx == s.length()) {
            globalResult.add(addSpace(result));
            return;
        }

        builder.append(s.charAt(idx));
        if (set.contains(builder.toString())){
            result.add(builder.toString());
            helper(s, set,idx+1,result,new StringBuilder());
            result.remove(result.size()-1);
        }
        helper(s,set,idx+1,result,builder);
    }

    // cat, sand, dog
    private String addSpace(List<String> result) {
        StringBuilder builder = new StringBuilder();

        for (String str : result) {
            builder.append(str).append(" ");
        }

        return builder.toString().trim();
    }

    public static void main(String[] args) {
        WordBreak2 obj = new WordBreak2();
        List dictionary = new ArrayList();
        dictionary.add("cat");
        dictionary.add("cats");
        dictionary.add("and");
        dictionary.add("sand");
        dictionary.add("dog");

        System.out.println(obj.wordBreak("catsanddog",dictionary));
    }

}