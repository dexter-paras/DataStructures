/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: AddAndSearchWord.java, v 0.1 2020-08-08 11:40 paras.chawla Exp $$
 * Paras
 * Par
 * Sonal
 * 5-> <Paras,Sonal>
 */
public class AddAndSearchWord {

    private Map<Integer, List<String>> map;

    private boolean cmp(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if (ch1 == '.') { continue; } else if (ch1 != ch2) { return false; }
        }
        return true;
    }

    /**
     * Initialize your val structure here.
     */
    public AddAndSearchWord() {
        map = new HashMap<>();
    }

    /**
     * Adds a word into the val structure.
     */
    public void addWord(String word) {
        int len = word.length();
        List<String> list = map.getOrDefault(len, new ArrayList<>());
        list.add(word);
        map.put(len, list);
    }

    /**
     * Returns if the word is in the val structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        int len = word.length();
        if (!map.containsKey(len)) { return false; }
        List<String> list = map.get(len);
        for (String str : list) {
            if (cmp(word, str)) { return true; }
        }
        return false;
    }
}