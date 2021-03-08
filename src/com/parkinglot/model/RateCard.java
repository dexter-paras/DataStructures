/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: RateCard.java, v 0.1 2021-03-04 7:10 PM paras.chawla Exp $$
 */

// 0-120 -> 20, 2-4-> 40; 4-8->90
public class RateCard {

    // duration of vehicle in parking Lot
    // key - minutes, value - price

    final Integer maxPrice = Integer.MAX_VALUE;
    Map<Duration,Integer> rateCardMap = new HashMap<>();

    public void addRates(Duration duration, int price) {
        this.rateCardMap.put(duration,price);
    }

    public int calculateCharges(double duration){
        for(Map.Entry<Duration,Integer> entry : rateCardMap.entrySet()){
            if(duration >= entry.getKey().startTime && duration <= entry.getKey().endTime){
                return entry.getValue();
            }
        }
        return maxPrice;
    }
}