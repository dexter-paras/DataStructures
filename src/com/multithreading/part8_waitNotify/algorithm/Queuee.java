/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part8_waitNotify.algorithm;

/**
 * @author paras.chawla
 * @version $Id: Queuee.java, v 0.1 2020-11-27 02:40 paras.chawla Exp $$
 */
public interface Queuee<E> {

    void put(E e) throws InterruptedException;

    E take()throws InterruptedException;
}