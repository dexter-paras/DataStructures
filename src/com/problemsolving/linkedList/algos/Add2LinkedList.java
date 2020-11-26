/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.linkedList.algos;

import com.problemsolving.linkedList.model.ListNode;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: Add2LinkedList.java, v 0.1 2020-11-08 11:01 paras.chawla Exp $$ Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 ->
 * 0 -> 7
 */
public class Add2LinkedList {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 != null) { return l2; } else if (l2 == null && l1 != null) { return l1; }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        Stack<Integer> stack3 = new Stack<>();
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + carry;
            if (sum > 9) {
                int digit = sum % 10;//0
                carry = sum / 10;//1
                stack3.add(digit);//
            } else {
                stack3.add(sum);
                carry=0;
            }
        }

        while (!stack1.isEmpty()) {
            if (carry != 0) {
                int sum = carry + stack1.pop();
                // 1+9
                if (sum > 9) {
                    int digit = sum % 10;//0
                    carry = sum / 10;//1
                    stack3.add(digit);//
                    stack3.add(carry);
                } else {
                    stack3.add(sum);
                }
            }
            if (!stack1.isEmpty()) { stack3.add(stack1.pop()); }
        }

        while (!stack2.isEmpty()) {
            if (carry != 0) {
                int sum = carry + stack2.peek();
                // 1+9
                if (sum > 9) {
                    int digit = sum % 10;//0
                    carry = sum / 10;//1
                    stack2.add(digit);//
                    stack2.add(carry);
                } else {
                    stack2.add(sum);
                }
            }
            if (!stack2.isEmpty()) { stack3.add(stack2.pop()); }
        }

        if(carry!=0){
            stack3.add(carry);
        }

        // create Linked List from stack

        ListNode node = new ListNode(stack3.pop());
        ListNode temp = node;
        while (!stack3.isEmpty()) {
            node.next = new ListNode(stack3.pop());
            node = node.next;
        }

        return temp;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(addTwoNumbers(l1, l2));
    }

}
