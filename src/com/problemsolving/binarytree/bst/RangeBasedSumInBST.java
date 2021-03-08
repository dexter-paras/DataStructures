/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: RangeBasedSumInBST.java, v 0.1 2021-03-08 2:45 AM paras.chawla Exp $$
 */
public class RangeBasedSumInBST {

    public int rangeSumBST(com.problemsolving.binarytree.TreeNode root, int low, int high) {
        return helper(root, low, high, 0);
    }

    private int helper(TreeNode root, int low, int high, int sum) {
        if (root == null) {
            return 0;
        }
        // if lies within the range
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val <= high) {
            sum += root.val + helper(root.left, low, high, sum);
        }

        if (root.val > low) {
            helper(root.right, low, high, sum);
        }
        return sum;
    }
}