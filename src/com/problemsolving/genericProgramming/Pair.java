/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.genericProgramming;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) &&
                value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}