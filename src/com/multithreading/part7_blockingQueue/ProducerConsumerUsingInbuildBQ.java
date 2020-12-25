/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part7_blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author paras.chawla
 * @version $Id: ProducerConsumerUsingInbuildBQ.java, v 0.1 2020-11-26 23:40 paras.chawla Exp $$
 */
public class ProducerConsumerUsingInbuildBQ {

    public static void main(String[] args) throws InterruptedException {

        // Handles concurrent thread access from single thread-safe queue
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(1000);

        // producer task, producing messages
        Runnable producer = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //Thread.sleep(i);
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + "adding item: " + i);
                } catch (InterruptedException e) {

                }
            }
            try {
                queue.put(100);
                System.out.println(Thread.currentThread().getName() + "adding final item 100");
            } catch (InterruptedException e) {

            }
        };

        // Same Consumer task utilized by mutiple threads , consuming messages from single thread-safe queue
        Runnable consumer = () -> {
            try {
                int item;
                do {
                    item = queue.take();
                    System.out.println(Thread.currentThread().getName() + "taking item: " + item);
                } while (item != 100);
            } catch (InterruptedException e) {
            }
        };

        // Creating single producer and consumer and start running the program
        Thread producerThread = new Thread(producer);
        producerThread.setName("Producer P ");

        Thread consumerThread1 = new Thread(consumer);
        consumerThread1.setName("Consumer C1 ");

        //Thread consumerThread2 = new Thread(consumer);
        //consumerThread2.setName("Consumer C2 ");

        producerThread.start();

        // Lets have 1 producer and 2 consumer threads
        consumerThread1.start();
        //consumerThread2.start();

        producerThread.join();
        consumerThread1.join();
        //consumerThread2.join();

        System.out.println("All Messages Produced , Consumed and Finished");

    }

}