/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: SerializeDeserializeBT.java, v 0.1 2020-08-04 23:36 paras.chawla Exp $$ Construct Binary Search Tree using PreOrder
 * traversal
 * <p>
 * You may serialize the following tree:
 *             1 <- root
 *          /    \
 *         2     3
 *        / \   /  \
 *       X  X 4    5
 *           / \  / \
 *          X  X X   X
 * <p>
 * PreOrder - Root Left Right "1,2,X,X,3,4,X,X,5,X,X," as "[1,2,3,null,null,4,5]" https://www.youtube.com/watch?v=jwzo6IsMAFQ
 */
public class SerializeDeserializeBT {

    //1. Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        // 1. base condition
        // If we have a null symbol...we encode a null symbol
        if (root == null) {
            return "X" + ",";
        }
        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);
        return root.val + "," + leftSerialized + rightSerialized; // 1,2,X,X,3,4,X,X,5,X,X,
    }

    //2. Decodes your encoded val to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        //1. Extract elements from queue
        String currValue = queue.poll();

        //2. base Condition
        if ("X".equals(currValue)) {
            return null;
        }

        // 3. Initiate new Node
        TreeNode newNode = new TreeNode(Integer.parseInt(currValue));
        newNode.left = deserializeHelper(queue);
        newNode.right = deserializeHelper(queue);
        return newNode;
    }

    // ---------------------------Convert BST into a PreOrder Array-------------------------
    // get PreOrder traversal of TreeNode and using PreOrder , Convert it back to TreeNode
    /*
     *     4
     *   /    \
     *  2      7
     * / \    / \
     *1  3    6  9
     *
     *
     * PreOrder Traversal Root -> Left SubTree -> Right SubTree
     * 4,2,1,3,7,6,9
     */
    public String serializeBST(TreeNode root) {

        if (root == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        preorder(root, builder);
        return builder.toString();
    }

    private void preorder(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }

        builder.append(root.val + ",");
        preorder(root.left, builder);
        preorder(root.right, builder);
    }

    // Using PreOrder,Convert it into int[]
    /*
     *     4
     *   /    \
     *  2      7
     * / \    / \
     *1  3    6  9
     *
     *
     * PreOrder Traversal Root -> Left SubTree -> Right SubTree
     * preOrder [4,2,1,3,7,6,9]
     */
    public static TreeNode deserializeBST(String preOrder) {

        String tokens[] = preOrder.split(",");
        int[] preorder = new int[tokens.length];
        int i = 0;

        for (String str : tokens) {
            preorder[i++] = Integer.parseInt(str);
        }

        // Approach 1 TC O(n^2)
        //return deSerializeArray(preorder,0,preorder.length-1);

        // Approach 2 TC O(n)
        //return deSerializeArraySol2(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);

        // Approach 3 TC O(n)
        int[] inorder = preorder.clone();
        Arrays.sort(inorder);
        return deSerializeArrayUsingInorderAndPreOrder(inorder, preorder);
    }

    //     index [0,1,2,3,4,5,6]
    // preOrder  [4,2,1,3,7,6,9]
    //            ^       ^   ^
    //           low     dp   high
    // PreOrder nodes are Root-> Left SubTree -> Right SubTree
    // https://www.youtube.com/watch?v=H594EV9OuDI
    /*                 4 <- root
     *  [2,1,3]    /       \  [7,6,9]
     *     [1,3] 2          7 [6,9]
     *      [1]/  \[3]  [6]/ \[9]
     *
     * TC O(n^2)
     * */
    private static TreeNode deSerializeArray(int[] preorder, int low, int high) {

        // base condition
        if (low > high) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[low]);
        // To find a point in array, where root-> left is lesser than root.val and right Subtree val is greater than root.val
        // division is the index where root.val < next greater element
        int division = findDivision(preorder, root.val, low, high);

        root.left = deSerializeArray(preorder, low + 1, division - 1); //root->left SubTree [2,1,3]
        root.right = deSerializeArray(preorder, division, high); //root->right Subtree [7,6,9]

        return root;
    }

    // return a point where right SubTree starts
    // Return idx with point > value
    private static int findDivision(int[] preorder, int value, int low, int high) {
        int i;
        for (i = low; i <= high; i++) {
            if (value < preorder[i]) { break; }
        }
        return i;
    }

    // Instead of knowing division idx which helps to bifurgate right and left subtree, use Binary Search Property
    // preOrder [4,2,1,3,7,6,9]
    //                 ^
    // PreOrder nodes are Root-> Left SubTree -> Right SubTree
    // https://www.youtube.com/watch?v=1pOqgdO327Q
    /*               4(MIN, MAX) <- root
     *            /                \
     *           2(MIN,4)           7(4,MAX)
     *          /       \           / \
     *         1(MIN,2)  3        6    9
     *
     *   TC O(n)
     */
    static int currentIdx = 0;

    // (preorder[],- MIN, MAX)
    private static TreeNode deSerializeArraySol2(int[] preorder, int min, int max) {

        if (currentIdx >= preorder.length) {
            return null;
        }

        TreeNode root = null;

        // Proceed with root, left and right creation only when value at currentIdx suffice BST property
        if (preorder[currentIdx] > min && preorder[currentIdx] < max) {

            root = new TreeNode(preorder[currentIdx]);
            currentIdx++;

            root.left = deSerializeArraySol2(preorder, min, root.val);
            root.right = deSerializeArraySol2(preorder, root.val, max);
        }
        return root;

    }

    // Need to consturct BST using InOrder and PreOrder
    private static TreeNode deSerializeArrayUsingInorderAndPreOrder(int[] inorder, int[] preorder) {

        // O(1) access, add elements in to Map
        Map<Integer, Integer> inMap = new HashMap<>();

        // K - Element , V - Index
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildBST(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    // Root -> Left Subtree -> Right Subtree
    // [4,2,1,3,7,6,9] <- preorder[]
    //  ^           ^
    //  preStart    preEnd

    // [1,2,3,4,6,7,9] <- inorder[]
    //  ^     ^     ^
    //  inS   Ridx  inEnd

    // inOrder Value -> Index Map
    // inMap 1->0, 2->1, 3->2, 4->3, 6->4, 7->5, 9->6

    /*
    *
    *   The basic idea is here:
        Say we have 2 arrays, PRE and IN.
        Preorder traversing implies that PRE[0] is the root node.
        Then we can find this PRE[0] in IN, say it's IN[5].
        Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
        Recursively doing this on subarrays, we can build a tree out of it :)
    *
    */
    //                      buildBST(preorder,              0,          preorder.length - 1, inorder, 0, inorder.length - 1, inMap)
    private static TreeNode buildBST(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
                                     Map<Integer, Integer> inMap) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // root would be always from preorder array
        TreeNode root = new TreeNode(preorder[preStart]);

        // Find root idx in Inorder
        int rootIdx = inMap.get(root.val);

        // Elements from inStart to rootIdx-1 is leftSubtree in inorder and Elements from rootIdx+1, inEnd is rightSubtree in inorder
        // V. IMP - the trick is that no matter which traversal is used, the number of nodes in the subtrees remain the same.
        // so if in the inorder traversal the length of left subarray is 3,
        // this length is also the same as that for pre/postorder traversal as well

        int numsLeft = rootIdx - inStart;
        root.left = buildBST(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, rootIdx - 1, inMap);
        root.right = buildBST(preorder, preStart + numsLeft + 1, preEnd, inorder, rootIdx + 1, inEnd, inMap);

        return root;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createSerializeDeserializeBT(tree);

        SerializeDeserializeBT obj = new SerializeDeserializeBT();
        /*
        String str =obj.serialize(tree.root);
        System.out.println("Serialized String is "+ str);
        */

        //obj.deserialize(str);

        // BST Serialize and Deserialize
        BinaryTree.createBinarySearchTree(tree);
        String str1 = obj.serializeBST(tree.root);
        System.out.println("Serialized String is " + str1);

        deserializeBST(str1);
    }
}