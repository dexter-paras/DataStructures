/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.multithreading.part9_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author paras.chawla
 * @version $Id: FixedThreadPool.java, v 0.1 2021-03-03 8:49 PM paras.chawla Exp $$

 * Fixed number of threads and task can be too much
 */
public class FixedThreadPool {

    public static void main(String[] args) {

        // create fixed Thread Pool
        ExecutorService service = Executors.newFixedThreadPool(10);

        // submit 100 tasks using these 10 Thread for executor
        for (int i = 0; i < 100; i++) {
            service.execute(() -> System.out.println("Thread Name: " + Thread.currentThread().getName()));
        }
    }
}