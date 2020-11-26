/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: DesignFileSystem2.java, v 0.1 2020-10-26 23:05 paras.chawla Exp $$
 *
 * If path already exist or its parent path non-exist, return false; Otherwise, create a new path and value.
 * https://leetcode.com/problems/design-file-system/discuss/365901/JavaPython-3-7-line-simple-HashMap-code-w-analysis.
 */
public class DesignFileSystem2 {

    Map<String, Integer> file = new HashMap<>();

    public DesignFileSystem2() {
        file.put("", -1);
    }

    public boolean create(String path, int value) {
        int idx = path.lastIndexOf("/");
        String parent = path.substring(0, idx);
        if (!file.containsKey(parent)) { return false; }
        return file.putIfAbsent(path, value) == null;
    }

    public int get(String path) {
        return file.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        DesignFileSystem2 obj = new DesignFileSystem2();
        obj.create("/leet",1);
        obj.create("/leet/code", 2); // return true
        obj.get("/leet/code"); // return 2
        obj.create("/leet/code", 3); // return false because the parent path "/c" doesn't exist.
        obj.get("/leet/code"); // return -1 because this path doesn't exist.

    }
}