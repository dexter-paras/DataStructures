/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: BSTToBBST.java, v 0.1 2020-03-15 08:07 paras.chawla Exp $$
 */
public class BSTToBBST {

    List<Integer> sortedList = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        constructSortedList(root);
        int[] sortedArray = sortedList.stream().mapToInt(Integer::intValue).toArray();
        return buildBalancedTree(sortedArray, 0, sortedArray.length - 1);
    }

    private void constructSortedList(TreeNode node) {
        if (node == null) {
            return;
        }
        constructSortedList(node.left);
        sortedList.add(node.val);
        constructSortedList(node.right);
    }

    private TreeNode buildBalancedTree(int[] nums, int low, int high) {
        // no elements present
        if (low > high) {
            return null;
        }

        int mid = (high + low) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBalancedTree(nums, low, mid - 1);
        root.right = buildBalancedTree(nums, mid + 1, high);

        return root;
    }

    public static void main(String[] args) {
        BSTToBBST obj = new BSTToBBST();
        BinaryTree tree = new BinaryTree();
        tree.createBST();
        System.out.println(obj.balanceBST(tree.root));
    }
}