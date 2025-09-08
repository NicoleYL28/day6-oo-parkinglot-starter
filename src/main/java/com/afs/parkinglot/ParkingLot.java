package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static int CAPACITY;
    private final Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int capacity) {
        CAPACITY = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new RuntimeException("Not enough position.");
        }
        ParkingTicket ticket = new ParkingTicket();
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null || !parkedCars.containsKey(ticket)) {
            throw new RuntimeException("unrecognized parking ticket");
        }
        return parkedCars.remove(ticket);
    }

    public boolean isFull() {
        return parkedCars.size() >= CAPACITY;
    }

    public boolean has(ParkingTicket ticket){
        return parkedCars.containsKey(ticket);
    }

    public int getAvailableSpaces() {
        return CAPACITY-parkedCars.size();
    }

    public double getCapacity() {
        return CAPACITY;
    }


}
