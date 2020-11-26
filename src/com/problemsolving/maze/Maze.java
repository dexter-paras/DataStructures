/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author paras.chawla
 * @version $Id: Maze.java, v 0.1 2019-09-15 13:44 paras.chawla Exp $$
 */
public class Maze {

    static MazeNode[][] nodes;

    public Maze(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));

        // read edges
        while (sc.hasNext()) {
            String line = sc.next();
            String[] tokens = line.split(" ");
        }
    }
}