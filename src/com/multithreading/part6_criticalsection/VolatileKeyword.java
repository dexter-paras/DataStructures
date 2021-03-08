/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

/**
 * @author paras.chawla
 * @version $Id: VolatileKeyword.java, v 0.1 2020-08-29 08:43 paras.chawla Exp $$
 */

/* 1. Volatile makes assignments to long atomic,
   however incrementing a volatile variable still involves multiple operations
   2. Making variables as volatile, reduce overhead of locking and concurrent behavior of threads
   3. Solves all data races by guaranteeing order
   4. Solves Race condition for read/write from/to long and double

1. Volatile keyword is used when you don't want local CPU core to cache the variable from main memory,
   instead all read/write to volatile variable should happen directly from main memory.
2. The Java volatile keyword guarantees visibility of changes to variables across threads.

##Scenario 1.
If multiple threads are trying to read the value of a variable and based on the current value it takes the
decision of incrementing or decrementing a variable value, is marking a variable volatile in this situation enough?

No, Volatile only guarantees ^visibility^, check and act is like we want two operations to be atomic in that case we
either need to use synchronized method/block or Atomic variables (AtomicInteger for example).

##Scenario 2.
There are multiple threads reading the value of shared variable but only one thread is writing to a shared variable,
is marking a variable volatile in this situation enough?

Yes. as is there no situation where we need more than one operation to be atomic here and also only one thread is writing
to a variable others are simply reading the shared variable, so volatile variable should work.

*/
public class VolatileKeyword {

    public static void main(String[] args) throws InterruptedException {
        VolatileThread volatileThread = new VolatileThread();
        volatileThread.start();

        // sleeping main thread to execute next statement
        Thread.sleep(2000);

        volatileThread.keepRunningFlag = false;
    }

}

class VolatileThread extends Thread {

    // keepRunningFlag as true in main Memory and all threads are reading from main memory
     volatile boolean keepRunningFlag = true;

    @Override
    public void run() {
        System.out.println("Volatile thread running");
        long counter = 0;
        while (keepRunningFlag) {
            counter++;
            System.out.println(counter);
        }
        System.out.println("Volatile thread terminated since FireBallRunner thread changes keepRunningFlag as true" + counter);
    }
}