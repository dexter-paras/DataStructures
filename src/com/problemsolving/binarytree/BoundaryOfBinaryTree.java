/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: BoundaryOfBinaryTree.java, v 0.1 2020-09-05 10:15 paras.chawla Exp $$

    https://leetcode.com/problems/boundary-of-binary-tree/

Input:
     ____1_____
    /          \
   2            3
  / \          /
4    5        6
     /       / \
    6       9  10
   / \
  7  8
Ouput:
[1,2,4,7,8,9,10,6,3]

 */
public class BoundaryOfBinaryTree {

    List<Integer> result = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        // base condition
        if(root==null){
            return result;
        }

        result.add(root.val);

        //find left boundary
        leftBoundary(root.left);
        // find left leaves node
        leafBoundary(root.left);
        // find right leaves node
        leafBoundary(root.right);
        // find right boundary
        rightBoundary(root.right);
        return result;
    }

    private void leftBoundary(TreeNode root) {

        // base condition, not adding leaf nodes in result
        if(root==null ||(root.left==null && root.right==null)){return;}

        // adding into result as root element is not null
        result.add(root.val);

        if(root.left==null){
            leftBoundary(root.right);
        }else{
            leftBoundary(root.left);
        }
    }

    private void leafBoundary(TreeNode root){
        if(root==null){
            return;
        }

        if(root.left==null && root.right==null){
            result.add(root.val);
        }
        leafBoundary(root.left);
        leafBoundary(root.right);
    }

    // Adding elements in reverse direction
    private void rightBoundary(TreeNode root) {

        // base condition, not adding leaf nodes in result
        if(root==null ||(root.left==null && root.right==null)){return;}

        if(root.right==null){
            rightBoundary(root.left);
        }else{
            rightBoundary(root.right);
        }
        result.add(root.val); // add after child visit(reverse)
    }


    public static void main(String[] args) {
        BoundaryOfBinaryTree obj = new BoundaryOfBinaryTree();
        BinaryTree tree = new BinaryTree();
        obj.createBinaryTree(tree);

        obj.boundaryOfBinaryTree(tree.root);
    }

    /*
* Input:
     ____1_____
    /          \
   2            3
  / \          /
4    5        6
     /       / \
    6       9  10
   / \
  7  8
* */

    /* create Binary Tree (BT doesn't belong nodes in order) */
    private static void createBinaryTree(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.left.right.left = new TreeNode(6);
        tree.root.left.right.left.left = new TreeNode(7);
        tree.root.left.right.left.right = new TreeNode(8);

        tree.root.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.left.left = new TreeNode(9);
        tree.root.right.left.right = new TreeNode(10);
    }


}