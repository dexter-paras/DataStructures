/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: TestLinkedHashMap.java, v 0.1 2020-01-05 18:18 paras.chawla Exp $$
 */
public class TestLinkedHashMap {

    public static void main(String[] args) {
        Map map = new LinkedHashMap<>();
        map.put(0,10);
        map.put(0,40);
        map.put(0,50);
        System.out.println(map);
    }
}