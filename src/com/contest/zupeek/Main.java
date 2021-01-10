/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.zupeek;

/**
 * @author paras.chawla
 * @version $Id: FireBallRunner.java, v 0.1 2020-09-06 21:04 paras.chawla Exp $$
 */
class Parent
{
    int a=100;
    public void display()
    {
        System.out.println("Display in parent A: "+a);
    }
}

class Child extends Parent
{
    int b=200;

    public void display()
    {
        System.out.println("Display in parent B: "+b);
    }
}

public class Main
{
    public static void main(String args[])
    {
        Parent obj = new Child();
        obj.display();
    }
}