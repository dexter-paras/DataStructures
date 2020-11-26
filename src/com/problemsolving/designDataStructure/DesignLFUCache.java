/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: DesignLFUCache.java, v 0.1 2020-10-30 21:17 paras.chawla Exp $$
 * https://www.youtube.com/watch?v=su3E22YwLB4
 */
class DesignLFUCache {
    int                 min      = 0;
    Map<Integer, Node>  nodeMap  = new HashMap<>();
    Map<Integer, Dlink> dlinkMap = new HashMap<>();
    int                 capacity = 0;

    public DesignLFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) { return -1; }

        Dlink dlink = dlinkMap.get(node.count);
        dlink.removeNode(node);
        if (dlink.len == 0) {
            dlinkMap.remove(node.count);
            if (node.count == min) { min++; }
        }
        node.count++;
        dlink = dlinkMap.getOrDefault(node.count, new Dlink());
        dlink.addNode(node);
        dlinkMap.putIfAbsent(node.count, dlink);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) { return; }
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            get(key);
            return;
        }

        if (nodeMap.size() == capacity) {
            Dlink dlink = dlinkMap.get(min);
            Node node = dlink.removeLeast();
            nodeMap.remove(node.key);
            if (dlink.len == 0) { dlinkMap.remove(min); }
        }

        Node newNode = new Node(value, 1, key);
        min = 1;
        nodeMap.put(key, newNode);
        Dlink dlink = dlinkMap.getOrDefault(1, new Dlink());
        dlink.addNode(newNode);
        dlinkMap.putIfAbsent(1, dlink);
    }
}

class Node {
    public int  value;
    public int  count;
    public int  key;
    public Node prev;
    public Node next;

    public Node(int value, int count, int key) {
        this.value = value;
        this.count = count;
        this.key = key;
    }
}

class Dlink {
    public Node head = new Node(0, 0, 0);
    public Node tail = new Node(0, 0, 0);
    public int  len  = 0;

    public Dlink() {
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void addNode(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        len++;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        len--;
    }

    public Node removeLeast() {
        Node node = head.next;
        removeNode(node);
        return node;
    }
}
