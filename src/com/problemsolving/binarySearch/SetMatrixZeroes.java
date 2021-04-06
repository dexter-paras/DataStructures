/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

import com.problemsolving.genericProgramming.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: SetMatrixZeroes.java, v 0.1 2020-12-12 12:23 paras.chawla Exp $$
 * <p>
 * Input: matrix = [
                     * [0,1,2,0],
                     * [3,4,5,2],
                     * [1,3,1,5]
                     *
                   ]
 * <p>
 * Output: [
                     * [0,0,0,0],
                     * [0,4,5,0],
                     * [0,3,1,0]
 *         ]
 * https://www.youtube.com/watch?v=1KnLIAvTxjQ
 *
 * Intuition - Use first row and first col as dummy row and col to store values...No need to create 2 seperate dummy arrays
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {

        // first pass to know position of zeroes in 2d matrix
        Set<Pair> set = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == 0) { set.add(new Pair(i, j)); }
            }
        }

        // 2nd pass, if current cordinates exist in set, then mark it 0 - row and col wise

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == 0 && set.contains(new Pair(i, j))) {

                    // make row 0
                    for (int k = 0; k < matrix[0].length; k++) {
                        matrix[i][k] = 0;
                    }

                    // make col 0
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[k][j] = 0;
                    }
                }
            }
        }

        //System.out.println(matrix);
    }

    public void setZeroes2(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // BEST Solution - What is Expected
    // O(1)
    // Space Complexity O(1) , without using any dummy row or dummy col
    public void setZeroesSol3(int[][] matrix) {

        // 1. Base condition
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length, n = matrix[0].length;
        boolean zeroRow=false, zeroCol=false;

        // 2. traverse row and check if any row element is 0 , we need to make first row as 0 based on that
        for (int col = 0; col < n; col++) {
            if (matrix[0][col] == 0) {
                zeroRow = true;
                break;
            }
        }

        for (int row = 0; row < m; row++) {
            if (matrix[row][0] == 0) {
                zeroCol = true;
                break;
            }
        }

        // 3. Using first row and first col as dummy row and col to mark as 0 in case any matrix[i][j]=0
        // traversing from second row and second col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][j]==0){
                    // setting row i , first cell as 0
                    matrix[i][0]=0;

                    // setting col j, first cell as 0
                    matrix[0][j]=0;
                }
            }
        }

        // 4. Traverse again and setting all elements as 0 based on first row and first col values
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }

        // 5. check if zeroRow=true, mark first row as 0
        if(zeroRow){
            for(int i=0;i<n;i++){
                matrix[0][i]=0;
            }
        }

        // 6. check if zeroCol=true, mark first col as 0
        if(zeroCol){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }

    }

    public static void main(String[] args) {
        setZeroes(new int[][] {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
    }

}