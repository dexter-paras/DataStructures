/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.stackandqueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author paras.chawla
 * @version $Id: kthPerson.java, v 0.1 2020-11-22 12:53 paras.chawla Exp $$
 */
public class kthPerson {

    public static int[] kthPerson(int busSize, int[] patienceLimit, int[] queries) {

        // output
        int[] result = new int[queries.length];

        // sort patience limit
        Arrays.sort(patienceLimit);

        // Convert patienceLimit into Deque
        Deque<Integer> deque = new LinkedList();

        for (int i : patienceLimit) {
            deque.add(i);
        }

        int lastPerson = 0;
        for (int i = 0; i < queries.length; i++) {
            int capacity = busSize;
            int busArrive = queries[i];

            int pollCount = 0;
            while (capacity-- > 0 && !deque.isEmpty() && deque.peekFirst() >= busArrive) {
                deque.pollFirst();
                lastPerson++;
                pollCount++;
            }
            result[i] = pollCount > 0 ? lastPerson : 0;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthPerson(2, new int[] {1, 2, 3, 4}, new int[] {1, 3, 4})));
        System.out.println(Arrays.toString(kthPerson(2, new int[] {3, 4}, new int[] {1, 3, 4})));
    }
}