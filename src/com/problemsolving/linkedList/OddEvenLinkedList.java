/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.linkedList;

/**
 * @author paras.chawla
 * @version $Id: OddEvenLinkedList.java, v 0.1 2020-07-28 17:17 paras.chawla Exp $$
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next!=null) {
            odd.next = even.next;
            odd = even.next;

            even.next = odd.next;
            even = odd.next;

        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(4);
        //head.next.next.next.next.next.next = new ListNode(7);
        new OddEvenLinkedList().oddEvenList(head);
    }
}