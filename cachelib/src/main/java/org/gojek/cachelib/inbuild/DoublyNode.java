/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.inbuild;

/**
 * @author paras.chawla
 * @version $Id: DoublyNode.java, v 0.1 2020-11-23 20:38 paras.chawla Exp $$
 */

/**
 * Doubly Linked List node
 *
 * @param <E>
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