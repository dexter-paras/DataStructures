/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

/**
 * @author paras.chawla
 * @version $Id: Vehicle.java, v 0.1 2021-03-04 6:15 PM paras.chawla Exp $$
 */

abstract public class Vehicle {

    private String      licensePlateNo = null;
    private String      color          = null;
    private VehicleType vehicleType;

    public Vehicle(String licensePlateNo, String color, VehicleType vehicleType) {
        this.licensePlateNo = licensePlateNo;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}