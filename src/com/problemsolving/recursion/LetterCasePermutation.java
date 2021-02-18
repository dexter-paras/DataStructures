/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: LetterCasePermutation.java, v 0.1 2021-02-17 10:25 AM paras.chawla Exp $$
 */
public class LetterCasePermutation {

    /* S=       "a1b2c3"
           /                \
         a1b2c3            A1b2c3
           ^                 ^
        /           \
     a1b2c3         a1B2c3
         ^              ^
     /     \           /    \
   a1b2c3  a1b2C3   a1B2c3  a1B2C3

    */
    public List<String> letterCasePermutation(String s) {

        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0) { return result; }

        helper(s.toCharArray(), result, 0);

        return result;

    }

    // s="a1b2c3",0
    // TIP - Its good to convert String to ch array when working with recursion
    private void helper(char[] chs, List<String> result, int pos) {

        // base condition
        if (pos == chs.length) {
            result.add(new String(chs));
            return;
        }

        // if not character, then skip
        if (!Character.isLetter(chs[pos])) {
            helper(chs, result, pos + 1);
            return;
        }

        // lower case
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, result, pos + 1);

        // upper case
        chs[pos]=Character.toUpperCase(chs[pos]);
        helper(chs, result, pos + 1);
    }

    // Approach -2 BFS
    /*
    Intuition - loop full string up to length l & process on queue


                      "a1b2c3"                          queue size = 1
                       ^
                   /            \
               a1b2c3          A1b2c3                         size = 2
                ^               ^
                |               |
               a1b2c3          A1b2c3
                 ^               ^
               /    \          /        \
            a1b2c3  a1B2c3    A1b2c3    A1B2c3               size = 4
               ^       ^         ^         ^
              |        |        |          |
            a1b2c3  a1B2c3    A1b2c3    A1B2c3
                ^       ^         ^         ^
             /  \      /    \        /  \           /\

           a1b2c3 a1b2C3    a1B2c3  a1B2C3  A1b2c3  A1b2C3  A1B2c3  A1B2C3  queue size =8

     */

    public List<String> letterCasePermutationBfs(String S) {

        if (S == null) {
            return new LinkedList<>();
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(S);

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }

        return new LinkedList<>(queue);
    }

    public static void main(String[] args) {
        LetterCasePermutation obj = new LetterCasePermutation();
        System.out.println(obj.letterCasePermutation("abc"));
        System.out.println(obj.letterCasePermutationBfs("a1b2c3"));

    }

}