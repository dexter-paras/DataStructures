/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.designDataStructure;

/**
 * @author paras.chawla
 * @version $Id: TicTacToe.java, v 0.1 2020-02-18 23:33 paras.chawla Exp $$
 */
class TicTacToe {

    private int[][] board;
    private int     size;
    private int     count;
    private int     dimention;

    /**
     * Initialize your val sctructure here.
     */
    TicTacToe(int n) {
        board = new int[n][n];
        size = n * n;
        dimention = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
     */
    //(0,0,1)
    int move(int row, int col, int player) {
        if (count++ < size) {
            board[row][col] = player;
            if (winMove(row, col, player)) {
                return player;
            }
            return 0;
        }
        return 0;
    }

    //(row=1,col=2,player=1)
    private boolean winMove(int row, int col, int player) {

        // check horizontal row 1
        int i, j, r, c;
        for (i = 0; i < dimention; i++) {
            if (board[row][i] != player) {
                break;
            }
        }
        if (i == dimention) {
            return true;
        }
        // check vertical row 2
        for (j = 0; j < dimention; j++) {
            if (board[j][col] != player) {
                break;
            }
        }
        if (j == dimention) {
            return true;
        }

        // check left to right diagonal if exist
        if (row == col) {
            for (i = 0; i < dimention; i++) {
                if (board[i][i] != player) {
                    break;
                }
            }
            if (i == dimention) {
                return true;
            }
        }

        // check right to left diagonal if exist
        if (Math.abs(row + col) == dimention - 1) {
            for (r = 0, c = dimention - 1; r < dimention; r++,c--) {
                if (board[r][c] != player) {
                    break;
                }
            }
            if (r == dimention){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(3);
        System.out.println(toe.move(0, 0, 1));
        System.out.println(toe.move(0, 2, 2));
        System.out.println(toe.move(2, 2, 1));
        System.out.println(toe.move(1, 1, 2));
        System.out.println(toe.move(2, 0, 1));
        System.out.println(toe.move(1, 0, 2));
        System.out.println(toe.move(2, 1, 1));
    }
}