/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: PartitionLabels.java, v 0.1 2021-03-27 12:40 AM paras.chawla Exp $$
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {

        List<Integer> result = new ArrayList<>();

        // Create a Map K- Character, V- last index of character
        Map<Character,Integer> charIdxMap = new HashMap<>();

        for(int i =0 ; i< S.length();i++){
            charIdxMap.put(S.charAt(i),i);
        }

        // Loop String again
        int maxLastIdx = -1;
        int lastSum=0;

        for(int i=0 ;i < S.length();i++){

            char c = S.charAt(i);

            int lastIdx = charIdxMap.get(c); // 15
            maxLastIdx = Math.max(maxLastIdx, lastIdx);//15

            // partioned Index, Save in answer
            if(i == maxLastIdx){

                if(result.isEmpty())
                    result.add(maxLastIdx +1);
                else
                    result.add(maxLastIdx + 1 - lastSum);

                lastSum += result.get(result.size()-1);
                maxLastIdx = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels obj = new PartitionLabels();
        System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));
    }

}