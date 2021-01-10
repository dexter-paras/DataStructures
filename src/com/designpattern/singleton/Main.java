/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.singleton;

/**
 * @author paras.chawla
 * @version $Id: FireBallRunner.java, v 0.1 2020-10-02 00:04 paras.chawla Exp $$
 */
public class Main {

    public static void main(String[] args) {
        // Approach 1
        EagerRegisry earlyInstance1 = EagerRegisry.getInstance();
        EagerRegisry earlyInstance2 = EagerRegisry.getInstance();

        // Approach 2
        LazyRegisryWithDCL lazyInstance1 = LazyRegisryWithDCL.getInstance();
        LazyRegisryWithDCL lazyInstance2 = LazyRegisryWithDCL.getInstance();

        // Approach 3 - Preffered way out of all
        LazyRegisryInitializationHolder lazyInstance3 = LazyRegisryInitializationHolder.getInstance();
        LazyRegisryInitializationHolder lazyInstance4 = LazyRegisryInitializationHolder.getInstance();

        // Approach 4
        RegistryEnum instance = RegistryEnum.INSTANCE;

        // equal operator means both references pointing to same object
        System.out.println(earlyInstance1 == earlyInstance2);
        System.out.println(lazyInstance1 == lazyInstance2);
        System.out.println(lazyInstance3 == lazyInstance4);

    }
}