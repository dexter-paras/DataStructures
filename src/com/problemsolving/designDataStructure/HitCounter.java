/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

/**
 * @author paras.chawla
 * @version $Id: HitCounter.java, v 0.1 2020-11-30 19:48 paras.chawla Exp $$
 * Basic ideal is using buckets. 1 bucket for every second because
 * we only need to keep the recent hits info for 300 seconds.
 * hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
 * If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
 * hit(1) -> hit2() -> hit(3) hits [0,1,1,1,]
 * https://leetcode.com/problems/design-hit-counter/discuss/83483/Super-easy-design-O(1)-hit()-O(s)-getHits()-no-fancy-data-structure-is-needed!
 * https://leetcode.com/problems/design-hit-counter/discuss/83490/Java-Circular-Array-Solution-with-a-really-detailed-explanation-post.
 * https://nuttynanaus.wordpress.com/2014/03/09/software-engineer-interview-questions/
 */
public class HitCounter {

    // storing actual timestamp in times[]
    private int[] times;

    // storing count associated with timestamp in times[]
    private int[] hits;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     * hit(1) -> index =1
     * if(times[1]!=1) -> if(0!=1) -> times[1]=1 && hits[1]=1
     *
     * hit(1) -> index =1
     * if(times[1]!=1) -> if(1!=1) -> times[1]=1 && hits[1]=2
     *
     * hit(2) -> index =2
     * if(times[2]!=2) -> if(2!=2) -> times[2]=2 && hits[2]=1
     *
     * hit(301) -> index =1
     * if(times[1]!=301) -> if(1!=301) -> times[1]=301 && hits[1]=1
     * If there are concurrent 5 hits hits(5) 5 times, to make it thread-safe use Lock Mechanism
     */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if(times[index]!= timestamp){
            times[index]=timestamp;
            hits[index]=1;
        }else{
            hits[index]++;
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     * gethits(1) can't come after hit(302)
     */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        HitCounter obj = new HitCounter();
        obj.hit(1);
        obj.hit(1);
        obj.hit(2);
        obj.hit(3);
        System.out.println(obj.getHits(4));
        obj.hit(300);
        obj.hit(301);
        // times[] = [300,301,2,3,.....]
        System.out.println(obj.getHits(303));
        obj.hit(302);
        System.out.println(obj.getHits(305));
        obj.hit(303);
        System.out.println(obj.getHits(305));
        System.out.println(obj.getHits(315));
    }

}