/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

/**
 * @author paras.chawla
 * @version $Id: DataRace.java, v 0.1 2020-08-29 08:45 paras.chawla Exp $$
 */
public class DataRaceTest {

    public static void main(String[] args) {
        SharedClass1 sharedObj = new SharedClass1();

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

class SharedClass1 {

    int x = 0, y = 0;

    public void increment() {
        x++;
        y++;
    }

    public void checkForDataRace() {
        if (y > x) {
            System.out.println("y > x - Data Race is detected");
        }
    }
}