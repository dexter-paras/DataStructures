/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

/**
 * @author paras.chawla
 * @version $Id: HashSetDS.java, v 0.1 2020-08-02 22:59 paras.chawla Exp $$
 */
public class HashSetDS<K> implements SetDS<K> {

    private transient HashMapDS map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    public HashSetDS() {
        map = new HashMapDS<>();
    }

    @Override
    public void add(K key) {
        map.put(key, PRESENT);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public static void main(String[] args) {
        HashSetDS set = new HashSetDS();
        set.add(2);
        set.add(1);
        set.add(5);
        set.add(7);
        set.remove(5);
    }
}

interface SetDS<K> {

    void add(K key);

    void remove(K key);

    boolean contains(K key);
}