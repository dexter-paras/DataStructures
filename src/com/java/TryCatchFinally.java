/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.java;

/**
 * @author paras.chawla
 * @version $Id: TryCatchFinally.java, v 0.1 2020-10-29 13:14 paras.chawla Exp $$
 */
public class TryCatchFinally {

    public int test(){
        try{
            return 0;
        }catch(Exception e){}
        finally {
            System.out.println("Finally");
        }
        return -1;
    }

    public static void main(String[] args) {
        TryCatchFinally obj = new TryCatchFinally();
        System.out.println(obj.test());
    }

}