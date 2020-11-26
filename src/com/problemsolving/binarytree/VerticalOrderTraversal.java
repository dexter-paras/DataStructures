/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author paras.chawla
 * @version $Id: VerticalOrderTraversal.java, v 0.1 2020-08-08 00:08 paras.chawla Exp $$
 */
public class VerticalOrderTraversal {

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Map<Integer, List<Integer>> map = new TreeMap<>();

        List<Integer> list = new ArrayList<>();
        list.add((int) root.val);
        map.put(0, list);

        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            if (currNode.left != null) {
                queue.add(currNode.left);
                int key = findKey(currNode, map);
                List<Integer> currList = map.get(key - 1);
                if (currList == null) {
                    currList = new ArrayList<>();
                }
                currList.add((int) currNode.left.val);
                map.put(key - 1, currList);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
                int key = findKey(currNode, map);
                List<Integer> currList = map.get(key + 1);
                if (currList == null) {
                    currList = new ArrayList<>();
                }
                currList.add((int) currNode.right.val);
                map.put(key + 1, currList);
            }
        }

        for (Map.Entry<Integer, List<Integer>> obj : map.entrySet()) {
            Collections.sort(obj.getValue());
            result.add(obj.getValue());
        }
        return result;
    }

    private int findKey(TreeNode currNode, Map<Integer, List<Integer>> map) {
        for (Map.Entry<Integer, List<Integer>> obj : map.entrySet()) {
            List<Integer> list = obj.getValue();
            for (int i : list) {
                if (i == (int) currNode.val) {
                    return obj.getKey();
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryTree.createSerializeDeserializeBT(tree);
        new VerticalOrderTraversal().verticalTraversal(tree.root);
    }
}