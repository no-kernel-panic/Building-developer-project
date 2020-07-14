package com.company;

public class TooManyRentalsException extends Exception {
    public TooManyRentalsException(){
        super("too many rentals");
    }
}
