/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.binarytree;

import java.util.ArrayList;

/**
 * @author paras.chawla
 * @version $Id: UniqueBST.java, v 0.1 2020-11-11 17:14 paras.chawla Exp $$
 */
public class UniqueBST {

    public static void main(String args[]) {
        ArrayList<Node> totalTreesFrom1toN = constructTrees(1, 3);
        System.out.println(totalTreesFrom1toN);
    }

    //  function for constructing trees
    static ArrayList<Node> constructTrees(int start, int end)
    {
        ArrayList<Node> list=new ArrayList<>();
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
            ArrayList<Node> leftSubtree  = constructTrees(start, i - 1);

            /*  constructing right subtree  */
            ArrayList<Node> rightSubtree = constructTrees(i + 1, end);

            /*  now looping through all left and right subtrees and connecting
                them to ith root  below  */
            for (int j = 0; j < leftSubtree.size(); j++)
            {
                Node left = leftSubtree.get(j);
                for (int k = 0; k < rightSubtree.size(); k++)
                {
                    Node right = rightSubtree.get(k);
                    Node node = new Node(i);        // making value i as root
                    node.left = left;              // connect left subtree
                    node.right = right;            // connect right subtree
                    list.add(node);                // add this tree to list
                }
            }
        }
        return list;
    }

}

class Node {
    int  key;
    Node left, right;

    Node(int data) {
        this.key = data;
        left = right = null;
    }
}