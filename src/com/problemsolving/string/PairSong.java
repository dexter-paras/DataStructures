/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.HashMap;

/**
 * @author paras.chawla
 * @version $Id: PairSong.java, v 0.1 2021-03-11 10:14 PM paras.chawla Exp $$
 */
public class PairSong {

    public int numPairsDivisibleBy60(int[] time) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int count = 0;

        for (int i : time) {
            int cal = (i / 60) * 60 + 60 - i;

            // check if cal exist in Map
            if (map.containsKey(cal)) {
                count += map.get(cal);
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return count;
    }

    public int numPairsDivisibleBy602(int[] time) {
        int count = 0, n = time.length;
        for (int i = 0; i < n; i++) {
            // j starts with i+1 so that i is always to the left of j
            // to avoid repetitive counting
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        PairSong obj = new PairSong();
        System.out.println(obj.numPairsDivisibleBy602(new int[] {15, 63, 451, 213, 37, 209, 343, 319}));

        /*    Map       cal
        15    15-1      45
        63    63-1      57
        451   451-1     29
        213
        37
        209
        343
        319
         */
    }
}