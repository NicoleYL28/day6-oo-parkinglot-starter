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
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_correct_parking_ticket() {

        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(ticket);

        assert fetchedCar == car;
    }

    // Given a parking lot with two parked cars, and two parking tickets, When fetch the car twice, Then return the right car with each ticket
    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_parking_lot_with_two_parked_cars_and_two_correct_parking_tickets() {

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
    void should_throw_error_message_when_fetch_given_parking_lot_with_parked_car_and_no_ticket() {

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
    void should_throw_error_message_when_fetch_given_parking_lot_and_wrong_parking_ticket() {

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
    void should_throw_error_message_when_fetch_given_parking_lot_and_used_parking_ticket() {

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
    void should_throw_error_message_when_park_given_parking_lot_is_full_and_car() {

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

    // The standard parking boy
    @Test
    void should_park_in_first_parking_lot_when_first_is_not_full_given_two_parking_lots_by_standard_boy(){
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        ParkingTicket ticket = standardParkingBoy.park(new Car());

        assertTrue(parkingLot1.has(ticket));
    }

    @Test
    void should_park_in_second_parking_lot_when_first_is_full_given_two_parking_lots_by_standard_boy(){
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        for(int i=0; i < 10; i++){
            standardParkingBoy.park(new Car());
        }
        ParkingTicket ticket = standardParkingBoy.park(new Car());

        assertTrue(parkingLot2.has(ticket));
    }

    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_two_parking_lots_with_two_parked_cars_and_two_parking_tickets_by_standard_boy() {

        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        Car car1 = new Car();
        Car car2 = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        ParkingTicket ticket1 = standardParkingBoy.park(car1);
        ParkingTicket ticket2 = standardParkingBoy.park(car2);

        Car fetchedCar1 = standardParkingBoy.fetch(ticket1);
        Car fetchedCar2 = standardParkingBoy.fetch(ticket2);

        assert fetchedCar1 == car1;
        assert fetchedCar2 == car2;
    }

    @Test
    void should_throw_error_message_when_fetch_given_parking_lot_with_parked_car_and_no_ticket_by_standard_boy() {

        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();

        standardParkingBoy.park(car);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            standardParkingBoy.fetch(null);
        });

        assertEquals("unrecognized parking ticket", exception.getMessage());
    }

    @Test
    void should_throw_error_message_when_fetch_given_parking_lot_and_used_parking_ticket_by_standard_boy() {

        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            standardParkingBoy.fetch(ticket);
        });

        assertEquals("unrecognized parking ticket", exception.getMessage());
    }

    @Test
    void should_throw_error_message_when_park_given_parking_lot_without_any_position_and_car_by_standard_boy() {

        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1, parkingLot2);
        for (int i = 0; i < 20; i++) {
            standardParkingBoy.park(new Car());
        }
        Car car = new Car();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            standardParkingBoy.park(car);
        });

        assertEquals("Not enough position.", exception.getMessage());
    }

    // The standard smart boy
    @Test
    void should_park_in_second_parking_lot_when_second_has_more_space_given_two_parking_lots_and_smart_parking_boy(){
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        for (int i = 0; i < 7; i++) {
            parkingLot1.park(new Car());
        }
        for (int i = 0; i < 2; i++) {
            parkingLot2.park(new Car());
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        Car car = new Car();
        ParkingTicket ticket = smartParkingBoy.park(car);

        assertTrue(parkingLot2.has(ticket));
    }

    @Test
    void should_park_in_first_parking_lot_when_first_and_second_have_same_space_given_two_parking_lots_and_smart_parking_boy(){
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        for (int i = 0; i < 5; i++) {
            parkingLot2.park(new Car());
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        Car car = new Car();
        ParkingTicket ticket = smartParkingBoy.park(car);

        assertTrue(parkingLot1.has(ticket));
    }

    // The standard super boy
    @Test
    void should_park_in_second_parking_lot_when_first_is_full_given_two_parking_lots_and_super_parking_boy(){
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(10);
        for (int i = 0; i < 5; i++) {
            parkingLot1.park(new Car());
        }
        for (int i = 0; i < 1; i++) {
            parkingLot2.park(new Car());
        }
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);

        Car car = new Car();
        ParkingTicket ticket = superParkingBoy.park(car);

        assertTrue(parkingLot2.has(ticket));
    }


}
