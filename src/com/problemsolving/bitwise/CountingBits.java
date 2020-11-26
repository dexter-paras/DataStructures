/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: CountingBits.java, v 0.1 2020-04-11 12:02 paras.chawla Exp $$
 */
public class CountingBits {

    Map<Integer, Integer> map = new HashMap<>();

    int[] prime = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
            109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241,
            251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397,
            401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563,
            569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719,
            727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883,
            887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    public int[] countBits(int num) {
        if (num == 0) {
            return new int[] {0};
        } else if (num == 1) {
            return new int[] {1};
        }

        map = createMap(map);
        int[] ans = new int[num + 1];

        ans[0] = 0;
        ans[1] = 1;
        for (int i = 2; i < ans.length; i++) {

            // counting one for prime numbers
            if (ans[i] == 0) {
                if (map.containsKey(i)) {
                    ans[i] = map.get(i);

                    //setting array values
                    for (int j = i * 2; j < ans.length; j *= 2) {
                        ans[j] = map.get(i);
                    }
                }else{
                    ans[i]=countOne(i);
                }
            }
        }
        return ans;
    }

    private Map<Integer, Integer> createMap(Map<Integer, Integer> map) {
        for (int num : prime) {
            map.put(num, countOne(num));
        }
        return map;
    }

    private int countOne(int num) {
        int oneCount = 0;
        for (int i = num; i > 0; i /= 2) {
            if (i % 2 == 1) { oneCount++; }
        }
        return oneCount;
    }

    public static void main(String[] args) {
        new CountingBits().countBits(500);
    }
}