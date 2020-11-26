/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LowestCommonAncestor.java, v 0.1 2020-03-05 19:46 paras.chawla Exp $$
 */
public class LowestCommonAncestor {

    // APPROACH 1 - Using parent Node and extra space hashMAP
    public BinaryNode lowestCommonAncestor(BinaryNode p, BinaryNode q) {

        // Creata a map to store ancestors of p
        Map<BinaryNode, Boolean> ancestors = new HashMap<>();

        while (p != null) {
            ancestors.put(p, true); // 4-true, 2- true,1-true
            p = p.parentChild;
        }

        while (q != null) {   //8->7->5->2
            if (ancestors.containsKey(q)) {
                return q;
            }
            q = q.parentChild;
        }
        return null;
    }

    // APPROACH 1 - Using Recursion
    public static int lowestCommonAncestorOfBST(TreeNode root, int p, int q) {

        // 1. if both p and q smaller than root, call recursively to left of root node
        if (p < (int) root.val && (int) q < (int) root.val) {
            return lowestCommonAncestorOfBST(root.left, p, q);
        }
        // 2. if both p and q greater than root, call recursively to right of root node
        if ((int) p > (int) root.val && (int) q > (int) root.val) {
            return lowestCommonAncestorOfBST(root.right, p, q);
        }
        /*
         * 3. One of x or y is equal to the root.
         * OR
         * 4. One of x or y is less than root and the other is greater than root.
         */
        return (int) root.val;
    }

    // Approach 2 - Using Recursion to find LCA of a BT
    public static TreeNode lowestCommonAncestorOfBT(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        findPath(root, p, path1);
        findPath(root, q, path2);

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).val != (path2.get(i).val)) { break; }
        }

        return path1.get(i - 1);
    }

    // To Find path of a node n from root node and find if path exist or not
    private static boolean findPath(TreeNode root, TreeNode x, List<TreeNode> path) {

        // base condition
        if (root == null) {
            return false;
        }

        // add current node in path
        path.add(root);

        //if node found, return true that path has been found
        if ((int) root.val == (int) x.val) {
            return true;
        }

        if (root.left != null && findPath(root.left, x, path)) {
            return true;
        }

        if (root.right != null && findPath(root.right, x, path)) {
            return true;
        }
        // if reached subtree and node not found, then return false and remove it from path

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        /*
        tree.createBST();

        System.out.println("USING RECURSION");
        System.out.println(lowestCommonAncestorOfBST(tree.root, 1, 101));
        System.out.println(lowestCommonAncestorOfBST(tree.root, 1, 27));
        System.out.println(lowestCommonAncestorOfBST(tree.root, 1, 25));
        System.out.println(lowestCommonAncestorOfBST(tree.root, 26, 27));
        */

        System.out.println("USING Recursion and finding path");
        BinaryTree.createBinaryTree3(tree);
        lowestCommonAncestorOfBT(tree.root, new TreeNode(4), new TreeNode(7));
    }

}