/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: RearrangeStringNoDuplicateChar.java, v 0.1 2021-01-31 8:25 AM paras.chawla Exp $$
 * Reaarange the given string so no adjacent cha are duplicate
 *
 * "bbaacaaab"
 * a-5
 * b-3
 * c-1
 *
 * abababaca
 * 423120100
 */
public class RearrangeStringNoDuplicateChar {

    static String rearrangeStringUniquely(String s) {

        // Create freqMap
        Map<Character,Integer> freqMap = new HashMap<>();

        for(char c : s.toCharArray()){
            freqMap.put(c, freqMap.getOrDefault(c, 0)+1);
        }

        // create max PriorityQueue
        PriorityQueue<Pair<Character,Integer>> maxHeap = new PriorityQueue<>((a, b)-> b.count-a.count);

        for(Map.Entry<Character,Integer> entry : freqMap.entrySet()){
            maxHeap.add(new Pair(entry.getKey(),entry.getValue()));
        }

        StringBuilder builder = new StringBuilder();

        while(!maxHeap.isEmpty()){

            Pair<Character,Integer> pair = maxHeap.poll();

            // if characters are not same after removing from maxHeap
            if(builder.length()==0 || builder.charAt(builder.length()-1)!= pair.c){
                builder.append(pair.c); //a
                // add again in pq
                if(pair.count>1){ //5
                    int newCount = pair.count-1;
                    maxHeap.add(new Pair(pair.c,newCount)); //a|4
                }
            }
            // if characters are same after removing from maxHeap
            else{ //a|2
                // impossible scenario
                if(maxHeap.isEmpty()){
                    return "Impossilbe Scenario";
                }
                Pair<Character,Integer> secondPair = maxHeap.poll();//b|3
                builder.append(secondPair.c);
                // add again in pq
                if(secondPair.count>1){ //5
                    int newCount = secondPair.count-1;
                    maxHeap.add(new Pair(secondPair.c,newCount)); //a|4
                }
                // add firstPoll as well
                maxHeap.add(pair);
            }

        }

        return builder.toString();
    }
}
class Pair<K,V>{
    Character c;
    Integer count;

    Pair(Character c,Integer count){
        this.c=c;
        this.count=count;
    }
}