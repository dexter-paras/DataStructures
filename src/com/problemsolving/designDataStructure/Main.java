/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

/**
 * @author paras.chawla
 * @version $Id: FireBallRunner.java, v 0.1 2020-10-07 00:49 paras.chawla Exp $$
 */
public class Main {

    public static void main(String[] args) {
        LRUCacheInBuild obj = new LRUCacheInBuild(3);

        obj.put(1, 10);
        obj.put(2, 20);
        obj.put(3, 30);
        obj.put(4, 30);
        obj.put(5, 30);
    }
}