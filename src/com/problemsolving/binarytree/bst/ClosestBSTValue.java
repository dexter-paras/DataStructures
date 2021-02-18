/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: ClosestBSTValue.java, v 0.1 2020-08-09 12:02 paras.chawla Exp $$
 */
public class ClosestBSTValue {

    double closestGap = Double.MAX_VALUE;
    int    resultNode;
    // inorder Traversal Left - Root - Right

    public int closestValue(TreeNode root, double target) {

        // Edge case when root left and right child is null , closest node is root itself
        if (root.left == null && root.right == null) {
            return root.val;
        }

        return helper(root, target);

    }

    public int helper(TreeNode root, double target) {
        if (root == null) {
            return resultNode;
        }
        helper(root.left, target);

        // store root value whose closestGap is minimum
        if (Math.abs(target - root.val) < closestGap) {
            closestGap = Math.min(closestGap, Math.abs(target - root.val));
            resultNode = root.val;
        }
        helper(root.right, target);
        return resultNode;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.createBST();
        System.out.println(new ClosestBSTValue().closestValue(tree.root, -1500000000.0));
    }

    // Approach 2- Excelent approach using Binary Search rather than going full inorder
    public int closestValueBinarySearch(TreeNode root, double target) {
        int resultNode = root.val;//1
        double closestGap = Double.MAX_VALUE;//1

        while (root != null) {
            if (Math.abs(root.val - target) < closestGap) {
                closestGap = Math.abs(root.val - target);
                resultNode = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return resultNode;
    }

}