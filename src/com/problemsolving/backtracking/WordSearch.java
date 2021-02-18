/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

/**
 * @author paras.chawla
 * @version $Id: WordSearch.java, v 0.1 2020-05-20 00:49 paras.chawla Exp $$
 * <p>
 * board =
 * [
 *   ['A','B','C','EAST'],
 *   ['SOUTH','F','C','SOUTH'],
 *   ['A','D','EAST','EAST']
 * ]
 *
 * [
 *  ['A','B','C','EAST'],
 *  ['*','F','C','SOUTH'],
 *  ['A','D','EAST','EAST']
 *  ]
 *
 * word = "ABCCED", return true word = "SEE", return
 * true
 */

/* Pseudo thoughts
 * start with board[0][0] and check if char matches with word.charAt(0)
 * if matches->move all directions board[i++][j] ,board[i--][j], board[i][j++], board[i][j--]
 * Make sure to traverse in all 4 directions adding index + 1
 * if matches , keep traversing in all 4 directions
 * if not matches -> move to next
 * if all 4 directions doesn't match , then pick next character from board[][]
 * */

public class WordSearch {

    // board - m*n
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                {
                    if (traverse(i, j, row, col, 0, board, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*Time Complexity:O(N⋅3^L)
    where N is the number of cells in the board and L is the length of the word to be matched.

    For the backtracking function, initially we could have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from).
    As a result, the execution trace after the first step could be visualized as a 3-ary tree, each of the branches represent a potential exploration in the corresponding direction. Therefore, in the worst case,
    the total number of invocation would be the number of nodes in a full 3-nary tree, which is about 3^L

    We iterate through the board for backtracking, i.e. there could be N times invocation for the backtracking function in the worst case.

    As a result, overall the time complexity of the algorithm would be O(N*3^L)
    */
    //                           0,     0,     3  ,       4,         0,     [][] board,       "DOG"
    private boolean traverse(int i, int j, int row, int col, int index, char[][] board, String word) {

        // when word found
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= row || j < 0 || j >= col || word.charAt(index) != board[i][j]) {
            return false;
        }

        // this is super important to mark visited character as * , if not -> in case of DOD , it will return true which is incorrect
        // need to mark visited char so that same can't be backtracked
        board[i][j] = '*';
        //Appraoch 2 - board[i][j] ^= 256;
        boolean exist = traverse(i + 1, j, row, col, index + 1, board, word) // Down (1,0,3,4,1,board,word) //'A' -> NA
                || traverse(i - 1, j, row, col, index + 1, board, word)      // Up (-1,0,3,4,1,board,word) //'A' -> NA
                || traverse(i, j + 1, row, col, index + 1, board, word)     // Right (0,1,3,4,1,board,word)  //'A' --> 'F'
                || traverse(i, j - 1, row, col, index + 1, board, word);    // Left (0,-1,3,4,1,board,word) //'A' -> NA
        //remove the visited mark, so that it can be visited again in the future
        board[i][j] = word.charAt(index);
        //Appraoch 2 - board[i][j] ^= 256;
        return exist;
    }

    public static void main(String[] args) {

        char[][] board = {{'A', 'B', 'C', 'E'}, {'D', 'O', 'G', 'F'}, {'P', 'X', 'E', 'T'}};
        System.out.println(new WordSearch().exist(board, "DOD"));
        //System.out.println(new WordSearch().exist(board, "ABCCED"));
        //System.out.println(new WordSearch().exist(board, "ABCB"));
    }

}