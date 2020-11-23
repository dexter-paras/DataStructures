/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.inbuild;

/**
 * @author paras.chawla
 * @version $Id: DoublyLinkedList.java, v 0.1 2020-11-23 21:42 paras.chawla Exp $$
 */

/**
 * Creating a seperate class so that same can be used in other Eviction policy or anywhere else.
 * A datastructure which support creating a list with non-contiguous memory allocation.
 * - Can't access random element using index
 * - Can traverse the list bidirectional if pointer to a node is given
 * - Adding new element at the tail
 */
public class DoublyLinkedList<E> {

    private DoublyNode<E> dummyHead;
    private DoublyNode<E> dummyTail;

    public DoublyLinkedList() {
        this.dummyHead = new DoublyNode<E>(null);
        this.dummyTail = new DoublyNode<E>(null);

        // Also Initially there are no items , just join dummyHead and Tail, we can add items in between them easily.
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    /**
     * Detach any random node from Doubly Linked List
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