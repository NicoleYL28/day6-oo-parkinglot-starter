package com.afs.parkinglot;

public class SuperParkingBoy extends ParkingBoy{
    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        double maxAvailableRate = 0;
        ParkingLot bestLot = null;
        for(ParkingLot parkingLot : getParkingLots()) {
            if (!parkingLot.isFull()) {
                double availableRate = (double) parkingLot.getAvailableSpaces() / parkingLot.getCapacity();
                if (availableRate > maxAvailableRate) {
                    maxAvailableRate = availableRate;
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
