/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: NodeInorderSuccessor.java, v 0.1 2020-01-11 15:09 paras.chawla Exp $$
 */


/*
       10
    /    \
   5     15
  / \    / \
 2  8   12 20
     \  /
     9 11
*/
public class NodeInorderSuccessor {

    private BinaryNode findInorderSuccessor(BinaryNode ptr) {
        if (ptr == null) {
            return null;
        }

        /* case 1 : when right subtree is not null
                   go to right subtree and then traverse to extreme left subtree
        Example 10 -> 11 */

        if (ptr.rightChild != null) {
            ptr = ptr.rightChild;
            while (ptr.leftChild != null) {
                ptr = ptr.leftChild;
            }
            return ptr;
        }

        /* case 2 : when right subtree and left subtree is null
                   go to parent node and then traverse to extreme left subtree
        Example 9 -> 10 */
        while (ptr.parentChild != null && ptr.parentChild.rightChild == ptr) {
            ptr = ptr.parentChild;
        }
        return ptr.parentChild;
    }

    // Apporach 2 - Using BST property
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        TreeNode candidate = null;
        TreeNode currNode = root;

        // Traverse towards p node
        while (currNode != null) {

            // potential candidate since currNode is greater than p Node
            if (currNode.val > p.val) {
                candidate = currNode;
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
        return candidate;
    }

    // Approach 3- Using Recursion In Order Successor
    //https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    // In Order Predecessor
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }
}

