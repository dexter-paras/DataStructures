/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part8_waitNotify.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author paras.chawla
 * @version $Id: MyOwnBlockingQueueWaitNotify.java, v 0.1 2020-11-27 02:39 paras.chawla Exp $$
 * Use this and and not MyOwnBlockingQueue.class
 * Awesome read
 * https://stackoverflow.com/questions/2056243/java-synchronized-block-for-class
 * https://stackoverflow.com/questions/14495776/synchronizethis-vs-synchronizemyclass-class
 *
 * this - is a reference to this particular instance of the class, and
 * MyOwnBlockingQueue.class - is a reference to the MyOwnBlockingQueue description object.
 *
 * The first example (acquiring lock on this) is meant to be used in instance methods,
 * the second one (acquiring lock on class object) -- in static methods.
 */
public class MyOwnBlockingQueueWaitNotify<E> implements Queuee<E> {

    private Queue<E> queue;
    private int      capacity;
    private int      DEFAULT_SIZE = 16;

    // Blocking Queue of Default Size
    public MyOwnBlockingQueueWaitNotify() {
        this.queue = new LinkedList<>();
        this.capacity = DEFAULT_SIZE;
    }

    // Blocking Queue of size = capacity
    public MyOwnBlockingQueueWaitNotify(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    // To maintain thread-safe , need to use synchronized
    // when queue.size==full, and multiple threads are trying to put, we need to block producer thread
    // In Order to block producer thread, call wait()
    @Override
    public void put(E e) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == this.capacity) {
                //producer thread releases the lock and go to sleep. Same will be awake up by consumer thread when new item is being consumed by consumer
                this.wait();
            }
            queue.add(e);
            // keep notify all waiting consumer threads as soon as new item being added by producer
            this.notifyAll();
        }
    }

    @Override
    public E take() throws InterruptedException {
        synchronized (this) {
            // consumer thread releases the lock when queue is empty and go to sleep. Same will be awake by producer thread when new item is being item
            while (queue.isEmpty()) {
                this.wait();
            }
            E e = queue.remove();
            // keep notify all waiting producer threads as soon as new item being consumed by consumer
            this.notifyAll();
            return e;
        }
    }
}