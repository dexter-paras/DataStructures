/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: KSmallestElement.java, v 0.1 2020-02-29 18:04 paras.chawla Exp $$
 */
public class KSmallestElement {

    //[9, 3, 7, -1, 5] k=2
    private int[] kSmallestElement(int[] array, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int[] smallestArray = new int[k];

        for (int i : array) {
            queue.add(i);
        }

        for (int i = 0; i < k; i++) {
            smallestArray[i] = queue.poll();
        }
        return smallestArray;
    }

    /*
      If we want the k smallest items, we don't care for large items. So max heap, we can remove top elements
      If we want the k largest items, we don't care for small items. So min heap, we can remove top elements
    */
    private static List<Integer> kSmallestElementSol2(int[] array, int k) {
        // create array size of k elements only and add in reverse order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int i : array) {
            maxHeap.add(i);

            if (maxHeap.size() == k + 1) {
                maxHeap.poll(); // over capacity by 1, eject largest item
            }
        }
        // Format to list just so we aren't returning a raw prioirty queue
        List<Integer> kSmallestElements = new ArrayList<>(maxHeap);

        return kSmallestElements;
    }

    public static void main(String[] args) {
        int array[] = {9, 3, 7, -1, 5};
        System.out.println(Arrays.toString(new KSmallestElement().kSmallestElement(array, 4)));
        System.out.println(Arrays.toString(kSmallestElementSol2(array, 3).toArray()));
    }
}