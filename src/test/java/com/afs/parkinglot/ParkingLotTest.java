package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    // Given a parking lot, and a car, When park the car, Then return a parking ticket.
    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_car() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        ParkingTicket ticket = parkingLot.park(car);

        assert ticket != null;
    }

    // Given a parking lot with a parked car, and a parking ticket, When fetch the car, Then return the parked car.
    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_parking_ticket() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(ticket);

        assert fetchedCar == car;
    }

    // Given a parking lot with two parked cars, and two parking tickets, When fetch the car twice, Then return the right car with each ticket
    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_parking_lot_with_two_parked_cars_and_two_parking_tickets() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket ticket1 = parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car2);

        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        assert fetchedCar1 == car1;
        assert fetchedCar2 == car2;
    }
    // Given a parking lot with parked car, and no ticket, When fetch the car, Then return nothing
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_with_parked_car_and_no_ticket() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        parkingLot.park(car);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(null);
        });

        assertEquals("unrecognized parking ticket", exception.getMessage());
    }

    // Given a parking lot, and a wrong parking ticket, When fetch the car, Then return nothing.
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_parking_ticket() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        parkingLot.park(car);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(new ParkingTicket());
        });

        assertEquals("unrecognized parking ticket", exception.getMessage());
    }

    // Given a parking lot, and a used parking ticket, When fetch the car, Then return nothing.
    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_parking_ticket() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.fetch(ticket);
        });

        assertEquals("unrecognized parking ticket", exception.getMessage());
    }

    // Given a parking lot without any position, and a car, When park the car, Then return nothing
    @Test
    void should_return_nothing_when_park_given_parking_lot_without_any_position_and_car() {

        ParkingLot parkingLot = new ParkingLot(10);
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car());
        }
        Car car = new Car();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            parkingLot.park(car);
        });

        assertEquals("Not enough position.", exception.getMessage());
    }





}
