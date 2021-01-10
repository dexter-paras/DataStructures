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

/*Pseudo thoughts
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

    //                           0,     0,     3  ,       4,         0,     [][] board,       "ABCCED"
    private boolean traverse(int i, int j, int row, int col, int index, char[][] board, String word) {

        // when word found
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= row || j < 0 || j >= col || word.charAt(index) != board[i][j]) {
            return false;
        }

        // this is super important to mark visited character as * , if not -> in case of ABCB , it will return true which is incorrect
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

        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(new WordSearch().exist(board, "SEE"));
        //System.out.println(new WordSearch().exist(board, "ABCCED"));
        //System.out.println(new WordSearch().exist(board, "ABCB"));
    }

}