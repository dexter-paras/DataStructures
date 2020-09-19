/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: CopyListRandomPointer.java, v 0.1 2020-05-21 23:41 paras.chawla Exp $$
 */
public class CopyListRandomPointer {

    public static void main(String[] args) {
        Node node0 = new Node(5);
        Node node1 = new Node(10);
        Node node2 = new Node(15);

        // set next pointer
        node0.setNext(node1);
        node1.setNext(node2);

        // set random pointer
        node0.setRandom(node2);
        node1.setRandom(node0);
        node2.setRandom(node1);

        // To find deep copy
        Node deepCopy = new CopyListRandomPointer().copyRandomList(node0);
        System.out.println(deepCopy);
    }

    private Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        // headNode is an oldNode and we need to clone all nodes in newNode
        Node oldNode = head;

        // create Visited Dictionary of oldNodes and newCloneNodes
        Map<Node, Node> visitedMap = new HashMap<>();

        // create clone of first node blindly
        Node cloneNode = new Node(oldNode.data);

        // add oldNode and cloneNode in visitedMap
        visitedMap.put(oldNode, cloneNode);

        while (oldNode != null) { //A

            cloneNode.setNext(getClonedNode(oldNode.next, visitedMap)); //B ->B'
            cloneNode.setRandom(getClonedNode(oldNode.random, visitedMap));//C -> C'

            oldNode = oldNode.next;
            cloneNode = cloneNode.next;
        }
        return visitedMap.get(head);
    }

    private Node getClonedNode(Node originalNode, Map<Node, Node> visitedMap) {
        if (originalNode != null) {
            if (visitedMap.containsKey(originalNode)) {
                // If its in the visited dictionary then return the new originalNode reference from the dictionary
                return visitedMap.get(originalNode);
            } else {
                // Otherwise create a new originalNode, add to the dictionary and return it
                Node cloneNode = new Node(originalNode.data);//B'
                visitedMap.put(originalNode, cloneNode);// B -> B'
                return cloneNode;
            }
        }
        return null;
    }
}

class Node {
    int  data;
    Node next;
    Node random;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.random = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setRandom(Node random) {
        this.random = random;
    }
}