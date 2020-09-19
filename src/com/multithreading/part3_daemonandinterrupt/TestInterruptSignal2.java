/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part3_daemonandinterrupt;

/**
 * @author paras.chawla
 * @version $Id: TestInterruptSignal.java, v 0.1 2020-08-22 23:07 paras.chawla Exp $$
 * <p>
 * As a rule of thumb, never leave a catch block empty, and use the InterruptedException catch block to gracefully stop the current thread
 * (by adding some print or cleaning code before returning from the run method)
 */
public class TestInterruptSignal2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new SleepingThread());
        thread.setName("InputWaitingThread");
        thread.start();
        // Thread.sleep() respond to the signal given by interrupt() and Thread throws InterruptedException
        // we need to make sure to catch exception and return from the program explicitly
        thread.interrupt();
    }

    private static class SleepingThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(100000);
                }
            } catch (InterruptedException e) {
                //As a rule of thumb, never leave a catch block empty, and use the InterruptedException catch block
                //to gracefully stop the current thread (by adding some print or cleaning code before returning from the run method
                System.out.println("Sleep() responding to interrupt called on thread, gracefully existing");
                return;
            }
        }
    }

}