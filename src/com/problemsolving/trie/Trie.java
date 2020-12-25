/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Trie.java, v 0.1 2020-08-01 13:18 paras.chawla Exp $$
 */
class Trie {

    TrieNode root;

    /**
     * Initialize your val structure here.
     */
    public Trie() {
        root = new TrieNode('#');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode currNode = root;
        char[] chArray = word.toCharArray();// 'a','p','p','l','e'

        for (char ch : chArray) {
            int idx = ch - 'a';
            if (currNode.adjacent[idx] == null) {
                currNode.adjacent[idx] = new TrieNode(ch);
            }
            currNode = currNode.adjacent[idx];
        }
        currNode.end = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode currNode = root;
        char[] chArray = word.toCharArray();// 'a','p','p','l','e'

        for (char ch : chArray) {
            int idx = ch - 'a'; // 0
            if (currNode.adjacent[idx] == null) {
                return false;
            }
            currNode = currNode.adjacent[idx];
        }
        return currNode.end;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode currNode = root;
        char[] chArray = prefix.toCharArray();// 'a','p','p'

        for (char ch : chArray) {
            int idx = ch - 'a'; // 0
            if (currNode.adjacent[idx] == null) {
                return false;
            }
            currNode = currNode.adjacent[idx];
        }
        return true;
    }

    // already inserted elements 'apple' ,'appstore','apt','applet'
    // prefix ="ap"
    private List<String> findAllWords(String prefix) {
        TrieNode currNode = root;
        char[] chArray = prefix.toCharArray();
        List<String> result = new ArrayList<>();

        // move till prefix end and then start using recursion
        for (char ch : chArray) {
            int idx = ch - 'a';
            if (currNode.adjacent[idx] == null) {
                return result;
            }
            currNode = currNode.adjacent[idx];
        }
        // currNode now points to node on prefix
        helperFindAllWords(currNode, result, new StringBuilder(prefix));
        return result;
    }

    private void helperFindAllWords(TrieNode curNode, List<String> result, StringBuilder prefix) {
        if (curNode.end) {
            result.add(new String(prefix));
            //return;
        }

        TrieNode[] adjacents = curNode.adjacent;
        for (TrieNode adj : adjacents) {
            if (adj != null) {
                prefix.append(adj.ch);
                helperFindAllWords(adj, result, prefix);
                prefix.deleteCharAt(prefix.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        /*trie.insert("bbd");
        trie.insert("dad");
        trie.insert("mad");
        trie.searchWordRegix(".ad");
*/
        System.out.println(trie.searchRecursive("applet"));   // returns true
        // trie.findAllWords("ap");
  /*      trie.search("apple");   // returns true
        trie.search("app");     // returns false
        trie.startsWith("app"); // returns true
        trie.insert("app");
        trie.search("app");     // returns true*/

        trie.insert("team");
        trie.insert("teath");
        trie.insert("teatl");
        trie.insert("tease");
        trie.insert("apple");
        trie.insert("apt");
        trie.insert("applet");
        trie.insert("appyfiz");
        System.out.println(trie.findAllWords("ap"));
        System.out.println(trie.findAllWords("app"));
        System.out.println(trie.findAllWords("tea"));
    }

    private boolean searchRecursive(String word) {
        TrieNode currNode = root;
        if (word == null) {
            return false;
        }
        return searchRecursive(currNode, word.toCharArray(), 0);
    }

    // 'a','p','p','l','e'
    private boolean searchRecursive(TrieNode currNode, char[] array, int i) {

        if (i == array.length) {
            return currNode.end;
        }
        int idx = array[i] - 'a';
        if (currNode.adjacent[idx] == null) {
            return false;
        }
        currNode = currNode.adjacent[idx];
        return searchRecursive(currNode, array, i + 1);
    }

    public boolean searchWordRegix(String word) {
        TrieNode currNode = root;
        if (word == null || word.isEmpty()) {
            return false;
        }
        return helper(currNode, word.toCharArray(), 0);
    }

    private boolean helper(TrieNode currNode, char[] array, int i) {

        // base condition, when to break recursion
        if (i == array.length) {
            return currNode.end;
        }

        // if char is a whitespace character
        if (array[i] == '.') {
            TrieNode[] adjacents = currNode.adjacent;
            for (TrieNode adj : adjacents) {
                if (adj != null && helper(adj, array, i + 1)) {
                    return true;
                }
            }
        } else if (currNode.adjacent[array[i] - 'a'] != null) {
            currNode = currNode.adjacent[array[i] - 'a'];
            return helper(currNode, array, i + 1);
        }
        return false;
    }
}

class TrieNode {

    char       ch;
    boolean    end;
    TrieNode[] adjacent;
    final static int ALPHABET_COUNT = 26;

    TrieNode(char ch) {
        this.ch = ch;
        this.end = false;
        this.adjacent = new TrieNode[ALPHABET_COUNT];
    }

}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new Trie(); obj.insert(word); boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */