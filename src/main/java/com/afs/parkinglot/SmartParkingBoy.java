package com.afs.parkinglot;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        int maxAvailableSpaces = 0;
        ParkingLot bestLot = null;
        for(ParkingLot parkingLot : getParkingLots()) {
            if (!parkingLot.isFull()) {
                int availableSpaces = parkingLot.getAvailableSpaces();
                if (availableSpaces > maxAvailableSpaces) {
                    maxAvailableSpaces = availableSpaces;
                    bestLot = parkingLot;
                }
            }
        }

        if (bestLot != null) {
            return bestLot.park(car);
        }

        throw new RuntimeException("Not enough position.");
    }


}
