/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.common.algorithms;

/**
 * @author paras.chawla
 * @version $Id: DoublyLinkedList.java, v 0.1 2020-12-23 12:35 paras.chawla Exp $$
 */

/* A datastructure which support creating a list with non-contiguous memory allocation.
 * - Can't access random element using index
 * - Can traverse the list bidirectional if pointer to a node is given
 * - Adding new element at the tail
 */
public class DoublyLinkedList<E> {

    DoublyNode<E> dummyHead;
    DoublyNode<E> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new DoublyNode<>(null);
        dummyTail = new DoublyNode<>(null);

        // Also Initially there are no items , just join dummyHead and Tail, we can add items in between them easily.
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

    }

    /**
     * Detach any random node from Doubly Linked List
     * O(1)
     * @param node
     */
    public void detachNode(DoublyNode<E> node){
        if(node!=null) {
            DoublyNode prev = node.prev;
            DoublyNode next = node.next;

            prev.next=next;
            next.prev=prev;

        }
    }

    /**
     * Adding node at the end
     * O(1)
     * @param node
     */
    public void addNodeAtLast(DoublyNode<E> node){
        DoublyNode tailPrev= dummyTail.prev;
        tailPrev.next=node;
        node.prev=tailPrev;
        node.next=dummyTail;
        dummyTail.prev=node;
    }

    /**
     *
     * @param element
     * @return Reference to new node created for the element
     * O(1)
     */
    public DoublyNode addElementAtLast(E element){
        DoublyNode newNode = new DoublyNode(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    /**
     * if any node exist
     * @return
     */
    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    /**
     * @return First Node of DLL
     */
    public DoublyNode getFirstNode(){

        // if no item present in DLL , return null
        if(!isItemPresent()){
            return null;
        }
        return dummyHead.next;
    }

    /**
     * Get last Node of DLL
     * @return
     */
    public DoublyNode getLastNode(){
        if(!isItemPresent()){
            return null;
        }
        return dummyTail.prev;
    }
}