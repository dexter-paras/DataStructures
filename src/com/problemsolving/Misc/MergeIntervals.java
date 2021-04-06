/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: MergeIntervals.java, v 0.1 2020-07-28 11:14 paras.chawla Exp $$
 */
public class MergeIntervals {

    //Input: [[1,3],[2,6],[8,10],[15,18]]
    //Output: [[1,6],[8,10],[15,18]]
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort input based on start
        List<Pairr> list = new ArrayList<>();
        List<Pairr> result = new ArrayList<>();

        // Fill startTime and endTIme in to List
        for (int[] interval : intervals) {
            list.add(new Pairr(interval[0], interval[1]));
        }

        // sort list based on start time of intervals
        list.sort(Comparator.comparingInt(Pairr::getStartTime));
        Pairr previous = list.get(0);
        result.add(previous);

        for (int i = 1; i < list.size(); i++) {
            Pairr current = list.get(i);
            if (current.startTime <= previous.endTime) {
                if (current.endTime >= previous.endTime) {
                    Pairr newNode = new Pairr(previous.startTime, current.endTime);
                    result.add(newNode);
                    previous = newNode;
                } else {
                    result.add(previous);
                }
            } else {
                result.add(current);
                previous = current;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals.length <= 1) { return intervals; }

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        // 0- start , 1 - end
        // keep extending end point of newInterval till newIntervalEnd(newInterval[1] >= currStart(currInterval[0])
        for (int[] currInterval : intervals) {
            if (newInterval[1] >= currInterval[0]) // Overlapping intervals, move the end if needed
            {
                newInterval[1] = Math.max(newInterval[1], currInterval[1]);
            } else {                             // Disjoint intervals, add the new currInterval to the list
                newInterval = currInterval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    // Best Approach
    // TC O(nlogn)
    // Intuition - Its not possible to use same intervals and keep updating.. For new merged answer, use new list
    public int[][] mergeApproach3(int[][] intervals) {
        if (intervals.length <= 1) { return intervals; }

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        LinkedList<int[]> merged = new LinkedList<>();

        for (int[] currInterval : intervals) {

            // non-overlapping, just add current Interval
            if(merged.isEmpty() || merged.getLast()[1]< currInterval[1]){
                merged.add(currInterval);
            }
            // currInterval is overlapping with last merged interval
            else{
                merged.getLast()[1]= Math.max(merged.getLast()[1],currInterval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }


    public static void main(String[] args) {
        new MergeIntervals().merge2(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        new MergeIntervals().merge2(new int[][] {{1, 4}, {5, 6}});
        new MergeIntervals().merge2(new int[][] {{1, 4}, {4, 5}});
    }
}

class Pairr {

    int startTime;
    int endTime;

    public Pairr(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}