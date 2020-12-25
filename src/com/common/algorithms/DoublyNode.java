/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.common.algorithms;

/**
 * @author paras.chawla
 * @version $Id: DoublyNode.java, v 0.1 2020-12-23 12:41 paras.chawla Exp $$
 */
public class DoublyNode<E> {

    DoublyNode<E> prev;
    DoublyNode<E> next;
    E             element;

    public E getElement() {
        return element;
    }

    public DoublyNode(E element) {
        this.prev = prev;
        this.next = next;
        this.element = element;
    }
}