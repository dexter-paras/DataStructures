/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

/**
 * @author paras.chawla
 * @version $Id: ValidBST.java, v 0.1 2019-12-29 00:12 paras.chawla Exp $$ Table of Contents - Binary Search Tree 1.1 BST Search Recursively
 * 1.2 BST Search Iteratively 1.3 BST Insertion Recursively 1.4 BST Insertion Iterative - DONE 1.5 BST Removing Element Recursively 1.6 BST
 * Removing Element Iteratively 1.7 BST valid or not without or without using extra space - DONE 1.8 BST calculate Size Recursively - DONE
 */

/*
      10   <- root
    /   \
   5    15
  / \   / \
 2  8  12 20
*/
public class ValidBST {

    private static int     index        = 0;
    static         Integer last_printed = null;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        // create BST
        tree.createBST();
        // calculate size of BT
        int size = sizeBinaryTree(tree.root);
        // create array of same size of BST
        int[] array = new int[size];
        // check if BST is valid
        copyBST(tree.root, array);
        // print inorder
        tree.inorderTraversal(tree.root);
        // check if Valid BST
        System.out.println("BST is valid or not: " + validateBST(tree.root,Integer.MIN_VALUE,Integer.MAX_VALUE));
        // check if Valid BST
        System.out.println("BST is valid or not: " + checkValidBST(array));
        // check if Valid BST
        System.out.println("BST is valid or not: " + checkValidBSTWOExtraSpace(tree.root));
        // delete iteratively in BST
        tree.deleteIteratively(tree.root);
    }

    private static boolean checkValidBST(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) { return false; }
        }
        return true;
    }

    // calculate size using recursive
    private static int sizeBinaryTree(TreeNode root) {
        if (root == null) { return 0; }
        return 1 + sizeBinaryTree(root.left) + sizeBinaryTree(root.right);
    }

    /* Check if Binary Tree is a Valid BST
     * create array out of BST and see if it is a sorted array or not
     * Inorder : Left -> Root -> Right
     */
    private static void copyBST(TreeNode root, int[] array) {
        if (root == null) { return; }
        copyBST(root.left, array);
        array[index++] = root.val;
        copyBST(root.right, array);
    }

    // Check if Valid BST without using Extra space
    private static boolean checkValidBSTWOExtraSpace(TreeNode root) {
        if (root == null) {return true;}

        //Check / recurse left
        if (!checkValidBSTWOExtraSpace(root.left)) { return false;}

        // Check current
        if (last_printed != null && (Integer) root.val <= last_printed) {return false;}

        last_printed = (Integer) root.val;

        //Check / recurse left
        if (!checkValidBSTWOExtraSpace(root.right)) {return false;}

        return true;//ALL GOOD
    }

    /*
      10   <- root
    /   \
   5    15
  / \   / \
 2  8  12 20
*/
    private static boolean validateBST(TreeNode root, int min, int max) {

        if (root == null) {
            return true;
        }

        boolean bool = root.val > min && root.val < max;
        if (!bool) {
            return false;
        }

        boolean leftValidate= validateBST(root.left, min, root.val);
        boolean rightValidate = validateBST(root.right, root.val, max);

        return leftValidate && rightValidate;
    }

}