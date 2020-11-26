/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: PhoneNumberLC17.java, v 0.1 2019-08-24 12:18 paras.chawla Exp $$
 */
public class PhoneNumberLC17 {

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty())
            return Collections.emptyList();

        Map<String, List<String>> directory = new HashMap<>();
        directory.put("2", Arrays.asList("a", "b", "c"));
        directory.put("3", Arrays.asList("d", "e", "f"));
        directory.put("4", Arrays.asList("g", "h", "i"));
        directory.put("5", Arrays.asList("j", "k", "l"));
        directory.put("6", Arrays.asList("m", "n", "o"));
        directory.put("7", Arrays.asList("p", "q", "r", "s"));
        directory.put("8", Arrays.asList("t", "u", "v"));
        directory.put("9", Arrays.asList("w", "x", "y", "z"));

        int length = digits.length();
        List<String> finalList = new ArrayList<>();

        // Getting first numeric digit of phonebook
        List list1 = directory.get(digits.charAt(0) + ""); // 2-> a,b,c

        // Looping with all other numeric digit of phonebook except first
        for (int i = 1; i < length; i++) {
            List list2 = directory.get(digits.charAt(i) + ""); // 3-> d,e,f
            for (int j = 0; j < list1.size(); j++) {
                String ch1 = (String) list1.get(j);
                for (int k = 0; k < list2.size(); k++) {
                    String ch2 = (String) list2.get(k);
                    finalList.add(ch1 + "" + ch2); //ad,ae,af,bd,be,bf,cd,ce,cf | w,x,y,z
                }
            }
            // overriding finalList result in list1
            list1 = finalList;
            finalList = new ArrayList<>();
        }
        return list1;
    }

    // "2" -> abc
    // "3" -> def
    // "9" -> wxyz
    public List<String> letterCombinationsSol2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        // digits="23"
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i)); //2 -> "abc"
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new PhoneNumberLC17().letterCombinationsSol2("239"));
    }
}