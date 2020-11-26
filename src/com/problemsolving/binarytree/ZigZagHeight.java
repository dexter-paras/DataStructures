/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: ZigZagHeight.java, v 0.1 2020-03-07 21:17 paras.chawla Exp $$
 */
public class ZigZagHeight {

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(calculateZigZagPath(root.left, 0), calculateZigZagPath(root.right, 0));
    }

    public int calculateZigZagPath(TreeNode root, int height) {
        if (root == null) {
            return height;
        }
        if (height % 2 == 0 && root.left != null) {
            height++;
            calculateZigZagPath(root.left, height);
        } else if (height % 2 != 0 && root.right != null) {
            height++;
            calculateZigZagPath(root.right, height);
        }
        return height;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree5(tree);
        System.out.println(new ZigZagHeight().longestZigZag(tree.root));
    }

}