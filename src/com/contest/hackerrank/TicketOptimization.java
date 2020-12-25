/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.hackerrank;

import com.problemsolving.genericProgramming.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paras.chawla
 * @version $Id: TicketOptimization.java, v 0.1 2020-12-11 10:01 paras.chawla Exp $$
 */

// Time taken by Alex to purchase all tickets needed as per given strategy
// https://stackoverflow.com/questions/43950000/hackerrank-buying-show-tickets-optimization
public class TicketOptimization {

    // [2,6,3,4,5] position = 2
    //      ^
    public static long waitingTime(int[] ticketsNeeded, int position) {
        Queue<Pair<Integer, Boolean>> queue = new LinkedList<>();

        for (int i = 0; i < ticketsNeeded.length; i++) {
            queue.add(new Pair(ticketsNeeded[i], i == position));
        }

        int timeTaken = 0;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            timeTaken++;
            if ((Integer) pair.getKey() == 1 && (Boolean) pair.getValue() == true) {
                break;
            } else if ((Integer) pair.getKey() > 1) {
                queue.add(new Pair((Integer) pair.getKey() - 1, pair.getValue()));
            }
        }
        return timeTaken;
    }

    static long waitingTime2(int[] tickets, int p) {
        long noOfIterations = 0;
        int ticketBeingProcessed = 0;
        int numberOfParticipantsInLine = tickets.length;
        if (numberOfParticipantsInLine > p) {
            while (tickets[p] != 0) {
                // The person has already got his ticket and exited the line, just go to the next person, dont increase number of
                // iterations because it took no time
                if (tickets[ticketBeingProcessed] != 0) {
                    // ticket being processed got one ticket
                    tickets[ticketBeingProcessed] = tickets[ticketBeingProcessed] - 1;
                    // if we have reached the end of the line
                    if (ticketBeingProcessed == numberOfParticipantsInLine - 1) { ticketBeingProcessed = 0; } else {
                        ticketBeingProcessed++;
                    }
                    noOfIterations++;
                } else {
                    if (ticketBeingProcessed == numberOfParticipantsInLine - 1) { ticketBeingProcessed = 0; } else {
                        ticketBeingProcessed++;
                    }
                }
            }
        }
        return noOfIterations;
    }

    public static void main(String[] args) {
        System.out.println(waitingTime2(new int[] {2, 6, 3, 4, 5}, 2));
        //System.out.println(waitingTime2(new int[]{1,2,5},1));
    }

}