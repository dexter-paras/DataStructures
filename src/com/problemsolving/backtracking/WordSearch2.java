/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 *  A B C E
 *  D O G F
 *  P X E T
 */

public class WordSearch2 {

    public static void main(String[] args) {

        char[][] board = {{'a', 'b', 'c', 'e'}, {'d', 'o', 'g', 'f'}, {'p', 'x', 'e', 't'}};
        WordSearch2 obj = new WordSearch2();
        //System.out.println(obj.findWords(board, new String[] {"DOG", "OXE"}));
        System.out.println(obj.findWordsUsingTrie(board, new String[] {"dog", "dab", "date"}));
    }

    public List<String> findWords(char[][] board, String[] words) {

        Set<String> result = new HashSet<>();

        int rows = board.length;
        int cols = board[0].length;

        //word="oath"
        for (String word : words) {

            // traverse into board array

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (word.charAt(0) == board[i][j]) {
                        backTrack(word, i, j, 0, rows, cols, board, result);
                    }
                }
            }
        }
        List<String> finalResult = new ArrayList<String>();

        finalResult.addAll(result);
        return finalResult;
    }

    // Do BackTracking on 2D matrix

    boolean backTrack(String word, int i, int j, int idx, int rows, int cols, char[][] board, Set<String> result) {

        // base condition
        if (idx == word.length()) {
            result.add(word);
            return true;
        }

        // check out of bound conditions
        if (i < 0 || i >= rows || j < 0 || j >= cols || word.charAt(idx) != board[i][j]) {
            return false;
        }

        // mark current cell in board as visited
        board[i][j] = '*';

        boolean ret = false;

        // rows and cols offset
        int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int k = 0; k < directions.length; k++) {
            int newI = directions[k][0] + i;
            int newJ = directions[k][1] + j;

            // V.Imp Concept -->Instead of returning directly once we find a match, we simply break out of the loop and do the cleanup
            // before returning.
            ret = this.backTrack(word, newI, newJ, idx + 1, rows, cols, board, result);
            if (ret) { break; }
        }

        // mark current cell in board as visited, Do the cleaning and then return
        board[i][j] = word.charAt(idx);

        return ret;
    }

    // Approach 2 https://leetcode.com/problems/word-search-ii/solution/
    // Issue with approach 1 is searching every word starting with same character from 00 in map
    // Using Trie, if we're at a character on which 5 words start, then we can search all 5 words from right there
    // without going back to 00(double for loop)

    /*  board[][]
                   A B C E
                   D O G F
                   P X E T


                   D
                  / \
                 O   A
                /     \
               G      B
     */

    TrieNode root;

    public WordSearch2() {
        root = new TrieNode('#');
    }

    // https://leetcode.com/problems/word-search-ii/discuss/712722/Two-Solution-or-Trie-DFS-Backtracking-or-Steps-and-Well-commented
    //https://leetcode.com/problems/word-search-ii/discuss/59881/My-Java-solution-using-Trie
    //https://leetcode.com/problems/word-search-ii/discuss/230245/Java-concise-Code(Very-Easy-to-understand)
    public List<String> findWordsUsingTrie(char[][] board, String[] words) {

        // create Trie using words
        for (String str : words) {
            TrieNode temp = root;
            for (char c : str.toCharArray()) { // "DOG" -> 'D', 'O','G'
                if (temp.adjacent[c - 'a'] == null) {
                    temp.adjacent[c - 'a'] = new TrieNode(c);
                }
                temp = temp.adjacent[c - 'a'];
            }
            temp.end = true;
            temp.word = str;
        }

        // Traverse 2D board and start searching dfs on Trie
        int R = board.length;
        int C = board[0].length;
        TrieNode temp = root;

        List<String> result = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char ch = board[i][j];
                int idx = ch - 'a';
                if (temp.adjacent[idx] != null && ch == temp.adjacent[idx].ch) {
                    dfs(i, j, R, C, board, temp.adjacent[idx], result);
                }
            }
        }
        return result;
    }

    private void dfs(int i, int j, int R, int C, char[][] board, TrieNode temp, List<String> result) {

        // base condition
        if (temp != null && temp.end == true) {
            result.add(temp.word);
            return;
        }

        if (i < 0 || i >= R || j < 0 || j >= C || temp == null || board[i][j] == '*' || board[i][j] != temp.ch) {
            return;
        }

        board[i][j] = '*';
        if (board[i + 1][j] != '*' && i + 1 < R) { dfs(i + 1, j, R, C, board, temp.adjacent[board[i + 1][j] - 'a'], result); }
        if (board[i - 1][j] != '*' && i - 1 > 0) { dfs(i - 1, j, R, C, board, temp.adjacent[board[i - 1][j] - 'a'], result); }
        if (board[i][j + 1] != '*' && j + 1 < C) { dfs(i, j + 1, R, C, board, temp.adjacent[board[i][j + 1] - 'a'], result); }
        if (board[i][j - 1] != '*' && j - 1 > 0) { dfs(i, j - 1, R, C, board, temp.adjacent[board[i][j - 1] - 'a'], result); }

        board[i][j] = temp.ch;
    }

    class TrieNode {
        char       ch;
        TrieNode[] adjacent;
        boolean    end;
        String     word;

        public TrieNode(char ch) {
            this.ch = ch;
            this.adjacent = new TrieNode[26];
            this.end = false;
        }
    }

}