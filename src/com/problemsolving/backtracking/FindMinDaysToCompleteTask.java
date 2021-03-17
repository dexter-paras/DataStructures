/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: FindMinDaysToCompleteTask.java, v 0.1 2021-03-09 1:54 PM paras.chawla Exp $$
 */

// Find minimum number of days to complete n task from k reportees

public class FindMinDaysToCompleteTask {


    static List<List<List<Integer>>> subLists = new ArrayList<>();

    public static void main(String[] args) {
        int[] task = new int[] {4, 2, 1, 3, 1, 2};
        int k = 3;

        List<List<Integer>> list = new ArrayList<>();
        //int numTasks = task.length;
        //helper(task, k, 0, list);
        System.out.println(findMinTime(k,1,task,task.length));
        //System.out.println(subLists);
    }

    // [4,2,1,3,1,2]
    //  ^
    private static void helper(int[] task, int k, int idx, List<List<Integer>> list) {

        // base condition
        if (idx == task.length) {
            subLists.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < task.length; i++) {
            if (k > 0) {
                // choose
                //list.add(task[idx]);
                //recurse
                helper(task, k - 1, idx + 1, list);
                // unchoose
                list.remove(list.size() - 1);
            }
        }
    }

    // Utility function to get
    // maximum element in job[0..n-1]
    static int getMax(int arr[], int n)
    {
        int result = arr[0];
        for (int i=1; i<n; i++)
            if (arr[i] > result)
                result = arr[i];
        return result;
    }

    // Returns true if it is possible to finish jobs[]
    // within given time 'time'
    // jobs , time =6
    static boolean isPossible(int time, int K,
                              int job[], int n)
    {
        // cnt is count of current
        // assignees required for jobs
        int cnt = 1;

        // time assigned to current assignee
        int curr_time = 0;

        for (int i = 0; i < n;)
        {
            // If time assigned to current assignee
            // exceeds max, increment count of assignees.
            if (curr_time + job[i] > time) {
                curr_time = 0;
                cnt++;
            }

            // Else add time of job to current
            // time and move to next job.
            else
            {
                curr_time += job[i];
                i++;
            }
        }

        // Returns true if count
        // is smaller than k
        return (cnt <= K);
    }

    // Returns minimum time required to
    // finish given array of jobs
    // k --> number of assignees =3
    // T --> Time required by every assignee to finish 1 unit
    // n --> Number of jobs
    // job[] =[4,2,1,3,1,2]
    // start =0; end = 13
    static int findMinTime(int K, int T, int job[], int n)
    {
        // Set start and end for binary search
        // end provides an upper limit on time
        int end = 0, start = 0;
        for (int i = 0; i < n; ++i)
            end += job[i];

        // Initialize answer
        int ans = end;

        // Find the job that takes maximum time
        int job_max = getMax(job, n);//4

        // Do binary search for
        // minimum feasible time
        while (start <= end)//0<=13
        {
            int mid = (start + end) / 2; //6

            // If it is possible to finish jobs in mid time
            if (mid >= job_max && isPossible(mid, K, job, n))//6>=4
            {
                // Update answer
                ans = Math.min(ans, mid);

                end = mid - 1;
            }

            else
                start = mid + 1;
        }

        return (ans * T);
    }

}