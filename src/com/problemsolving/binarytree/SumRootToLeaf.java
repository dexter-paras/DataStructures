/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: SumRootToLeaf.java, v 0.1 2020-03-04 06:50 paras.chawla Exp $$
 */

/*
target = 22
      [22]     5
              / \
      [17]   4   8
            /   / \
     [13]  11  5  9
          / \
    [2]  7  2

       path1  5-4-11-2
       path2  5-8-9
*/
public class SumRootToLeaf {

    static boolean hasPathSum(TreeNode node, int target) {
        // root= null ,hence no path exist
        if (node == null) {
            return false;
        }

        // find if node is leaf node
        boolean isLeaf = node.left == null && node.right == null;

        // base condition ,when node is leaf node and target-node value comes to equal
        if (isLeaf && node.val == target) {
            return true;
        }

        return hasPathSum(node.left, target - node.val) ||
                hasPathSum(node.right, target - node.val);
    }

    /*
target = 22
         root->5
              / \
             4   8
            /   / \
           11  13  4
          / \       \
         7  2       1
*/
    // Create 2 stacks , 1 containing node and another containing remaining sum
    static boolean hasPathSumIteration(TreeNode root, int target) {

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        nodeStack.add(root); //5
        sumStack.add(target - (int) root.val); //12

        //5,12 (Node, Remaining Sum)
        while (!nodeStack.isEmpty()) {
            TreeNode e = nodeStack.pop();//5
            int rSum = sumStack.pop(); //12

            if (e.left == null && e.right == null && rSum == 0) {
                return true;
            }
            if (e.right != null) {
                nodeStack.add(e.right);
                sumStack.add(rSum - (int) e.right.val);
            }
            if (e.left != null) {
                nodeStack.add(e.left);
                sumStack.add(rSum - (int) e.left.val);
            }
        }
        return false;
    }

    // Given a binary tree and a sum,
    // find all root-to-leaf paths where each path's sum equals the given sum.
/*
             5
            / \
           4   8
          /   / \
         11  13  4
        /  \    / \
       7    2  5   1

       5-4-11-2 = 22
       5-8-4-5 = 22
*/
    /* List of all unique paths*/
    public static List<List<Integer>> findAllUniquepathSum(TreeNode root, int sum) {

        // List containing 1 unique path which sums to target sum...
        List<Integer> pathNodes = new ArrayList<>();

        // List containing all unique paths...
        List<List<Integer>> pathsList = new ArrayList<List<Integer>>();

        recurseTree(root, sum, pathNodes, pathsList);

        return pathsList;
    }

    // Helper function which includes all unique path which sums to target sum...
    private static void recurseTree(TreeNode root, int rSum, List<Integer> pathNodes, List<List<Integer>> pathsList) {

        if (root == null) {
            return;
        }
        pathNodes.add(root.val);//5

        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf && root.val == rSum) {
            pathsList.add(new ArrayList<>(pathNodes));
        } else {
            recurseTree(root.left, rSum -root.val, pathNodes, pathsList);
            recurseTree(root.right, rSum -root.val, pathNodes, pathsList);
        }
        pathNodes.remove(pathNodes.size() - 1);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        //BinaryTree.createBinaryTree4(tree);
        BinaryTree.createBinaryTree4EdgeCase2(tree);
        //System.out.println(hasPathSum(tree.root, 22));
        //System.out.println(hasPathSumIteration(tree.root, 22));
        //System.out.println(findAllUniquepathSum(tree.root, 22));
        System.out.println(new SumRootToLeaf().pathSum(tree.root, -1));
    }

    // path sum 3,The path does not need to start or end at the root or a leaf,
    // but it must go downwards (traveling only from parent nodes to child nodes).

    int globalCount = 0;

    public int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }
        inOrderTraversal(root, sum);
        return globalCount;
    }

    // 1. dfs1 inorder traversal, passing every node of tree to another dfs2 i.e. pathSumToTarget
    private void inOrderTraversal(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        pathSumToTarget(root, sum);
        inOrderTraversal(root.left, sum);
        inOrderTraversal(root.right, sum);
    }

    /* 2. given a particular tree, it calculates whether target to sum from that particular currNode to any node is possible or not*/
    private void pathSumToTarget(TreeNode root, int rSum) {

        if (root == null) {
            return;
        }

        if (root.val == rSum) {
            globalCount++;
        }
        pathSumToTarget(root.left, rSum - root.val);
        pathSumToTarget(root.right, rSum - root.val);

    }

}
