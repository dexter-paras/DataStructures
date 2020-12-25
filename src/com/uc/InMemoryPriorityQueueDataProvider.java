/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.uc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: InMemoryPriorityQueueDataProvider.java, v 0.1 2020-12-07 18:33 paras.chawla Exp $$
 * <p>
 * add(1, 20) add(2, 30) Add(3, 10) Add(4, 15)
 * <p>
 * get(startValue = 20) => [1, 20], [2, 30]
 * <p>
 * delete(2) get(startValue = 20) => [1, 20]
 * <p>
 * [10,30,10]
 * <p>
 *
 * Min heap
 * 10|3  (n-1/2)
 * /    \
 * 15|4 20|1
 *       \
 *       30|2
 */
public class InMemoryPriorityQueueDataProvider implements DataProvider {

    private Map<Integer, Integer> map;
    private PriorityQueue<Pair>   minHeap;

    public InMemoryPriorityQueueDataProvider() {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.startValue - o2.startValue;
            }
        });
    }

    public void add(int jobId, int startValue) {
        map.put(jobId, startValue);
        minHeap.offer(new Pair(jobId, startValue));
    }

    public void delete(int jobId) {
        map.remove(jobId);
        for (int i = 0; i < minHeap.size(); i++) {
            if (minHeap.peek().jobId == jobId) {
                minHeap.poll();
                break;
            }
        }
    }

    public List<Pair> get(int startValue) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < minHeap.size(); i++) {
            if (minHeap.peek().startValue >= startValue) {
                list.add(minHeap.peek());
            }
        }
        return list;
    }

    public static void main(String[] args) {
        InMemoryPriorityQueueDataProvider obj = new InMemoryPriorityQueueDataProvider();
        obj.add(1, 20);
        obj.add(2, 30);
        obj.add(3, 10);
        obj.add(4, 15);

        System.out.println("Before Delete" +obj.map);
        System.out.println(obj.get(15));
        obj.delete(1);
        System.out.println("After delete"+obj.map);
    }

}

