/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part6_criticalsection;

import java.util.Random;

/**
 * @author paras.chawla
 * @version $Id: FireBallRunner.java, v 0.1 2020-09-01 22:22 paras.chawla Exp $$
 */

/* DEADLOCK
 Thread1 taking lock on Resource1 and Thread2 taking lock on Resource2
 Thread1 trying to take lock on Resource2 and Thread1 trying to take lock on Resource1

 Train1 locking RoadA and hence passing through Road1 and
 Train2 locking RoadB and hence passing through Road2

 Train1 trying to lock on RoadB as well so as to avoid collison,
 similarly Train2 trying to lock on RoadA so as to avoid collison.

DEADLOCK CONDITIONS:
1. Mutual Exclusion - ONly 1 thread can have exclusive access to a resource
2. Hold and Wait - At least only 1 thread is holding a resource and is waiting for another resource taken by another thread
3. Non-preemptive allocation -No thread can take another thread taken resource. A resource is released only after thread is done using it.
4. Circular wait - 1 thread waiting for resource taken by another thread

DEADLOCK InMemoryDataStructure
Enforcing a strict order on lock acquisition prevent deadlocks
 */

public class Main {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        trainAThread.setName("Train A");

        Thread trainBThread = new Thread(new TrainB(intersection));
        trainBThread.setName("Train B");
        trainAThread.start();
        trainBThread.start();
    }

}

class Intersection {

    Object roadA = new Object(); // Resource1
    Object roadB = new Object(); // Resource2

    // Train1 locking RoadA and then locking RoadB to avoid collison
    public void takeRoadA() {
        synchronized (roadA) {
            System.out.println("RoadA is locked by" + Thread.currentThread().getName());
            synchronized (roadB) {
                System.out.println("Train passing through A , has to lock RoadB as well" + Thread.currentThread().getName());
            }
        }
    }

    // Train2 locking RoadB and then locking RoadA to avoid collison
    public void takeRoadB() {
        synchronized (roadB) {
            System.out.println("RoadB is locked by " + Thread.currentThread().getName());

            synchronized (roadA) {
                System.out.println("Train passing through B , has to lock RoadA as well" + Thread.currentThread().getName());
            }
        }
    }
}

class TrainA implements Runnable {

    Intersection obj = new Intersection();// shared among all trains
    private Random random = new Random();//random train schedule

    public TrainA(Intersection obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true) {
            long sleepingTime = random.nextInt(5);// wait until train comes
            try {
                Thread.sleep(sleepingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.takeRoadA();// train passing RoadA
        }
    }
}

class TrainB implements Runnable {

    Intersection obj = new Intersection();// shared among all trains
    private Random random = new Random(); //random train schedule

    public TrainB(Intersection obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true) {
            long sleepingTime = random.nextInt(5);// wait until train comes
            try {
                Thread.sleep(sleepingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.takeRoadB();// train passing RoadB
        }
    }
}