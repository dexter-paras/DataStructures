/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author paras.chawla
 * @version $Id: FindPathInMatrix.java, v 0.1 2020-09-13 17:02 paras.chawla Exp $$
 */
public class FindPathInMatrix {

    int[][]          steps = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // final result having path from start to end
    List<Coordinate> path  = new ArrayList<>();

    public List<Coordinate> findPath(
            List<List<Color>> maze,
            Coordinate start,
            Coordinate end) {

        // base condition
        if (maze.size() == 0 || maze.get(0).size() == 0) {
            return path;
        }

        dfs(maze, start, end, maze.size(), maze.get(0).size());
        return path;
    }

    private boolean dfs(List<List<Color>> maze, Coordinate start, Coordinate end,
                        int rows, int cols) {


        // if already visited, No need to process again
        if (maze.get(start.row).get(start.col).equals(Color.BLACK)) {
            return false;
        }

        // add coordinate in path
        path.add(start);

        // base condition
        if (start.row == end.row && start.col == end.col) {
            return true;
        }

        // mark visited and color it BLACK
        maze.get(start.row).set(start.col, Color.BLACK);

        // traverse all 4 directions and call dfs on all again
        for (int step[] : steps) {

            int currX = start.row;
            int currY = start.col;

            if (currX + step[0] >= 0 && currX + step[0] < rows &&
                    currY + step[1] >= 0 && currY + step[1] < cols &&
                    maze.get(currX + step[0]).get(currY + step[1]) == Color.WHITE) {
                currX += step[0];
                currY += step[1];
            }

            if (dfs(maze, new Coordinate(currX, currY), end, rows, cols)) {
                return true;
            }
        }

        // if explored all directions and still no result , then remove node from path and back track
        path.remove(path.size() - 1);
        return false;
    }
    /*  0 1 2 3 4
     0  B B B B WEST
     1  B B B B B
     2  WEST B WEST B WEST
     3  WEST B WEST B WEST
     4  WEST WEST WEST B WEST

     00-01-11-12-13-14
     dfs(maze, start ,end)
        - base condition when start==end
        - path.add(start);
        - for(all directions)
        - update start based on direction
        - if(dfs(maze, new start, end)) return true;
     */

    public static void main(String[] args) {
        FindPathInMatrix obj = new FindPathInMatrix();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(4, 4);
        List<List<Color>> maze = new ArrayList<>();
        maze.add(Arrays.asList(Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE));
        maze.add(Arrays.asList(Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
        maze.add(Arrays.asList(Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));
        maze.add(Arrays.asList(Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE));
        maze.add(Arrays.asList(Color.WHITE, Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE));
        System.out.println(obj.findPath(maze, start, end));
    }

}

enum Color {
    WHITE,
    BLACK
}

class Coordinate {
    public int row, col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;
        if (row != that.row || col != that.col) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}