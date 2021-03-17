/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.*;

/**
 * @author paras.chawla
 * @version $Id: TwoSum4.java, v 0.1 2021-01-28 11:04 AM paras.chawla Exp $$
 */
public class TwoSum4 {

    public boolean findTarget(TreeNode root, int k) {

        // traverse BST and create HashMap
        HashMap<Integer, Integer> map = new HashMap<>();

        inorder(root, map);

        if (map.size() == 1) {
            return false;
        }

        System.out.println(map);

        for (Map.Entry<Integer, Integer> entrySet : map.entrySet()) {
            if (map.containsKey(k - entrySet.getKey())) {
                if (k - entrySet.getKey() == entrySet.getKey() && entrySet.getValue() > 1) {
                    return true;
                } else if (k - entrySet.getKey() != entrySet.getKey()) {
                    return true;
                }
            }
        }
        return false;
    }
    // inorder Traversal of BST

    void inorder(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        inorder(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        inorder(root.right, map);
    }

    public boolean findTarget2(TreeNode root, int k) {

        Set<Integer> set = new HashSet<>();

        // inorder traversal and checking
        return inorder(root, k, set);
    }

    private boolean inorder(TreeNode root, int k, Set<Integer> set) {

        if (root == null) {
            return true;
        }

        inorder(root.left, k, set);
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        inorder(root.right, k, set);
        return false;
    }

}