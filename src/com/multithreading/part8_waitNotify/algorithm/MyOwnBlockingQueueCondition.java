/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part8_waitNotify.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author paras.chawla
 * @version $Id: MyOwnBlockingQueueWaitNotify.java, v 0.1 2020-11-27 02:39 paras.chawla Exp $$
 * Using Java Condition
 */
public class MyOwnBlockingQueueCondition<E> implements Queuee<E> {

    private Queue<E> queue;
    private int      capacity;
    private int      DEFAULT_SIZE = 16;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    // Blocking Queue of Default Size
    public MyOwnBlockingQueueCondition() {
        this.queue = new LinkedList<>();
        this.capacity = DEFAULT_SIZE;
    }

    // Blocking Queue of size = capacity
    public MyOwnBlockingQueueCondition(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    // To maintain thread-safe , need to use synchronized
    // when queue.size==full, and multiple threads are trying to put, we need to block producer thread
    // await for notFull condition to be fulfilled
    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        try{
            while (queue.size() == this.capacity) {
                // await till this condition gets fulfilled, this condition doesn't fulfil if q size is Max, hence producer thread release lock and go to wait state
                notFull.await();
            }
            queue.add(e);
            // As soon as any new element is being added by producer, signal to all consumer threads waiting for notEmpty condition to get fulfil
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        try{
            // when queue is empty, we want consumer thread to wait and hence looking forward to notEmpty condition to get fulfil
            while (queue.isEmpty()) {
                // this condition doesn't fulfil since queue is Empty..hence consumer thread release lock and go to wait state
                notEmpty.await();
            }
            E e = queue.remove();
            // As soon as new element is being removed by consumer, signal to all producer threads awaiting on notFull condition to get fulfil
            notFull.signalAll();
            return e;
        }finally {
            lock.unlock();
        }
    }
}