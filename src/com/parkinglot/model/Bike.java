/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

/**
 * @author paras.chawla
 * @version $Id: Car.java, v 0.1 2020-11-13 00:23 paras.chawla Exp $$
 */
public class Bike extends Vehicle {

    public Bike(String licensePlateNo, String color) {
        super(licensePlateNo, color,VehicleType.TWO_WHEELER);
    }
}