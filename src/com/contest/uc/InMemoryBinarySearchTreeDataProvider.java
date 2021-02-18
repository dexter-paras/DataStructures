/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.uc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: InMemoryPriorityQueueDataProvider.java, v 0.1 2020-12-07 18:33 paras.chawla Exp $$
 * <p>
 * add(1, 20) add(2, 30) Add(3, 10) Add(4, 15)
 * <p>
 * get(startValue = 20) => [1, 20], [2, 30]
 * <p>
 * delete(2) get(startValue = 20) => [1, 20]
 * <p>
 * [10,30,10]
 * <p>
 * <p>
 * Balanced Binary Search Tree
 *
 *      1|20 <- root
 *      /  \
 *   3|10   2|30
 *     \
 *    4|15
 *
 * https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
 * https://codereview.stackexchange.com/questions/209751/generic-binary-search-tree-implementation-in-java
 */
public class InMemoryBinarySearchTreeDataProvider implements DataProvider {

    private Map<Integer, Integer> map;
    private BST<Integer, Integer> bst;

    public InMemoryBinarySearchTreeDataProvider() {
        map = new HashMap<>();
        bst = new BST<>();
    }

    public void add(int jobId, int startValue) {
        map.put(jobId, startValue);
        bst.insertBST(bst.root, jobId, startValue);
    }

    public void delete(int jobId) {
        map.remove(jobId);
    }

    public List<Pair> get(int startValue) {
        List<Pair> list = new ArrayList<>();

        return list;
    }

    public static void main(String[] args) {
        InMemoryBinarySearchTreeDataProvider obj = new InMemoryBinarySearchTreeDataProvider();
        obj.add(1, 20);
        obj.add(2, 30);
        obj.add(3, 10);
        obj.add(4, 15);

        System.out.println(obj.get(15));
        obj.delete(1);
        System.out.println("After delete" + obj.map);
    }
}

class TreeNode<K, V extends Comparable<V>> {
    K key;
    V value;

    TreeNode<K, V> leftChild;
    TreeNode<K, V> rightChild;

    public TreeNode() {}

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class BST<K, V extends Comparable<V>> {

    TreeNode<K, V> root;

    public TreeNode insertBST(TreeNode root, K key, V value) {
        if (root == null) {
            this.root = new TreeNode(key, value);
            return this.root;
        }
        // insert into left SubTree
        if (root.value.compareTo(value) > 0) {
            root.leftChild = insertBST(root.leftChild, key, value);
        } else {
            root.rightChild = insertBST(root.rightChild, key, value);
        }
        return root;
    }
}
