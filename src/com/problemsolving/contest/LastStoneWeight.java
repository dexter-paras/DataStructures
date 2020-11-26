/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author paras.chawla
 * @version $Id: AnagramDifference.java, v 0.1 2020-04-13 09:43 paras.chawla Exp $$
 */
public class LastStoneWeight {

    //[2,7,4,1,8,1]
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }

        List<Integer> list = Arrays.stream(stones).boxed().collect(Collectors.toList());
        while (!list.isEmpty()) {
            int firstMax = Integer.MIN_VALUE;
            int secondMax = Integer.MIN_VALUE;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > firstMax) {
                    secondMax = firstMax;
                    firstMax = list.get(i);
                } else if (list.get(i) > secondMax) {
                    secondMax = list.get(i);
                }
            }

            if (firstMax != secondMax) {
                list.add(firstMax - secondMax);
            }
            list.remove(Integer.valueOf(firstMax));
            list.remove(Integer.valueOf(secondMax));
            if (list.size() == 1) {
                return list.get(0);
            }
        }
        return 0;
    }

    public int lastStoneWeight2(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < stones.length; i++) {
            queue.add(stones[i]);
        }

        while (queue.size()>1) {
            int firstMax = queue.poll();
            int secondMax = queue.poll();
            if (firstMax != secondMax) {
                queue.add(firstMax - secondMax);
            }
        }
        return queue.isEmpty()?0:queue.remove();
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWeight().lastStoneWeight(new int[] {2, 7, 4, 1, 8, 1}));
        System.out.println(new LastStoneWeight().lastStoneWeight2(new int[] {2, 7, 4, 1, 8, 1}));
    }
}