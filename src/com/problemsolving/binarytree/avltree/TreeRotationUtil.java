/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.avltree;

import com.problemsolving.binarytree.BinaryNode;

/**
 * @author paras.chawla
 * @version $Id: TreeRotationUtil.java, v 0.1 2020-03-14 10:32 paras.chawla Exp $$
 */
public class TreeRotationUtil {

    /* LEFT CHILD LEFT SUBTREE
     *
     *   8 <- node <- violation will be handled by Grand Parent node
     *   /
     *  6  <- temp <- parent node
     *  /
     * 4  <- Imbalance node
     * Needed : RIGHT ROTATION on grand parent node
     */
    public static BinaryNode rightRotation(BinaryNode node) {
        BinaryNode temp = node.leftChild;
        node.leftChild=temp.rightChild;
        temp.rightChild=node;
        return temp;
    }

    /* RIGHT CHILD RIGHT SUBTREE
     *
     * 4 <- node <- violation will be handled by Grand Parent node
     * \
     *  6  <- temp <- parent node
     *  \
     *   8  <- Imbalance node
     * Needed : LEFT ROTATION on grand parent node
     */
    public static BinaryNode leftRotation(BinaryNode node) {
        BinaryNode temp = node.rightChild;
        node.rightChild=temp.leftChild;
        temp.leftChild=node;
        return temp;
    }

    /* RIGHT CHILD LEFT SUBTREE
     *
     * 4 <- grand Parent node
     * \
     *  8 <- parent node
     * /
     * 6  <- Imbalance node
     * Needed : RIGHT ROTATION on parent node,then LEFT ROTATION ON GrandParent
     */
    public static BinaryNode rightLeftRotation(BinaryNode node) {
        node.rightChild=rightRotation(node.rightChild); // 4-6-8
        return leftRotation(node);
    }

    /* RIGHT CHILD LEFT SUBTREE
     *
     *   8 <- node
     *  /
     * 6  <- parent node
     * \
     *  7  <- Imbalance node
     * Needed : LEFT ROTATION on parent node,then RIGHT ROTATION ON GrandParent
     */
    public static BinaryNode leftRightRotation(BinaryNode node) {
        node.leftChild=leftRotation(node.leftChild);
        return rightRotation(node);
    }

}