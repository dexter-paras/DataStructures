/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.binarytree;

/**
 * @author paras.chawla
 * @version $Id: InsertIntoBST.java, v 0.1 2020-04-21 10:00 paras.chawla Exp $$
 */

// 8,5,1,7,10,12
public class InsertIntoBST {

    public TreeNode insertIntoBST(TreeNode root, int value) {
        TreeNode temp = root;
        while (root != null) {
            if (value < (int) root.val) {
                if (root.left == null) {
                    root.left = new TreeNode(value);
                    break;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(value);
                    break;
                }
                root = root.right;
            }
        }
        return temp;
    }

    public TreeNode insertBST(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        // insert into left SubTree
        if (value < (int) root.val) {
            root.left = insertBST(root.left, value);
        }
        // insert into right SubTree
        else {
            root.right = insertBST(root.right, value);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        new InsertIntoBST().insertBST(root, 5);
        new InsertIntoBST().insertBST(root, 1);
        new InsertIntoBST().insertBST(root, 7);
        new InsertIntoBST().insertBST(root, 10);
        new InsertIntoBST().insertBST(root, 12);
    }

}