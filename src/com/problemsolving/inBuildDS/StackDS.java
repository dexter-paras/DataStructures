/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.inBuildDS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: StackDS.java, v 0.1 2019-09-27 07:29 paras.chawla Exp $$
 */

/*Build Stack using Array*/
public class StackDS {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("Paras");
        stack.push("Sonal");
        stack.add("Usha");
        stack.pop();
        System.out.println(stack);

        Queue queue = new LinkedList();
        queue.add("Paras");
        queue.add("Sonal");
        queue.offer("Usha");
        queue.remove();
        ((LinkedList) queue).removeFirst();
        System.out.println(queue);
    }
}