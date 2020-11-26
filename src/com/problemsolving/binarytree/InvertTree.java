/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: InvertTree.java, v 0.1 2020-07-26 18:27 paras.chawla Exp $$
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        // 1. Traverse tree using postOrder i.e Process Left - Right and then root
        if (root == null) { return null; }

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    // Idea is to use PostOrder(Left-> Right-> Root) or(Right -> Left -> Root), find left and right node and swap root.left=right and
    // root.right=left
    private TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree2(root.right);
        TreeNode left = invertTree2(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);
        //System.out.println(new InvertTree().invertTree2(tree.root));
        System.out.println(new InvertTree().invertTreeIterative(tree.root));
    }
}