/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

/**
 * @author paras.chawla
 * @version $Id: SingletonTest.java, v 0.1 2020-09-04 10:14 paras.chawla Exp $$
 */
/*
 * https://javabypatel.blogspot.com/2020/08/when-to-use-volatile-vs-synchronized-in-java.html
 * In the example below since the outer line "if (instance == null)" is not inside synchronized block,
 * what will happen is, say if Thread A gets a chance and is executing the line "instance = new SingletonTest();"
 * and after that it gets preempted. Now, what will Thread 2 see which reached the line "if (instance == null)
 * {" outside the synchronized block, whether the instance would be null or non-null. possibly it may see null
 * if the local cache of the thread 1 is not flushed. so to make sure Thread 2 see the update value of the instance
 * variable instance is marked as volatile here.
 * */

public class SingletonTest {

    volatile static SingletonTest instance = null;

    public static SingletonTest getInstance() {
        if (instance == null) {
            synchronized (SingletonTest.class) {
                if (instance == null) {
                    instance = new SingletonTest();
                    System.out.println("instance created");
                }
            }
        }
        return instance;
    }
}