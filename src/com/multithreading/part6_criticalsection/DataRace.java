/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

/**
 * @author paras.chawla
 * @version $Id: DataRace.java, v 0.1 2020-08-29 08:45 paras.chawla Exp $$
 */
public class DataRace {

    public static void main(String[] args) {
        SharedClass sharedObj = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedObj.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedObj.checkForDataRace();
            }
        });
        thread1.start();
        thread2.start();
    }

}

class SharedClass {

    // Making variables as volatile, reduce overhead of locking and concurrent behavior of threads
    volatile int x = 0, y = 0;

    /* Since both operations are independent of each other , hence OS has the power to
     reshuffle these 2 operations and if treated with multithreading, it could be possible
     that y > x */
    public void increment() {
        // code comes before volatile variable will be executed before access instruction
        x++;
        y++;
        // code comes after volatile variable will be executed before access instruction
    }

    public void checkForDataRace() {
        if (y > x) {
            System.out.println("y > x - Data Race is detected");
        }
    }
}