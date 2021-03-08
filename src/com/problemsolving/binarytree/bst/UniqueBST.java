/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import java.util.ArrayList;

/**
 * @author paras.chawla
 * @version $Id: UniqueBST.java, v 0.1 2020-11-11 17:14 paras.chawla Exp $$
 */
public class UniqueBST {

    public static void main(String args[]) {
        ArrayList<TreeNode> totalTreesFrom1toN = constructTrees(1, 3);
        System.out.println(totalTreesFrom1toN);
    }

    //  function for constructing trees
    static ArrayList<TreeNode> constructTrees(int start, int end)
    {
        ArrayList<TreeNode> list=new ArrayList<>();
        /*  if start > end   then subtree will be empty so returning NULL
            in the list */
        if (start > end)
        {
            list.add(null);
            return list;
        }

        /*  iterating through all values from start to end  for constructing\
            left and right subtree recursively  */
        for (int i = start; i <= end; i++)
        {
            /*  constructing left subtree   */
            ArrayList<TreeNode> leftSubtree  = constructTrees(start, i - 1);

            /*  constructing right subtree  */
            ArrayList<TreeNode> rightSubtree = constructTrees(i + 1, end);

            /*  now looping through all left and right subtrees and connecting
                them to ith root  below  */
            for (int j = 0; j < leftSubtree.size(); j++)
            {
                TreeNode left = leftSubtree.get(j);
                for (int k = 0; k < rightSubtree.size(); k++)
                {
                    TreeNode right = rightSubtree.get(k);
                    TreeNode node = new TreeNode(i);        // making value i as root
                    node.left = left;              // connect left subtree
                    node.right = right;            // connect right subtree
                    list.add(node);                // add this tree to list
                }
            }
        }
        return list;
    }

}

class TreeNode {
    int      key;
    TreeNode left, right;

    TreeNode(int data) {
        this.key = data;
        left = right = null;
    }

    TreeNode(int data, TreeNode left, TreeNode right){
        this.key= data;
        this.left =left;
        this.right = right;
    }
}