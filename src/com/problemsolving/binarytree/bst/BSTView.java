/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree.bst;

import com.problemsolving.binarytree.BinaryTree;
import com.problemsolving.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author paras.chawla
 * @version $Id: BSTView.java, v 0.1 2020-01-01 18:05 paras.chawla Exp $$
 */
public class BSTView {

    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create binary tree
        createBinaryTree(tree);

        // height of binary tree recursive
        System.out.println("Height of binary tree Recursively is :" + heightBinaryTreeRecursive(tree.root));
        System.out.println("Height of binary tree Recursively is :" + height(tree.root));

        // height of binary tree itertative
        System.out.println("Height of binary tree Iteratively is :" + heightBinaryTreeIterative(tree.root));

        // 1.1 left View Iterative
        printLeftViewIterative(tree.root);
        // 1.2 left View Recursive
        printLeftViewRecursive(tree.root);

        // 2.1 right View Iterative
        printRightViewIterative(tree.root);
        // 2.2 right View Recursive
        printRightViewRecursive(tree.root);

        /* 3.1 top View Recursive
           key - horizontal distance from root node , v
           value - nodes present at same horizontal distance
        */
        Map<Integer, List<Integer>> map = new TreeMap<>();
        printTopViewRecursive(tree.root, 0, map);
        System.out.println(map);

        // 3.2 top View Iterative

        // 4.1 bottom View Iterative
        // 4.2 bottom View Recursive

        // 5.1 diagonal View Iterative
        // 5.2 diagonal View Recursive

        // 6.1 boundary of a Binary Tree
        //BinaryTree tree1 = new BinaryTree();
        //createBoundaryTree(tree1);
        printBoundaryView(tree.root);

        boundaryOfBinaryTree(tree.root);

    }

    private static List<Integer> printBoundaryView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> leftResult = new ArrayList<>();
        List<Integer> rightResult = new ArrayList<>();
        List<Integer> leafResult = new ArrayList<>();

        // 1. Calculate height of binary tree
        int height = heightBinaryTreeRecursive(root);

        // 2. Traverse level order wise
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        // 3. Traverse queue
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxSize = size;

            while (size-- > 0) {
                TreeNode currNode = queue.poll();
                if (currNode.left == null && currNode.right == null) {
                    leafResult.add((int) currNode.val);
                } else {
                    if (maxSize - 1 == size && level != height) {
                        leftResult.add((int) currNode.val);
                    }
                    if (size == 0 && level != height) {
                        rightResult.add((int) currNode.val);
                    }
                }
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            level++;
        }

        // Case 1: if root left subtree doesn't exist
        if (root.left == null && root.right != null) {
            result.add((int) root.val);
            result.addAll(leafResult);

            // collect right result
            int length = rightResult.size();
            for (int i = length; i > 1; i--) {
                result.add(rightResult.get(i - 1));
            }
        }

        // Case 2:if root right subtree doesn't exist
        else if (root.right == null && root.left != null) {
            result.addAll(leftResult);
            result.addAll(leafResult);
        }
        // Case 3: if root left and right subtree both exist
        else {
            result.addAll(leftResult);
            result.addAll(leafResult);
            // collect right result
            int length = rightResult.size();
            for (int i = length; i > 1; i--) {
                result.add(rightResult.get(i - 1));
            }
        }
        return result;
    }

    // Print right view Recursive
    private static void printRightViewRecursive(TreeNode root) {
        helperRightView(root, 0);
    }

    // traverse nodes in pre-order fashion Root -> Right -> Left
    private static void helperRightView(TreeNode root, int level) {
        // traverse tree level by level starting from right Child
        if (level == result.size()) {
            result.add((int) root.val);
        }
        if (root.right != null) {
            helperRightView(root.right, level + 1);
        }
        if (root.left != null) {
            helperRightView(root.left, level + 1);
        }
    }

/*
* Input:
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10
* */

    /* create Binary Tree (BT doesn't belong nodes in order) */
    private static void createBinaryTree(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.left.right.left = new TreeNode(7);
        tree.root.left.right.right = new TreeNode(8);

        tree.root.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.left.left = new TreeNode(9);
        tree.root.right.left.right = new TreeNode(10);
    }

    /*
    * Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]
    * */

    private static void createBoundaryTree(BinaryTree tree) {
        tree.root = new TreeNode(1);

        tree.root.right = new TreeNode(2);
        tree.root.right.left = new TreeNode(3);
        tree.root.right.right = new TreeNode(4);
    }


    /*
 root->  1
       /   \
      2     3
       \   / \
       4  5  6
         / \
        7  8
     */

    // In level order traversal, print 1st node of every current level
    private static void printLeftViewIterative(TreeNode root) {

        if (root == null) { return; }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        System.out.println("LeftViewIterative:");
        while (!queue.isEmpty()) {

            // calculate nodes in current level
            int size = queue.size();
            int i = 0;

            // iterate all nodes in current level
            while (i++ < size) {

                TreeNode currNode = queue.poll();

                // print first node of current level
                if (i == 1) { System.out.print(currNode.val + "->"); }

                if (currNode.left != null) { queue.add(currNode.left); }

                if (currNode.right != null) { queue.add(currNode.right); }
            }
        }
    }

    // In level order traversal, print last node of every current level
    private static void printRightViewIterative(TreeNode root) {

        if (root == null) { return; }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        System.out.println("\n RightViewIterative:");
        while (!queue.isEmpty()) {

            // calculate nodes in current level
            int size = queue.size();
            int i = 0;

            // iterate all nodes in current level
            while (i++ < size) {

                TreeNode currNode = queue.poll();

                // print last node of current level
                if (i == size) { System.out.print(currNode.val + "->"); }

                if (currNode.left != null) { queue.add(currNode.left); }

                if (currNode.right != null) { queue.add(currNode.right); }
            }
        }
    }

    /*
     1
   /   \
  2     3
   \   / \
   4  5  6
     / \
    7  8
 */

    private static void printLeftViewRecursive(TreeNode root) {
        // create an empty HashMap to store first node for each level
        Map<Integer, Integer> map = new HashMap<>();

        // traverse the tree and fill map
        leftView(root, 1, map);

        // iterate through the HashMap in sorted order of its keys
        // and print the left view
        System.out.println("LeftViewRecursive:");
        for (int i = 1; i <= map.size(); i++) {
            System.out.print(map.get(i) + " ");
        }
    }

    // traverse nodes in pre-order fashion Root -> Left -> Right
    private static void leftView(TreeNode root, int level, Map<Integer, Integer> map) {
        // base case
        if (root == null) {
            return;
        }

        // if level is visited for the first time, insert the current node
        // and level information into the map
        if (!map.containsKey(level)) {
            map.put(level, (int) root.val);
        }

        leftView(root.left, level + 1, map);
        leftView(root.right, level + 1, map);

    }

    // calculate height using recursive
    public static int heightBinaryTreeRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightBinaryTreeRecursive(root.left), heightBinaryTreeRecursive(root.right));
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = 1 + height(node.left);
        int rightHeight = 1 + height(node.right);
        if (leftHeight > rightHeight) { return leftHeight; } else { return rightHeight; }
    }

    // calculate height using iterative
    private static int heightBinaryTreeIterative(TreeNode root) {
        if (root == null) { return 0; }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode currNode = queue.poll();
                if (currNode.left != null) { queue.add(currNode.left); }
                if (currNode.right != null) { queue.add(currNode.right); }
            }
            height++;
        }
        return height - 1;
    }

    /*
        root->      10(0)
                /        \
             20(-1)       30(1)
                \       /    \
                40(0) 50(0)  60(2)
                      /       \
                    70(-1)     80(3)
*/
    /* To print Top view, need to calculate horizontal distance from root node
     */
    private static void printTopViewRecursive(TreeNode root, int dist, Map<Integer, List<Integer>> map) {

        if (root == null) {
            return;
        }
        insertIntoMultiMap(map, dist, (int) root.val);
        printTopViewRecursive(root.left, dist - 1, map);
        printTopViewRecursive(root.right, dist + 1, map);
    }

    /* map which includes how many nodes are there at dist d */
    private static void insertIntoMultiMap(Map<Integer, List<Integer>> map, int dist, int data) {
        if (!map.containsKey(dist)) {
            map.put(dist, new ArrayList<>());
        }
        map.get(dist).add(data);
    }


    /*
    1
    2
    4 7
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

  OR

   1
 /   \
7    2
\   /  \
 4  3   4
 \
  5
  /
 8 * */

    static Set<TreeNode> leafBoundary = new LinkedHashSet<>();

    // print boundary of Binary Tree
    public static List<Integer>  boundaryOfBinaryTree(TreeNode root) {
        Set<TreeNode> leftBoundary = new LinkedHashSet<>();
        Set<TreeNode> rightBoundary = new LinkedHashSet<>();

        if (root.left == null) {
            leftBoundary.add(root);
        } else {
            leftBoundary = calculateLeftBoundary(root);
        }

        if (root.right == null) {
            rightBoundary.add(root);
        } else {
            rightBoundary = calculateRightBoundary(root);
        }

        calculateLeafNode(root);

        return aggregate(leftBoundary, leafBoundary, rightBoundary);
    }

    private static List<Integer> aggregate(Set<TreeNode> leftBoundary, Set<TreeNode> leafBoundary, Set<TreeNode> rightBoundary) {

        Set<TreeNode> result = new LinkedHashSet<>();
        result.addAll(leftBoundary);
        result.addAll(leafBoundary);

        List<TreeNode> list = new ArrayList<>(rightBoundary);
        Collections.reverse(list);
        result.addAll(list);

        List<Integer> finalList = new ArrayList<>();
        Iterator<TreeNode> itr = result.iterator();
        while (itr.hasNext()) {
            finalList.add(itr.next().val);
        }
        return finalList;
    }

    private static void calculateLeafNode(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafBoundary.add(root);
        }
        calculateLeafNode(root.left);
        calculateLeafNode(root.right);
    }

    private static Set<TreeNode> calculateLeftBoundary(TreeNode root) {

        Set<TreeNode> boundary = new LinkedHashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int i = 0;
            int size =queue.size();
            // iterate all nodes in current level
            while (i++ < size) {
                TreeNode currNode = queue.poll();

                // print first node of current level
                if (i == 1) { boundary.add(currNode); }
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
        }
        return boundary;
    }

    private static Set<TreeNode> calculateRightBoundary(TreeNode root) {

        Set<TreeNode> boundary = new LinkedHashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int i = 0;
            int size = queue.size();
            // iterate all nodes in current level
            while (i++ < size) {
                TreeNode currNode = queue.poll();

                // print first node of current level
                if (i == size) { boundary.add(currNode); }
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
        }
        return boundary;
    }

}