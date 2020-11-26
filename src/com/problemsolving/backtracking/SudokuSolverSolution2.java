/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.backtracking;

/**
 * @author paras.chawla
 * @version $Id: SudokuSolverSolution2.java, v 0.1 2019-10-30 21:53 paras.chawla Exp $$
 */
public class SudokuSolverSolution2 {
    public static int[][] GRID_TO_SOLVE =
            {{9, 0, 0, 1, 0, 0, 0, 0, 5}, {0, 0, 5, 0, 9, 0, 2, 0, 1},
                    {8, 0, 0, 0, 4, 0, 0, 0, 0}, {0, 0, 0, 0, 8, 0, 0, 0, 0}, {0, 0, 0, 7, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 6, 0, 0, 9}, {2, 0, 0, 3, 0, 0, 0, 0, 6}, {0, 0, 0, 2, 0, 0, 9, 0, 0},
                    {0, 0, 1, 9, 0, 4, 5, 7, 0},};

    private             int[][] board;
    public static final int     EMPTY = 0, SIZE = 9;

    public SudokuSolverSolution2(int[][] board) {
        this.board = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) { for (int j = 0; j < SIZE; j++) { this.board[i][j] = board[i][j]; } }
    }

    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) { if (board[row][i] == number) { return true; } }

        return false;
    }

    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++) { if (board[i][col] == number) { return true; } }

        return false;
    }

    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) { for (int j = c; j < c + 3; j++) { if (board[i][j] == number) { return true; } } }

        return false;
    }

    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {
                            board[row][col] = number;

                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) { System.out.print(" " + board[i][j]); }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        SudokuSolverSolution2 sudoku = new SudokuSolverSolution2(GRID_TO_SOLVE);
        System.out.println("Sudoku grid to solve");
        sudoku.display();

        if (sudoku.solve()) {
            System.out.println("Sudoku Grid solved with simple BT");
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
    }
}