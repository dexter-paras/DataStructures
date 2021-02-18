/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: SelfIterator.java, v 0.1 2021-02-09 8:06 AM paras.chawla Exp $$
 */
public class SelfIterator {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        Iterator<Integer> iterator = list.iterator();
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());

        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());

        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
    }



}