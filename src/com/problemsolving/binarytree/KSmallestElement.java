/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: KSmallestElement.java, v 0.1 2020-07-26 12:44 paras.chawla Exp $$
 */
public class KSmallestElement {

    // root = [3,1,4,null,2], k = 1
    public int kthSmallest(TreeNode root, int k) {

        List<Integer> result = new ArrayList<>();
        //inorder(root, result);
        return inorderIterative(root, result, k);
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add((int) root.val);
        inorder(root.right, result);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);
        System.out.println(new KSmallestElement().kthSmallestSol2(tree.root, 4));
    }

    private int inorderIterative(TreeNode root, List<Integer> result, int k) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {

            // 1. Keep going left just like recursive function going left
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }

            // 2. when curr points to null and can't go further,remove node from stack and add in result
            TreeNode node = stack.pop();
            result.add((int) node.val);
            if (result.size() == k) {
                return result.get(k - 1);
            }

            // 3. point curr to poped node right child
            curr = node.right;
        }
        return 0;
    }

    /*
     *     4
     *   /  \
     *  2    7
     * / \  / \
     *1  3 6  9
     *
     *
     * k=3
     */
    public int kthSmallestSol2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root !=null) {

            // 1. Keep going left just like recursive function going left
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 2. when curr points to null and can't go further,remove node from stack
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }
}