/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.sorting;

import com.problemsolving.linkedList.model.ListNode;

/**
 * @author paras.chawla
 * @version $Id: SortLinkedList.java, v 0.1 2020-10-19 15:08 paras.chawla Exp $$ Given the head of a linked list, return the list after
 * sorting it in ascending order.
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortLinkedList {

    public ListNode sortList(ListNode head) {

        // base condition
        if (head == null || head.next == null) {
            return head;
        }
        return null;
    }

    // Merge Two Linked List
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode endOfSortedList = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                endOfSortedList.next = l1;
                l1 = l1.next;
            } else {
                endOfSortedList.next = l2;
                l2 = l2.next;
            }

            endOfSortedList = endOfSortedList.next;
        }

        if (l1 != null) {
            endOfSortedList.next = l1;
        }

        if (l2 != null) {
            endOfSortedList.next = l2;
        }

        return dummyHead.next;
    }

    // Finding middle Node of LinkedList
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // base condition
        if (head == null || head.next == null) {
            return head;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
       /* SortLinkedList obj = new SortLinkedList();
        ListNode l1 = new ListNode(3);
        l1.next();
        ListNode l1 = new ListNode(3);

        ListNode l1 = new ListNode(3);
        ListNode l1 = new ListNode(3);
        ListNode l1 = new ListNode(3);

        obj.mergeTwoLists(l1,l2);*/
    }

}