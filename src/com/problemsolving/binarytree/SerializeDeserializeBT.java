/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: SerializeDeserializeBT.java, v 0.1 2020-08-04 23:36 paras.chawla Exp $$
 * <p>
 * You may serialize the following tree: 1 <- root /   \ 2     3 /  \ 4    5
 * <p>
 * PreOrder - Root Left Right "1,2,X,X,3,4,X,X,5,X,X," as "[1,2,3,null,null,4,5]"
 */
public class SerializeDeserializeBT {

    //1. Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        // 1. base condition
        // If we have a null symbol...we encode a null symbol
        if (root == null) {
            return "X" + ",";
        }
        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);
        return root.val + "," + leftSerialized + rightSerialized;
    }

    //2. Decodes your encoded val to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        //1. Extract elements from queue
        String currValue = queue.poll();

        //2. base Condition
        if ("X".equals(currValue)) {
            return null;
        }

        // 3. Initiate new Node
        TreeNode newNode = new TreeNode(Integer.parseInt(currValue));
        newNode.left = deserializeHelper(queue);
        newNode.right = deserializeHelper(queue);
        return newNode;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createSerializeDeserializeBT(tree);

        String str =new SerializeDeserializeBT().serialize(tree.root);
        System.out.println("Serialized String is "+ str);

        new SerializeDeserializeBT().deserialize(str);
    }
}