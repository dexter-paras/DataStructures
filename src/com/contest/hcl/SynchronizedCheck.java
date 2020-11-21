/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.hcl;

/**
 * @author paras.chawla
 * @version $Id: SynchronizedCheck.java, v 0.1 2020-08-29 18:50 paras.chawla Exp $$
 */
public class SynchronizedCheck {

    public static void main(String[] args) {
        SharedClass sharedObj = new SharedClass();

        Thread thread1 = new Thread(() -> {
            sharedObj.method1();
        });

        Thread thread2 = new Thread(() -> {
            sharedObj.method2();
        });

        thread1.start();
        thread2.start();
    }

    private static class SharedClass {
        Object lock1 = new Object();
        Object lock2 = new Object();

        void method1() {
            synchronized (lock1) {
                System.out.println("Lock acquired by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        }

        void method2() {
            synchronized (lock2) {
                System.out.println("Lock acquired by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}


