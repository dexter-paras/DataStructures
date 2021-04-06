/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: ConstructBTPostInOrder.java, v 0.1 2021-03-19 8:08 PM paras.chawla Exp $$
 */
public class ConstructBTPostInOrder {

    //  inorder = [9,3,15,20,7] <- Left Root Right
    //               ^
    //               1 <- rootIdx

    // leftSubtreeLen =0
    // rightSubtreeLen =2

    //  postorder = [9,15,7,20,3] <- Left Right Root
    //                         ^ is the root node

    /*
     *                     3
     *                [0]/    \[2]
     *
     *                 9      15,20,7
     *
     * */

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // base condition
        if (inorder.length == 0 || postorder.length == 0 || inorder ==null || postorder ==null) {
            return null;
        }

        // For Faster retrieval of index
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inMap);
    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd,
                            Map<Integer, Integer> inMap) {

        // base condition
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // This is the first node of TreeNode we're buidling
        TreeNode node = new TreeNode(postOrder[postEnd]);

        // Find index of Root node in Inorder array
        int rootIdx = inMap.get(postOrder[postEnd]);
        int leftSubTreeLen = rootIdx -1- inStart;
        int rightSubTreeLen = inEnd - rootIdx - 1;

        node.left = helper(inorder, inStart, rootIdx - 1, postOrder,postStart , postStart+leftSubTreeLen, inMap);
        node.right = helper(inorder, rootIdx + 1, inEnd, postOrder,postEnd-1-rightSubTreeLen ,postEnd-1 , inMap);

        return node;
    }

    public static void main(String[] args) {
        ConstructBTPostInOrder obj = new ConstructBTPostInOrder();

        int[] inOrder = new int[]{9,3,15,20,7};
        int[] postOrder = new int[]{9,15,7,20,3};

        System.out.println(obj.buildTree(inOrder,postOrder));
    }

}