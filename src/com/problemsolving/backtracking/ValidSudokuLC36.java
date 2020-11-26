/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: ValidSudokuLC36.java, v 0.1 2019-10-31 06:49 paras.chawla Exp $$
 */
public class ValidSudokuLC36 {

    public static final  int  SIZE        = 9;
    private static final char EMPTY_ENTRY = '.';

    private boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (isOk(row, col, board)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOk(int row, int col, char[][] board) {
        return !isInRow(row, board[row][col], board) && !isInCol(col, board[row][col], board) && !isInBox(row, col, board[row][col], board);
    }

    private boolean isInRow(int row, char number, char[][] board) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (number != EMPTY_ENTRY && board[row][i] == number) {
                count++;
            }
        }

        return count > 1;
    }

    private boolean isInCol(int col, char number, char[][] board) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) { if (number != EMPTY_ENTRY && board[i][col] == number) { count++; } }

        return count > 1;
    }

    private boolean isInBox(int row, int col, char number, char[][] board) {
        int r = row - row % 3;
        int c = col - col % 3;
        int count = 0;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (number != EMPTY_ENTRY && board[i][j] == number) {
                    count++;
                }
            }
        }

        return count > 1;
    }

    public boolean isValidSudokuSolution2(char[][] board) {
        Set<String> seen = new HashSet();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char number = board[row][col];
                if (number != EMPTY_ENTRY) {
                    if (!seen.add(number + " in row " + row) ||
                            !seen.add(number + " in col " + col) ||
                            !seen.add(number + " in block " + row / 3 + "-" + col / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new ValidSudokuLC36().isValidSudoku(board));
        System.out.println(new ValidSudokuLC36().isValidSudokuSolution2(board));
    }

}