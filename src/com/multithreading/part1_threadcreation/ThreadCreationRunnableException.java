/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part1_threadcreation;

/**
 * @author paras.chawla
 * @version $Id: ThreadCreationRunnable.java, v 0.1 2020-08-19 09:41 paras.chawla Exp $$ Threads Creation - Part 1.2, Catching exception if
 * thread running has done something wrong
 */
public class ThreadCreationRunnableException {

    public static void main(String[] args) {

        // Create Runnable object and throw intentional exception
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Intentional Exception");
        });

        // Set Thread Name
        thread.setName("Misbehaving thread");


        /**
         * Catch exception thrown in thread
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         * @param t the thread
         * @param e the exception
         */
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println("A critical error happened in thread " + t.getName()
                + " the error is " + e.getMessage()));
        thread.start();
    }
}