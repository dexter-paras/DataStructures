/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.stackandqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author paras.chawla
 * @version $Id: SunsetView.java, v 0.1 2020-02-23 19:01 paras.chawla Exp $$ We are given an array buildings of integers. Each item
 * represents the height of a building on a skyline from left to right.
 */
public class SunsetView {

    List<Integer> computeBuildingWithSunsetView(int[] buildings) {
        Stack<Integer> stack = new Stack();
        List<Integer> buildingList = new ArrayList<>();
        stack.add(buildings[0]);
        buildingList.add(0);
        for (int i = 1; i < buildings.length; i++) {
            if (buildings[i] > stack.peek()) {
                stack.push(buildings[i]);
                buildingList.add(i);
            }
        }
        return buildingList;
    }

    public static void main(String[] args) {
        int[] buildings = {11, 12, 13, 14, 15};
        int[] buildings1 = {7, 4, 3, 1, 9};
        System.out.println(Arrays.toString(new SunsetView().computeBuildingWithSunsetView(buildings).toArray()));
        System.out.println(Arrays.toString(new SunsetView().computeBuildingWithSunsetView(buildings1).toArray()));
    }
}