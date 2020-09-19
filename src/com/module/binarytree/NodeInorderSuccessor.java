/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.binarytree;

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
}

