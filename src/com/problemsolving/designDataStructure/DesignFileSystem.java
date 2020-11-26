/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: DesignFileSystem.java, v 0.1 2020-10-26 22:42 paras.chawla Exp $$
 */
class DesignFileSystem {

    Map<String, Integer> map;

    public DesignFileSystem() {
        map = new HashMap<>();
    }

    // Approach 1 - High Time Complexity
    public boolean createPath(String path, int value) {
        if (map.containsKey(path)) {
            return false;
        }

        // Split path using delimeter '/'
        String[] tokens = path.split("/");

        StringBuilder builder = new StringBuilder();

        // traverse till second last token if entries does exist in map
        for (int i = 1; i < tokens.length - 1; i++) {
            builder.append("/").append(tokens[i]);
            if (!map.containsKey(builder.toString())) {
                return false;
            }
        }
        map.put(path, value);
        return true;
    }

    public int get(String path) {
        return map.get(path) == null ? -1 : map.get(path);
    }

    public static void main(String[] args) {
        DesignFileSystem fileSystem = new DesignFileSystem();
        fileSystem.createPath("/leet", 1); // return true
        fileSystem.createPath("/leet/code", 2); // return true
        fileSystem.get("/leet/code"); // return 2
        fileSystem.createPath("/leet/code", 3); // return false because the parent path "/c" doesn't exist.
        fileSystem.get("/leet/code"); // return -1 because this path doesn't exist.

    }

}
