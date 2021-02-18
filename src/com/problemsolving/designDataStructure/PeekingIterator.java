/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.Iterator;

/**
 * @author paras.chawla
 * @version $Id: PeekingIterator.java, v 0.1 2021-02-09 8:17 AM paras.chawla Exp $$
 */
class PeekingIterator<E> implements Iterator<E> {

    Iterator<E> iterator;
    E           peekedValue;
    boolean     hasPeeked;

    public PeekingIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    // Saving peekedValue in variable so that if next on iterator called, it can get it from there only.
    public E peek() {

        if (!hasPeeked) {
            peekedValue = iterator.next();
            hasPeeked = true;
        }
        return peekedValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public E next() {

        // if peek() not called yet or peekedValue is null
        if (!hasPeeked) { return iterator.next(); }

        E toReturn = peekedValue;
        peekedValue = null;
        hasPeeked = false;

        return toReturn;
    }

    @Override
    public boolean hasNext() {

        return hasPeeked || iterator.hasNext();
    }
}