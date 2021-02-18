/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.stackandqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: BasicCalculator2.java, v 0.1 2021-01-29 8:15 AM paras.chawla Exp $$ https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculator2 {

    // s = " 3+5 / 2*4-11*2 "
    public int calculate(String s) {

        // 1. Create a Stack of Characters
        Stack<Character> stack = new Stack<>();

        // 2. Create freqMap of operators
        Map<Character, Integer> freqMap = new HashMap<>();

        // 3. Created freqMap of operators + added characters in Stack as well
        for (char c : s.toCharArray()) {

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            } else if (c == ' ') {
                continue;
            }
            stack.push(c);
        }

        // * as first priority // K = * , V=2
        while (freqMap.get('*') != 0) {

        }

        // / as second priority
        while (freqMap.get('/') != 0) {

        }

        // + as third priority
        while (freqMap.get('+') != 0) {

        }

        // - as last priority
        while (freqMap.get('-') != 0) {

        }
        return 0;
    }

    //APproach 2=  Without using Stack
    public int calculateApproach2(String s) {
        if (s == null) { return 0; }
        s = s.trim().replaceAll(" +", "");
        int length = s.length();

        int res = 0;
        long preVal = 0; // initial preVal is 0
        char sign = '+'; // initial sign is +
        int i = 0;
        while (i < length) {
            long curVal = 0;
            while (i < length && (int) s.charAt(i) <= 57 && (int) s.charAt(i) >= 48) { // int
                curVal = curVal * 10 + (s.charAt(i) - '0');
                i++;
            }
            if (sign == '+') {
                res += preVal;  // update res
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;  // update res
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
            } else if (sign == '/') {
                preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
            }
            if (i < length) { // getting new sign
                sign = s.charAt(i);
                i++;
            }
        }
        res += preVal;
        return res;
    }

    // Approach-3
    // https://leetcode.com/problems/basic-calculator-ii/discuss/371392/Java-solution-with-explanation
    // "22-3*4+7"
    public int calculateApproach3(String s) {

        // 1. base condition
        if (s.isEmpty()) {
            return 0;
        }

        // 2. Start traversal
        int currNum = 0;
        char operator = '+';
        int length = s.length();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {

            // 3. if curr char is digit
            char currChar = s.charAt(i);
            if (Character.isDigit(currChar)) {
                currNum = (currNum * 10) + (currChar - '0');//2
            }

            // 4. if currChar is not digit and it is a operator and we're end of String
            // when full string is processed, we need to perform evaluation on last operand
            if (!Character.isDigit(currChar) && !Character.isWhitespace(currChar) || i == length - 1) {

                if (operator == '-') {
                    stack.push(-currNum);
                } else if (operator == '+') {
                    stack.push(currNum);
                } else if (operator == '*') {
                    stack.push( stack.pop() * currNum);
                } else if (operator == '/') {
                    stack.push(stack.pop()* currNum);
                }

                operator = currChar;
                currNum = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator2 obj = new BasicCalculator2();
        System.out.println(obj.calculateApproach3("3/2 "));
    }
}