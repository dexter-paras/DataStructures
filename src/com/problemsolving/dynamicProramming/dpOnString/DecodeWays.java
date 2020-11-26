/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author paras.chawla
 * @version $Id: DecodeWays.java, v 0.1 2020-03-22 23:41 paras.chawla Exp $$
 */
public class DecodeWays {

    HashMap<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new DecodeWays().decodeWays("2261"));
        System.out.println(new DecodeWays().numDecodings("2261"));
    }

    private int decodeWays(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }
        return decode(0, str);
    }

    // decode("121",0) -> "1" + decode("21",1) ||  "12" + decode("1",2)
    // decode("21",1) -> "2" + decode("1",2)   || "21" +  decode("",3)
    // decode("1",2) -> "1" + decode("")
    private int decode(int index, String str) {

        if (index == str.length()) {
            return 1;
        }
        if (str.charAt(index) == '0') {
            return 0;
        }
        if (index == str.length() - 1) {
            return 1;
        }
        // Memoization is needed since we might encounter the same sub-string.
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        int ans = decode(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += decode(index + 2, str);
        }
        // Save for memoization
        memo.put(index, ans);

        return ans;
    }

    // "2261" -> BBFA , BZA, UFA -> 3
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // Create an array storing subproblem computation at particular decodePointer(index)
        int dp[] = new int[s.length()];
        Arrays.fill(dp, -1);
        return helper(s, 0, dp);
    }

    private int helper(String s, int decodePointer, int[] dp) {

        if (decodePointer >= s.length()) {
            return 1;
        }
        int countDecode = 0;

        if (dp[decodePointer] > -1) {
            return dp[decodePointer];
        }
        // From a given index, we've 2 possiblities of decoding characters
        // either single char i.e. 1 to 9 or combination of chars i.e. 10 to 26
        // With using DP, we can store result of value at decodepointer in DP so as not to compute similar subproblem
        /*            helper("2261",0)
                  /                    \
              2+h("261")             22,h("61")
                /     \                  \
      2,2 + h("61")  2,26+h("1")        return 1;
              /        \
   2,2,6 + h("1")    2,26,1+h("")
            /
 2,2,6,1 +h("")
        */
        for (int i = 1; i <= 2; i++) {
            if (decodePointer + i <= s.length()) {
                String snippet = s.substring(decodePointer, decodePointer + i);
                if (isValid(snippet)) {
                    countDecode += helper(s, decodePointer + i, dp);
                }
            }
        }

        // Record subproblem answer to decompositions from (decodePointer)...(s.length - 1)
        dp[decodePointer] = countDecode;
        return countDecode;
    }

    // String snippet must always to of length 1 or 1 and first character if 0 should be ignored
    private boolean isValid(String snippet) {
        if (snippet.length() == 0 && snippet.charAt(0) == '0') {
            return false;
        }
        int value = Integer.parseInt(snippet);
        return value >= 1 && value <= 26;
    }

}