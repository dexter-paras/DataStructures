/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LongestWordInDictionary.java, v 0.1 2021-02-23 8:31 AM paras.chawla Exp $$
 */
public class LongestWordInDictionary {

    //    01234567
    // s="abpcplea"
    // a->0,7 | b->1 | p->2,4 |c->3 | l->5 | e->6
    public String findLongestWord(String s, List<String> d) {

        // K - Character, V- Indexes
        Map<Character, LinkedList<Integer>> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, new LinkedList<>());
            }
            map.get(ch).add(i);
        }

        int maxLength = Integer.MIN_VALUE;
        String longestWord = "";

        //"ale"
        for (String str : d) {
            // Deep copy a HashMap
            HashMap<Character, LinkedList<Integer>> cloneMap = new HashMap(map);
            int lastIdxChoose = -1;
            int j;
            for (j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (!cloneMap.containsKey(c)) {
                    break;
                } else {
                    LinkedList<Integer> indexList = cloneMap.get(c);
                    int indexChoose = indexList.pollFirst();
                    if (indexChoose > lastIdxChoose) {
                        lastIdxChoose = indexChoose;
                    } else {
                        break;
                    }
                }
            }

            // if String find out
            if (j == str.length()) {
                if (str.length() > maxLength) {
                    maxLength = str.length();
                    longestWord = str;
                }
            }
        }

        return longestWord;
    }

    public String findLongestWord2(String s, List<String> d) {
        String res = "";
        for (String w : d) {
            if (isSubsequence(s, w) && compare(w, res) < 0)
                res = w;
        }

        return res;
    }

    private int compare(String a, String b) {
        return a.length() == b.length() ? a.compareTo(b) : b.length() - a.length();
    }

    private boolean isSubsequence(String s, String w) {
        if (w.length() > s.length()) return false;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (s.charAt(i) == w.charAt(j)) {
                j++;
                if (j == w.length()) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LongestWordInDictionary obj = new LongestWordInDictionary();
        List<String> dict = new ArrayList<>();
        dict.add("ale");
        dict.add("lea");
        dict.add("apple");
        dict.add("monkey");
        dict.add("plea");
        obj.findLongestWord2("abpcplea", dict);
    }

}