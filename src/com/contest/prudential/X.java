/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

/**
 * @author paras.chawla
 * @version $Id: X.java, v 0.1 2020-09-16 22:28 paras.chawla Exp $$
 */
public class X {

    public static void main(String[] args) {
        try {
            badMethod();
            System.out.println("A");
        } catch (Exception e) {
            System.out.println("B");
        } finally {
            System.out.println("C");
        }
        System.out.println("D");
    }

    public static void badMethod() {}
}