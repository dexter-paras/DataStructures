/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: WordLadder.java, v 0.1 2020-07-25 14:33 paras.chawla Exp $$
 * https://www.youtube.com/watch?v=M9cVl4d0v04 Given two words
 * (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord
 *
 * Why BFS and not DFS
 * 1) Always good to have BFS in order to find shortest distance in Graph
 * 2) Always good to have DFS in order to find shortest distance in Graph
 *
 * TC O(26*NORTH*NORTH*WEST)
 * NORTH - Number of characters in String
 * 26*NORTH - Number of transformations happening in a string of NORTH characters
 * 26*NORTH*NORTH - Comparison with endWord string ( String comparison is O(NORTH))
 * WEST - Number of Words in the BFS call
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 1.Convert list to set, to check if string is visited or not while exploring
        Set<String> set = new HashSet<>(wordList);

        // 2. check if endWord is in set, if not then transformation isn't possible
        if (!set.contains(endWord)) {
            return 0;
        }

        // 3. Start from beginWord and explore bfs
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int depth = 1;

        // 4. start explore level by level until endWord not reached
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                char[] currWordChars = queue.poll().toCharArray(); //'h','i','t'

                // 5. Explore char by char and change every char of hit by 1 by 1 and check in set
                for (int i = 0; i < currWordChars.length; i++) {
                    char originalChar = currWordChars[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        currWordChars[i] = ch; // 'h' -> 'a' ; 'h' -> 'b' ;'h'->'c' --- 'h' ->'z'
                        String transformedString = new String(currWordChars);

                        if (set.contains(transformedString)) {
                            // 6. if transformedString equals endWord, return depth+1
                            if (transformedString.equals(endWord)) {
                                return depth + 1;
                            }

                            // 7. if transformedString is in wordList/Set, add in queue to process level by level
                            queue.add(transformedString);
                            // 8. 'hit'->'hot'->'hit'->'hot'
                            // want unique path and not duplicates
                            set.remove(transformedString);
                        }
                    }
                    // undo originalChar to transformed ith char of char[]
                    currWordChars[i] = originalChar;
                }
            }
            depth++;
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
        System.out.println(new WordLadder().ladderLength("hit", "cog", list));
    }
}