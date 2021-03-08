/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot.service;

import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.RateCard;
import com.parkinglot.model.Vehicle;

/**
 * @author paras.chawla
 * @version $Id: ParkingService.java, v 0.1 2021-03-04 6:20 PM paras.chawla Exp $$
 */
public interface ParkingService {

    ParkingLot createParkingLot(int level, int twoWheelerCapacity, int fourWheelerCapacity,RateCard twoWheelerRateCard, RateCard fourWheelerRateCard) throws ParkingLotException;

    Integer parkVehicle(int level, Vehicle vehicle) throws ParkingLotException;

    boolean unparkVehicle(int level, Vehicle vehicle, Integer slotNumber) throws ParkingLotException;
}