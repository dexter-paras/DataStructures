/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: MinElementInCollection.java, v 0.1 2020-08-09 20:27 paras.chawla Exp $$
 */
public class MinElementInCollection {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        // To Find minimum in collection of list
        System.out.println(Collections.min(list));

        // To Find minimum in collection of list closest to target value
        // -1 if o2 > o1 ; 1 if o1 > o2
        int target = 37;
        System.out.println(Collections.min(list, (Integer o1, Integer o2) -> Math.abs(target - o1) < Math.abs(target - o2) ? -1 : 1));
    }
}