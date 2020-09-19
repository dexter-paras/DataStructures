/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: TestThreads.java, v 0.1 2020-09-16 22:22 paras.chawla Exp $$
 */
public class TestThreads {


    public static void main(String[] args) {

        Thread t = new MyThread() {
            public void run() {
                System.out.println(" foo");
            }
        };
        t.start();
    }

}

class MyThread extends Thread {
    MyThread() {
        System.out.println(" MyThread");
    }

    public void run() {
        System.out.println("bar");
    }

    public void run(String s) {
        System.out.println(" baz");
    }
}