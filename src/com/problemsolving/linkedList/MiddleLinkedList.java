/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.linkedList;

/**
 * @author paras.chawla
 * @version $Id: MiddleLinkedList.java, v 0.1 2020-04-08 13:27 paras.chawla Exp $$
 */
public class MiddleLinkedList {

    public ListNode middleNode(ListNode head) {
        //if only 1 node
        if (head.next == null) {
            return head;
        }

        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }
}

class ListNode {
    int      val;
    ListNode next;

    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}