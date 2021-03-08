/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.palindrome;

/**
 * @author paras.chawla
 * @version $Id: ValidPalindrome.java, v 0.1 2020-05-16 14:41 paras.chawla Exp $$
 */
public class ValidPalindrome {

    // Approach 1 - Issue with this solution is that it is possible that
    // after removing char at start index,it may be possible that solution lies in removing char at end index
    // which might make it palindrome
    public boolean validPalindrome(String s) {

        //An empty string is not a validate palindrome
        if (s == null || s.length() == 0) {
            return false;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else if (s.charAt(start) != s.charAt(end)) {
                // removing char at start index and check if subString after removing start index till end index is a palindrome or not
                // removing char at end index and check if subString from start index till end index is a palindrome or not
                if (isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1)) {
                    return true;
                }
                //even if after removing both start index or end index, still its not palindrome then return false
                else {
                    return false;
                }
            }
        }
        // no need to check palindrome, string itself is a palindrome
        return true;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Approach 2 - To find valid palindrome recursively
    // This is generalise solution for at most K character delete.
    public boolean validPalindrome2(String s, int k) {

        //An empty string is not a validate palindrome
        if (s == null || s.length() == 0) {
            return false;
        }

        int start = 0;
        int end = s.length() - 1;

        return helper(s, start, end, 0, 1);

    }

    // check recursively if validPalindrome after deleting k characters
    private boolean helper(String s, int start, int end, int deleted, int k) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                // reached atmost k times
                if (deleted == k) {
                    return false;
                }
                return helper(s, start + 1, end, deleted + 1, k) ||
                        helper(s, start, end - 1, deleted + 1, k);
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean validPalindrome3(String s) {

        int p1 = 0;
        int p2 = s.length()-1;

        while(p1<=p2){

            if(s.charAt(p1)== s.charAt(p2)){
                p1++;
                p2--;
            }else{
                return isPalindrome(s.substring(p1+1,p2+1)) || isPalindrome(s.substring(p1,p2));
            }
        }
        return true;
    }

    private boolean isPalindrome(String str){

        if(str.length()==1)
            return true;

        int p1=0;
        int p2=str.length()-1;

        while(p1<p2){
            if(str.charAt(p1)!= str.charAt(p2))
                return false;
            else{
                p1++;
                p2--;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        new ValidPalindrome().validPalindrome3("deeee");
        new ValidPalindrome().validPalindrome(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga");
        new ValidPalindrome().validPalindrome2(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga", 1);
    }
}