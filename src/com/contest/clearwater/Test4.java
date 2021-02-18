/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.clearwater;

/**
 * @author paras.chawla
 * @version $Id: Test4.java, v 0.1 2021-01-27 8:33 PM paras.chawla Exp $$
 */
public final class Test4{

    class Inner{
        void test() {
            if ( Test4.this.flag ){
                sample();
            }
        }
    }

    private boolean flag = true;
    public void sample(){
        System.out.println( "Sample" );
    }

    public Test4(){
        (new Inner()).test();
    }

    public static void main(String[] args){
        new Test4();
    }

}