/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: CountingElements.java, v 0.1 2020-04-07 15:30 paras.chawla Exp $$
 */
public class CountingElements {

    //arr = [1,1,3,2,3,5,0]
    //[1,1,2,0]
    public int countElements(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                int temp = map.get(arr[i]);
                map.put(arr[i], ++temp);
            }
            if (map.containsKey(Math.abs(arr[i] - 1))) {
                count += map.get(Math.abs(arr[i] - 1));
                map.put(Math.abs(arr[i] - 1), 0);
            }
        }
        System.out.println(count);
        return count;
    }

    //arr = [1,1,3,2,3,5,0]
    //[1,1,2,0]
    public int countElements2(int[] arr) {
        int count = 0;
        int[] countArray = new int[1001];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (countArray[arr[i] + 1] == 1) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    /*Approach2 - Use HashSet and loop twice*/

    /*Approach3 - Sort array o(nlogn) and then loop array and count*/

    public static void main(String[] args) {
        new CountingElements().countElements2(new int[] {1, 2, 3});
        new CountingElements().countElements2(new int[] {1, 1, 3, 3, 5, 5, 7, 7});
        new CountingElements().countElements2(new int[] {1, 3, 2, 3, 5, 0});
        new CountingElements().countElements2(new int[] {1, 1, 2, 2});
    }
}