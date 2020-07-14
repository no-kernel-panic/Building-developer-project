package com.company;

public class ParkingAlreadyRentedException extends Exception {
    public ParkingAlreadyRentedException(){
        super("Parking is already rented");
    }
}
