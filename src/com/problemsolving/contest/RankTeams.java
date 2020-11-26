/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: RankTeams.java, v 0.1 2020-03-01 22:29 paras.chawla Exp $$
 */
public class RankTeams {

    private static String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int l = votes[0].length();

        // A -[0] 5, [1] 0 ,[2] 0
        // B -[0] 0, [1] 2 ,[2] 3
        // C -[0] 0, [1] 3 ,[2] 2
        for (String vote : votes) {
            for (int i = 0; i < l; i++) {
                char c = vote.charAt(i);
                map.putIfAbsent(c, new int[l]);
                map.get(c)[i]++;
            }
        }

        // Sorting A and B then B and C and then A and C based on values in arrays
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> {
            for (int i = 0; i < l; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        rankTeams(new String[] {"ABC", "ACB", "ABC", "ACB", "ACB"});
    }
}