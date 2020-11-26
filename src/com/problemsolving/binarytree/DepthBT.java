/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: DepthBT.java, v 0.1 2020-04-12 14:53 paras.chawla Exp $$
 */
public class DepthBT {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int righHeight = height(root.right);

        if (righHeight == 0 && leftHeight > 0) {
            return 1 + leftHeight;
        } else if (leftHeight == 0 && righHeight > 0) {
            return 1 + righHeight;
        }
        return Math.min(1 + leftHeight, 1 + righHeight);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.min(height(root.left), height(root.right));
    }

    public int maxDepth(TreeNode root) {
        return root != null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree7(tree);

        System.out.println(new DepthBT().maxDepth(tree.root));

    }
}