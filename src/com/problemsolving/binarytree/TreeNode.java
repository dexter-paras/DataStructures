/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: TreeNode.java, v 0.1 2019-12-28 10:05 paras.chawla Exp $$
 */

/*Binary TreeNode class with type T*/
public class TreeNode {

    public int         val;
    public TreeNode left, right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val,TreeNode left, TreeNode right) {
        this.val = val;
        this.left = null;
        this.right = null;
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}