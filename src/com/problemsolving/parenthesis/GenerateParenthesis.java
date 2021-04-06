/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.parenthesis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: GenerateParenthesis.java, v 0.1 2020-10-21 15:33 paras.chawla Exp $$ https://leetcode.com/problems/generate-parentheses/ LC
 * 22. Generate Parentheses
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        //obj.generateParenthesis(1);
        obj.generateParenthesisSol2(3);
        obj.generateParenthesisSol3(3);
    }

    // Approach 1 - Get all the permutation of string ="((()))" and filter based on valid parenthesis - Very slow approach
    public List<String> generateParenthesis(int n) {

        // create a String of 2*n characters , n characters with left parenthesis and n chars with right parenthesis
        char[] ch = new char[2 * n];
        for (int i = 0, j = (2 * n) - 1; i < n; i++, j--) {
            ch[i] = '(';
            ch[j] = ')';
        }
        String str = new String(ch);

        Set<String> set = new HashSet<>();
        helper("", str, set);
        List<String> result = new ArrayList<>();
        result.addAll(set);
        return result;
    }

    private Set<String> helper(String prefix, String suffix, Set<String> result) {

        if (suffix.length() == 0) {
            if (isValid2(prefix)) {
                result.add(prefix);
            }
        }

        for (int i = 0; i < suffix.length(); i++) {
            if (prefix.startsWith("(") || prefix.startsWith("")) {
                helper(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1), result);
            }
        }
        return result;
    }

    public boolean isValid2(String s) {
        HashSet<Character> openBracketSet = new HashSet<>();
        openBracketSet.add('(');

        HashSet<Character> closeBracketSet = new HashSet<>();
        closeBracketSet.add(')');

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            char popChar = ' ';
            if (openBracketSet.contains(ch)) {
                stack.push(ch);
            } else if (closeBracketSet.contains(ch)) {
                if (!stack.isEmpty()) { popChar = stack.pop(); }
                if ((ch == ')' && popChar == '(')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    /* Approach 2 -https://backtobackswe.com/platform/content/generate-all-strings-with-n-matched-parentheses/solutions
      Lets say for n =3 ,we can have 3 open brackets and 3 close brackets
      Choice - place '(' or ')'
      Constraints - 1) can't close until its open
                    2) count of open bracket matters
                    3) If openParenCount==closeParenCount -> only 1 branch exist i.e. use open bracket, can't use close bracket
                    4) If openParenCount < closeParenCount -> two branches converge i.e. either use open bracket if available or use close
                     paren
                    5) If openParentCount== closeParenCount==0 -> Reached base condition
      Goal - To place 2*n characters successfully in String
    */

    public List<String> generateParenthesisSol2(int n) {

        List<String> result = new ArrayList<>();

        // kick off the recursion
        generateBalancedParenthesis(n, n, "", result);
        return result;
    }

    // numLeftParenNeeded =3 and numRightParenNeeded=3 initially and we need to branch down to 0,0 to get final answer
    private void generateBalancedParenthesis(int numLeftParenNeeded, int numRightParenNeeded, String parenSIP, List<String> result) {

        /* Base Condition -> We have used all left and right parens necessary within constraints up to this point.
         Therefore, the answer we add will be a valid paren string.
         We can add this answer and then backtrack so the previous call can exhaust more possibilities and express more answers...and
         then return to its caller,
        etc. etc
        */

        if (numLeftParenNeeded == 0 && numRightParenNeeded == 0) {
            result.add(parenSIP);
        }

        /*
      At each frame of the recursion we have 2 things we can do:

      1.) Insert a left parenthesis
      2.) Insert a right parenthesis

      These represent all of the possibilities of paths we can take from this
      respective call. The path that we can take all depends on the state coming
      into this call.

      Can we insert a left parenthesis? Only if we have lefts remaining to insert
      at this point in the recursion
      */
        if (numLeftParenNeeded > 0) {
            generateBalancedParenthesis(numLeftParenNeeded - 1, numRightParenNeeded, parenSIP +'(', result);
        }

        /*
        Can we insert a right parenthesis? Only if the number of left parens needed
        is less than then number of right parens needed.

        This means that there are open left parenthesis to close OTHERWISE WE CANNOT
        USE A RIGHT TO CLOSE ANYTHING. We would lose balance.
        */
        if (numRightParenNeeded > numLeftParenNeeded) {
            generateBalancedParenthesis(numLeftParenNeeded, numRightParenNeeded - 1, parenSIP +')', result);
        }

    }

    // Approach 3
    //https://leetcode.com/problems/generate-parentheses/discuss/10100/Easy-to-understand-Java-backtracking-solution
    public List<String> generateParenthesisSol3(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){

        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

}