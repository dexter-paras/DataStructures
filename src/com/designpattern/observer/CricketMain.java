/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.designpattern.observer;

/**
 * @author paras.chawla
 * @version $Id: CricketMain.java, v 0.1 2019-11-03 12:45 paras.chawla Exp $$
 */
public class CricketMain {

    public static void main(String[] args) {

        // Initialize Subject/Channel which push notification
        CricketData cricketData = new CricketData();

        // Initialize Observers who receives update from Subject
        CurrentScoreDisplay currentScoreObserver = new CurrentScoreDisplay();
        AverageScoreDisplay avgScoreObserver = new AverageScoreDisplay();

        // register Observers who wants notification once scoreboard updates
        cricketData.registerObserver(currentScoreObserver);
        cricketData.registerObserver(avgScoreObserver);

        // fetch latest scoreCard and notify to all Observers
        cricketData.refreshScoreboard();

        // unregister an Observer
        cricketData.unregisterObserver(avgScoreObserver);
        cricketData.refreshScoreboard();
    }
}