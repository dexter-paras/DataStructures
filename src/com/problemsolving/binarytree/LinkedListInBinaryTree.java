/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: LinkedListInBinaryTree.java, v 0.1 2020-03-01 08:47 paras.chawla Exp $$
 */
public class LinkedListInBinaryTree {

    public static boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root);
    }

    // going dfs left and then right to find if there is any match
    static boolean dfs(ListNode head, TreeNode root) {
        if (root == null) { return false; }
        if (check(head, root)) { return true; }

        if (dfs(head, root.left) || dfs(head, root.right)) { return true; }
        return false;
    }

    /* head ->  [4->2->8-> null]
       root ->
              5
           /    \
          4      4
           \    /
           2   2
          /   / \
         1   6  8
     checking particular root node with LinkedList head
     */
    static boolean check(ListNode head, TreeNode root) {
        if (head == null) { return true; }
        else if (root == null) { return false; }

        if (head.val != (int) root.val) { return false; }
        if (check(head.next, root.left)) { return true; }
        if (check(head.next, root.right)) { return true; }
        return false;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree2(tree);

        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(8);
        System.out.println(isSubPath(head, tree.root));
    }
}

class ListNode {
    int      val;
    ListNode next;

    ListNode(int x) { val = x; }
}