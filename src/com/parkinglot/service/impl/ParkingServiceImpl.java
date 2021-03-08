/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.parkinglot.service.impl;

import com.parkinglot.constants.ParkingConstants;
import com.parkinglot.dao.InMemoryParkingManager;
import com.parkinglot.dao.ParkingDao;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.RateCard;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingService;
import com.parkinglot.strategy.ParkingStrategy;
import com.parkinglot.strategy.impl.NearestParkingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author paras.chawla
 * @version $Id: ParkingServiceImpl.java, v 0.1 2021-03-04 6:26 PM paras.chawla Exp $$
 */
public class ParkingServiceImpl implements ParkingService {

    private ParkingDao<Vehicle> parkingDao = null;
    Map<ParkingLot, ParkingDao> map = new HashMap<>();

    @Override
    public ParkingLot createParkingLot(int level, int twoWheelerCapacity, int fourWheelerCapacity, RateCard twoWheelerRateCard, RateCard fourWheelerRateCard) throws ParkingLotException {

        if (parkingDao != null) {
            throw new ParkingLotException(ParkingConstants.PARKING_ALREADY_EXIST);
        }

        ParkingStrategy parkingStrategy = new NearestParkingStrategy();
        this.parkingDao = new InMemoryParkingManager(level, twoWheelerCapacity, fourWheelerCapacity, parkingStrategy,twoWheelerRateCard,fourWheelerRateCard);

        ParkingLot lot = new ParkingLot();
        lot.setParkingId(UUID.randomUUID().toString());
        lot.setTwoWheelerSlots(twoWheelerCapacity);
        lot.setFourWheelerSlots(fourWheelerCapacity);

        map.put(lot,parkingDao);

        return lot;
    }

    @Override
    public Integer parkVehicle(int level, Vehicle vehicle) throws ParkingLotException {
        int value = -1;
        validateParkingLot();
        try {
            value = parkingDao.parkVehicle(level, vehicle);
        } catch (Exception e) {
            throw new ParkingLotException(ParkingConstants.PROCESSING_ERROR, e);
        }
        return value;
    }

    @Override
    public boolean unparkVehicle(int level, Vehicle vehicle, Integer slotNumber) throws ParkingLotException {
        validateParkingLot();
        try {
            return parkingDao.leaveVehicle(level, vehicle, slotNumber);
        } catch (Exception e) {
            throw new ParkingLotException();
        }
    }

    private void validateParkingLot() throws ParkingLotException {
        if (parkingDao == null) {
            throw new ParkingLotException(ParkingConstants.PARKING_NOT_EXIST_ERROR);
        }
    }
}