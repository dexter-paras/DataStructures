/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.linkedList.model;

/**
 * @author paras.chawla
 * @version $Id: ListNode.java, v 0.1 2019-06-24 21:51 paras.chawla Exp $$
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}