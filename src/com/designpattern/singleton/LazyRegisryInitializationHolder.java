/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.singleton;

/**
 * @author paras.chawla
 * @version $Id: EagerRegisry.java, v 0.1 2020-10-02 00:01 paras.chawla Exp $$
 */

// Approach 3 -Creating singleton instance of class as per need without using Synchronized or DC or taking care of multiple thread access
public class LazyRegisryInitializationHolder {

    // creating single singleton instance reference, no instantiation during start
    private static LazyRegisryInitializationHolder INSTANCE;

    // making constructor private makes it unavailable to create instance of class outside and inheritance as well
    private LazyRegisryInitializationHolder() {
        System.out.println("Inside LazyRegisryInitializationHolder Constructor");
    }

    // creating inner static class which is instantiating the instance
    // No way of calling RegistryHolder from outside as we've kept it private
    private static class RegistryHolder {
        static LazyRegisryInitializationHolder INSTANCE = new LazyRegisryInitializationHolder();
    }

    // static method called by client to get singleton instance as per requirement
    public static LazyRegisryInitializationHolder getInstance() {
        return RegistryHolder.INSTANCE;
    }

}