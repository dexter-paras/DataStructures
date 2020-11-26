/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: RotateList.java, v 0.1 2020-10-08 10:20 paras.chawla Exp $$
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || k == 0) {
            return head;
        }

        ListNode first = head, temp = head;
        ListNode last = null;
        ListNode result = null;
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
            if (head != null && head.next == null) {
                last = head;
            }
        }

        if (length == 1) {
            return head;
        }

        int r = length - (k % length);
        int count = 1;
        while (count < r) {
            count++;
            temp = temp.next;
        }
        if (temp.next != null) {
            result = temp.next;
        }else{
            return first;
        }
        temp.next = null;
        last.next = first;
        return result;
    }

    public static void main(String[] args) {
        RotateList obj = new RotateList();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        //ListNode node3 = new ListNode(3);
        //ListNode node4 = new ListNode(4);
        //ListNode node5 = new ListNode(5);
        node1.next = node2;
        //node2.next = node3;
        //node3.next = node4;
        //node4.next = node5;

        //System.out.println(obj.rotateRight(node1, 1));
        //System.out.println(obj.rotateRight(node1, 2));
        System.out.println(obj.rotateRight(node1, 2));
    }

}

class ListNode {
    int      val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}