/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author paras.chawla
 * @version $Id: CheckedException.java, v 0.1 2021-01-05 1:24 PM paras.chawla Exp $$
 */
public class CheckedException {

    public static void main(String[] args) throws Exception{

        // Checked Exception, Compiler showing exception before running the program
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // Unchecked Exception , Compiler will still compile below snippet and give exception during run time only
        int a =90/0;
        System.out.println(a);
    }

}