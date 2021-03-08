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
        if (p < root.val && q < root.val) {
            return lowestCommonAncestorOfBST(root.left, p, q);
        }
        // 2. if both p and q greater than root, call recursively to right of root node
        if (p > root.val && q > root.val) {
            return lowestCommonAncestorOfBST(root.right, p, q);
        }
        /*
         * 3. One of x or y is equal to the root.
         * OR
         * 4. One of x or y is less than root and the other is greater than root.
         */
        return root.val;
    }

    // Approach 2.1 - Using Recursion to find LCA of a BT
    public static TreeNode lowestCommonAncestorOfBT(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        findPath(root, p, path1);
        findPath(root, q, path2);

        // path1 3->5
        // path2 3->5->2->4

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).val != (path2.get(i).val)) {
                break;
            }
        }

        return path1.get(i - 1);
    }

    // To Find path of a node x from root node and find if path exist or not
    private static boolean findPath(TreeNode root, TreeNode x, List<TreeNode> path) {

        // base condition
        if (root == null) {
            return false;
        }

        // add current node in path
        path.add(root);

        //if node found, return true that path has been found
        if (root.val == x.val) {
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

    /* Approach 2.2
        Using Recursion , only single traversal
        O(n) TC and O(1) SC
     */

    public static TreeNode findLCA(TreeNode node, int p, int q) {

        // base case
        if (node == null) { return null; }

        if (node.val ==p || node.val ==q)
            return node;

        // Look for keys in left and right subtree
        TreeNode left_lca = findLCA(node.left, p, q);
        TreeNode right_lca = findLCA(node.right, p, q);

        // If both of the above calls return Non-NULL, then one key
        // is present in one subtree and other is present in other,
        // So this node is the LCA

        if(left_lca!=null && right_lca!=null)
            return node;

        // Otherwise check if left subtree or right subtree is LCA
        return (left_lca != null) ? left_lca : right_lca;
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
        BinaryTree.createBinaryTreePrintNodes(tree);
        //lowestCommonAncestorOfBT(tree.root, new TreeNode(4), new TreeNode(7));
        System.out.println(findLCA(tree.root,4,5));
        System.out.println(findLCA(tree.root,11,7));
    }

}