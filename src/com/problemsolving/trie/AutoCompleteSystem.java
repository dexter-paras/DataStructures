/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: AutoCompleteSystem.java, v 0.1 2020-08-15 17:43 paras.chawla Exp $$
 */
public class AutoCompleteSystem {

    // used for tracking input character - 2nd part
    String prefix;

    // root node consisting of all sentences and times
    TrieNode root;

    // add sentences along with count in trieNode created
    // [paras,page,parrot,pot] [3,5,7,10]
    public AutoCompleteSystem(String[] sentences, int[] times) {

        root = new TrieNode();
        prefix = "";

        for (int i = 0; i < sentences.length; i++) {
            addSentence(sentences[i], times[i]);
        }
    }

    // sentence = "i love you" , time = 5
    private void addSentence(String sentence, int time) {
        TrieNode currNode = root;

        // sentence = "i love you", count = 5
        for (char ch : sentence.toCharArray()) {
            int idx = ch - 'a';
            if (currNode.adjacent[idx] == null) {
                currNode.adjacent[idx] = new TrieNode();
            }

            // it could be possible that "paras",3 and "paras",4 come twice so count should be 7
            // every character downstream is having Map<Sentence,Count>
            currNode.adjacent[idx].map.put(sentence, currNode.adjacent[idx].map.getOrDefault(sentence, 0) + time);

            //currNode.adjacent[idx].map.put(sentence,time) <-- incorrect , shouldn't override with same sentence
            currNode = currNode.adjacent[idx];
        }
    }

    // List hot top list once char is typed
    public List<String> input(char c) {

        List<String> result = new ArrayList<>();

        // 1. user finished the input, the sentence prefix should be saved as a historical sentence in system
        if (c == '#') {
            addSentence(prefix, 1);
            // 1.1 Reset prefix as empty string and lets user initiate again
            prefix = "";
            return result;
        }

        //2. if char 'c' is typed, below should list top 3 hot words
        prefix += c;
        TrieNode currNode = root;

        // 3. reached to particular character typed
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (currNode.adjacent[idx] == null) {
                return result;
            }
            currNode = currNode.adjacent[idx];
        }

        // 4. take map of all sentences at that particular character
        // 4.1 convert map into PriorityQueue based on count and if count is same then lexiographically order
        // 4.2 Elements are ordered according to the specified comparator
        PriorityQueue<Pair<String, Integer>> maxHeap = new PriorityQueue<>(
                (pair1, pair2) -> (pair1.count == pair2.count ? pair1.sentence.compareTo(pair2.sentence) : pair2.count - pair1.count));

        for (Map.Entry<String, Integer> entry : currNode.map.entrySet()) {
            maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
        }

        // 5. poll only top 3 elements and return
        // could be possible that maxHeap is having less than 2
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) {
            result.add((String) maxHeap.poll().sentence);
        }

        return result;
    }

    public static void main(String[] args) {
        //case 1
        //String[] sentences = new String[] {"paras","page","parrot","pot"};
        //int[] times = new int[] {3,5,7,10};

        //case 2:
        String[] sentences = new String[] {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = new int[] {5, 3, 2, 2};

        AutoCompleteSystem system = new AutoCompleteSystem(sentences, times);
        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
    }

    // TrieNode corresponding to AutoCompleteSystem
    class TrieNode {

        // trieNode array consisting of all possible characters
        TrieNode[] adjacent;

        // each character is carrying a map of String starting from that particular prefix along with count
        Map<String, Integer> map;

        public TrieNode() {
            this.adjacent = new TrieNode[128];
            this.map = new HashMap<>();
        }
    }

    // Need to map Map<String,Integer> to a single class Pair so as to put it in PriorityQueue
    class Pair<String, Integer> {
        Integer count;
        String  sentence;

        public Pair(String sentence, Integer count) {
            this.count = count;
            this.sentence = sentence;
        }
    }
}
