/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: LuckyNumber.java, v 0.1 2020-03-15 08:32 paras.chawla Exp $$
 */
public class LuckyNumber {

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> luckyNumbers = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            int minIndex = min(row, matrix);
            if (checkLucky(row, minIndex, matrix)) {
                luckyNumbers.add(matrix[row][minIndex]);
            }
        }
        return luckyNumbers;
    }

    // check if minIndex is max element in the column as well //row=2,index=3
    private boolean checkLucky(int row, int col, int[][] matrix) {
        int max = matrix[row][col]; //12
        int i;
        for (i = 0; i < matrix.length; i++) {
            if (matrix[i][col] > max) {
                break;
            }
        }
        if (i == matrix.length) {
            return true;
        } else {
            return false;
        }
    }

    private int min(int row, int[][] matrix) {
        int minIndex = 0; //15 , row=2
        for (int i = 1; i < matrix[row].length; i++) {
            if (matrix[row][i] < matrix[row][minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        LuckyNumber obj = new LuckyNumber();
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        int[][] matrix2 = {{1, 10, 4,2}, {9, 3, 8,7}, {15, 16, 17,12}};
        int[][] matrix3 = {{7, 8}, {1,2}};
        System.out.println(obj.luckyNumbers(matrix));
        System.out.println(obj.luckyNumbers(matrix2));
        System.out.println(obj.luckyNumbers(matrix3));
    }
}