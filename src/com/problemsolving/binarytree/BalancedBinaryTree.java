/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: BalancedBinaryTree.java, v 0.1 2020-01-05 02:21 paras.chawla Exp $$
 */

/* A balanced binary tree is one whose every node maintain below property:
 * |height of leftSubTree - height of rightSubTree| <=1
 * check in recursion for every node that
 * a) its leftsubtree and rightSubtree are balanced
 * b) check if subtrees are balanced or not -> |height(leftSubtree) - height(RightSubtree)| <=1
 *
 * We care about 2 things as our recursion goes upwards after bottoming out:
  - The node's height
  - Whether its left and right subtrees are balanced
 /*
 root->       10
         /           \
   (1,t)20            30(2,t)
       \            /       \
(-1,t) (0,t)40   50(1,t)       60(1,true)
                /               \
             70(0,t)(-1,t)       80(-1,true)
     */

public class BalancedBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree(tree);

        System.out.println(checkBalancedBinaryTree(tree.root).isBalanced());
    }

    //Bottom up approach , calculating height once found that subtree is balanced
    private static BalanceStatusWithHeight checkBalancedBinaryTree(TreeNode root) {
        // leaf node is balance by default with height -1(deep rooted)
        if (root == null) {
            return new BalanceStatusWithHeight(true, -1);
        }

        // Go deep into the left subtree and get a result back
        BalanceStatusWithHeight leftResult = checkBalancedBinaryTree(root.left);
        if (!leftResult.isBalanced()) {
            return leftResult;
        }

        // Go deep into the right subtree and get a result back
        BalanceStatusWithHeight rightResult = checkBalancedBinaryTree(root.right);
        if (!rightResult.isBalanced()) {
            return rightResult;
        }

        // check if height(left subtree) - height(right subtree <=1
        boolean subTreeBalanced = Math.abs(leftResult.getHeight() - rightResult.getHeight()) <= 1;
        // calculate height
        int height = 1 + Math.max(leftResult.getHeight(), rightResult.getHeight());

        return new BalanceStatusWithHeight(subTreeBalanced, height);
    }
}

// Every subtree is either balanced or not and possess height
class BalanceStatusWithHeight {
    private boolean isBalanced;
    private int     height;

    BalanceStatusWithHeight(boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }

    boolean isBalanced() {
        return isBalanced;
    }

    int getHeight() {
        return height;
    }
}