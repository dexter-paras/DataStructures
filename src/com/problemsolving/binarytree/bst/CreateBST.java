/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CreateBST.java, v 0.1 2020-08-29 14:27 paras.chawla Exp $$
 */
public class CreateBST {

    TreeNode root;
    int      count = 0;

    public CreateBST() {
        root = null;
    }

    public void createBST(List<Integer> keys) {
        for (int key : keys) {
            root = insert(root, key);
        }
        System.out.println("Count of elements added" + count);
    }

    private TreeNode insert(TreeNode root, int key) {

        count++;
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.val) {
            root.left = insert(root.left, key);
        } else if (key > root.val) {
            root.right = insert(root.right, key);
        }

        /* return the (unchanged) node pointer */
        return root;

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(5);
        list.add(15);
        list.add(2);
        new CreateBST().createBST(list);
    }

}