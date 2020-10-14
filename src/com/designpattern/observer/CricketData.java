/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CricketData.java, v 0.1 2019-11-03 12:26 paras.chawla Exp $$
 */
public class CricketData implements Subject {

    int runs, wickets;
    float overs;

    List<Observer> obsList;

    public int getLatestRuns() {
        return 100;
    }

    public int getLatestWickets() {
        return 1;
    }

    public float getLatestOvers() {
        return 15.2f;
    }

    public CricketData() {
        obsList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        obsList.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        obsList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : obsList) {
            observer.update(runs, wickets, overs);
        }
    }

    // Called automatically when user click refresh
    public void refreshScoreboard() {
        runs = getLatestRuns();
        wickets = getLatestWickets();
        overs = getLatestOvers();

        notifyObservers();
    }

}