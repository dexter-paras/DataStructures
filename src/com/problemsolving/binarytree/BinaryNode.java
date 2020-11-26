/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: BinaryNode.java, v 0.1 2020-03-14 10:33 paras.chawla Exp $$
 */ /*BinaryNode class with type T*/
public class BinaryNode<T> {

    public T data;
    public BinaryNode<T> leftChild, rightChild, parentChild;

    public BinaryNode(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
        this.parentChild = null;
    }
}