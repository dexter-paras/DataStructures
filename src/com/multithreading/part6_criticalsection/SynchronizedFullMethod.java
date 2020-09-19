/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

/**
 * @author paras.chawla
 * @version $Id: NonAtomicIncrementResourceSharing.java, v 0.1 2020-08-27 21:51 paras.chawla Exp $$
 */

/* Here we're sharing an object btw 2 threads
   IncrementingCountingThread incrementing value of the object
   DecrementingCountingThread decrementing value of the object simultaneously
*/

public class SynchronizedFullMethod {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("Final value " + inventoryCounter.getItems());

    }

    // 1. An object InventoryCounter with items attribute shared between multiple threads
    // 1.1 items++ and items --
    private static class InventoryCounter {
        int items = 0;

        // 1.2 Synchronized critical method, here if thread1 executing increment1 , no other thread can execute increment() and decrement()
        // 1.3 Synchronized is applied per object and in this case object is InventoryCounter <- Called Monitor
        // 1.4 By Synchronized, there gets lock on InventoryCounter object
        public synchronized void increment() {items++;}

        public synchronized void decrement() {items--;}

        public synchronized int getItems() {
            return items;
        }
    }

    // 2. Thread 1 having access of InventoryCounter object
    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    // 3. Thread 2 having access of InventoryCounter object
    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }
}