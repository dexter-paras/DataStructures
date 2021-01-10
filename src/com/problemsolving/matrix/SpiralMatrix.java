/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: SpiralMatrix.java, v 0.1 2021-01-06 7:16 PM paras.chawla Exp $$
 */
public class SpiralMatrix {

    List<Integer> printSpiralMatrix(int[][] matrix) {

        int m =matrix.length;
        int n = matrix[0].length;

        int T = 0, B = m - 1, L = 0, R = n - 1;
        int dir = 0;

        List<Integer> result = new ArrayList<>();

        while (T <= B && L <= R) {

            if (dir == 0) {
                for (int i = L; i <= R; i++) {
                    result.add(matrix[T][i]);
                }
                T++;
            }

            if (dir == 1) {
                for (int i = T; i <= B; i++) {
                    result.add(matrix[i][R]);
                }
                R--;
            }

            if (dir == 2) {
                for (int i = R; i >= L; i--) {
                    result.add(matrix[B][i]);
                }
                B--;
            }

            if (dir == 3) {
                for (int i = B; i >= T; i--) {
                    result.add(matrix[i][L]);
                }
                L++;
            }
            dir= (dir+1)%4;
        }

        return result;
    }

}