/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Nov;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: N8_BinaryTreeTilt.java, v 0.1 2020-11-09 10:54 paras.chawla Exp $$
 * https://leetcode.com/problems/binary-tree-tilt/solution/
 * Post-Order
 */
public class N8_BinaryTreeTilt {

    private static int totalTilt = 0;

    // sum of a node = sum(node.left) + sum(node.right) + node.val;
    private static int sum(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftSum = sum(node.left);
        int rightSum = sum(node.right);

        totalTilt += Math.abs(leftSum - rightSum);

        return leftSum + rightSum + node.val;
    }

    // tilt(node)= |sum(node.left) - sum(node.right)|
    // sum(node) = sum(node.left) + sum(node.right) + node.val;
    public static int findTilt(TreeNode root) {
        totalTilt = 0;
        System.out.println(sum(root));
        return totalTilt;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.createSerializeDeserializeBT(tree);
        findTilt(tree.root);
    }
}