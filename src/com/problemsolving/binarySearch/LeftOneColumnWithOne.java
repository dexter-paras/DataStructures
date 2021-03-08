/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: LeftOneColumnWithOne.java, v 0.1 2020-04-21 19:32 paras.chawla Exp $$
 */
public class LeftOneColumnWithOne {

    public int leftMostColumnWithOne(BinaryMatrixImpl binaryMatrix) {

        //Get Dimentions
        List<Integer> dimentions = binaryMatrix.dimensions();
        int row = dimentions.get(0);
        int col = dimentions.get(1);
        int minColumnIndex = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int columnIndex = binarySearch(binaryMatrix, 0, col - 1, i);
            minColumnIndex = Math.min(minColumnIndex, columnIndex);
        }
        return minColumnIndex == Integer.MAX_VALUE ? -1 : minColumnIndex;
    }

    private int binarySearch(BinaryMatrixImpl binaryMatrix, int low, int high, int row) {

        if (low >= high) {
            return Integer.MAX_VALUE;
        }
        int mid = low + (high - low) / 2;

        int temp = binaryMatrix.get(row, mid);
        if (mid < high && temp == 0 && binaryMatrix.get(row, mid + 1) == 1) {
            return mid + 1;
        } else if (mid > low && temp == 1 && binaryMatrix.get(row, mid - 1) == 0) {
            return mid;
        } else if (mid == low && temp == 1) {
            return mid;
        } else if (mid > low && temp == 1 && binaryMatrix.get(row, mid - 1) == 1) {
            return binarySearch(binaryMatrix, low, mid - 1, row);
        } else {
            return binarySearch(binaryMatrix, mid + 1, high, row);
        }
    }

    public int leftMostColumnWithOneEasy(BinaryMatrixImpl binaryMatrix) {

        //Get Dimentions
        List<Integer> dimentions = binaryMatrix.dimensions();

        int lastrow = dimentions.get(0) - 1;
        int col = dimentions.get(1) - 1;

        int row = 0;
        int result = -1;
        while (row <= lastrow && col >= 0) {
            if (binaryMatrix.get(row, col) == 0) {
                row++;
            } else {
                result = col--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LeftOneColumnWithOne().leftMostColumnWithOneEasy(new BinaryMatrixImpl(new int[][] {
                {0, 0, 0,0, 0}, {0, 0, 1, 1,1}, {0,0,0,0,1},{0,1,1,1, 1}})));
        System.out.println(new LeftOneColumnWithOne().leftMostColumnWithOneEasy(new BinaryMatrixImpl(new int[][] {{0, 0}, {0, 1}})));
        System.out.println(new LeftOneColumnWithOne().leftMostColumnWithOneEasy(new BinaryMatrixImpl(new int[][] {{0, 0}, {0, 0}})));
    }

}

interface BinaryMatrix {
    public int get(int x, int y);

    public List<Integer> dimensions();
}

class BinaryMatrixImpl implements BinaryMatrix {

    int[][] matrix = null;

    public BinaryMatrixImpl() {
    }

    public BinaryMatrixImpl(int[][] matrix) {
        this.matrix = matrix;
    }

    public int get(int x, int y) {
        return matrix[x][y];
    }

    public List<Integer> dimensions() {
        List<Integer> list = new ArrayList<>();
        list.add(matrix.length);
        list.add(matrix[0].length);
        return list;
    }
}