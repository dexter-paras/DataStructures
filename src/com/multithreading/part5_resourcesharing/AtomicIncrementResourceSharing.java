/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part5_resourcesharing;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author paras.chawla
 * @version $Id: NonAtomicIncrementResourceSharing.java, v 0.1 2020-08-27 21:51 paras.chawla Exp $$
 */

/* Here we're sharing an object btw 2 threads
   IncrementingCountingThread incrementing value of the object
   DecrementingCountingThread decrementing value of the object simultaneously
*/

public class AtomicIncrementResourceSharing {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        long startTime = System.currentTimeMillis();

        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        // Since both threads working on AtomicInteger which is thread safe, hence result items comes to 0
        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken using Atomic Integer" + (endTime - startTime));//9,13
        System.out.println("Final value " + inventoryCounter.getItems());

    }

    // 1. An object InventoryCounter with items attribute shared between multiple threads
    // 1.1 items++ and items --
    private static class InventoryCounter {
        AtomicInteger items = new AtomicInteger(0);

        public void increment() {items.incrementAndGet();}

        public void decrement() {items.decrementAndGet();}

        public int getItems() {
            return items.get();
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
            for (int i = 0; i < 100000; i++) {
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
            for (int i = 0; i < 100000; i++) {
                inventoryCounter.decrement();
            }
        }
    }
}