/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.linkedList.algos;

/**
 * @author paras.chawla
 * @version $Id: DoublyNode.java, v 0.1 2020-08-13 08:19 paras.chawla Exp $$
 */
public class DoublyNode {

    int        data;
    DoublyNode next;
    DoublyNode prev;

    public static void main(String[] args) {
        //{} -> {} -> {}-> {} ->
        //^                ^
        //head             tail

        //[EAST,SOUTH,WEST,NORTH]
        // 0,1,2,3

        // current dir= SOUTH
        // if clause directionArray
        // move(x,y,'L') ->(x,y,array[curDir-1]) -> x,y,EAST
        // move(x,y,'R') ->(x,y,array[curDir+1]) -> x,y,WEST
        // move(x,y,'M') -> directionCheck ->
        //(5,5,x) -> move(x,y,M) -> no action
        //(x<0 || x>length|| y) ->no action
        // NORTH - y+1
        // WEST - x-1
        // SOUTH - y-1
        // EAST - x+1
    }

}