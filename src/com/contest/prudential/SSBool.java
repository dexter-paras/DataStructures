/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

/**
 * @author paras.chawla
 * @version $Id: SSBool.java, v 0.1 2020-09-16 22:31 paras.chawla Exp $$
 */
public class SSBool {

    public static void main(String[] args) {
        boolean b1=true;
        boolean b2=false;
        boolean b3=true;

        if (b1 & b2 | b2 & b3 | b2) { System.out.println("ok"); }
        if (b1 & b2 | b2 & b3 | b2 | b1) { System.out.println("dokey"); }
    }
}