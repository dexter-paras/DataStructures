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
 * 1. modularize 2. method signature 3. base condition
 * https://leetcode.com/problems/n-queens/discuss/19805/My-easy-understanding-Java-Solution
 *
 * Intuition is When we place a QUEEN at a particular row, then jump to the next row and for all columns, check if we can place queen or
 * not.
 * dfs() - traverse from one row to another
 * for loop - traverse all columns of a particular row
 *
 */
public class NQueenProblem {

    public static void main(String[] args) {
        NQueenProblem obj = new NQueenProblem();
        System.out.println(obj.solveNQueens(4));
    }

    // APproach 1 - Using Depth First Search
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

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

}