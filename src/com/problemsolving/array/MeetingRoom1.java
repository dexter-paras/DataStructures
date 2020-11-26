/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: MeetingRoom1.java, v 0.1 2020-05-11 22:20 paras.chawla Exp $$
 */
public class MeetingRoom1 {

    public boolean canAttendMeetings(int[][] intervals) {

        List<Pair> list = new ArrayList<>();
        // Fill startTime and endTIme in to List
        for (int[] interval : intervals) {
            list.add(new Pair(interval[0], interval[1]));
        }

        // sort list based on start time of meeting
        list.sort(Comparator.comparingInt(Pair::getStartTime));

        for (int i = 1; i < list.size(); i++) {
            Pair previous = list.get(i - 1);
            Pair current = list.get(i);

            if (current.getStartTime() < previous.getEndTime()) {
                return false;
            }
        }

        /*  Approach 2 - Using meeting overlapping
            Checking if meetings are overlapping
        for (int i = 0; i < list.size() - 1; i++) {
            Pair previous = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Pair current = list.get(j);

                if (!(current.getStartTime() >= previous.getEndTime() || current.getEndTime() <= previous.getStartTime())) {
                    return false;
                }
            }
        }*/
        return true;

    }

    public boolean canAttendMeetings2(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }

        // Sort based on start time
        // [0,30],[5,10],[15,20]
        Arrays.sort(intervals,((int[] a, int[] b) -> a[0]- b[0]));

        // Can Attend Meeting when start time of current meeting should be greater than end time of last meeting
        for(int i=1;i< intervals.length;i++){
            if(intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRoom1().canAttendMeetings(new int[][] {{13, 15}, {1, 13}, {6, 9}}));
        //System.out.println(new MeetingRoom1().canAttendMeetings(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
    }
}

class Pair {

    int startTime;
    int endTime;

    public Pair(int startTime, int endTime) {
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