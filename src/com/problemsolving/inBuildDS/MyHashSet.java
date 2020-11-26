/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

/**
 * @author paras.chawla
 * @version $Id: HashSetDS.java, v 0.1 2020-08-02 22:59 paras.chawla Exp $$
 */
public class MyHashSet implements MySet {

    private transient HashMapDS map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMapDS<>();
    }

    @Override
    public void add(int key) {
        map.put(key, PRESENT);
    }

    @Override
    public void remove(int key) {
        map.remove(key);
    }

    @Override
    public boolean contains(int key) {
        return map.containsKey(key);
    }
}

interface MySet {

    void add(int key);

    void remove(int key);

    boolean contains(int key);
}