/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.hardlc;

import java.util.LinkedList;

/**
 * @author paras.chawla
 * @version $Id: ConsecutiveNumberSum.java, v 0.1 2021-02-09 1:32 PM paras.chawla Exp $$
 * https://www.youtube.com/watch?v=oKOCYZd4m7E&t=325s
 */
public class ConsecutiveNumberSum {

    // N =15
    public int consecutiveNumbersSum(int N) {
        if (N == 1) { return 1; }
        LinkedList<Integer> queue = new LinkedList<>();

        int count = 1;

        int i = 1;
        int sum = 0;

        // keep adding into right side of queue
        while (sum < N) {
            sum += i;
            queue.add(i++);
        }

        while (i <= (N + 3) / 2) {
            if (sum == N) {
                count++;
                int removedE = queue.pollFirst();
                sum -= removedE;
            } else if (sum < N) {
                sum += i;
                queue.add(i++);
            } else {
                int removedE = queue.pollFirst();
                sum -= removedE;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ConsecutiveNumberSum obj = new ConsecutiveNumberSum();
        System.out.println(obj.consecutiveNumbersSum(1));
        System.out.println(obj.consecutiveNumbersSum(2));
        //System.out.println(obj.consecutiveNumbersSum(15));
    }

}