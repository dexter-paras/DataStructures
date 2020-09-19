/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.graph;

import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DetectCycleUsingColors.java, v 0.1 2020-09-19 00:58 paras.chawla Exp $$
 * <p>
 * WHITE- unvisited vertex
 * GREY- In processing
 * BLACK - Visited and no cycle found
 */
public class DetectCycleUsingColors {

    public boolean isCyclic(List<GraphVertex> graph) {

        // 1. traverse all vertices - it could be not connected graph
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).color == GraphVertex.Color.WHITE && hasCycleFromVertex(graph.get(i))) {
                return true;
            }
        }
        return false;
    }

    // 2. To find if cycle exist from particular vertex - check all childrens and recurse
    private boolean hasCycleFromVertex(GraphVertex graphVertex) {

        /* Base condition
        If this vertex is currently being visited and we just returned to
        it that means that there is a cycle
        */
        if (graphVertex.color == GraphVertex.Color.GRAY) {
            return true;
        }

        // mark graphVertex as GREY which means Work is being done on current vertex
        graphVertex.color = GraphVertex.Color.GRAY;

        // list all current vertex  childrens
        for (GraphVertex children : graphVertex.edges) {
            // check if children isn't BLACK, Black means already visisted and no cycle
            if (children.color != GraphVertex.Color.BLACK) {
                return hasCycleFromVertex(children);
            }
        }
        // once all childrens are traverssed and no cycle found, mark it black
        graphVertex.color = GraphVertex.Color.BLACK;
        // no cycle found from particular vertex
        return false;
    }

    static class GraphVertex {
        enum Color {
            WHITE,
            GRAY,
            BLACK
        };
        Color             color;
        List<GraphVertex> edges;
    }

}

