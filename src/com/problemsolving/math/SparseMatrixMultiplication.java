/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.math;

/**
 * @author paras.chawla
 * @version $Id: SparseMatrixMultiplication.java, v 0.1 2021-03-12 12:53 AM paras.chawla Exp $$ A= 2*3 B 3*3 => C 2*3 /* A = [ [ 1, 0, 0],
 * [-1, 0, 3] ]
 * <p>
 * B = [ [ 7, 0, 0 ], [ 0, 0, 0 ], [ 0, 0, 1 ] ]
 * <p>
 * Output: |  1 0 0 |   | 7 0 0 |   |  7 0 0 | AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 | | 0 0 1 |
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76151/54ms-Detailed-Summary-of-Easiest-JAVA-solutions-Beating-99.9
 *
 * Further improvement - Efficient way of storing Matrix in another DS?
 * https://en.wikipedia.org/wiki/Sparse_matrix#Dictionary_of_keys_(DOK)
 */

public class SparseMatrixMultiplication {

    // First row * first col = first value
    // The Key is: if we calculate it this way, we finishing calculating the final value for the result matrix at once
    public int[][] multiply(int[][] A, int[][] B) {

        int m1r = A.length, m1c = A[0].length, m2r = B.length, m2c = B[0].length;
        int[][] m3 = new int[m1r][m2c];

        // Intution - C[ i ][ j ] = A[ i ][0]*B[0][j] + A[i][1]*B[1][j] + A[i][2]*B[2][j] + ... A[i][K]*B[K][j]
        //( which is the sum of each multiplication of corresponding K values from row i of A and K values from column j of B )

        // row
        for (int i = 0; i < m1r; i++) {
            // col
            for (int j = 0; j < m2c; j++) {
                // num of times
                for (int k = 0; k < m1c; k++) {
                    m3[i][j] = A[i][k] * B[k][j];
                }
            }
        }
        return m3;
    }

    /*
    *
    * A
    * [ 0 2 3
    *   1 1 2
    *   2 1 2
    * ]
    *
    *  B
    *  [ 1 2
    *    2 1
    *    1 1
    *  ]
    *
    * c[
    * 1*1        1*2
    *  +          +
    * 2*2        2*2
    *  +          +
    * 3*1        3*1
    * ]
    *
    *  The smart solution, the key part of smart solution is that: it does not calculate the final result at once, and it takes each value from A, and
    *  calculate and partial sum and accumulate it into the final spot:
    * */
    public int[][] multiplySmart(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) { C[i][j] += A[i][k] * B[k][j]; }
                    }
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        SparseMatrixMultiplication obj = new SparseMatrixMultiplication();
        int[][] A = new int[][] {{1, 2, 3}, {1, 1, 2}, {2, 1, 2}};
        int[][] B = new int[][] {{1, 2}, {2, 1}, {1, 1}};

        System.out.println(obj.multiplySmart(A,B));
    }

}