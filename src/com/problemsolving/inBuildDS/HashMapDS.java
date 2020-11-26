/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: HashMapDS.java, v 0.1 2020-02-13 18:28 paras.chawla Exp $$ List or Array of Entry<K,V> An Entry is a linked List which
 * include key, value and next Entry
 */
public class HashMapDS<K, V> implements MapDS<K, V> {

    private List<Entry<K, V>> bucketList;
    private int               capacity;
    private int               size;

    // Initializing HashMap with bucketList and capacity
    public HashMapDS() {
        this.bucketList = new ArrayList<>();
        this.capacity = 10000;
        for (int i = 0; i < capacity; i++) {
            bucketList.add(null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V put(K key, V value) {

        //1. get Index of bucketList where Entry<K,V> needs to be stored
        int index = getIndex(key);

        //2. check if any entry already exist
        Entry<K, V> head = bucketList.get(index);

        //3. Get ReferenceIf Key Exist, it exist only when head isn't null
        //3.1 if head is null, bucketEntryFound=null
        Entry<K, V> bucketEntryFound = getReferenceIfKeyExists(head, key);

        // means it can be first element in new bucket or last entry of existing bucket
        if (bucketEntryFound == null) {
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.next = head;
            bucketList.set(index, newEntry);
            size++;
        } else {
            bucketEntryFound.value = value;
        }
        return value;
    }

    private Entry<K, V> getReferenceIfKeyExists(Entry<K, V> head, K key) {
        Entry<K, V> curr = head;

        // loop runs only in case of collision else it'll always return null
        while (curr != null) {
            if (curr.key == key) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        //1. get Index based on key
        int index = getIndex(key);

        // get LinkedList of entries corresponding to particular index
        Entry<K, V> head = bucketList.get(index);

        Entry<K, V> curr = head;
        while (curr != null) {
            if (curr.key == key) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        // get index
        int index = getIndex(key);

        // get LinkedList of entries corresponding to particular index
        Entry<K, V> head = bucketList.get(index);

        Entry<K, V> curr = head;
        Entry<K, V> prev = head;
        int count = 1;
        while (curr != null) {
            if (curr.key == key) {
                if (count == 1 && curr.next == null) {
                    bucketList.set(index, null);
                } else if (count == 1 && curr.next != null) {
                    bucketList.set(index, curr.next);
                } else {
                    prev.next = curr.next;
                    bucketList.set(index,head);
                }
                size--;
                return curr.value;
            }
            count++;
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        // 1. get index
        int idx = getIndex(key);

        // 2. get element from bucketList
        Entry<K, V> head = bucketList.get(idx);

        // 3. Traverse full chain of that particular idx
        Entry<K, V> bucketEntryFound = getReferenceIfKeyExists(head, key);
        if (bucketEntryFound == null) {
            return false;
        }
        return true;
    }

    // get bucketList index logic using hashCode on key
    int getIndex(K key) {
        int hashCode = 5;
        return Math.abs(hashCode) % capacity;
    }

    public static void main(String[] args) {
        HashMapDS hashMap = new HashMapDS<>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));            // returns 1
        System.out.println(hashMap.get(3));            // returns -1 (not found)
        hashMap.put(2, 1);                             // update the existing value
        System.out.println(hashMap.get(2));            // returns 1
        System.out.println(hashMap.remove(2));    // remove the mapping for 2
        System.out.println(hashMap.get(2));            // returns -1 (not found)

    }
}

class Entry<K, V> {
    K key;
    V value;

    Entry next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

interface MapDS<K, V> {

    int size();

    boolean isEmpty();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);
}