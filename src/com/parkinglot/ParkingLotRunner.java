/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot;

import com.parkinglot.constants.ParkingConstants;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.Bike;
import com.parkinglot.model.Car;
import com.parkinglot.model.Duration;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.RateCard;
import com.parkinglot.service.ParkingService;
import com.parkinglot.service.impl.ParkingServiceImpl;

/**
 * @author paras.chawla
 * @version $Id: ParkingLotRunner.java, v 0.1 2021-03-04 7:23 PM paras.chawla Exp $$
 */
public class ParkingLotRunner {

    public static void main(String[] args) throws ParkingLotException {

        ParkingService parkingService = new ParkingServiceImpl();

        Duration d1 = new Duration(0,2);
        Duration d2 = new Duration(3,5);
        Duration d3 = new Duration(4,24);

        RateCard twoWheelerRateCard = new RateCard();
        twoWheelerRateCard.addRates(d1,20);
        twoWheelerRateCard.addRates(d2,40);
        twoWheelerRateCard.addRates(d3,100);

        RateCard fourWheelerRateCard = new RateCard();
        fourWheelerRateCard.addRates(d1,50);
        fourWheelerRateCard.addRates(d2,100);
        fourWheelerRateCard.addRates(d3,200);

        ParkingLot lot = parkingService.createParkingLot(1, 1,1,twoWheelerRateCard,fourWheelerRateCard);

        Car car = new Car("HR12Z5216","WHITE");
        final Integer slotNumber = parkingService.parkVehicle(0,car);

        if (slotNumber == ParkingConstants.NOT_AVAILABLE) {
            System.out.println("Sorry, Parking Lot is full");
        } else if (slotNumber == ParkingConstants.VEHICLE_ALREADY_PARKED) {
            System.out.println("Sorry, Vehicle is already parked");
        } else {
            System.out.println("Allocated slot number" + slotNumber);
        }

        Bike bike = new Bike("HR12S8715","BLACK");
        final Integer slotNumber2 = parkingService.parkVehicle(0,bike);

        parkingService.unparkVehicle(0, car,slotNumber);
    }
}