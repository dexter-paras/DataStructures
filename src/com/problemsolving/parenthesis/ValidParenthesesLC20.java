/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.parenthesis;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: ValidParenthesesLC20.java, v 0.1 2019-08-18 14:42 paras.chawla Exp $$
 */
public class ValidParenthesesLC20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char popChar = ' ';
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (!stack.isEmpty()) { popChar = stack.pop(); }
                if ((c == ')' && popChar == '(') || (c == '}' && popChar == '{') || (c == ']' && popChar == '[')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public boolean isValid2(String s) {
        HashSet<Character> openBracketSet = new HashSet<>();
        //openBracketSet.add('{');
        //openBracketSet.add('[');
        openBracketSet.add('(');

        HashSet<Character> closeBracketSet = new HashSet<>();
        //closeBracketSet.add('}');
        //closeBracketSet.add(']');
        closeBracketSet.add(')');

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            char popChar = ' ';
            if (openBracketSet.contains(ch)) {
                stack.push(ch);
            } else if (closeBracketSet.contains(ch)) {
                if (!stack.isEmpty()) { popChar = stack.pop(); }
                if ((ch == ')' && popChar == '(') || (ch == '}' && popChar == '{') || (ch == ']' && popChar == '[')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(new ValidParenthesesLC20().isValid2("()))"));
        System.out.println(new ValidParenthesesLC20().isValid("()"));
        System.out.println(new ValidParenthesesLC20().isValid("()[]{}"));
        System.out.println(new ValidParenthesesLC20().isValid("(]"));
        System.out.println(new ValidParenthesesLC20().isValid("([)]"));
        System.out.println(new ValidParenthesesLC20().isValid("{[]}"));
    }
}