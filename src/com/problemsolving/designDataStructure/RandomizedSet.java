/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author paras.chawla
 * @version $Id: RandomizedSet.java, v 0.1 2021-03-08 7:26 PM paras.chawla Exp $$
 */
public class RandomizedSet {

    // K - number, V - Index in arrayList
    Map<Integer, Integer> map;

    // number in ArrayList
    List<Integer> list;

    Random rand;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {

        // insert in both map and list
        if (map.containsKey(val)) { return false; }

        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    // Map  10-1, 20-2, 30-3 , 40-4, 50-5
    // List 10,20,30,40,50
    //          ^       ^
    //          idx     lastIdx
    // remove(20)
    // List 10,50,30,40 -->50(removed)
    //         ^
    // map  10-1, 50-2, 30-3 , 40-4
    // Smart way of deleting any value in ArrayList in O(1)
    public boolean remove(int val) {
        // check if val exist
        if(!map.containsKey(val)){
            return false;
        }

        int valIdx = map.get(val); //2
        // swap valIdx with last idx of list
        int lastIdx=list.size()-1;

        int lastIdxElement = list.get(lastIdx);
        list.set(valIdx,lastIdxElement);
        list.remove(lastIdx);

        // remove from dict as well
        map.put(lastIdxElement,valIdx);
        map.remove(val);

        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.remove(0));
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(0));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(0));
    }

}