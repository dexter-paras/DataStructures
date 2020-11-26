/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.avltree;

/**
 * @author paras.chawla
 * @version $Id: AvlTreeMain.java, v 0.1 2020-03-14 13:44 paras.chawla Exp $$
 */
public class AvlTreeMain {

    //43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51
    public static void main(String[] args) {
        AvlTree avlTree= new AvlTree();
        int[] data = {43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51};
        for(int i:data){
            avlTree.add(i);
        }
    }
}