/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Nov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: D1_ShortestDistance.java, v 0.1 2020-12-02 15:49 paras.chawla Exp $$
 */
public class D1_ShortestDistance {

    //              1->           2->5      3          4      2->5
    // words[] = ["practice", "makes", "perfect", "coding", "makes"]
    // word1 = “coding”, word2 = “practice”

    public static int shortestDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            List<Integer> indexList;
            if (!map.containsKey(words[i])) {
                indexList = new ArrayList<>();
            } else {
                indexList = map.get(words[i]);
            }
            indexList.add(i + 1);
            map.put(words[i], indexList);
        }

        // word1="coding" , word2="practice"
        // 4->null          1->null
        List<Integer> word1IndexList = map.get(word1);
        List<Integer> word2IndexList = map.get(word2);

        // word1="coding" , word2="makes"
        // 4->null          2->5

        int p1 = 0, p2 = 0;
        int minDist = Integer.MAX_VALUE;
        while (p1 < word1IndexList.size() && p2 < word2IndexList.size()) {
            minDist = Math.min(minDist, Math.abs(word2IndexList.get(p2) - word1IndexList.get(p1)));
            if (word1IndexList.get(p1) < word2IndexList.get(p2)) {
                p1++;
            } else {
                p2++;
            }

        }
        return minDist;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(shortestDistance(words, "coding", "practice"));
        System.out.println(shortestDistance(words, "makes", "coding"));

    }
}