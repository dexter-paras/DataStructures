/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LRUCache.java, v 0.1 2020-10-01 15:32 paras.chawla Exp $$
 *
 * Map deals with Keys only
 * DLL deals with Values and next and previous Nodes
 * capacity = 4
 *              Map K       V                      DLL
 * put(1,5) ->      1   ListNode1(5,n,p)           LN1->null
 * put(2,6) ->      2   ListNode2(6,n,LN1)         LN2->LN1->null
 * put(3,7) ->      3   ListNode3(7,n,LN2)         LN3-> LN2->LN1->null
 * put(4,8) ->      4   ListNode4(8,n,LN3)         LN4->LN3-> LN2->LN1->null
 * get(2)   ->  get(4) -> LN4 -> 8                 LN2->LN4->LN3->LN1->null
 * put(5,10)->      5   ListNode5(10,n,p)          Evict Tail Node  LN2->LN4->LN3->null
 *                           Add new node in front LN5->LN2->LN4->LN3->null
 */

public class LRUCache {

    // Key - Integer ; Value as ListNode stored in DLL
    Map<Integer, ListNode> hashtable = new HashMap<>();

    // Doubly Linked List
    ListNode head;
    ListNode tail;

    int totalItemsInCache;
    int capacity;

    public LRUCache(int capacity) {
        // Cache starts empty and capacity is set by client
        totalItemsInCache = 0;
        this.capacity = capacity;

        // Dummy head and tail nodes to avoid empty states
        head = new ListNode();
        tail = new ListNode();

        // Wire the head and tail together
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode node = hashtable.get(key);

        if (node == null) {
            return -1;
        }

        // Item has been accessed. Move to the front of the cache
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        ListNode node = hashtable.get(key);

        if (node == null) {
            // Item not found, create a new entry
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;

            // Add to the hashtable and the actual list that represents the cache
            hashtable.put(key, newNode);
            addToFront(newNode);
            totalItemsInCache++;

            // If over capacity remove the LRU item
            if (totalItemsInCache > capacity) {
                removeLRUEntry();
            }
        } else {
            // If item is found in the cache, just update it and move it to the head of the list
            node.value = value;
            moveToHead(node);
        }
    }

    private void removeLRUEntry() {
        ListNode tail = popTail();

        hashtable.remove(tail.key);
        --totalItemsInCache;
    }

    private ListNode popTail() {
        ListNode tailItem = tail.prev;
        removeFromList(tailItem);

        return tailItem;
    }

    private void addToFront(ListNode node) {
        // Wire up the new node being to be inserted
        node.prev = head;
        node.next = head.next;

      /*
        Re-wire the node after the head. Our node is still sitting "in the middle of nowhere".
        We got the new node pointing to the right things, but we need to fix up the original
        head & head's next.

        head <-> head.next <-> head.next.next <-> head.next.next.next <-> ...
        ^            ^
        |- new node -|

        That's where we are before these next 2 lines.
      */
        head.next.prev = node;
        head.next = node;
    }

    private void removeFromList(ListNode node) {
        ListNode savedPrev = node.prev;
        ListNode savedNext = node.next;

        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;
    }

    private void moveToHead(ListNode node) {
        removeFromList(node);
        addToFront(node);
    }

    private class ListNode {
        int key;
        int value;

        ListNode prev;
        ListNode next;
    }
}