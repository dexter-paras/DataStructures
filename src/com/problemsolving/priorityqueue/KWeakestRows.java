/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: KWeakestRows.java, v 0.1 2021-02-16 9:54 AM paras.chawla Exp $$
 */
public class KWeakestRows {

    /*
 Input: mat =
            [[1,1,0,0,0],
             [1,1,1,1,0],
             [1,0,0,0,0],
             [1,1,0,0,0],
             [1,1,1,1,1]],
            k = 3
            Output: [2,0,3]
Explanation:
            The number of soldiers for each row is:
            row 0 -> 2
            row 1 -> 4
            row 2 -> 1
            row 3 -> 2
            row 4 -> 5
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
    *
    * */

    public int[] kWeakestRows(int[][] mat, int k) {

        /*        // 0th -> num of soldiers in row i, 1th -> row i index
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if  number of soldiers are not same
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                // return based on row index
                else {
                    return o1[1] - o2[1];
                }
            }
        });*/

        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (o1, o2) -> {
            // if  number of soldiers are not same
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            // return based on row index
            else {
                return o1[1] - o2[1];
            }
        });

        // Adding data into Pq only to its capacity
        for (int i = 0; i < mat.length; i++) {

            int[] row = mat[i];
            // count number of soldiers
            int count = countNumSoldiers(row);
            queue.add(new int[] {count, i});
        }

        // Polling top k weakest rows
        int[] result = new int[k];
        int idx = 0;
        while (k > idx) {
            result[idx++] = queue.poll()[1];
        }

        return result;
    }

    private int countNumSoldiers(int[] rows) {
        int count = 0;

        for (int i : rows) {
            if (i != 1) {
                break;
            }
            count++;
        }
        return count;
    }

    // Approach 2 - Subtle difference

    public int[] kWeakestRows2(int[][] mat, int k) {
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b)-> a[0]!=b[0] ? b[0]-a[0] : b[1]-a[1]);

        int i=0;
        for(int[] row : mat){
            pq.offer(new int[]{countOnes(row),i});
            i++;
            if(pq.size() > k){
                pq.poll();
            }
        }
        int[] ans = new int[k];
        while(k>0){
            ans[--k] = pq.poll()[1];
        }

        return ans;
    }

    // 1, 1, 0, 0, 0
    public int countOnes(int[] row){
        int lo=0;
        int hi=row.length;

        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(row[mid]==1){
                lo = mid+1;
            }
            else{
                hi = mid;
            }
        }
        return lo;
    }


    public static void main(String[] args) {
        KWeakestRows obj = new KWeakestRows();
        int[][] mat = new int[][] {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        //int[][] mat = new int[][] {{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}};

        System.out.println(obj.kWeakestRows2(mat, 3));
    }

}