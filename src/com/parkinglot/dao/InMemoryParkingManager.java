/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot.dao;

import com.parkinglot.constants.ParkingConstants;
import com.parkinglot.model.RateCard;
import com.parkinglot.model.Vehicle;
import com.parkinglot.strategy.ParkingStrategy;
import com.parkinglot.strategy.impl.NearestParkingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author paras.chawla
 */
public class InMemoryParkingManager implements ParkingDao<Vehicle> {

    AtomicInteger twoWheelercapacity  = new AtomicInteger();
    AtomicInteger fourWheelercapacity = new AtomicInteger();

    AtomicInteger twoWheelerAvailablity  = new AtomicInteger();
    AtomicInteger fourWheelerAvailablity = new AtomicInteger();

    ParkingStrategy twoWheelerParkingStrategy;
    ParkingStrategy fourWheelerParkingStrategy;

    Map<Integer, Optional<Vehicle>> twoWheelerSlotVehicleMap;
    Map<Integer, Optional<Vehicle>> fourWheelerSlotVehicleMap;

    RateCard twoWheelerRateCard, fourWheelerRateCard;

    Map<String,Integer> parkingHistoryMap = new HashMap<>();

    public InMemoryParkingManager(int level, int twoWheelerCapacity, int fourWheelerCapacity,
                                  ParkingStrategy parkingStrategy, RateCard twoWheelerRateCard, RateCard fourWheelerRateCard) {
        this.twoWheelercapacity.set(twoWheelerCapacity);
        this.fourWheelercapacity.set(fourWheelerCapacity);

        this.twoWheelerAvailablity.set(twoWheelerCapacity);
        this.fourWheelerAvailablity.set(fourWheelerCapacity);

        this.twoWheelerParkingStrategy = new NearestParkingStrategy();
        this.fourWheelerParkingStrategy = new NearestParkingStrategy();

        this.twoWheelerRateCard = twoWheelerRateCard;
        this.fourWheelerRateCard = fourWheelerRateCard;

        this.twoWheelerSlotVehicleMap = new ConcurrentHashMap<>();
        this.fourWheelerSlotVehicleMap = new ConcurrentHashMap<>();

        // fill map with empty Optional vehicle
        for (int i = 1; i <= twoWheelerCapacity; i++) { //20
            twoWheelerSlotVehicleMap.put(i, Optional.empty());
            twoWheelerParkingStrategy.add(i);
        }

        // fill map with empty Optional vehicle
        for (int i = 1; i <= fourWheelerCapacity; i++) { //50
            fourWheelerSlotVehicleMap.put(i, Optional.empty());
            fourWheelerParkingStrategy.add(i);
        }
    }

    @Override
    public int parkVehicle(int level, Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case TWO_WHEELER:
                // 1. check parking availablity
                if (twoWheelerAvailablity.get() == 0) {
                    return ParkingConstants.NOT_AVAILABLE;
                }

                // 2. check vehicle in slotMap if already exist
                if (twoWheelerSlotVehicleMap.containsValue(Optional.of(vehicle))) {
                    return ParkingConstants.VEHICLE_ALREADY_PARKED;
                }

                // 3. get freeslot and park vehicle to that particular slot
                int availableSlot = twoWheelerParkingStrategy.getSlot();
                twoWheelerSlotVehicleMap.put(availableSlot, Optional.of(vehicle));
                this.twoWheelerAvailablity.decrementAndGet();
                twoWheelerParkingStrategy.removeSlot(availableSlot);
                return availableSlot;

            case FOUR_WHEELER:
                // 1. check parking availablity
                if (fourWheelerAvailablity.get() == 0) {
                    return ParkingConstants.NOT_AVAILABLE;
                }

                // 2. check vehicle in slotMap if already exist
                if (fourWheelerSlotVehicleMap.containsValue(Optional.of(vehicle))) {
                    return ParkingConstants.VEHICLE_ALREADY_PARKED;
                }

                // 3. get freeslot and park vehicle to that particular slot
                int fwAvailableSlot = fourWheelerParkingStrategy.getSlot();
                fourWheelerSlotVehicleMap.put(fwAvailableSlot, Optional.of(vehicle));
                this.fourWheelerAvailablity.decrementAndGet();
                fourWheelerParkingStrategy.removeSlot(fwAvailableSlot);
                return fwAvailableSlot;
        }
        return -1;
    }

    @Override
    public boolean leaveVehicle(int level, Vehicle vehicle, int slotNumber) {

        switch (vehicle.getVehicleType()) {
            case TWO_WHEELER:
                // 1. check if slot already empty
                if (!twoWheelerSlotVehicleMap.get(slotNumber).isPresent()) {
                    return false;
                }
                twoWheelerParkingStrategy.add(slotNumber);
                this.twoWheelerAvailablity.incrementAndGet();
                twoWheelerSlotVehicleMap.remove(slotNumber);// Map<SlotNumber -> Vehicle>
                calculateParkingCharges(twoWheelerRateCard,vehicle, 0, 2);
                return true;

            case FOUR_WHEELER:
                // 1. check if slot already empty
                if (!fourWheelerSlotVehicleMap.get(slotNumber).isPresent()) {
                    return false;
                }
                fourWheelerParkingStrategy.add(slotNumber);
                this.fourWheelerAvailablity.incrementAndGet();
                fourWheelerSlotVehicleMap.remove(slotNumber);
                calculateParkingCharges(fourWheelerRateCard,vehicle, 0, 2);
                return true;
        }
        return false;
    }

    private void calculateParkingCharges(RateCard rateCard,Vehicle vehicle, int entryTime, int exitTime) {
        int duration = exitTime - entryTime;
        int calculatedCharge = rateCard.calculateCharges(duration);
        System.out.println("Calculated Charge for vehicle :"+ vehicle.getLicensePlateNo() + "is :"+ calculatedCharge);
        parkingHistoryMap.put(vehicle.getLicensePlateNo(),calculatedCharge);
    }

    public void showVehicleHistory(String vehicleId){
        System.out.println(parkingHistoryMap.get(vehicleId));
    }
}