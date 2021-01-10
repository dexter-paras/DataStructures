/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: BackSpaceString.java, v 0.1 2020-04-09 19:20 paras.chawla Exp $$
 */
public class BackSpaceString {

    //SOUTH = "ab#c", T = "ad#c"  -->ac true
    //SOUTH = "ab##", T = "c#d#"  -->"" true
    //SOUTH = "a##c", T = "#a#c"  -->c true
    //SOUTH = "a#c", T = "b"      -->  false
    //SOUTH = "##", T = "c#d#"  -->"" true
    public boolean backspaceCompare(String S, String T) {

        StringBuilder SNew = getString(S);
        StringBuilder TNew = getString(T);
        return SNew.toString().equals(TNew.toString());
    }

    private StringBuilder getString(String S) {
        int bsCount = 0;
        StringBuilder string = new StringBuilder("");
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == '#') {bsCount++;} else if (S.charAt(i) != '#' && bsCount != 0) {
                bsCount--;
            } else {
                string.append(S.charAt(i));
            }
        }
        return string;
    }

    public boolean backspaceCompare2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        System.out.println(new BackSpaceString().backspaceCompare2("ab#c", "ad#c"));
        System.out.println(new BackSpaceString().backspaceCompare2("ab##", "c#d#"));
        System.out.println(new BackSpaceString().backspaceCompare2("a##c", "#a#c"));
        System.out.println(new BackSpaceString().backspaceCompare2("a#c", "b"));
    }
}