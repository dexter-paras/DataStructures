/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.genericProgramming;

/**
 * @author paras.chawla
 * @version $Id: SortedPair.java, v 0.1 2020-04-05 23:39 paras.chawla Exp $$
 */

// SortedPair of Type T
public class SortedPair<T extends Comparable<T>> {

    private T first;
    private T second;

    public SortedPair(T first, T second) {
        if (first.compareTo(second) < 0) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    @Override
    public String toString() {
        return "SortedPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}