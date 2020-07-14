package com.company;

public class ProblematicTenantException extends Exception {


    public ProblematicTenantException(Osoba najemca, String wynajetypomieszczenia){

    super("Osoba "+ najemca.imie +" posiadała już najem pomieszczeń: "+ wynajetypomieszczenia);
    }

}





