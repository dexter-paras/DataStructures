/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: FindPermutationIncreasingDecreasing.java, v 0.1 2020-11-24 13:40 paras.chawla Exp $$
 */
public class FindPermutationIncreasingDecreasing {

    int smallestNumber1(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }

        String result = new String();
        if (str.charAt(0) == 'I') {
            result = "12";
        } else if (str.charAt(0) == 'D') {
            result = "21";
        }

        if (str.length() == 1) {
            return Integer.parseInt(result);
        }

        Set<Integer> lastUsedSet = new HashSet<>();
        lastUsedSet.add(1);
        lastUsedSet.add(2);
        return helper(str, result, 1, lastUsedSet);
    }

    int helper(String str, String builder, int i, Set<Integer> lastUsedSet) {

        // base condition
        if (i == str.length()) {
            return Integer.parseInt(builder);
        }

        char ch = str.charAt(i);

        if (ch == 'D') {
            int min = justSmallestNumber(builder, lastUsedSet);
            if (min == -1) {
                return helper(str, builder, i - 1, lastUsedSet);
            } else {
                lastUsedSet.add(min);
                builder = builder + "" + min;
            }
        } else if (ch == 'I') {
            int max = justLargestNumber(builder, lastUsedSet);
            lastUsedSet.add(max);
            builder = builder + "" + max;
        }
        return helper(str, builder, i + 1, lastUsedSet);
    }

    private int justSmallestNumber(String builder, Set<Integer> lastUsedSet) {
        int letter = builder.charAt(builder.length() - 1) - '0';
        while (--letter > 0) {
            if (!lastUsedSet.contains(letter)) {
                return letter;
            }
        }
        return -1;
    }

    private int justLargestNumber(String builder, Set<Integer> lastUsedSet) {
        int letter = builder.charAt(builder.length() - 1) - '0';
        while (++letter > 0) {
            if (!lastUsedSet.contains(letter)) {
                return letter;
            }
        }
        return -1;
    }

    // Approach 2- Using Stack as DS // str= "IIDDIIII"
    // Intuition - Use Stack for reversing anything
    private int[] smallestNumber(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        // for a str with l as character length ,lexicographically smallestNumber will be having length = length+1;
        // for str="IIDDIIII" - 123456789 <- min permutation without considerting s pattern

        Stack<Integer> stack = new Stack<>();
        int idx=0, i = 0;
        int[] result = new int[str.length() + 1];

        for (i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'I') {
                stack.add(i + 1);
                while (!stack.isEmpty()) {
                    result[idx++] = stack.pop();
                }
            } else if (ch == 'D') {
                // keep adding so that when encounter 'I', take reverse from stack and add into result
                stack.add(i + 1);
            }
        }

        // when str is processed, add last digit into stack and pop stack to result array
        stack.add(i + 1);
        while (!stack.isEmpty()) {
            result[idx++] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        FindPermutationIncreasingDecreasing obj = new FindPermutationIncreasingDecreasing();
        //System.out.println(obj.smallestNumber("I"));
        //System.out.println(obj.smallestNumber("II"));
        //System.out.println(obj.smallestNumber("D"));
        //System.out.println(obj.smallestNumber("DII"));
        //System.out.println(obj.smallestNumber("DIID"));
        System.out.println(Arrays.toString(obj.smallestNumber("IIDDIIIDDDD")));
        System.out.println(Arrays.toString(obj.smallestNumber("IIDDIIIIIII")));
    }
}