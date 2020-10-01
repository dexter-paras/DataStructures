/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.zeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: SmallestNumberSwapEvenOdd.java, v 0.1 2020-09-23 23:30 paras.chawla Exp $$

Given a numeric string str, the task is to find the smallest integer that can be
formed by swapping adjacent digits of distinct parity.

Input: 836360
Output: 338660
Explanation:
1st Swap: 836360 -> 386360
          ^^
2nd Swap: 386360 -> 383660
            ^^
3rd Swap: 383660 -> 338660
           ^^

Explanation:
1st Swap: 831360 -> 381360
^^
2nd Swap: 381360 -> 318360
^^
3rd Swap: 318360 -> 313860
^^
2 subsets
{8,6,0},{3,1,3}
 ^         ^
result 3,1,3,8,6,0
Input: 1003
Output: 13
 */
public class SmallestNumberSwapEvenOdd {

     String findSmallestNumberAfterSwapInfiniteTimes(String s) {

        // 1. Add evenList elements in evenList list and oddList elements in oddList list
        List<Integer> evenList = new ArrayList<>();
        List<Integer> oddList = new ArrayList<>();

        for (char ch : s.toCharArray()) {
            int digit = ch - '0';
            if (digit % 2 == 0) {
                evenList.add(digit);
            } else {
                oddList.add(digit);
            }
        }

        // 2. initiate 2 pointers to both evenList and oddList list
        int evenPtr=0;
        int oddPtr=0;

        String result="";
        while(evenList.size()>evenPtr && oddList.size() > oddPtr){
            if(evenList.get(evenPtr) > oddList.get(oddPtr)){
                result+= oddList.get(oddPtr++);
            }else{
                result+=evenList.get(evenPtr++);
            }
        }

        // 3. Add remaining even or odd list to end of result string
        // In case number of even and
        // odd digits are not equal
        // If odd digits are remaining
        while (oddPtr < oddList.size())
            result += oddList.get(oddPtr++);

        // If even digits are remaining
        while (evenPtr < evenList.size())
            result += evenList.get(evenPtr++);

        // Removal of leading 0's
        while (result.charAt(0) == '0')
        {
            result = result.substring(1);
        }
        return result;
    }

    public static void main(String[] args) {
        SmallestNumberSwapEvenOdd obj = new SmallestNumberSwapEvenOdd();
        System.out.println(obj.findSmallestNumberAfterSwapInfiniteTimes("836360"));
        System.out.println(obj.findSmallestNumberAfterSwapInfiniteTimes("1003"));
        System.out.println(obj.findSmallestNumberAfterSwapInfiniteTimes("894687536"));
    }

}