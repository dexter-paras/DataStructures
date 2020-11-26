/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: MapList.java, v 0.1 2020-02-14 16:41 paras.chawla Exp $$
 * <p>
 * Design a val structure that supports all following operations in average O(1) time.
 * <p>
 * 380. Insert Delete GetRandom O(1) > insert(val): Inserts an item val to the set if not already present. > remove(val): Removes an item
 * val from the set if present. > getRandom: Returns a random element from current set of elements.
 */
public class MapList {

    Map  map;
    List list;

    public MapList() {
        map = new HashMap<>();
        list = new ArrayList();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element. remove(30)
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            //get array index from map
            int index = (int) map.get(val); //2
            // value at last index;
            int lastIndexVal = (int) list.get(list.size() - 1); //50
            // add last element into current element index
            list.set(index, lastIndexVal);//10,20,50,40,50
            //remove last element
            list.remove(list.size() - 1); //10,20,50,40
            // edit map on same index
            map.put(lastIndexVal, index);
            //remove from map as well
            map.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return (int) list.get((int)(Math.random() * list.size()));
    }

    public static void main(String[] args) {
        MapList obj = new MapList();
        System.out.println(obj.insert(10));
        System.out.println(obj.insert(20));
        System.out.println(obj.insert(30));
        System.out.println(obj.insert(40));
        System.out.println(obj.insert(50));
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(30));
        System.out.println(obj.remove(50));
    }

}