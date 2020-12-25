/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: NQueenProblem.java, v 0.1 2020-10-12 08:55 paras.chawla Exp $$
 * <p>
 * 1. modularize 2. method signature 3. base condition https://leetcode
 * .com/problems/n-queens/discuss/19805/My-easy-understanding-Java-InMemoryDataStructure
 * <p>
 * Intuition is When we place a QUEEN at a particular row, then jump to the next row and for all columns, check if we can place queen or
 * not. dfs() - traverse from one row to another for loop - traverse all columns of a particular row
 */
public class NQueenProblem {

    public static void main(String[] args) {
        NQueenProblem obj = new NQueenProblem();
        System.out.println(obj.solveNQueens2(4));
    }

    // APproach 1 - Using Depth First Search and 2-D Array
    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];

        // Fill the board with dot
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board, 0, res);
        return res;
    }

    // When we successfully place a Q in any coln of particular rowIdx, move to next row and recurse again
    private void dfs(char[][] board, int row, List<List<String>> res) {

        // base condition
        if (row == board.length) {
            res.add(builder(board));
            return;
        }

        // Check every coln and place coln which suffice bounded constraints
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                // Choose
                board[row][col] = 'Q';
                // Recurse
                dfs(board, row + 1, res);
                // UnChoose
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] mat, int x, int y) {
        // only check rows above current one
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < mat.length; j++) {
                // not need to check current position
                if (i == x && j == y) {
                    continue;
                }
                // if 'Q' in the same col or the diagonal line, return false
                if ((j == y || Math.abs(x - i) == Math.abs(y - j)) && mat[i][j] == 'Q') {
                    return false;
                }
            }
        }
        return true;
    }

    // build solution from temporary chessboard
    private List<String> builder(char[][] mat) {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            String t = new String(mat[i]);
            tmp.add(t);
        }
        return tmp;
    }

    // APproach 2 - Using Recursion and back-tracking and 1-d Array
    public List<List<String>> solveNQueens2(int n) {

        List<List<String>> result = new ArrayList<>();

        List<Integer> colPlacements = new ArrayList<>();
        helper(0, n, colPlacements, result);
        return result;
    }

    private void helper(int rowIdx, int n, List<Integer> colPlacements, List<List<String>> result) {

        // base condition
        if (rowIdx == n) {
            result.add(generate(colPlacements, n));
            return;
        }

        //try all cols
        for (int colIdx = 0; colIdx < n; colIdx++) {
            colPlacements.add(colIdx);
            if (isValid(colPlacements)) {
                helper(rowIdx + 1, n, colPlacements, result);
            }
            colPlacements.remove(colPlacements.size() - 1);
        }
    }

    private boolean isValid(List<Integer> colPlacements) {
    /*
      rowWeAreValidatingOn is the row that we just placed a queen on
      and we need to validate the placement
    */
        int rowWeAreValidatingOn = colPlacements.size() - 1;

    /*
      Loop and check our placements in every row previous against
      the placement that we just made
    */
        for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {
      /*
        Get the absolute difference between:

        1.) The column of the already placed queen we are comparing against -> colPlacements.get(ithQueenRow)

        2.) The column of the queen we just placed -> colPlacements.get(rowWeAreValidatingOn)
      */
            int absoluteColumnDistance = Math.abs(
                    colPlacements.get(ithQueenRow) - colPlacements.get(rowWeAreValidatingOn)
            );

      /*
        1.) absoluteColumnDistance == 0
          If the absolute difference in columns is 0 then we placed in a column being
          attacked by the i'th queen.

        2.) absoluteColumnDistance == rowDistance
          If the absolute difference in columns equals the distance in rows from the
          i'th queen we placed, then the queen we just placed is attacked diagonally.

        For Constraint #2 imagine this:

        [
          "--Q-",  <--- row 0 (Queen 1)
          "Q---",  <--- row 1 (Queen 2)
          "-Q--",  <--- row 2 (Queen 3)
          "----"
        ]

        1.)
          Absolute Column Distance Between Queen 2 & 3 == 1.
          Queen 2 is in col 0, Queen 3 is in col 1. 1 - 0 = 1.

        2.)
          Absolute Row Distance Between Queen 2 & 3 == 1
          Queen 2 is in row 1, Queen 3 is in row 2. 2 - 1 = 1.
      */
            int rowDistance = rowWeAreValidatingOn - ithQueenRow;
            if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }

    private List<String> generate(List<Integer> colPlacements, int n) {

        List<String> board = new ArrayList<>();
        int totalItemsPlaced = colPlacements.size();

        // Materialize a row for each queen that we placed
        for (int row = 0; row < totalItemsPlaced; row++) {
            StringBuilder sb = new StringBuilder();

            for (int col = 0; col < n; col++) {
                if (col == colPlacements.get(row)) {
                    sb.append('Q');
                } else {
                    sb.append('-');
                }
            }
            // Add the row to the board
            board.add(sb.toString());
        }
        return board;
    }
}