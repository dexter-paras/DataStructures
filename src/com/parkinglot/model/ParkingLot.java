/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author paras.chawla
 */

@Getter
@Setter
public class ParkingLot {

    private String parkingId;
    private int levels;
    private int twoWheelerSlots;
    private int fourWheelerSlots;

}