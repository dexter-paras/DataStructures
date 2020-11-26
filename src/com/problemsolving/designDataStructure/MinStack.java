/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: MinStack.java, v 0.1 2020-02-12 00:00 paras.chawla Exp $$
 */
public class MinStack {

    // Normal stack
    Stack<Integer> stack;

    // minStack contains element lesser than its own existing elements
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public int pop() {
        int x = stack.pop();
        if (x == minStack.peek()) {
            minStack.pop();
        }
        return x;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(0);
        obj.push(1);
        obj.push(0);
        System.out.println(obj.getMin());  // --> Returns -3.
        System.out.println(obj.pop());
        System.out.println(obj.getMin());   //--> Returns -2.
    }
}

