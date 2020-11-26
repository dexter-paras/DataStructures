/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: ConstrcutBBTFromSortedArray.java, v 0.1 2020-01-11 16:17 paras.chawla Exp $$
 */
public class ConstrcutBBTFromSortedArray {

    public static void main(String[] args) {
        int nums[] = {-10, -3, 0, 5, 9};
        TreeNode binarySearchTree = new ConstrcutBBTFromSortedArray().sortedArrayToBST(nums);
        BinaryTree.inorderTraversal(binarySearchTree);
    }

    private TreeNode sortedArrayToBST(int[] nums) {
        return buildBalancedTree(nums, 0, nums.length - 1);
    }

    // helper code
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
}