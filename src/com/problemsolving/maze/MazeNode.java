/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.maze;

import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: MazeNode.java, v 0.1 2019-09-15 13:41 paras.chawla Exp $$
 */
public class MazeNode {

    int row;
    int column;
    char dispName;
    List<MazeNode> neighbors;

    public MazeNode(int row, int column, char dispName, List<MazeNode> neighbors) {
        this.row = row;
        this.column = column;
        this.dispName = dispName;
        this.neighbors = neighbors;
    }
}