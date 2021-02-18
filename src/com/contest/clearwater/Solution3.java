/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.clearwater;

/**
 * @author paras.chawla
 * @version $Id: Solution1.java, v 0.1 2021-01-27 7:35 PM paras.chawla Exp $$
 */
public class Solution3 {

    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        //b=c;
        newPrint(b);
    }

    public static void newPrint(A a){
        a.printName();
    }
}

class A{
    public void printName(){
        System.out.println("Value-A");
    }
}

class B extends A{
    public void printName(){
        System.out.println("Value-B");
    }
}

class C extends A{
    public void printName(){
        System.out.println("Value-C");
    }
}