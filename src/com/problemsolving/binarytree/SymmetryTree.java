/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: SymmetryTree.java, v 0.1 2020-02-25 23:29 paras.chawla Exp $$
 * /* 1
 *   /  \
 *  2    2
 * / \   / \
 *3   4  4  3
 /\  / \/\ /\
1 2 2  1
 */

public class SymmetryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree(tree);

        System.out.println(checkdfs(tree.root));
        System.out.println(checkbfs(tree.root));
    }

    // add base condition and going dfs
    private static boolean checkdfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSymmetry(root.left, root.right);
    }

    // going dfs. Subtrees are symmetric if there values are equal and there
    // one rightsubtree value equals to other leftsutree value
    private static boolean checkSymmetry(TreeNode leftChild, TreeNode rightChild) {
        // base condition, if both null
        if (leftChild == null && rightChild == null) {
            return true;
        }
        // if both not null
        if (leftChild != null && rightChild != null) {
            return leftChild.val == rightChild.val &&
                    checkSymmetry(leftChild.right, rightChild.left) &&
                    checkSymmetry(leftChild.left, rightChild.right);
        }
        // in case if 1 subtree is not empty and other is empty, they can not be
        // symmetric
        return false;
    }

    // Approach 2 - Iteratively
    public boolean isSymmetricApproach2(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);

        }
        return true;

    }


    // check if symmetric or not breadth first search
    private static boolean checkbfs(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> list = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode currNode = queue.poll();
                if (currNode.left != null) {
                    queue.add(currNode.left);
                    list.add((int) currNode.left.val);
                } else {
                    list.add(Integer.MAX_VALUE);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                    list.add((int) currNode.right.val);
                } else {
                    list.add(Integer.MAX_VALUE);
                }
            }
            if (queue.size() == 0) {
                return true;
            }
            if (!checkList(list)) { return false; }
        }
        return true;
    }

    private static boolean checkList(List<Integer> list) {
        if (list.size() % 2 != 0) {
            return false;
        }
        for (int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {
            if (list.get(i) != list.get(j)) { return false; }
        }
        return true;
    }

}