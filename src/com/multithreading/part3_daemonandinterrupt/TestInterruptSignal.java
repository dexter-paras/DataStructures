/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part3_daemonandinterrupt;

import java.io.IOException;

/**
 * @author paras.chawla
 * @version $Id: TestInterruptSignal.java, v 0.1 2020-08-22 23:07 paras.chawla Exp $$
 *
 * When do you want to interrupt threads?
 *  - memory and kernel resources
 *  - cpu cycles
 *  Currently in thread A
 *  ..
 *  ..
 *  ..
 *  threadB.interrupt() // sends a signal to threadB from threadA
 */
public class TestInterruptSignal {

    public static void main(String[] args) {
        Thread thread = new Thread(new WaitingForUserInput());
        thread.setName("InputWaitingThread");
        // Only way of progrmatically kill thread is by setting as Daemon thread
        //thread.setDaemon(true);
        thread.start();
        // won't help as System.in.read() doesn't respond to interrupt
        thread.interrupt();
    }

    private static class WaitingForUserInput implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    // System.in.read() does not respond to Thread.interrupt();
                    // Only way to stop thread is to set thread as Daemon
                    char input = (char) System.in.read();
                    if (input == 'q') {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("An exception was caught " + e);
            }
        }
    }

}