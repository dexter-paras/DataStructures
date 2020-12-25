/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import com.common.algorithms.DoublyLinkedList;
import com.common.algorithms.DoublyNode;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author paras.chawla
 * @version $Id: MaxStack.java, v 0.1 2020-12-23 12:34 paras.chawla Exp $$
 * <p>
 * DLL head-> 4->7->10->2->10->6 <-tail
 * <p>
 * TreeMap Key - Integer  Value -List<Node>
 * <p>
 *      7 <- root
 *     / \
 *    4  10
 *   / \  /
 *  2  6 10
 * <p>
 * 10-> Node3->Node5
 */
public class MaxStack {

    DoublyLinkedList<Integer>          dll;
    TreeMap<Integer, List<DoublyNode>> map;

    /**
     * initialize your data structure here.
     */
    public MaxStack() {
        dll = new DoublyLinkedList<>();
        map = new TreeMap<>();
    }

    // DLL - O(1)
    // TreeMap - O(logn)
    public void push(int x) {
        DoublyNode newNode = new DoublyNode(x);
        dll.addNodeAtLast(newNode);

        map.putIfAbsent(x, new LinkedList<DoublyNode>());
        map.get(x).add(newNode);
    }

    // Dll - O(1)
    // TreeMap - Non-Duplicate - O(logn)
    // TreeMap - Duplicate - O(1)
    public int pop() {
        DoublyNode node = dll.getLastNode();
        dll.detachNode(node);

        int element = (int) node.getElement();
        int listSize = map.get(element).size();
        if (listSize == 1) {
            map.remove(element);
        } else {
            map.get(element).remove(listSize - 1);
        }
        return element;
    }

    // O(1)
    public int top() {
        return (int) dll.getLastNode().getElement();
    }

    // get max in TreeMap is O(logn) since its a Balanced Binary Search Tree
    public int peekMax() {
        return map.lastKey();
    }

    // Dll - O(1)
    public int popMax() {

        int maxElement = map.lastKey();
        int listSize = map.get(maxElement).size();

        DoublyNode popNode;
        // get Node
        if(listSize==1){
            popNode = map.get(maxElement).get(0);
            map.remove(popNode.getElement());
        }else{
            popNode= map.get(maxElement).get(listSize-1);
            map.get(popNode.getElement()).remove(listSize - 1);
        }

        // Pop node from dll
        dll.detachNode(popNode);
        return maxElement;
    }

    public static void main(String[] args) {
        MaxStack stk = new MaxStack();
        stk.push(4);
        stk.push(7);
        stk.push(10);
        System.out.println(stk.top());
        stk.push(2);
        stk.push(10);
        stk.push(6);
        System.out.println(stk.pop());
        System.out.println(stk.popMax());  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        System.out.println(stk.top());     // return 1, [5, 1] the stack did not change.
        System.out.println(stk.peekMax()); // return 5, [5, 1] the stack did not change.
        System.out.println(stk.pop());     // return 1, [5] the top of the stack and the max element is now 5.
        System.out.println(stk.top());     // return 5, [5] the stack did not change.
    }

}


