/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.gojek.cachelib.cache.policies;

/**
 * @author paras.chawla
 * @version $Id: LRUEvictionPolicy.java, v 0.1 2020-11-23 22:02 paras.chawla Exp $$
 */

import org.gojek.cachelib.inbuild.DoublyLinkedList;
import org.gojek.cachelib.inbuild.DoublyNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Eviction policy based on LRU principle
 * - Need to manager Key only
 * - Values are managed by Cache only
 * - Doesn't deal with Capacity, Cache class deals with capacity
 */
public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private Map<K, DoublyNode<K>> map;
    private DoublyLinkedList<K>   dll;

    public LRUEvictionPolicy() {
        this.map = new HashMap();
        this.dll = new DoublyLinkedList<K>();
    }

    public void keyAccessed(K key) {
        if (map.containsKey(key)) {
            DoublyNode node = map.get(key);
            this.dll.detachNode(node);
            this.dll.addNodeAtLast(node);
        } else {
            DoublyNode newNode = this.dll.addElementAtLast(key);
            this.map.put(key,newNode);
        }
    }

    public K evictKey() {
        DoublyNode<K> evictNode= this.dll.getFirstNode();
        if(evictNode==null){
            return null;
        }
        this.dll.detachNode(evictNode);
        this.map.remove(evictNode.getElement());
        return evictNode.getElement();
    }
}