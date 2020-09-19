/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.module.string;

/**
 * @author paras.chawla
 * @version $Id: ExceptionTest.java, v 0.1 2019-08-30 17:44 paras.chawla Exp $$
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try{
            int a=10/0;
            System.out.println("Next Line");
        }catch (ArithmeticException e){
            System.out.println("Catch Exception");
        }
    }
}