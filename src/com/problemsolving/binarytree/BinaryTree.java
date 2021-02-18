/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: BinaryTree.java, v 0.1 2019-12-28 10:18 paras.chawla Exp $$
 */
public class BinaryTree<T> {

    // root of a binary Tree
    public TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create normal binary tree
        createBinaryTree(tree);

        tree.levelOrder(tree.root);
        tree.reverseLevelOrder(tree.root);

        System.out.println("Inorder Traversal before insertion");
        tree.inorderTraversal(tree.root);

        insertionLevelOrder(tree.root, 12);

        System.out.println(" \n Inorder Traversal after insertion");
        tree.inorderTraversal(tree.root);

        System.out.println(" \n Inorder Traversal Without Recursion");
        inorderTraversalWithoutRecursion(tree.root);

        tree.createBST();
        System.out.println(" \n Inorder Traversal after insertion in a Binary Search Tree");
        tree.inorderTraversal(tree.root);

    }

    public void createBST() {
        /*Create binary search tree*/
        while (true) {
            System.out.println("Enter 1 to continue insert val else Enter 0 to break");
            Scanner scanner = new Scanner(System.in);
            int scan = scanner.nextInt();
            if (scan == 1) {
                System.out.println("Enter val");
                int data = scanner.nextInt();
                insertIterativelyInBST(data);
            } else { break; }
        }

    }

    /*
      10
    /   \
   5    15
  / \   / \
 2  8  12 20
    */

    /* Binary Search tree support ordering of nodes */
    public void insertIterativelyInBST(int data) {
        TreeNode currentNode, parentNode;

        /*Insert root element */
        if (root == null) {
            root = new TreeNode(data);
            return;
        } else {
            currentNode = root;
        }
        while (true) {
            parentNode = currentNode;
            if ((int) currentNode.val > data) {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    parentNode.left = new TreeNode(data);
                    return;
                }
            } else if (data > (int) currentNode.val) {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    parentNode.right = new TreeNode(data);
                    return;
                }
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        traversal(root, 0, map);

        for (int i = 0; i < map.size(); i++) {
            result.add(i, map.get(i));
        }
        return result;
    }

    public List<List<Integer>> reverseLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        traversal(root, 0, map);

        for (int i = map.size() - 1; i >= 0; i--) {
            result.add(map.get(i));
        }
        return result;
    }

    void traversal(TreeNode root, int level, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        insertIntoMap(map, level, root);
        traversal(root.left, level + 1, map);
        traversal(root.right, level + 1, map);
    }

    void insertIntoMap(Map<Integer, List<Integer>> map, int level, TreeNode root) {
        if (!map.containsKey(level)) {
            map.put(level, new ArrayList<>());
        }
        map.get(level).add(root.val);
    }

    /* Binary tree which doesn't support any ordering of nodes *//*
    private static void createBinaryTree(BinaryTree tree) {
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(11);
        tree.root.left.left = new TreeNode(7);
        tree.root.right = new TreeNode(9);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(8);

    }*/

    private static void insertionLevelOrder(TreeNode root, int data) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.remove();
            if (currNode.left != null && currNode.right != null) {
                queue.add(currNode.left);
                queue.add(currNode.right);
            }
            if (currNode.left == null) {
                currNode.left = new TreeNode(data);
                break;
            }
            if (currNode.right == null) {
                currNode.right = new TreeNode(data);
                break;
            }
        }
    }

    /* Inorder traversal of Binary Tree : Left -> Root -> Right */
    public static void inorderTraversal(TreeNode root) {
        if (root == null) { return; }
        inorderTraversal(root.left);
        System.out.print(root.val + "->");
        inorderTraversal(root.right);
    }

    /*Use Stack to print inorder Traversal */
    private static void inorderTraversalWithoutRecursion(TreeNode root) {
        if (root == null) { return; }
        Stack<TreeNode> stack = new Stack();

        TreeNode currNode = root;
        while (true) {
            if (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            } else {
                if (stack.isEmpty()) { break; }
                currNode = stack.pop();
                System.out.print(currNode.val + "->");
                currNode = currNode.right;
            }
        }
    }

    /*
          10   <- parent
        /   \
       5      15 <- case 1.3
      / \   /   \
     2  8  12    20 <- case 1.1
            \   / \
            13 17  22
   1.1 delete 20 <- no childern <- delete , no issue
   1.2 delete 12 <- only 1 child <- copy 13 to 15 left , no issue
   1.3 delete 15 <- tricky case <- Determine the next highest element (inorder successor) in the right subtree.
                                   Replace the node to be removed with the inorder successor. Delete the inorder successor duplicate.
   */
    public void deleteIteratively(TreeNode root) {
    }

    /*
           1
          / \
         2  2
        /\  /\
       3 4 4 3
    * */
    public static void createBinaryTree(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(4);
        tree.root.right.right = new TreeNode(3);
    }

    public static void createBinaryTree2(BinaryTree tree) {
        tree.root = new TreeNode(5);
        tree.root.left = new TreeNode(4);
        tree.root.right = new TreeNode(4);
        tree.root.left.right = new TreeNode(2);
        tree.root.left.right.left = new TreeNode(1);
        tree.root.right.left = new TreeNode(2);
        tree.root.right.left.left = new TreeNode(6);
        tree.root.right.left.right = new TreeNode(8);
    }

    // LCA of a BT
    public static void createBinaryTree3(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
    }

    // Path Sum
    /*
target = 22
         root->5
              / \
             4   8
            /   / \
           11  5  4
          / \    /
         7  2   5
*/
    public static void createBinaryTree4(BinaryTree tree) {
        tree.root = new TreeNode(5);
        tree.root.left = new TreeNode(4);
        tree.root.right = new TreeNode(8);
        tree.root.left.left = new TreeNode(11);
        tree.root.right.left = new TreeNode(5);
        tree.root.right.right = new TreeNode(4);
        tree.root.left.left.left = new TreeNode(7);
        tree.root.left.left.right = new TreeNode(2);
        tree.root.right.right.left = new TreeNode(5);
    }

    public static void createBinaryTree4EdgeCase(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.right = new TreeNode(2);
        tree.root.right.right = new TreeNode(3);
        tree.root.right.right.right = new TreeNode(4);
        tree.root.right.right.right.right = new TreeNode(5);
    }

    public static void createBinaryTree4EdgeCase2(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(-2);
        tree.root.right = new TreeNode(-3);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(-2);
        tree.root.left.left.left = new TreeNode(-1);
    }

    // Path Sum
    /*
target = 22
         root->5
              / \
             4   8
            /   / \
           11  5  9
          / \  \
         7  2  9
              /
             10
             \
             11
*/

    public static void createBinaryTree5(BinaryTree tree) {
        tree.root = new TreeNode(5);
        tree.root.left = new TreeNode(4);
        tree.root.right = new TreeNode(8);
        tree.root.left.left = new TreeNode(11);
        tree.root.right.left = new TreeNode(5);
        tree.root.right.left.right = new TreeNode(9);
        tree.root.right.left.right.left = new TreeNode(10);
        tree.root.right.left.right.left.right = new TreeNode(11);
        tree.root.right.right = new TreeNode(9);
        tree.root.left.left.left = new TreeNode(7);
        tree.root.left.left.right = new TreeNode(2);
    }

    public static void createBinaryTree6(BinaryTree tree) {
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(7);
    }

    public static void createBinaryTree7(BinaryTree tree) {
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
        tree.root.left.left = new TreeNode(5);
        tree.root.left.left.right = new TreeNode(2);
        tree.root.left.left.right.left = new TreeNode(5);
        tree.root.left.left.right.right = new TreeNode(4);
        tree.root.left.right = new TreeNode(7);
        tree.root.left.right.left = new TreeNode(4);
        tree.root.left.right.right = new TreeNode(6);
        tree.root.left.right.right.right = new TreeNode(3);
    }

    /*
     *     4
     *   /  \
     *  2    7
     * / \  / \
     *1  3 6  9
     *
     */

    public static void createBinarySearchTree(BinaryTree tree) {
        tree.root = new TreeNode(4);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(7);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(9);
    }

    /*
     *     4
     *   /  \
     *  1    6
     * / \  / \
     *0  2 5  7
     *   \     \
     *   3     8
     *
     */

    public static void createBinarySearchTree3(BinaryTree tree) {
        tree.root = new TreeNode(4);
        tree.root.left = new TreeNode(1);
        tree.root.right = new TreeNode(6);
        tree.root.left.left = new TreeNode(0);
        tree.root.left.right = new TreeNode(2);
        tree.root.left.right.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(5);
        tree.root.right.right = new TreeNode(7);
        tree.root.right.right.right = new TreeNode(8);
    }


    /*
     *     3 <- root
     *   /  \
     *  0    4
     *   \
     *    2
     *   /
     *  1
     */

    public static void createBinarySearchTree2(BinaryTree tree) {
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(0);
        tree.root.right = new TreeNode(4);
        tree.root.left.right = new TreeNode(2);
        tree.root.left.right.left = new TreeNode(1);
    }

    /*
    1 <- root
  /   \
 2     3
      /  \
     4   5
    */
    public static void createSerializeDeserializeBT(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(4);
        tree.root.right.right = new TreeNode(5);
    }

    /*
            1
          /    \
         2      3
       /  \    / \
       4   5  6  7
           /\  / \
          8 9 13 14
          /
         10
         / \
        11 12


    */
    public static void createBinaryTreePrintNodes(BinaryTree tree) {
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        tree.root.left.right.left = new TreeNode(8);
        tree.root.left.right.right = new TreeNode(9);
        tree.root.left.right.left.left = new TreeNode(10);
        tree.root.left.right.left.left.left = new TreeNode(11);
        tree.root.left.right.left.left.right = new TreeNode(12);
        tree.root.right.right.left = new TreeNode(13);
        tree.root.right.right.right = new TreeNode(14);
    }

}