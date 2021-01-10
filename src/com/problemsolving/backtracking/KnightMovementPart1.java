/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: KnightMovementPart1.java, v 0.1 2020-08-24 22:04 paras.chawla Exp $$
 */

/*
 https://www.techiedelight.com/chess-knight-problem-find-shortest-path-source-destination/
 Not an infinite Chess
 Source and destination  coordinates are already given

 Analysis: From 1 particular location, traversing to all 8 nearest locations and then from every individual location,
 traverse to 8 nearest locations and so on. .. ... till couldn't find destination coordinates

*/
public class KnightMovementPart1 {

    // Below arrays details all 8 possible movements
    // for a knight
    private static int[] row = {2, 2, -2, -2, 1, 1, -1, -1};
    private static int[] col = {-1, 1, 1, -1, 2, -2, 2, -2};

    public static void main(String[] args) {
        // Chess of NORTH*NORTH
        int N = 8;

        // Knight on 0,0 position
        Node src = new Node(0, 7);

        // destination cordinate
        Node dest = new Node(7, 0);

        System.out.println("Minimum number of steps required is" + minStepsToReachDest(src, dest, N));
    }

    private static int minStepsToReachDest(Node src, Node dest, int N) {

        // Need which nodes are already visited and which all aren't visited
        Set<Node> visited = new HashSet<>();

        // queue to add further directional nodes and enqueue first node
        Queue<Node> queue = new LinkedList<>();
        queue.add(src);

        // loop till queue is not empty
            while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            System.out.println("X:" + currNode.x + " Y:" + currNode.y + " Dist:" + currNode.dist);

            // nirvana state when currNode cordinates meet with dest
            if (currNode.x == dest.x && currNode.y == dest.y) {
                return currNode.dist;
            }

            // skip if location is already visited
            if (!visited.contains(currNode)) {
                visited.add(currNode);

                // check for all 8 possible directions & enqueue, traversing Breadth wise
                // Checking all +directions from src direction
                for (int i = 0; i < 8; i++) {
                    int newX = currNode.x + row[i];
                    int newY = currNode.y + col[i];
                    if (valid(newX, newY, N)) {
                        queue.add(new Node(newX, newY, currNode.dist + 1));
                    }
                }
            }
        }
        // return INFINITY if path is not possible
        return Integer.MAX_VALUE;
    }

    // Check if (x, y) is valid chess board coordinates
    // Note that a knight cannot go out of the chessboard
    private static boolean valid(int x, int y, int N) {
        if (x < 0 || y < 0 || x >= N || y >= N) { return false; }

        return true;
    }
}

// Node used in BFS
// Using Node as a key in Set i.e. in HashMap so would be good to implement equals and hashCode method
class Node {

    // x,y represent board coordinates
    int x;
    int y;

    // minimum distance from the source
    int dist;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Node node = (Node) o;
        if (x == node.x) { return false; }
        if (y == node.y) { return false; }
        return dist == node.dist;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + dist;
        return result;
    }
}