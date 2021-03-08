/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.multithreading.part9_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author paras.chawla
 * @version $Id: CachedThreadPool.java, v 0.1 2021-03-03 9:36 PM paras.chawla Exp $$
 */

/* No Fixed number of threads
  Store all tasks in Delay Queue
  task1 - running after 10 secs
  task2 - running after 20 secs
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        // task to run after 10 secs delay
        service.schedule(()-> System.out.println("Hey"), 1, TimeUnit.SECONDS);

        // task to run repeatedly every 2 secs
        service.scheduleAtFixedRate(()-> System.out.println("Hi"),2,2, TimeUnit.SECONDS);

        // task to run repeatedly 10 seconds after previous task completes
        service.scheduleWithFixedDelay(()-> System.out.println("Hello"),5,5, TimeUnit.SECONDS);
    }

}