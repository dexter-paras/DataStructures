/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author paras.chawla
 * @version $Id: LeaderBoard.java, v 0.1 2020-12-17 10:46 paras.chawla Exp $$ http://www.michaelpollmeier
 * .com/selecting-top-k-items-from-a-list-efficiently-in-java-groovy
 */
public class LeaderBoard {

    //K - PlayerId, V - Score
    Map<Integer, Integer> scoreMap;


    public LeaderBoard() {
        scoreMap = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        scoreMap.put(playerId, scoreMap.getOrDefault(playerId, 0) + score);
    }

    // 1000 Elements, Create min-Heap having K entries
    // 1,2,3,4,10,9,8,7,6,5  -- Top(3) -- 10,9,8
    public int top(int K) {

        int sum = 0;

        PriorityQueue<Integer> topScoreMinHeap=new PriorityQueue<>();


        // Min-Heap left with top K elements after this loop
        for (Map.Entry<Integer, Integer> entry : scoreMap.entrySet()) {

            // O(K) to construct the initial heap
            topScoreMinHeap.offer(entry.getValue());

            // For the rest of the NORTH-K elements, we perform the extractMin and
            // add operations on the heap each of which take (logK) time.
            if (topScoreMinHeap.size() > K) {
                topScoreMinHeap.poll();
            }

        }

        // sum K entries in Heap
        while (!topScoreMinHeap.isEmpty()) {
            sum += topScoreMinHeap.poll();
        }

        return sum;
    }

    public void reset(int playerId) {
        // remove from map
        scoreMap.remove(playerId);
    }

    public static void main(String[] args) {
        LeaderBoard leaderboard = new LeaderBoard();
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(1));           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(3));           // returns 141 = 51 + 51 + 39;
    }

}