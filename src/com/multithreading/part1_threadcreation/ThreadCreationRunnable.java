/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part1_threadcreation;

/**
 * @author paras.chawla
 * @version $Id: ThreadCreationRunnable.java, v 0.1 2020-08-19 09:41 paras.chawla Exp $$ Threads Creation -
 * Part 1.1, Using Runnable
 * Interface Approach 1 of Creating Thread
 * Declare a class that * implements the <code>Runnable</code> interface.
 * That class then implements the <code>run</code> method. An instance of the class can * then be allocated, passed as an argument when creating *
 * <code>Thread</code>, and started.
 */
public class ThreadCreationRunnable {

    public static void main(String[] args) {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         */

        // Create Runnable object and pass into Thread constructor
        // Anonymous class implementing Runnable interface implementing run() method and passed as an argument in Thread
        Thread thread = new Thread(() -> System.out.println("New Worker thread " + Thread.currentThread().getName() + " spawn"));

        // Set Thread Name
        thread.setName("Worker Thread 1");

        // set Thread Priority, given static priority to OS to take better scheduling decision
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");


    }
}