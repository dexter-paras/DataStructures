/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.expedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: Solution1.java, v 0.1 2021-02-08 12:43 PM paras.chawla Exp $$
 */
public class Solution1 {

    // List<String> - group the anagrams

    /*Input   "eat", "tea", "tan", "ate", "nat", "bat"
               X - eat,tea
               Y- tan

     "eat" =X
     "tea" =X
      Output
     */

    /* Pseudo

    Approach 1
    ----------
    Tc - O(n*(klogk))
    eat - aet
    tea - aet
    ate - aet

    Map<Sorted String,List<String>>
        aet - <eat,tea,ate>
        ant - <nat,tan>
    n - size of LIst<string>
    k - max number of characters in a String

    SC - O(n)

    Approach 2
    ----------
    int[] prime = new int[26]{1,2,3,5,7,.....}

    "eat" - prime[e-'a'] * prime[a -'a']* prime[t-'a']=7*1*99  = X
    "tea" -prime[t-'a'] * prime[e-'a'] *prime[a -'a'] = X

    BigInteger="

    */

    // 1. empty list
    // 2. no anagram matching
    // 3. String length is huge


    public List<List<String>> groupAnagrams(List<String> list) {

        List<List<String>> result = new ArrayList<>();

        if(list!=null || list.isEmpty())
            return result;

        // K - sorted String , V - list<String
        Map<String, List<String>> map = new HashMap<>();

        for (String str : list) {
            String key = sortedKey(str);
            map.put(key, map.getOrDefault(key, new ArrayList<>()));
            map.get(key).add(str);
        }

        for (String key : map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }

    private String sortedKey(String str) {

        // 'e','a','t'
        // 'a','e','t'
        char[] ch = str.toCharArray();
        //Arrays.sort(ch);// counting sort o(k)
        return new String(ch);
    }

    public static void main(String[] args) {
        Solution1 obj = new Solution1();

        List<String> list = new ArrayList<>();
        list.add("eat");
        list.add("tea");
        list.add("bat");
        list.add("ate");
        list.add("tab");
        list.add("tan");
        list.add("nat");
        System.out.println(obj.groupAnagrams(list));
    }

}