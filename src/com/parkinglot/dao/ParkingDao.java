/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.parkinglot.dao;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;

import java.util.List;

/**
 * @author paras.chawla
 */
public interface ParkingDao<T extends Vehicle> {

    // give SlotNumber in return
    int parkVehicle(int level,T vehicle);

    // whether its success or fail
    boolean leaveVehicle(int level, Vehicle vehicle, int slotNumber);
}