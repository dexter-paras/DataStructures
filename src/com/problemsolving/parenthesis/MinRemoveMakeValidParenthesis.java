/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.parenthesis;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: MinRemoveMakeValidParenthesis.java, v 0.1 2021-01-16 12:51 PM paras.chawla Exp $$
 */
public class MinRemoveMakeValidParenthesis {

    // Not working
    public String minRemoveToMakeValid(String s) {

        if(s==null || s.length()==0){
            return s;
        }

        Stack<Integer> forwardStack = new Stack<>();
        Stack<Integer> backwardStack = new Stack<>();
        StringBuilder builder= new StringBuilder(s);

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('){
                forwardStack.add(i);
            }else if(c==')'){
                backwardStack.add(i);
            }
        }

        // if bStack index is greater than forward stack index, pop both
        while(!forwardStack.isEmpty() && !backwardStack.isEmpty() && backwardStack.peek()> forwardStack.peek()){
            backwardStack.pop();
            forwardStack.pop();
        }

        // index whose parentheses need to be removed is left in both stacks
        while(!forwardStack.isEmpty()){
            int index = forwardStack.pop();
            builder.deleteCharAt(index);
        }

        while(!backwardStack.isEmpty()){
            int index = backwardStack.pop();
            builder.deleteCharAt(index);
        }

        return builder.toString();
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        String str1 = concatenate(word1);
        String str2 = concatenate(word2);

        return str1.equals(str2);
    }

    private String concatenate(String[] words){

        StringBuilder builder = new StringBuilder();

        for(String str : words){
            builder.append(str);
        }
        return builder.toString();
    }


    // Approach 1
    public String minRemoveToMakeValid2(String s) {

        // s= "lee(t(c)o)de)"
        //     0123456789111
        //               012
        // stack = 12
        // 1. if encounter any ')' when stack is empty, put it in removal list
        Set<Integer> indexToRemove = new HashSet<>();

        Stack<Integer> stack = new Stack();

        // 2. Traverse all characters of s
        for(int i=0; i<s.length(); i++){

            char ch = s.charAt(i);
            if(ch == '('){
                stack.push(i);
            }else if(ch==')'){

                // encountered ')' when previous characters are well-balanced
                if(stack.isEmpty()){
                    indexToRemove.add(i);
                } else{
                    stack.pop();
                }
            }
        }

        // 3. if stack has unleft '(', need to add these in removal list as well
        // ))((
        // 0123
        // indexToRemove => 0,1 ; Stack =>2,3
        while(!stack.isEmpty()){
            indexToRemove.add(stack.pop());
        }
        // indexToRemove => 0,1,2,3

        // 4. We've list of all indexes which needs to be removed
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!indexToRemove.contains(i)){
                builder.append(c);
            }
        }

        // 5. convert builder to String
        return builder.toString();
    }

    public static void main(String[] args) {
        MinRemoveMakeValidParenthesis obj = new MinRemoveMakeValidParenthesis();
        System.out.println(obj.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(obj.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(obj.minRemoveToMakeValid("))(("));
        System.out.println(obj.minRemoveToMakeValid("(a(b(c)d)"));
    }

}