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

Think of synchronized keyword when you want to make more than one instruction to be marked as single unit that is Atomic operation.

Example: i++ where the operation is break down as,
1. Reading the value of i first
2. Incrementing the value of i.

So that is two step operation and you want to make this two step as a single Atomic operation.

*/

public class SynchronizedCriticalSection {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        long startTime = System.currentTimeMillis();

        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken using Single lock " + (endTime - startTime));// > 29, More time
        System.out.println("Final value " + inventoryCounter.getItems());

    }

    // 1. An object InventoryCounter with items attribute shared between multiple threads
    // 1.1 items++ and items --
    private static class InventoryCounter {
        int items = 0;

        // Synchronized only critical section - Gives more flexiblity
        // 1. only critical section is not working concurrently, rest all code is working concurrently,i.e. sharing btw multiple threads
        // 2. we can have differnt locks on different critical section i.e. synchronized accepting different lock objects
        // 3. Lock must be on 1 object else if we create another lock2 and put lock2 in decrement,thread 2 will start working on items--
        // which we want to stop
        // 4. By Synchronized, there gets lock on lock1 object and 1 thread at a time can hold lock1,another thread has to wait
        Object lock1 = new Object();
        //Object lock2 = new Object();

        public void increment() {
            synchronized (lock1) {
                items++;
            }
        }

        public void decrement() {
            synchronized (lock1) {
                items--;
            }
        }

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