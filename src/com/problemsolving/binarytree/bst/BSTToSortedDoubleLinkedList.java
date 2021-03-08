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

    // Approach 1 - Not Working
    public com.problemsolving.binarytree.TreeNode treeToDoublyList(com.problemsolving.binarytree.TreeNode root) {

        if (root == null) { return root; }

        // K- Child & V- Parent
        HashMap<com.problemsolving.binarytree.TreeNode, com.problemsolving.binarytree.TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        inorder(root, parentMap);

        return null;

    }

    private void inorder(com.problemsolving.binarytree.TreeNode root,
                         HashMap<com.problemsolving.binarytree.TreeNode, com.problemsolving.binarytree.TreeNode> map) {

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

    // Use Inorder and cleverly use dummy node
    // Intuition is to do InOrder traversal and connect currNode and prev node accordingly
    // https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/149151/Concise-Java-solution-Beats-100
    // TODO - Read https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/273834/Morris-traversal-idea-to-beat-100-time-and-space
    TreeNode prev = null;

    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) { return null; }
        TreeNode dummy = new TreeNode(0, null, null);
        prev = dummy;
        helper(root);
        //connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper(TreeNode cur) {
        if (cur == null) { return; }
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
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

        obj.treeToDoublyList2(tree.root);
    }
}