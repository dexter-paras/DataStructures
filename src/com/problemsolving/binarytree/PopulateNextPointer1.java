/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: PopulateNextPointer1.java, v 0.1 2021-04-07 11:05 AM paras.chawla Exp $$
 */
public class PopulateNextPointer1 {

    // TC - O(n)
    // SC - O(n/2), store maximum n/2 leaf nodes
    // Approach 1 - Using BFS
    public Node connect(Node root) {

        if (root == null) { return root; }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Node currNode = queue.poll();

                if (i < size - 1) {
                    currNode.next = queue.peek();
                }

                if (currNode.left != null) { queue.offer(currNode.left); }

                if (currNode.right != null) { queue.offer(currNode.right); }
            }
        }

        return root;
    }

    // TC - O(1)
    // Approach 2 - Recursive function
    public Node connect2(Node root) {

        if (root == null) { return root; }

        Node temp = root;
        helper(temp);

        return root;
    }

    private void helper(Node temp) {

        if (temp == null) { return; }

        if (temp.left != null && temp.right != null) {
            temp.left.next = temp.right;
        }

        if (temp.next != null && temp.right != null && temp.next.left != null) {
            temp.right.next = temp.next.left;
        }

        helper(temp.left);
        helper(temp.right);
    }

    public static void main(String[] args) {
        PopulateNextPointer1 obj = new PopulateNextPointer1();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        obj.connect2(node1);

        System.out.println(node1);
    }
}

class Node {
    public int  val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}