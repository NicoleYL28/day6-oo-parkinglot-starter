package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;

public class ParkingBoy {
    private final ArrayList<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket park(Car car) {
        return null;
    }

    public Car fetch(ParkingTicket ticket) {
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.has(ticket)){
                return parkingLot.fetch(ticket);
            }
        }
        throw new RuntimeException("unrecognized parking ticket");
    }
}
