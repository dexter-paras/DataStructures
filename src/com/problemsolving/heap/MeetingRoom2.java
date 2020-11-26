/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: MeetingRoom2.java, v 0.1 2020-05-14 19:09 paras.chawla Exp $$
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of
 * conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]] Output: 2 Example 2:
 * <p>
 * Input: [[7,10],[2,4]] Output: 1
 */
public class MeetingRoom2 {

    public int minMeetingRooms(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }

        List<Pair> list = new ArrayList<>();

        // Fill startTime and endTIme in to List
        for (int[] interval : intervals) {
            list.add(new Pair(interval[0], interval[1]));
        }

        // sort list based on start time of meeting
        list.sort(Comparator.comparingInt(Pair::getStartTime));

        List<Room> listRooms = new ArrayList<>();
        listRooms.add(new Room(1, list.get(0).getEndTime()));
        for (int i = 1; i < list.size(); i++) {
            Pair current = list.get(i);

            if (newRoomNeeded(current, listRooms)) {
                int lastRoomNumber = listRooms.get(listRooms.size() - 1).getRoomNumber();
                listRooms.add(new Room(lastRoomNumber + 1, current.getEndTime()));
            }
        }
        return listRooms.get(listRooms.size() - 1).getRoomNumber();
    }

    public int minMeetingRooms2(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }

        List<Pair> list = new ArrayList<>();

        // Fill startTime and endTIme in to List
        for (int[] interval : intervals) {
            list.add(new Pair(interval[0], interval[1]));
        }

        // sort list based on start time of meeting
        list.sort(Comparator.comparingInt(Pair::getStartTime));

        // Create a min-heap storing least end time in order
        // we'll get to know using heap which room gets free at the earliest
        // if all rooms are in use, we keep adding new rooms in heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(list.get(0).getEndTime());

        for (int i = 1; i < list.size(); i++) {
            Pair current = list.get(i);
            if (current.getStartTime() >= heap.peek()) {
                heap.poll();
            }
            heap.add(current.getEndTime());
        }
        // number of rooms required = size of heap
        return heap.size();
    }

    // Approach -3 No need to create any different class
    public int minMeetingRooms3(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }

        // sort list based on start time of meeting
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });
        Arrays.sort(intervals, ((int[] a, int[] b) -> a[0] - b[0]));

        // Create a min-heap storing least end time in order
        // we'll get to know using heap which room gets free at the earliest
        // if all rooms are in use, we keep adding new rooms in heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(intervals.length);
        heap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // if start time of a meeting is greater than end time of a room which was earlier being used
            // we should remove earlier booking and add current booking
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            // if start time of a meeting is less than end time of a room which is currently in used
            // OVERLAPPING, we should create new room
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    private boolean newRoomNeeded(Pair current, List<Room> listRooms) {
        for (Room room : listRooms) {
            if (room.getOccupiedTill() <= current.getStartTime()) {
                room.setOccupiedTill(current.getEndTime());
                return false;
            }
        }
        return true;
    }
}

class Room {

    int roomNumber;

    int occupiedTill;

    public Room(int roomNumber, int occupiedTill) {
        this.roomNumber = roomNumber;
        this.occupiedTill = occupiedTill;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getOccupiedTill() {
        return occupiedTill;
    }

    public void setOccupiedTill(int occupiedTill) {
        this.occupiedTill = occupiedTill;
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