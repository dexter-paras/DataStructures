/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.genericProgramming;

/**
 * @author paras.chawla
 * @version $Id: Pair.java, v 0.1 2019-09-27 18:01 paras.chawla Exp $$
 */

/* Pair class with dataType as K and V*/
public class Pair<K, V> {

    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}