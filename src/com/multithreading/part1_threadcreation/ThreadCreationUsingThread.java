/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part1_threadcreation;

/**
 * @author paras.chawla
 * @version $Id: ThreadCreationUsingThread.java, v 0.1 2020-08-21 00:23 paras.chawla Exp $$
 *
 *  * Second way to create Thread is to
 *  * declare a class to be a subclass of <code>Thread</code>. This
 *  * subclass should override the <code>run</code> method of class
 *  * <code>Thread</code>. An instance of the subclass can then be
 *  * allocated and started.
 */
public class ThreadCreationUsingThread {

    public static void main(String[] args) {
        Thread thread = new TaskThread();
        thread.start();
    }

    // Creating thread in a seperate class
    private static class TaskThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello from" + this.getName());
        }
    }

}

