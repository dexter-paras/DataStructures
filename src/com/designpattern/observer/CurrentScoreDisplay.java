/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.designpattern.observer;

/**
 * @author paras.chawla
 * @version $Id: CurrentScoreDisplay.java, v 0.1 2019-11-03 12:26 paras.chawla Exp $$
 */
public class CurrentScoreDisplay implements Observer {

    private int runs, wickets;
    private float overs;

    @Override
    public void update(int runs, int wickets,
                       float overs)
    {
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
        display();
    }

    public void display()
    {
        System.out.println("\nCurrent Score Display:\n"
                + "Runs: " + runs +
                "\nWickets:" + wickets +
                "\nOvers: " + overs );
    }
}