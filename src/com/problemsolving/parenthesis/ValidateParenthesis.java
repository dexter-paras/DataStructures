/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.parenthesis;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: ValidateParenthesis.java, v 0.1 2020-04-16 22:37 paras.chawla Exp $$
 * https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidateParenthesis {

    //(**()) APproach 1
    // case 1 * - ( ; bal=2 , we have more ( & * to balance ), no ) left for pairing
    // case 2 * - ) ; bal=2 , we have more ) & * to balance ( , no ( left for pairing

    public boolean checkValidString(String s) {
        int bal = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                bal++;
            } else if (bal-- == 0) { return false; }
        }
        if (bal == 0) { return true; }
        bal = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') { bal++; } else if (bal-- == 0) { return false; }
        }
        return true;
    }

    public static void main(String[] args) {
        new ValidateParenthesis().checkValidStringUsingStack("(**()))**))");
    }

    public boolean checkValidStringUsingStack(String s) {

        /* Create 2 stacks, 1 for left bracket and another for * bracket
           Keep index of left and right parenthesis
           s = "(**())"
        */
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        /* > When left parenthesis occur, add index in leftStack
         * > When star occur, add star index in startStack
         * > When right parenthesis occur, remove from leftStack to balance left and right
         * > When right parenthesis occur and leftStack is empty, remove from starStack - here star is replacing as ( to balance left and
         * right
         * > When right parenthesis occur and leftStack & starStack is both empty - there is nothing behind to balance ) , return false
         * > Case s ="***(((" , here starStack=[0,1,2] and leftStack=[3,4,5] left after for loop
         * ; this means that all right parenthesis are already balanced with * or
         * (..Now whenever there is a left bracket index appears after the Last star,
         * a false statement can be made, because no ) is there to cancel ( parenthesis
         * > Case s="(((***" after for loop ends ; here we'll start poping out ( and * both as * replacing with ) here
         * > Case s="***)))", here starStack=[0,1,2] -> pop, pop,pop -> return true
         * > A correct sequence should have an empty left bracket stack. You don't need to take care of the star stack
         * */

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftStack.add(i);
            } else if (s.charAt(i) == '*') {
                starStack.add(i);
            } else {
                if (leftStack.isEmpty() && starStack.isEmpty()) {
                    return false;
                } else if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else {
                    starStack.pop();
                }
            }
        }

        // run this when leftStack and startStack both are not empty
        // After for loop lets we're left with this
        // case 1 "***(((" , 012 * stack , 345 ( stack
        // case 2 "(((***" , 012 ( stack , 345 ) stack
        // case 3 "***)))"
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            // star index must appear after left Index. If left index appear after start index,then return false
            if (leftStack.pop() > starStack.pop()) {
                return false;
            }
        }

        return leftStack.isEmpty();
    }


}

