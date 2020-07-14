package com.company;

public class NoApartmenException extends Exception {
    public NoApartmenException (){
        super("No apartments in this osiedle");
    }
}
