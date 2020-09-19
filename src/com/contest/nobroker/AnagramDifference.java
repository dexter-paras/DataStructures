/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.nobroker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: AnagramDifference.java, v 0.1 2020-09-13 19:45 paras.chawla Exp $$
 */
public class AnagramDifference {

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {

        List<Integer> result = new ArrayList<>();

        if (a.size() == 0 || b.size() == 0) {
            return result;
        }

        for (int i = 0; i < a.size(); i++) {
            int count = minSteps(a.get(i), b.get(i));
            result.add(count);
        }
        return result;
    }

    public static int minSteps(String s, String t) {

        if(s.length()!=t.length()){
            return -1;
        }

        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) > 0) {
                map.put(ch, map.get(ch) - 1);
            } else {
                ans++;
            }
        }

        return ans;

    }

}