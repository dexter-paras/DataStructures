/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author paras.chawla
 * @version $Id: PriorityQueueDS.java, v 0.1 2019-09-23 12:39 paras.chawla Exp $$
 */

// 10,20,15,5
public class PriorityQueueDS<E> {

    /* The number of elements in the priority queue */
    private int size = 0;
    Object[] queue;
    static int DEFAULT_CAPACITY = 11;

    public PriorityQueueDS() {
        this(DEFAULT_CAPACITY);
    }

    public PriorityQueueDS(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.queue = new Object[initialCapacity];
    }

    public static void main(String[] args) {
        PriorityQueueDS priorityQueue = new PriorityQueueDS();
        addElements(priorityQueue);
        System.out.println((int) priorityQueue.remove());
        System.out.println((int) priorityQueue.remove());
        System.out.println((int) priorityQueue.remove());
        System.out.println((int) priorityQueue.remove());
        System.out.println((int) priorityQueue.remove());
    }

    private E remove() {
        E x = poll();
        if (x != null) { return x; } else { throw new NoSuchElementException(); }

    }

    private E poll() {
        if (size == 0) { return null; }
        int lastIndex = --size;
        E result = (E) queue[0];
        E lastElement = (E) queue[lastIndex];
        swap(0, size);
        queue[lastIndex] = null;
        heapifyDown(0, lastElement);
        return result;
    }

    // pI =0 , sCi =1
    private void heapifyDown(int parentIndex, E lastElement) {
        int half = size >>> 1;
        while (parentIndex < half) {
            int smallerChildIndex = smallestValueByIndex(parentIndex, getLeftChildIndex(parentIndex), getRightChildIndex(parentIndex)); // 1
            if ((int) queue[parentIndex] < (int) queue[smallerChildIndex] || parentIndex == smallerChildIndex) { break; } else {
                swap(parentIndex, smallerChildIndex);
            }
            parentIndex = smallerChildIndex;
        }

    }

    public int smallestValueByIndex(int parentIndex,
                                    int leftChildIndex, int rightChildIndex) {
        if ((int) queue[leftChildIndex] < (int) queue[parentIndex]
                && hasRightChild(parentIndex) && (int) queue[leftChildIndex]
                < (int) queue[rightChildIndex]) {
            return leftChildIndex;
        } else if (hasRightChild(parentIndex)
                && (int) queue[rightChildIndex] < (int) queue[parentIndex]
                && hasLeftChild(parentIndex) && (int) queue[rightChildIndex]
                < (int) queue[leftChildIndex]) { return rightChildIndex; } else { return parentIndex; }
    }

    private static void addElements(PriorityQueueDS priorityQueue) {
        priorityQueue.add(5);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.add(1);
        priorityQueue.add(3);
    }

    public boolean add(E e) {
        ensureExtraCapacity();
        queue[size] = e;
        size++;
        heapifyUp(e);
        System.out.println(Arrays.toString(queue));
        return true;
    }

    /* add -> 5 [5]
     * add -> 7 [5]
     * add -> 2 [5]
     * add -> 1 [5]
     * add -> 3 [5]
         1
        / \
       2  5
      / \
     7  3
     */
    private void heapifyUp(E e) {
        int childIndex = size - 1;
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) >>> 1; //
            if (Integer.parseInt(e.toString()) > Integer.parseInt(queue[parentIndex].toString())) // 2!>5
            { break; }
            queue[childIndex] = queue[parentIndex];
            childIndex = parentIndex;
        }
        queue[childIndex] = e;
    }

    private void ensureExtraCapacity() {
        if (size == DEFAULT_CAPACITY) {
            int oldCapacity = queue.length;
            // Double size if small; else grow by 50%
            int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                    (oldCapacity + 2) :
                    (oldCapacity >> 1));
            queue = Arrays.copyOf(queue, newCapacity);
        }
    }

    int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    E leftChild(int index) {
        return (E) queue[getLeftChildIndex(index)];
    }

    E rightChild(int index) {
        return (E) queue[getRightChildIndex(index)];
    }

    E parent(int index) {
        return (E) queue[getParentIndex(index)];
    }

    boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = (int) queue[indexOne];
        queue[indexOne] = queue[indexTwo];
        queue[indexTwo] = temp;
    }

}