/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.heap;

import com.problemsolving.linkedList.model.ListNode;

import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: MergeKSortedLinkedList.java, v 0.1 2020-12-25 12:22 AM paras.chawla Exp $$
 * https://leetcode.com/problems/merge-k-sorted-lists/discuss/10809/13-lines-in-Java
 */
public class MergeKSortedLinkedList {

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode head = new ListNode(0);
        ListNode tail = head;

        // base condition
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        // Add head node of all listNodes
        for (ListNode headNode : lists) {
            if (headNode != null) { minHeap.offer(headNode); }
        }

        while (!minHeap.isEmpty()) {
            tail.next= minHeap.poll();
            tail = tail.next;

            if(tail.next!=null){
                minHeap.offer(tail.next);
            }
        }

        return head.next;
    }
}