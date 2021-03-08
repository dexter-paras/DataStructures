/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.multithreading.part9_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author paras.chawla
 * @version $Id: CachedThreadPool.java, v 0.1 2021-03-03 9:36 PM paras.chawla Exp $$
 */

/* No Fixed number of threads
 No Queue which hold tasks
 Synchronous Queue for single task
 If no thread is available, it'll create new Thread
 Can kill threads if thread is no longer required
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            service.execute(() -> System.out.println("Thread Name: " + Thread.currentThread().getName()));
        }
    }

}