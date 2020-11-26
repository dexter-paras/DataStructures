/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DecomposeString.java, v 0.1 2020-09-06 11:42 paras.chawla Exp $$
 * https://backtobackswe.com/platform/content/decompose-string/solutions
 */
public class DecomposeString {

    /*
      Input: s = "apple",
             dictionary = ["ap", "pl", "ppp", "pple"]
      Output: false
      Explanation: No combination of words in our dictionary can be put together to form “apple”.
      The closest we can get is “appl”, which is missing the last "e" character ("e" is not in the dictionary)
    */

    // Approach 1- Top Down - Recursion

    List<List<String>> finalResult = new ArrayList<>();

    public boolean canDecompose(List<String> dictionary, String s) {

        for (int i = 0; i < dictionary.size(); i++) {
            String prefix = dictionary.get(i);
            // process only those substring starting from a i.e. a,ap,app,appl,apple
            if (s.startsWith(prefix)) {
                List<String> currList = new ArrayList<>();
                currList.add(prefix);
                helper("", s.substring(prefix.length()), currList);
            }
        }
        return false;
    }

    // prefix = "" , suffix  ="ple"
    private void helper(String prefix, String suffix, List<String> currList) {

        // fully processed
        if (suffix.length() == 0) {
            finalResult.add(new ArrayList<>(currList));
            return;
        }

        //prefix="", suffix ="ple"
        for (int i = 0; i < suffix.length(); i++) {
            helper(prefix + suffix.charAt(i), suffix.substring(i + 1, suffix.length()), currList);
        }
    }

    public boolean canDecomposeDp(List<String> dictionary, String s) {

        // Set the 0 index to True since we can always decompose an empty string
        boolean[] decomposeString = new boolean[s.length() + 1];
        decomposeString[0] = true;

        // We will utilize two pointers to check if substrings exist in our dictionary
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (decomposeString[j] && dictionary.contains(s.substring(j, i))) {
                    // Update our array and break, since we have verified we can decompose our string up to index i
                    decomposeString[i] = true;
                    break;
                }
            }
        }

        // Return true if we can decompose the whole string (the last index is true)
        return decomposeString[s.length()];
    }


    public static void main(String[] args) {
        DecomposeString obj = new DecomposeString();
        List<String> list = Arrays.asList(new String[] {"ap", "pl", "ppp", "pple"});
        obj.canDecomposeDp(list, "apple");
    }
}