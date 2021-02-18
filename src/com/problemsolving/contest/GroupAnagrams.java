/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: GroupAnagrams.java, v 0.1 2020-04-07 09:09 paras.chawla Exp $$
 */
public class GroupAnagrams {

    /* Approach 1 - Using HashMap
        ["eat", "tea", "tan", "ate", "nat", "bat"]
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String key = getSortedKey(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }

    private String getSortedKey(String key) {
        char[] ch = key.toCharArray();
        Arrays.sort(ch);
        return String.valueOf(ch);
    }


    /* Approach 2
     * Intent is to find same key for "abc" , "bca" , "cba"
     * timeComplexity NORTH*K , NORTH(number of Strings) and K(max length of a String)
     */

    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19,
                23, 29, 31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73, 79, 83, 89,
                97, 101};

        // str - "abc"
        // p = p* primes[c-'a'] = 1* primes['a' -'a'] = 1*2
        // p = p* primes[c-'a'] = p* primes['b' -'a'] = 2*3
        // p = p* primes[c-'a'] = p* primes['c' -'a'] = 6*5
        // p =30

        // str - "cba"
        // p = p* primes[c-'a'] = 1* primes['c' -'a'] = 1*5 =5
        // p = p* primes[c-'a'] = p* primes['b' -'a'] = 5*3=15
        // p = p* primes[c-'a'] = p* primes['a' -'a'] = 15*2=30
        // p = 30

        for (String str : strs) {
            int p = 1;
            for (char c : str.toCharArray()) {
                p *= primes[c - 'a'];
            }
            List<String> list;
            if (map.containsKey(p)) {
                list = res.get(map.get(p));
            } else {
                list = new ArrayList<>();
                res.add(list);
                map.put(p, res.size() - 1);
            }
            list.add(str);
        }
        return res;
    }

    public boolean isAnagram(String s, String t) {

        if(s.length()!=t.length())
            return false;

        Map<Character, Integer> charMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        // a-3,n-1,g-1,r-1,m-1
        // traverse t and charMap should be empty if all characters matched
        for (char c : t.toCharArray()) {
            if (!charMap.containsKey(c)) {
                return false;
            }
            // reduce count and remove from map if count=0
            else {
                int count = charMap.get(c);
                int newCount = count - 1;
                if (newCount > 0) {
                    charMap.put(c, newCount);
                } else {
                    charMap.remove(c);
                }
            }
        }

        return charMap.size() == 0 ? true : false;
    }

    public static void main(String[] args) {
        GroupAnagrams obj = new GroupAnagrams();
        obj.isAnagram("anagram", "nagaram");
        //System.out.println(new GroupAnagrams().groupAnagrams(new String[] {"ate", "tae", "bat", "cat", "tab"}));
        System.out.println(new GroupAnagrams().groupAnagrams2(new String[] {"ate", "tae", "bat", "cat", "tab"}));
    }
}