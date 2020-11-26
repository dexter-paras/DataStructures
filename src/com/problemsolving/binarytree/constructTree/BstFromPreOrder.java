/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.constructTree;

import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: BstFromPreOrder.java, v 0.1 2020-04-21 10:49 paras.chawla Exp $$
 */
public class BstFromPreOrder {

    //[8,5,1,7,10,12]
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;

        for (int i = 0; i < preorder.length; i++) {
            root = insertBST(root, preorder[i]);
        }
        return root;
    }

    public TreeNode insertBST(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        // insert into left SubTree
        else if (value < (int) root.val) {
            root.left = insertBST(root.left, value);
        }
        // insert into right SubTree
        else {
            root.right = insertBST(root.right, value);
        }
        return root;
    }

    public static void main(String[] args) {
        new BstFromPreOrder().bstFromPreorder(new int[] {8, 5, 1, 7, 10, 12});
    }
}