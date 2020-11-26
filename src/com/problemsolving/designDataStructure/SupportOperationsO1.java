/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author paras.chawla
 * @version $Id: SupportOperationsO1.java, v 0.1 2020-02-13 07:55 paras.chawla Exp $$
 * <p>
 * Design a val structure that supports all following operations in average O(1) time.
 * <p>
 * 1) insert(val): Inserts an item val to the set if not already present.
 * 2) remove(val): Removes an item val from the set if present.
 * 3) getRandom: Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 */
public class SupportOperationsO1 {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet();
        HashMap<Integer,Integer> map = new HashMap<>();
        System.out.println(set.add(1));
        System.out.println(set.add(1));
        System.out.println(set.add(2));
        System.out.println(set.remove(3));
        System.out.println(set.remove(2));
    }
}