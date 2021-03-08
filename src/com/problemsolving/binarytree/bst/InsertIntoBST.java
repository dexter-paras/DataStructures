/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: InsertIntoBST.java, v 0.1 2020-04-21 10:00 paras.chawla Exp $$
 */

// 8,5,1,7,10,12
public class InsertIntoBST {

    public TreeNode insertIntoBST(TreeNode root, int value) {
        TreeNode temp = root;
        while (root != null) {
            if (value < root.val) {
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
        if (value < root.val) {
            root.left = insertBST(root.left, value);
        }
        // insert into right SubTree
        else {
            root.right = insertBST(root.right, value);
        }
        return root;
    }

    private TreeNode deleteNodeBST(TreeNode root, int key) {

        if (root == null) { return root; }

        // found node which needs to be deleted
        if (root.val == key) {

            // case 1. node to be deleted is leaf node
            if (root.right == null && root.left == null) {
                return null;
            }

            // case 2. node to be deleted has no right subtree
            if (root.right == null) {
                return root.left;
            }

            // case 3. node to be deleted has both left and right subtree
            TreeNode currNode = root.right;
            // 3.1 find successor node
            while (currNode.left != null) {
                currNode = currNode.left;
            }

            // 3.2 paste left subtree of (to be deleted node) to current TreeNode(Successor node)
            currNode.left = root.left;

            return root.right;
        }

        // traverse left subtree
        if (key < root.val) {
            root.left = deleteNodeBST(root.left, key);
        }

        // traverse right subtree
        if (key > root.val) {
            root.right = deleteNodeBST(root.right, key);
        }

        return root;
    }

    public static void main(String[] args) {
        InsertIntoBST obj = new InsertIntoBST();
        TreeNode root = new TreeNode(4);
        obj.insertBST(root, 2);
        obj.insertBST(root, 1);
        obj.insertBST(root, 3);
        obj.insertBST(root, 6);
        obj.insertBST(root, 8);
        obj.insertBST(root, 5);
        obj.insertBST(root, 7);
        obj.insertBST(root, 10);

        // delete root node
        //obj.deleteNodeBST(root, 10);

        // delete node with no left subtree
        //obj.deleteNodeBST(root,8);

        // delete node with both left and right subtree present
        obj.deleteNodeBST(root, 6);
    }

}