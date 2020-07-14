package com.company;

public class SamochodMiejski extends Samochod {


    String numerRejestracyjne;
    String numerKartePaliwowej;


    public SamochodMiejski(String nazwa, Double powierszchnia, String marka, String model, String typSilnika, int pojemnoscSilnika, int iloscOsob,   String numerRejestracyjne, String numerKartePaliwowej) {

        super(nazwa,powierszchnia,marka, model, typSilnika, pojemnoscSilnika, iloscOsob);
        this.numerRejestracyjne = numerRejestracyjne;
        this.numerKartePaliwowej = numerKartePaliwowej;
        Menu.listPojazd.add(this);

    }

    @Override
    public void Klakson() {
        System.out.println("PIIIIIIIIIIII");
    }

    @Override
    public void jechac() {
        super.ruszasie = true;

    }

    @Override
    public void zatrzymacsie() {
        super.ruszasie = false;

    }

    @Override
    public String toString() {
        return super.nazwa;
    }
}
