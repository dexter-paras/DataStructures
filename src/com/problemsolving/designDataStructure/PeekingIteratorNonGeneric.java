/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PeekingIterator.java, v 0.1 2021-02-09 8:17 AM paras.chawla Exp $$
 * Intuitions
 */
class PeekingIteratorNonGeneric implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer           peekedValue;
    boolean           hasPeeked;

    public PeekingIteratorNonGeneric(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    // Saving peekedValue in variable so that if next on iterator called, it can get it from there only.
    public Integer peek() {

        if (!hasPeeked) {
            peekedValue = iterator.next();
            hasPeeked = true;
        }
        return peekedValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {

        // if peek() not called yet or peekedValue is null
        if (!hasPeeked) { return iterator.next(); }

        Integer toReturn = peekedValue;
        peekedValue = null;
        hasPeeked = false;

        return toReturn;
    }

    @Override
    public boolean hasNext() {

        return hasPeeked || iterator.hasNext();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        Iterator<Integer> iterator = list.iterator();
        PeekingIteratorNonGeneric obj = new PeekingIteratorNonGeneric(iterator);

        System.out.println(obj.next());
        System.out.println(obj.peek());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
        System.out.println(obj.peek());
        System.out.println(obj.next());

    }
}