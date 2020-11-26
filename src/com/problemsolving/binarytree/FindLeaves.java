/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: FindLeaves.java, v 0.1 2020-03-15 19:51 paras.chawla Exp $$
 */
public class FindLeaves {

    int                         sum       = 0;
    Map<Integer, List<Integer>> map       = new HashMap<>();
    int                         maxHeight = -1;

    public static void main(String[] args) {
        FindLeaves obj = new FindLeaves();

        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree4(tree);
        System.out.println(obj.findOnlyLeaves(tree.root));
        System.out.println(obj.sumOfLeftLeaves(tree.root));
        System.out.println(obj.findAllLeaves(tree.root));
    }

    // Find Only leaf nodes
/*
         root->5
              / \
             4   8
            /   / \
           11  5  4
          / \
         7  2
*/
    /* TOPIC -1 Find only leaves node of Binary Tree*/
    public List<Integer> findOnlyLeaves(TreeNode root) {
        List<Integer> leafNodeList = new ArrayList<>();
        leafNodeList = findLeaf(leafNodeList, root);
        return leafNodeList;
    }

    /* TOPIC -2 Find sum of left leaves node of Binary Tree*/
    public int sumOfLeftLeaves(TreeNode root) {
        boolean isLeft = false;
        sum = sumOfLeftLeaves(root, isLeft);
        return sum;
    }

    private int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null) {
            return sum;
        }
        boolean isLeafAndLeftChild = root.left == null && root.right == null && isLeft;
        if (isLeafAndLeftChild) {
            sum += (int) root.val;
        }
        sumOfLeftLeaves(root.left, true);
        sumOfLeftLeaves(root.right, false);
        return sum;
    }

    private List<Integer> findLeaf(List<Integer> res, TreeNode node) {

        if (node == null) {
            return res;
        }
        boolean isLeaf = node.left == null && node.right == null;

        if (isLeaf) {
            res.add((int) node.val);
        }
        findLeaf(res, node.left);
        findLeaf(res, node.right);
        return res;
    }

    // Create a HashMap<Height,List<Nodes>> at that particular heigh
    public List<List<Integer>> findAllLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root);
        for (int i = 0; i <= maxHeight; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    // Thinking is to add 0 height nodes in a list ,1 height nodes in another list and so on...
    private int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        // calculate height of particular node
        int height = 1 + Math.max(height(root.left), height(root.right));
        if (map.containsKey(height)) {
            map.get(height).add((int) root.val);
        } else {
            List<Integer> nodes = new ArrayList<>();
            nodes.add((int) root.val);
            map.put(height, nodes);
        }
        if (height > maxHeight) {
            maxHeight = height;
        }
        return height;
    }

    /*
    * root->   5
              / \
             4   8
            /   / \
           11  5  4
          / \
         7  2
    * */

}