/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.airasia;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: DisjointString.java, v 0.1 2020-10-29 10:12 paras.chawla Exp $$
 */

//Given a dictionary and a String. Split the string into disjoint valid words
// { i, like, sam, sung, samsung, mobile,ice,cream,icecream, man, go, mango} 
// Input: ilike -> i,like
// ilikemobile  -> i, like, mobile
// ilikexyz -> i, like
// ilikesamsung -> i , like, sam, sung, samsung

public class DisjointString {

    Set<String> findWords(HashSet<String> dictionary, String str) {

        Set<String> result = new HashSet<>();

        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder builder = new StringBuilder().append(str.charAt(i));
            if (dictionary.contains(builder.toString())) {
                result.add(builder.toString());
            }
            for (int j = i + 1; j < str.length(); j++) {
                builder.append(str.charAt(j));
                if (dictionary.contains(builder.toString())) {
                    result.add(builder.toString());
                }
            }
            builder = new StringBuilder();
        }
        System.out.println(result);
        System.out.println(result.size());
        return result;
    }

    public static void main(String[] args) {
        DisjointString obj = new DisjointString();
        HashSet set = new HashSet();
        set.add("i");
        set.add("like");
        set.add("sam");
        set.add("samsung");
        set.add("mobile");
        set.add("ice");
        set.add("icecream");
        set.add("man");
        set.add("go");
        set.add("mango");
        System.out.println(obj.findWords(set, "xyz"));
    }

}