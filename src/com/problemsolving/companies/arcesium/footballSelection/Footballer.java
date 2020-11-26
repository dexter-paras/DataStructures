/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.footballSelection;

/**
 * @author paras.chawla
 * @version $Id: Footballer.java, v 0.1 2020-05-10 14:20 paras.chawla Exp $$
 */
public class Footballer {

    String name;

    double height;

    double bmi;

    int scores;

    int defends;

    boolean isStriker;

    boolean isDefender;

    boolean isAllRounder;

    public Footballer(String name, double height, double bmi, int scores, int defends) {
        this.name = name;
        this.height = height;
        this.bmi = bmi;
        this.scores = scores;
        this.defends = defends;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        return bmi;
    }

    public int getScores() {
        return scores;
    }

    public int getDefends() {
        return defends;
    }

    public boolean isStriker() {
        return isStriker;
    }

    public boolean isDefender() {
        return isDefender;
    }

    public boolean isAllRounder() {
        return isAllRounder;
    }

    public void setStriker(boolean striker) {
        isStriker = striker;
    }

    public void setDefender(boolean defender) {
        isDefender = defender;
    }

    public void setAllRounder(boolean allRounder) {
        isAllRounder = allRounder;
    }
}