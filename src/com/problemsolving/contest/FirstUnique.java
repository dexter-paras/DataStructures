/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: FirstUnique.java, v 0.1 2020-04-28 21:44 paras.chawla Exp $$
 */
public class FirstUnique {

    LinkedHashMap<Integer, Integer> map = null;

    public FirstUnique(int[] nums) {
        map = new LinkedHashMap<>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                int temp = map.get(i);
                map.put(i, ++temp);
            }
        }
    }

    public int showFirstUnique() {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if ((int) pair.getValue() == 1) {
                return (int) pair.getKey();
            }
        }
        return -1;
    }

    public void add(int value) {
        if (!map.containsKey(value)) {
            map.put(value, 1);
        } else {
            int temp = map.get(value);
            map.put(value, ++temp);
        }
    }

    public static void main(String[] args) {
        FirstUnique obj = new FirstUnique(new int[] {7, 7, 7, 7, 7, 7});
        System.out.println(obj.showFirstUnique());
        obj.add(7);
        System.out.println(obj.showFirstUnique());
        obj.add(3);
        System.out.println(obj.showFirstUnique());
        obj.add(3);
        System.out.println(obj.showFirstUnique());
        obj.add(17);
        System.out.println(obj.showFirstUnique());
    }
}