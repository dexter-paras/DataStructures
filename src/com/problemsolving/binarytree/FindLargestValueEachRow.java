/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author paras.chawla
 * @version $Id: FindLargestValueEachRow.java, v 0.1 2021-01-14 7:28 PM paras.chawla Exp $$
 */
public class FindLargestValueEachRow {

    public List<Integer> largestValues(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if(root==null){
            return result;
        }

        // Create Map<Integer, List<Integer>
        Map<Integer, List<Integer>> map = new HashMap<>();

        preOrder(root,map,0);

        for(int i=0;i<map.size();i++){
            List<Integer> list = map.get(i);
            int max = list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
            result.add(max);
        }

        return result;
    }

    void preOrder(TreeNode root, Map<Integer, List<Integer>> map,int level){

        if(root==null){
            return;
        }

        if(!map.containsKey(level)){
            map.put(level,new ArrayList<Integer>());
        }
        map.get(level).add(root.val);
        preOrder(root.left,map,level+1);
        preOrder(root.right,map,level+1);
    }

    // Approach 2- No need to create a Map having all integers in a Map...Store only Max value of level in a Map
    public List<Integer> largestValuesSol2(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // Create Map<Integer,Integer>
        Map<Integer, Integer> map = new HashMap<>();

        preOrderSol2(root, map, 0);

        for (int i = 0; i < map.size(); i++) {
            result.add(map.get(i));
        }

        return result;
    }

    void preOrderSol2(TreeNode root, Map<Integer, Integer> map,int level) {

        if (root == null) {
            return;
        }

        if (!map.containsKey(level)) {
            map.put(level, root.val);
        } else {
            map.put(level, Math.max(map.get(level), root.val));
        }
        preOrderSol2(root.left, map, level + 1);
        preOrderSol2(root.right, map, level + 1);
    }


    public static void main(String[] args) {
        FindLargestValueEachRow obj = new FindLargestValueEachRow();
        BinaryTree tree = new BinaryTree();
        BinaryTree.createBinarySearchTree(tree);
        obj.largestValuesSol2(tree.root);
    }
}