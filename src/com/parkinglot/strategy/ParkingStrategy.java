/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.parkinglot.strategy;

/**
 * @author paras.chawla
 */
public interface ParkingStrategy {

    // when creating a parkinglot
    public void add(int i);

    // when a vehicle comes and we need to give a slot from available slots
    public int getSlot();

    // when a car get slot, need to remove slot
    public void removeSlot(int slot);

}