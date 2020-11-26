/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: ZigZagTraversal.java, v 0.1 2020-08-18 21:13 paras.chawla Exp $$
 */
public class ZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.add(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            List<Integer> currList = new ArrayList<>();

            // Stack traversing from left to right i.e. s1 [3|2]
            while (!s1.isEmpty()) {
                TreeNode currNode = s1.pop();
                currList.add(currNode.val);
                if (currNode.left != null) {
                    s1.push(currNode.left);
                }
                if (currNode.right != null) {
                    s1.push(currNode.right);
                }
            }
            result.add(currList);
            currList = new ArrayList<>();

            // Stack traversing from right to left i.e. s2 [4|5]
            while (!s2.isEmpty()) {
                TreeNode currNode = s2.pop();
                currList.add(currNode.val);

                if (currNode.right != null) {
                    s2.push(currNode.right);
                }
                if (currNode.left != null) {
                    s2.push(currNode.left);
                }
            }
            if (!currList.isEmpty()) { result.add(currList); }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createSerializeDeserializeBT(tree);
        new ZigZagTraversal().zigzagLevelOrder(tree.root);
    }
}