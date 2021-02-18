/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.stackandqueue;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: SimplifyPath.java, v 0.1 2021-02-06 8:40 AM paras.chawla Exp $$
 */
public class SimplifyPath {


    /* path = "/home/"
       path = "/../"
       path = "/home//foo/"
       path = "/a/./b/../../c/"
    */

    public static void main(String[] args) {
        //System.out.println(simplifyPath("/home/"));
        //System.out.println(simplifyPath("/../"));
        //System.out.println(simplifyPath("/home//foo"));
        System.out.println(simplifyPath("/a/./b/../../c/d/e"));
    }

    public static String simplifyPath(String path) {

        String[] tokens = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {

            if (token.isEmpty()) {
                continue;
            }
            // remove string from Stack if exist
            else if (token.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!token.equals(".") && !token.equals("..")) {
                stack.push(token);
            }
        }

        /*// create canonical path from Strings exist in Stack
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append("/").append(stack.pop());
        }

        String tokens1[] = builder.toString().split("/");
        StringBuilder result = new StringBuilder();
        for (int i = tokens1.length - 1; i > 0; i--) {
            result.append("/").append(tokens1[i]);
        }
        */

        StringBuilder result = new StringBuilder();
        for (String token : stack) {
            result.append("/").append(token);
        }
        return result.toString().isEmpty() ? "/" : result.toString();
    }

}