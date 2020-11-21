/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.singleton;

/**
 * @author paras.chawla
 * @version $Id: EagerRegisry.java, v 0.1 2020-10-02 00:01 paras.chawla Exp $$
 */

// Approach 1 -Creating singleton instance of class during runtime
public class EagerRegisry {

    

    // creating single singleton instance during start
    private static final EagerRegisry INSTANCE = new EagerRegisry();

    // making constructor private makes it unavailable to create instance of class outside and inheritance as well
    private EagerRegisry() {
        System.out.println("Default constructor of EagerRegisry called");
    }

    // static method which gives other classes access of singleton instance
    public static EagerRegisry getInstance() {
        return INSTANCE;
    }

}