/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author paras.chawla
 * @version $Id: HitCounter.java, v 0.1 2020-11-30 19:48 paras.chawla Exp $$
 * Using AtomicIntegerArray time; AtomicIntegerArray hit;
 * this way will not prevent concurrency issues since hit and get are not thread safe themselves. Two threads enter the if statement in hit() at the
 * same time. Need to make hit and get thread-safe using read and write lock
 * <p>
 * AtomicInteger will be used as an atomic counter which guarantees arithmetic operations can be done concurrently. However, these methods
 * themselves are not thread-safe. For example, At the same time, we have two counter.hit(1). Ideally, getHits(2) needs return 2. However,
 * if these two operations "counter.hit(1)" meet the if (time[index] != timestamp) at the same time, they will say "hey this statement is
 * true" and then both of them will execute
 * <p>
 * time[index] = timestamp; hits[index] = 1;
 */
public class HitCounterThreadSafe {

    private final ReentrantReadWriteLock rwl       = new ReentrantReadWriteLock();
    private final Lock   readLock  = rwl.readLock();
    private final Lock   writeLock = rwl.writeLock();

    // storing actual timestamp in times[]
    private int[] times;

    // storing count associated with timestamp in times[]
    private int[] hits;

    /**
     * Initialize your data structure here.
     */
    public HitCounterThreadSafe() {
        times = new int[300];
        hits = new int[300];
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity). hit(1) -> index =1 if(times[1]!=1) -> if(0!=1) -> times[1]=1 &&
     * hits[1]=1
     *  <p>
     *  hit(1) -> index =1 if(times[1]!=1) -> if(1!=1) -> times[1]=1 && hits[1]=2
     *  <p>
     *  hit(2) -> index =2 if(times[2]!=2) -> if(2!=2) -> times[2]=2 && hits[2]=1
     *  <p>
     *  hit(301) -> index =1 if(times[1]!=301) -> if(1!=301) -> times[1]=301 && hits[1]=1 If there are concurrent 5 hits
     *  hits(5) 5 times, to make it thread-safe use Lock Mechanism
     */
    public void hit(int timestamp) {
        writeLock.lock();
        try {
            int index = timestamp % 300;
            // not in the same 5 minute window
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity). gethits(1) can't come after hit(302)
     */
    public int getHits(int timestamp) {
        int total = 0;
        readLock.lock();
        try {
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
        } finally {
            readLock.unlock();
        }
        return total;
    }

    public static void main(String[] args) {
        HitCounterThreadSafe obj = new HitCounterThreadSafe();
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