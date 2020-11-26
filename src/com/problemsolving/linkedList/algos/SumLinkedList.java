/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.linkedList.algos;

import com.problemsolving.linkedList.model.ListNode;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: SumLinkedList.java, v 0.1 2019-06-24 21:53 paras.chawla Exp $$
 */
public class SumLinkedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNumbers(l1, l2);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null)
            return l2;
        else if (l2 == null && l1 != null)
            return l1;

        Stack stack[] = new Stack[2];
        stack[0] = composeStackOutOfList(l1);
        stack[1] = composeStackOutOfList(l2);

        return additionStack(stack[0], stack[1]);
    }

    private static ListNode additionStack(Stack stack1, Stack stack2) {
        ListNode head = new ListNode();
        ListNode tmp = head;
        int extra = 0;
        while (stack1.size() != 0 && stack2.size() != 0) {
            tmp.val = (int) stack1.pop() + (int) stack2.pop() + extra;
            extra = tmp.val / 10;
            ListNode newNode = new ListNode(tmp.val);
            newNode.next = new ListNode();
            tmp = newNode;
        }
        if (stack1.size() == 0 && stack2.size() == 0)
            return head;
        else if (stack1.size() == 0 && stack2.size() != 0) {
            while (stack2.size() != 0) {
                head.next = new ListNode((int) stack2.pop());
            }
        } else if (stack1.size() != 0 && stack2.size() == 0) {
            while (stack1.size() != 0) {
                head.next = new ListNode((int) stack1.pop());
            }
        }
        return head;
    }

    private static Stack composeStackOutOfList(ListNode listNode) {
        Stack stack = new Stack();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        return stack;
    }
}