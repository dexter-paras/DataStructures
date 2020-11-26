/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: PrintNodesKDistanceFromLeaf.java, v 0.1 2020-10-20 09:35 paras.chawla Exp $$ Print all nodes that are at distance k from a
 * leaf node
 * <p>
 * 1 <- null /  \ 2    3 / \  / \ 4  5  6 7 / 8
 * <p>
 * 1) find all leaf nodes - leaf.left == null && leaf.right==null 2) traverse towards root node using parentChild to distance=k 3)
 * Set<BinaryTree> </BinaryTree>
 * <p>
 * 4-2 8-5 6-3 7-3 k=1, {2,5,3}
 *
 * https://javabypatel.blogspot.com/2016/12/print-nodes-at-k-distance-from-leaf.html
 */

public class PrintNodesKDistanceFromLeaf {

    Set<BinaryTreee> binarySet = new HashSet<>();
    Set<TreeNode>    set       = new HashSet<>();

    private Set<BinaryTreee> printNodesKDistance(BinaryTreee root, int k) {

        // find all leaf nodes using BFS
        Queue<BinaryTreee> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreee currNode = queue.poll();
            // Already at leaf node
            if (currNode.leftChild == null && currNode.rightChild == null) {
                backTraverseToKDistance(currNode, k);
            }
            if (currNode.leftChild != null) {
                queue.offer(currNode.leftChild);
            }
            if (currNode.rightChild != null) {
                queue.offer(currNode.rightChild);
            }
        }
        return binarySet;
    }

    private void backTraverseToKDistance(BinaryTreee currNode, int k) {
        while (currNode.parentChild != null) {
            k--;
            if (k == 0) {
                binarySet.add(currNode);
                break;
            }
            currNode = currNode.parentChild;
        }
    }

    // Approach 2- Without using any parent Node concept
    private Set<TreeNode> printNodesKDistanceRecursive(TreeNode root, int k) {
        // CurrentNode, ParentNode
        Map<TreeNode, TreeNode> map = new HashMap<>();

        // base condition
        if (root == null) {
            return null;
        }

        map.put(root, null);

        helper(root, k, map);
        return set;
    }

    // running PreOrder - Left/Root/Right
    private void helper(TreeNode root, int k, Map<TreeNode, TreeNode> map) {

        if (root == null) {
            return;
        }
        // leaf node
        if (root.left == null && root.right == null) {
            backTraverseToKDistance(root, map, k);
            return;
        }

        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }

        helper(root.left, k, map);
        helper(root.right, k, map);
    }

    private void backTraverseToKDistance(TreeNode root, Map<TreeNode, TreeNode> map, int k) {
        TreeNode currNode = map.get(root);
        k--;
        if (k == 0) {
            set.add(currNode);
            return;
        }
        backTraverseToKDistance(currNode, map, k);
    }

    public static void main(String[] args) {
        PrintNodesKDistanceFromLeaf obj = new PrintNodesKDistanceFromLeaf();
        com.problemsolving.binarytree.BinaryTree tree = new com.problemsolving.binarytree.BinaryTree();
        tree.createBinaryTreePrintNodes(tree);
        obj.printNodesKDistanceRecursive2(tree.root, 2);

    }

    /* Approach 3- Without using Map
       pathList -> current traversal path
       visitedList -> whether node is already visited or not

    STEP 1: Traverse the Tree in Pre-order traversal and increment variable currentDistanceFromRoot
                which captures distance of node from Root node.

    STEP 2: Also, while traversing a tree, we will capture all the nodes in a Path until we encounter a
                Leaf node. This will help us to identify Node at distance K from Leaf node.

    STEP 3: When we reach a Leaf node that is a node which doesn't has left and right child, we need to
                find a node which is at K distance away from current Leaf  node.
                (currentDistanceFromRoot - K) will give us index of Node which is at K distance away
                from current Leaf node.
                 We will directly look into Path and print Node present at that index.
    */

    List<TreeNode> result = new ArrayList<>();
    private void printNodesKDistanceRecursive2(TreeNode root, int k) {

        // base condition
        if (root == null) {
            return;
        }
        List<TreeNode> pathList = new ArrayList<>();
        List<Boolean> alreadyVisitedFlagList = new ArrayList<Boolean>();

        helper(root, k, 0, pathList, alreadyVisitedFlagList);
    }

    private void helper(TreeNode root, int k, int currentDistanceFromRoot, List<TreeNode> traversalList,
                        List<Boolean> alreadyVisitedFlagList) {

        // add current node in list
        traversalList.add(root);

        // mark it not printed yet
        alreadyVisitedFlagList.add(false);

        // check if leaf node
        if (root.right == null && root.left == null) {

            int indexToPrint = currentDistanceFromRoot - k;
            if (indexToPrint >= 0 && !alreadyVisitedFlagList.get(indexToPrint)) {

                result.add(traversalList.get(indexToPrint));

                //marking as node already printed
                alreadyVisitedFlagList.set(indexToPrint, true);
                return;
            }

        }

        // Traverse PreOrder Left -> Right
        if (root.left != null) {
            helper(root.left, k, currentDistanceFromRoot + 1, traversalList, alreadyVisitedFlagList);

            // Already visited Left subtree of current node. Remove current node form pathList
            traversalList.remove(currentDistanceFromRoot+1);

            // Already visited Left subtree of current node. Remove current node form alreadyVisitedFlagList
            alreadyVisitedFlagList.remove(currentDistanceFromRoot+1);
        }

        if (root.right != null) {
            helper(root.right, k, currentDistanceFromRoot + 1, traversalList, alreadyVisitedFlagList);

            // Already visited Left subtree of current node. Remove current node form pathList
            traversalList.remove(currentDistanceFromRoot+1);

            // Already visited Left subtree of current node. Remove current node form alreadyVisitedFlagList
            alreadyVisitedFlagList.remove(currentDistanceFromRoot+1);

        }

    }

    class BinaryTreee {
        BinaryTreee parentChild;
        BinaryTreee leftChild;
        BinaryTreee rightChild;
        int         data;
    }

}

