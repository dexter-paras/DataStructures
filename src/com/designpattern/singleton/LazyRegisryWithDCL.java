/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.singleton;

/**
 * @author paras.chawla
 * @version $Id: EagerRegisry.java, v 0.1 2020-10-02 00:01 paras.chawla Exp $$
 */

// Approach 2 -Creating singleton instance of class as per need
public class LazyRegisryWithDCL {

    // creating single singleton instance reference, no instantiation during start
    // The Java volatile keyword guarantees visibility of changes to variables across threads.
    // Instead all read/write to volatile variable should happen directly from main memory.
    private static volatile LazyRegisryWithDCL INSTANCE;

    // making constructor private makes it unavailable to create instance of class outside and inheritance as well
    private LazyRegisryWithDCL() {
        
    }

    // static method called by client to get singleton instance as per requirement
    public static LazyRegisryWithDCL getInstance() {

        // check if singleton instance already exist
        if (INSTANCE == null) {
            // if multiple threads t1 & t2 both went inside finding instance as null
            // creation logic of instance must be protected so that multiple threads not instantiate
            synchronized (LazyRegisryWithDCL.class) {
                // added double check to prevent multiple instantiation by waiting thread after entrying first check
                if (INSTANCE == null) {
                    INSTANCE = new LazyRegisryWithDCL();
                }
            }
        }
        return INSTANCE;
    }

}