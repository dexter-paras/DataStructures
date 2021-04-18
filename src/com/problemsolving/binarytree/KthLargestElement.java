/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: KthLargestElement.java, v 0.1 2021-04-07 8:18 AM paras.chawla Exp $$
 */


/*
 *     4
 *   /  \
 *  2    7
 * / \  / \
 *1  3 6  9
 *
 */
public class KthLargestElement {

    // Iterative solution
    public int kthLargestElement(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.right;
            }

            TreeNode currNode = stack.pop();
            if (--k == 0) {
                return currNode.val;
            }
            root = currNode.left;
        }
        return -1;
    }

    // Recursively Solution
    int count = 0;
    int result = -1;

    public int kthLargestElementRecursive(TreeNode root, int k) {
        reverseInorder(root, k);
        return result;
    }

    /*
     *     4
     *   /  \
     *  2    7
     * / \  / \
     *1  3 6  9
     *
     */
    private void reverseInorder(TreeNode root, int k) {

        // base condition
        if (root == null || count >= k) {
            return;
        }

        reverseInorder(root.right, k);

        count++;

        if (count == k) {
            result = root.val;
            return;
        }
        reverseInorder(root.left, k);
    }

    public static void main(String[] args) {
        KthLargestElement obj = new KthLargestElement();

        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);

        // Recursively
        System.out.println(obj.kthLargestElementRecursive(tree.root, 0));
        System.out.println(obj.kthLargestElementRecursive(tree.root, 3));
        System.out.println(obj.kthLargestElementRecursive(tree.root, 2));
        System.out.println(obj.kthLargestElementRecursive(tree.root, 1));

        /* Iteratively
        System.out.println(obj.kthLargestElement(tree.root, 0));
        System.out.println(obj.kthLargestElement(tree.root, 1));
        System.out.println(obj.kthLargestElement(tree.root, 2));
        System.out.println(obj.kthLargestElement(tree.root, 3));
        */
    }
}