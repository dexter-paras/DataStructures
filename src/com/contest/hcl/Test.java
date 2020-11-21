/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.hcl;

/**
 * @author paras.chawla
 * @version $Id: Test.java, v 0.1 2020-08-29 14:00 paras.chawla Exp $$
 */
public class Test {

    StringBuilder contents = new StringBuilder();

    public void log(String message) {
        contents.append(System.currentTimeMillis());
        contents.append(": ");
        contents.append(Thread.currentThread().getName());
        contents.append(message);
        contents.append("/n");
    }

    public String getContents() {
        return contents.toString();
    }

    public static void main(String[] args) {
        Test obj = new Test();
        Thread t0 = new Thread(() -> {
            while (true) {
                obj.log("Message By Thread0");
            }
        });
        Thread t1 = new Thread(() -> {
            while (true) {
                obj.log("Message By Thread1");
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                obj.log("Message By Thread2");
            }
        });

        Thread t00 = new Thread(() -> {
            while (true) {
                System.out.println(obj.getContents());
            }
        });
        Thread t11 = new Thread(() -> {
            while (true) {
                System.out.println(obj.getContents());
            }
        });
        Thread t22 = new Thread(() -> {
            while (true) {
                System.out.println(obj.getContents());
            }
        });

        t0.start();
        t1.start();
        t2.start();
        t00.start();
        t11.start();
        t22.start();
    }
}
