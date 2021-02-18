/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: TrimBST.java, v 0.1 2021-02-03 8:18 AM paras.chawla Exp $$
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {

        if (root == null) { return root; }

        // if root.val > high, then trimmed binary tree must occur to the left of root
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        // if root.val < low, then trimmed binary tree must occur to the right of root
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }

        // if root lies within low and high, trim both sides of the tree
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;

    }

    /* Example 1
     *
     *     4 <- root
     *   /  \
     *  2    7
     * / \  / \
     *1  3 6  9
     *
     * low=1, high=3
     *
     * Example 2
     *
     *     3 <- root
     *   /  \
     *  0    4
     *   \
     *    2
     *   /
     *  1
     *
     * low=1, high=4
     */

    public static void main(String[] args) {
        TrimBST obj = new TrimBST();
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.right = new TreeNode(2);
        //BinaryTree.createBinarySearchTree2(tree);

        System.out.println(obj.trimBST(tree.root, 2, 4));
    }

}