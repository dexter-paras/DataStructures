/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part5_resourcesharing;

/**
 * @author paras.chawla
 * @version $Id: NonAtomicIncrementResourceSharing.java, v 0.1 2020-08-27 21:51 paras.chawla Exp $$
 */

/* Here we're sharing an object btw 2 threads
   IncrementingCountingThread incrementing value of the object
   DecrementingCountingThread decrementing value of the object simultaneously
*/

public class NonAtomicIncrementResourceSharing {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        /* Unexpected count as both threads are working on non-atomic operation simultaneously
        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();
        */
        System.out.println("Final value " + inventoryCounter.getItems());

    }

    // 1. An object InventoryCounter with items attribute shared between multiple threads
    // 1.1 items++ and items --
    private static class InventoryCounter {
        // Volatile makes assignments to long atomic,
        // however incrementing a volatile variable still involves multiple operations
        private int items = 0;

        public void increment() {items++;}

        public void decrement() {items--;}

        public int getItems() {
            return items;
        }
    }

    // 2. Thread  1 having access of InventoryCounter object
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