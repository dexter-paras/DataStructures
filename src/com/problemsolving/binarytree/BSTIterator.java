/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: BSTIterator.java, v 0.1 2021-02-09 11:05 AM paras.chawla Exp $$
 */

// Iterator over the in-order traversal of a binary search tree (BST):
public class BSTIterator {

    // Approach 1 - Full traversal SC O(n) and TC O(n)

    /*
    LinkedList<Integer> queue;

    // do inorder and store in Dequeue
    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        inorder(root, queue);
    }

    private void inorder(TreeNode root, Queue<Integer> queue) {

        if (root == null) { return; }

        inorder(root.left, queue);
        queue.add(root.val);
        inorder(root.right, queue);
    }

    public int next() {
        return queue.pollFirst();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }*/

    // Approach 2 - Using Stack next() and hasNext() to run in average O(1)

    //  use Stack to store directed left children from root.
    Stack<TreeNode> stack; // 15 ->10
    TreeNode        node;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        node = root;
    }

    // When next() be called, I just pop one element and process its right child as new root.
    public int next() {

        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        // node is null now, no more left node can be added in STACK
        TreeNode node = stack.pop(); //7
        int toReturn = node.val;
        node = node.right; // 9
        return toReturn;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);

        BSTIterator obj = new BSTIterator(tree.root);
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
    }
}