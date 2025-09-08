package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardParkingBoy extends ParkingBoy{
    public StandardParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        for(ParkingLot parkingLot : getParkingLots()) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new RuntimeException("Not enough position.");
    }


}
