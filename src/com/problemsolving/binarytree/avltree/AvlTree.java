/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.avltree;

import com.problemsolving.binarytree.BinaryNode;

/**
 * @author paras.chawla
 * @version $Id: AvlTree.java, v 0.1 2020-03-14 10:52 paras.chawla Exp $$
 *
 *
 *
 */
public class AvlTree {

    // root of an AVL Tree
    BinaryNode root;
    // size of an AVL Tree
    int        size;

    public AvlTree() {
        root = null;
        size = 0;
    }

    void add(int data) {
        // construct a node
        BinaryNode node = new BinaryNode(data);
        if (root == null) {
            root = node;
            size++;
            return;
        }
        add(root, node);
    }

    // Passing Root Node and Node which needs to be added
    private void add(BinaryNode root, BinaryNode newNode) {
        // traverse to the right
        if ((int) newNode.data > (int) root.data) {
            if (root.rightChild == null) {
                root.rightChild = newNode;
                newNode.parentChild = root;
                size++;
            } else {
                add(root.rightChild, newNode);
            }
        } // traverse to the left
        else {
            if (root.leftChild == null) {
                root.leftChild = newNode;
                newNode.parentChild = root;
                size++;
            } else {
                add(root.leftChild, newNode);
            }
        }
        // After insertion, we need to make sure that AVL property is maintained
        // we'll be passing the node which we've just added
        checkBalance(newNode);
    }

    // if difference of height is > 1, we need to do Rotation else if 0/1 , no rotation is required
    private void checkBalance(BinaryNode newNode) {
        if ((height(newNode.leftChild) - height(newNode.rightChild) > 1) ||
                (height(newNode.leftChild) - height(newNode.rightChild) < -1)) {
            rebalance(newNode);
        }
        // if we have reached to the root, it means that tree is balanced
        if (newNode.parentChild == null) {
            return;
        }// if we haven't reached till root, we need to check further
        else {
            checkBalance(newNode.parentChild);
        }
    }

    // rebalance the node which is having imbalance of height of left subTree and rightSubtree
    // check height of left or right subtree, Figure out if it needs LR,RR,LRR,RLR
    private void rebalance(BinaryNode node) {
        // Tree skewed towards Left
        if (height(node.leftChild) - height(node.rightChild) > 1) {
            // Need to check if Tree skewed towards Left- Left or Left-Right
            if (height(node.leftChild.leftChild) > height(node.leftChild.rightChild)) {
                node = TreeRotationUtil.rightRotation(node);
            } else {
                node = TreeRotationUtil.leftRightRotation(node);
            }
        } else {
            // height of right subTree is greater than height of left subTree
            // Need to check if Tree skewed towards Left- Left or Left-Right
            // To find whether its right Child right Subtree or right child left Subtree
            if (height(node.rightChild.rightChild) > height(node.rightChild.leftChild)) {
                node = TreeRotationUtil.leftRotation(node);
            } else {
                node = TreeRotationUtil.rightLeftRotation(node);
            }
        }
        if (node.parentChild == null) {
            root = node;
        }
    }

    // height of a TreeNode
    private int height(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = 1 + height(node.leftChild);
        int rightHeight = 1 + height(node.rightChild);
        if (leftHeight > rightHeight) { return leftHeight; } else { return rightHeight; }
    }

}