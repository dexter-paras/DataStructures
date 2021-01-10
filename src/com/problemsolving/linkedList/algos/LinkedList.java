/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.linkedList.algos;

/**
 * @author paras.chawla
 * @version $Id: LinkedList.java, v 0.1 2019-06-27 22:47 paras.chawla Exp $$
 */
public class LinkedList<E> {
    Node head;

    static class Node {
        int data;
        Node next;

        Node() {

        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * 2.0 printing of a linkedList
     *
     * @param printNode
     */
    private void printLinkedList(Node printNode) {
        Node tmp = printNode;
        while (tmp != null) {
            System.out.print(tmp.data + "->");
            tmp = tmp.next;
        }
        System.out.print("null");
    }

    /**
     * 3.0 length of linkedList
     *
     * @return
     */
    private int lengthLinkedList() {
        int count = 0;
        Node tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        System.out.println("\n" + "Length of LinkedList is " + count);
        return count;
    }

    /**
     * 4.0 insertion of a node in a linkedList
     *
     * @param newNode
     * @param position
     */
    private void insertElement(Node newNode, int position) {
        Node tmp = head;
        int length = lengthLinkedList();
        int traversalCount = 1;
        if (position <= length && position >= 1) {
            while (tmp != null && traversalCount < position) {
                traversalCount++;
                tmp = tmp.next;
            }
            newNode.next = tmp.next;
            tmp.next = newNode;
        } else if (position == 0) {
            newNode.next = head;
            head = newNode;
        }
        System.out.println("Element inserted at position " + position);
        printLinkedList(head);
    }

    /**
     * 5.0 search an element using recursie/iterative approach
     *
     * @param element
     */
/*    private int searchElement(EAST element, TreeNode tmp) {
        int count = 1;
        while (tmp != null && !tmp.val.equals(element)) {
            count++;
            tmp = tmp.next;
            searchElement(element, tmp);
        }
        return count;
    }*/

    /* 7.0 remove the n-th node from the end of list
     * 1->2->3-4->-5->6->null , n =3*/
    private Node removeNthNode(int n) {
        Node mainNode = head;
        Node referenceNode = head;
        int count = 1;
        if (head != null && n >= 1 && n < lengthLinkedList()) {
            while (referenceNode != null && count <= n) {
                referenceNode = referenceNode.next;
                count++;
            }
            while (referenceNode.next != null) {
                mainNode = mainNode.next;
                referenceNode = referenceNode.next;
            }
            mainNode.next = mainNode.next.next;
        } else if (n >= 1 && n == lengthLinkedList()) {
            head = mainNode.next;
        }
        return head;
    }

    /* 8.0 Merge Two Sorted Lists
     * l1  1->7->11->15->19->null
     * l2  4->13->null
     * l3  1->4->7->11->13->15->19->null
     */
    private Node mergeNodes(Node l1, Node l2) {
        if (l1 != null && l2 == null)
            return l1;
        else if (l2 != null && l1 == null)
            return l2;
        Node newNode;
        Node temp = null;
        Node mergeNode = null;
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                newNode = new Node(l1.data);
                if (temp == null) {
                    temp = newNode;
                    mergeNode = temp;
                } else {
                    temp.next = newNode;
                    temp = temp.next;
                }
                l1 = l1.next;
            } else if (l2.data < l1.data) {
                newNode = new Node(l2.data);
                if (temp == null) {
                    temp = newNode;
                    mergeNode = temp;
                } else {
                    temp.next = newNode;
                    temp = temp.next;
                }
                l2 = l2.next;
            }
        }
        while (l1 != null && l2 == null) {
            newNode = new Node(l1.data);
            temp.next = newNode;
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null && l1 == null) {
            newNode = new Node(l2.data);
            temp.next = newNode;
            temp = temp.next;
            l2 = l2.next;
        }
        return mergeNode;
    }

    /* 8.0 Merge Two Sorted Lists
     * l1       1->7->11->15->19->null
     * l2       4->13->null
     * Output   1->4->7->11->13->15->19->null
     */
    private Node mergeNodesSol2(Node l1, Node l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        Node head = new Node(0);
        Node handler = head;
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                handler.next = l1;
                l1 = l1.next;
            } else {
                handler.next = l2;
                l2 = l2.next;
            }
            handler=handler.next;
        }
        if (l1 != null) {
            handler.next = l1;
        } else if (l2 != null) {
            handler.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args) {

        /* 1.0 Creation of linkedList*/
        LinkedList list = new LinkedList();
        Node l1 = new Node(1);
        Node node2 = new Node(7);
        Node node3 = new Node(11);
        Node node4 = new Node(15);
        Node node5 = new Node(19);

        l1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node l2 = new Node(4);
        Node node22 = new Node(13);

        l2.next = node22;

        /*
         *//* 2.0 printing of a linkedList*//*
        list.printLinkedList(list.head);

        *//* 3.0 insertion of a node in a linkedList*//*
        //list.insertElement(new TreeNode("4"), 4);

        *//* 4.0 length of linkedList*//*
        int length = list.lengthLinkedList();

        *//* 5.0 search an element using recursie/iterative approach*//*
        int count = list.searchElement("4", list.head);

        if (count <= length)
            System.out.println("Element found at " + count);
        else
            System.out.println("Element not found");

        *//* 6.0 Detect loop in linkedlist
        Approach 1 - fast/slow pointers APproach2 - use hashSet
        *//*

         *//* 7.0 remove the n-th node from the end of list*//*
        TreeNode leftNode = list.removeNthNode(6);
        list.printLinkedList(leftNode);*/

        /*8.0 Merge two linked-list*/
        Node mergedNode = list.mergeNodesSol2(l1, l2);
        list.printLinkedList(mergedNode);
    }

}