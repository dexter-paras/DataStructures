/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: AverageTop5.java, v 0.1 2020-09-04 19:30 paras.chawla Exp $$
 *
 * Intuition --> Need top elements to store hence take min heap so as to remove min elements from heap
 */
public class AverageTop5 {

    public int[][] highFive(int[][] items) {

        LinkedHashMap<Integer, PriorityQueue> map = new LinkedHashMap<>();
        PriorityQueue<Integer> queue = null;

        for (int i = 0; i < items.length; i++) {
            if (!map.containsKey(items[i][0])) {
                //queue = new PriorityQueue(5, Collections.reverseOrder());
                queue = new PriorityQueue(5);
                queue.offer(items[i][1]);
                map.put(items[i][0], queue);
            } else {
                queue = map.get(items[i][0]);
                queue.offer(items[i][1]);
                if (queue.size() > 5) {
                    queue.remove();// no need to store more than 5 elements
                }
                map.put(items[i][0], queue);

            }
        }

        int[][] result = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, PriorityQueue> entry : map.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = average(entry.getValue());
            i++;
        }
        return result;
    }

    private int average(PriorityQueue<Integer> queue) {
        int sum = 0;
        int size;

        if (queue.size() > 5) {
            size = 5;
        } else {
            size = queue.size();
        }
        int count = size;
        while (queue != null && count-- > 0) {
            sum += queue.poll();
        }
        return sum / size;
    }

    public static void main(String[] args) {
        //int[][] input = new int[][] {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        int[][] input = new int[][] {{1, 84}, {1, 72}, {1, 47}, {1, 43}, {1, 78}, {1, 79}, {1, 4}, {1, 23}, {2, 88}, {2, 79}, {3, 75},
                {3, 80}, {3, 38}, {3, 73}, {3, 4}};
        new AverageTop5().highFive(input);
    }

}

