/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PhoneCombinationCombination.java, v 0.1 2020-11-26 13:46 paras.chawla Exp $$
 */
public class PhoneCombinationCombination {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return result;
        }

        String builder = "";

        HashMap<Integer, String> map = new HashMap<>();
        fillMap(map);

        helper(digits, 0, result, builder, map);

        return result;
    }

    private void helper(String digits, int idx, List<String> result, String builder, HashMap<Integer, String> map) {

        // base condition
        if (idx == digits.length()) {
            result.add(builder);
            return;
        }

        int number = digits.charAt(idx) - '0'; // 2
        String value = map.get(number); // "abc"

        for (int i = 0; i < value.length(); i++) {

            /* Very Interesting part, if using builder + ch, using backtracking
            char ch = value.charAt(i); // 'a'
            builder=builder+ch;
            */

            helper(digits, idx + 1, result, builder+ value.charAt(i), map);
            //builder=builder.substring(0, builder.length() - 1);
        }
    }

    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }

    // Approach 2- Recursive
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations2(String digits) {
        List<String> ret = new LinkedList<>();
        combination("", digits, 0, ret);
        return ret;
    }

    private void fillMap(HashMap<Integer, String> map) {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
    }

    public static void main(String[] args) {
        PhoneCombinationCombination obj = new PhoneCombinationCombination();
        System.out.println(obj.letterCombinations("23"));
    }

}