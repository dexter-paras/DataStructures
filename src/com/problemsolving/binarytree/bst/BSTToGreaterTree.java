/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: BSTToGreaterTree.java, v 0.1 2021-02-10 8:33 AM paras.chawla Exp $$
 */
public class BSTToGreaterTree {

    // Approach 1 - Using Normal Inorder
    public TreeNode convertBST(TreeNode root) {

        // 1. Convert BST into Sorted List
        List<Integer> sortedList = new ArrayList<>();

        // sortedList [0,  1, 2, 3,  4, 5, 6, 7,8]
        // sumList    [36,36, 35,33,30,26,21,15,8]
        inorder(root, sortedList);

        // 2. Calculate total sum
        int sum = 0;
        for (int i : sortedList) {
            sum += i;
        }

        // 3. Create new List having sum of original key plus sum of all keys greater than the original key in BST
        List<Integer> sumList = new ArrayList<>();
        sumList.add(sum);

        for (int i = 1; i < sortedList.size(); i++) {
            sumList.add(sumList.get(i - 1) - sortedList.get(i - 1));
        }

        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < sortedList.size(); i++) {
            sumMap.put(sortedList.get(i), sumList.get(i));
        }

        // 4. Inorder Traversal of BST and update TreeNode values
        inorderConversion(root, sumMap);

        return root;
    }

    private void inorderConversion(TreeNode root, Map<Integer, Integer> sumMap) {

        if (root == null) { return; }
        inorderConversion(root.left, sumMap);
        root.val = sumMap.get(root.val);
        inorderConversion(root.right, sumMap);
    }

    private void inorder(TreeNode root, List<Integer> sortedList) {

        if (root == null) { return; }
        inorder(root.left, sortedList);
        sortedList.add(root.val);
        inorder(root.right, sortedList);
    }

    // Approach 2 - Using Recursion
    int sum = 0;
    public TreeNode convertBSTApproach2(TreeNode root) {

        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode root) {

        if (root == null) { return; }
        reverseInorder(root.right);
        sum += root.val;
        root.val = sum;
        reverseInorder(root.left);

    }

    public static void main(String[] args) {
        BSTToGreaterTree obj = new BSTToGreaterTree();
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree3(tree);

        System.out.println(obj.convertBSTApproach3(tree.root));
        System.out.println(tree.root);
    }

    // Approach 3 - Using Recursion
    /*
     *     4
     *   /  \
     *  1    6
     * / \  / \
     *0  2 5  7
     *   \     \
     *   3     8
     *
     */
    private int previousSum=0;
    public TreeNode convertBSTApproach3(TreeNode root) {

        reverseInorder2(root);
        return root;
    }

    private void reverseInorder2(TreeNode root) {

        if (root == null) { return; }
        reverseInorder2(root.right);
        int temp = root.val;//8
        root.val = previousSum;//0
        previousSum += temp;
        reverseInorder2(root.left);
    }

}