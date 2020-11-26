/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

/**
 * @author paras.chawla
 * @version $Id: DiameterBT.java, v 0.1 2020-04-12 10:01 paras.chawla Exp $$
 * <p>
 * > The depth of a node is the number of edges from the node to the tree's root node. A root node will have a depth of 0.
 * <p>
 * > The height of a node is the number of edges on the longest path from the node to a leaf. A leaf node will have a height of 0
 * <p>
 * https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
 * <p>
 * > The height of a tree is always equal to the depth of the deepest node
 */
public class DiameterBT {

    // Max of (if passing through root, if not passing through root)
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int leftDiamter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        int diamter = Math.max(1 + leftHeight + rightHeight, Math.max(leftDiamter, rightDiameter));
        return diamter;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.left);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        RecursiveAnswer finalAnswer = subtreeMaxDiameter(root);
        return finalAnswer.bestDiameterSeenSoFar;
    }

    /*
     * The "diameter" of a given subtree is the longest path between "any two nodes
     * in the tree". If we want a maximal diameter, we want to know the deepest
     * depth of the left & right subtree of a node (this maximizes the path) and we
     * then add 1 to account for the subtree root.
     */
    private RecursiveAnswer subtreeMaxDiameter(TreeNode root) {
        if (root == null) {
            return new RecursiveAnswer(0, 0);
        }

        RecursiveAnswer left = subtreeMaxDiameter(root.left);
        RecursiveAnswer right = subtreeMaxDiameter(root.right);

        //Best diameter = LH+RH , Max(leftDiameter,RightDiamter)
        int bestDiameterSeenSoFar = Math.max(Math.max(left.bestDiameterSeenSoFar, right.bestDiameterSeenSoFar),
                left.subtreeHeight + right.subtreeHeight);

        // +1 to include the root itself along with the deeper subtree height
        int subtreeHeight = Math.max(left.subtreeHeight, right.subtreeHeight) + 1;

        return new RecursiveAnswer(bestDiameterSeenSoFar, subtreeHeight);
    }

    private class RecursiveAnswer {
        int bestDiameterSeenSoFar;
        int subtreeHeight;

        public RecursiveAnswer(int bestDiameterSeenSoFar, int subtreeHeight) {
            this.bestDiameterSeenSoFar = bestDiameterSeenSoFar;
            this.subtreeHeight = subtreeHeight;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinaryTree7(tree);

        System.out.println(new DiameterBT().diameterOfBinaryTree(tree.root));
        System.out.println(new DiameterBT().diameterOfBinaryTree2(tree.root));
    }
}