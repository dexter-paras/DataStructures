/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

import java.util.HashMap;

/**
 * @author paras.chawla
 * @version $Id: BSTToSortedDoubleLinkedList.java, v 0.1 2021-02-03 12:51 PM paras.chawla Exp $$
 */
public class BSTToSortedDoubleLinkedList {

    public TreeNode treeToDoublyList(TreeNode root) {

        if (root == null) { return root; }

        // K- Child & V- Parent
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        inorder(root, parentMap);

        return null;

    }

    private void inorder(TreeNode root, HashMap<TreeNode, TreeNode> map) {

        if (root == null) { return; }

        if (root.left != null) {
            map.put(root.left, root);
            inorder(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right, root);
            inorder(root.right, map);
        }
    }

    /*
     *     4 <- root
     *   /  \
     *  2    7
     * / \  / \
     *1  3 6  9
     *
     */
    public static void main(String[] args) {
        BSTToSortedDoubleLinkedList obj = new BSTToSortedDoubleLinkedList();

        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);

        obj.treeToDoublyList(tree.root);
    }
}