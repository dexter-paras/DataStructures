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
        TreeNode c = root;

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (c == null) { return ans; }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        s1.push(root);

        while (!s1.isEmpty()) {
            List<Integer> tmp = new ArrayList<Integer>();
            while (!s1.isEmpty()) {
                c = s1.pop();
                tmp.add(c.val);
                if (c.left != null) { s2.push(c.left); }
                if (c.right != null) { s2.push(c.right); }
            }
            ans.add(tmp);
            tmp = new ArrayList<Integer>();
            while (!s2.isEmpty()) {
                c = s2.pop();
                tmp.add(c.val);
                if (c.right != null) { s1.push(c.right); }
                if (c.left != null) { s1.push(c.left); }
            }
            if (!tmp.isEmpty()) { ans.add(tmp); }
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createSerializeDeserializeBT(tree);
        new ZigZagTraversal().zigzagLevelOrder(tree.root);
    }
}