/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Nov;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: N9_MaxDiffNodeAncestor.java, v 0.1 2020-11-10 10:09 paras.chawla Exp $$
 */
public class N9_MaxDiffNodeAncestor {

    int maxDiff = 0;

    public int maxAncestorDiff(TreeNode root) {

        // CurrentNode, ParentNode
        Map<TreeNode, TreeNode> map = new HashMap<>();

        // base condition
        if (root == null) {
            return 0;
        }

        map.put(root, null);

        helper(root, map);
        return maxDiff;
    }

    // running PreOrder - Left/Root/Right
    private void helper(TreeNode root, Map<TreeNode, TreeNode> map) {

        if (root == null) {
            return;
        }
        // leaf node
        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            backTraverseToRoot(root, map, list);
            return;
        }

        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }

        helper(root.left, map);
        helper(root.right, map);
    }

    private void backTraverseToRoot(TreeNode root, Map<TreeNode, TreeNode> map, List<Integer> list) {

        TreeNode nextNode = map.get(root);
        if (nextNode == null) {
            maxAbsDiff(list, list.size());
            return;
        } else {
            list.add(nextNode.val);
            backTraverseToRoot(nextNode, map, list);
        }
    }

    private void maxAbsDiff(List<Integer> list, int n) {

        // To store the minimum and the maximum
        // elements from the list
        int minEle = list.get(0);
        int maxEle = list.get(0);
        for (int i = 1; i < n; i++) {
            minEle = Math.min(minEle, list.get(i));
            maxEle = Math.max(maxEle, list.get(i));
        }
        maxDiff = Math.max(maxDiff, maxEle - minEle);
    }

    public static void main(String[] args) {
        N9_MaxDiffNodeAncestor obj = new N9_MaxDiffNodeAncestor();
        BinaryTree tree = new BinaryTree();
        tree.createBinaryTreePrintNodes(tree);
        System.out.println(obj.maxAncestorDiff(tree.root));
    }

    public int maxAncestorDiff2(TreeNode root) {
        if(root==null){
            return maxDiff;
        }

        helper(root,root.val,root.val);
        return maxDiff;
    }

    private void helper(TreeNode node, int curMax, int curMin){
        // base condition
        if(node ==null){
            return;
        }
        // update the max and min
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);
        maxDiff= Math.max(maxDiff, Math.abs(curMin- curMax));
        helper(node.left,curMax, curMin);
        helper(node.right,curMax, curMin);
        return;
    }


}