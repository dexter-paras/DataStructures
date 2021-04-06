/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: CountNegative.java, v 0.1 2021-03-20 3:18 PM paras.chawla Exp $$
 */
public class CountNegative {

    public int countNegatives(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;

        for (int i = 0; i < row; i++) {
            count += countNegative(grid[i], 0, col - 1);
        }

        return count;
    }

    // Find index from where negative elements are starting
    private int countNegative(int[] grid, int low, int high) {

        int mid = 0;

        // starting element is negative
        if (grid[low] < 0) {
            return grid.length;
        }

        if (grid[high] >= 0) {
            return 0;
        }

        while (low < high) {

            mid = low + (high - low) / 2;
            int element = grid[mid];

            // positive, move right
            if (element >= 0) {

                // returning index of element with first negative element
                if (mid + 1 < grid.length && grid[mid + 1] < 0) {
                    return grid.length - mid - 1;
                }
                low = mid + 1;
            }
            // negative, move left
            else {
                // check if previous element is positive, if yes return current index
                if (mid - 1 >= 0 && grid[mid - 1] >= 0) {
                    return grid.length - mid;
                }
                high = mid - 1;
            }
        }

        return grid.length - mid;
    }

    // Improvement - index with first negative value coming from row i is high of binary search for next row
    // https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/discuss/512165/Java-binary-search-beats-100-explained
    public int countNegativesSol2(int[][] grid) {

        int rows = grid.length, cols = grid[0].length;
        int res = 0, lastNeg = cols - 1;

        for (int row = 0; row < rows; row++) {
            //check edge cases - if first element is < 0 - all elements in row are negative
            if (grid[row][0] < 0) {
                res += cols;
                continue;
            }
            //if last element is positive - it means there are no negative numbers in a row
            if (grid[row][cols - 1] > 0) { continue; }
            //there is a mix of negative and positive ones, need to find the border. starting
            //binary search
            int l = 0, r = lastNeg;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (grid[row][m] < 0) {
                    r = m - 1;
                } else { l = m + 1; }
            }
            //l points to the first negative element, which means cols - l is a number of
            //such elements
            res += (cols - l);
            lastNeg = l;
        }
        return res;
    }

    // Approach 3 - Tricky
    public int countNegativesSol3(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0) {

            // if curr element is negative, count curr and below elements in result
            if (grid[i][j] < 0) {
                res += m - i;
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CountNegative obj = new CountNegative();

        int[] grid = new int[] {14, 10, 7, 6, 5, 4, 3, 2, 1, -13, -16, -21, -25, -30};
        int[] grid2 = new int[] {14, 10, 1, -1, -2, -3, -4, -7, -9, -13, -16, -21, -25, -30};

        int[][] gridd = new int[][] {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};

        //System.out.println(obj.countNegative(grid, 0, grid.length - 1));
        //System.out.println(obj.countNegative(grid2, 0, grid2.length - 1));

        //System.out.println(obj.countNegatives(gridd));
        System.out.println(obj.countNegativesSol3(gridd));
    }
}