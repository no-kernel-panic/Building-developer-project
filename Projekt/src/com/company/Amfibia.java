package com.company;

public class Amfibia extends Samochod implements Mozeplywac {


    public Amfibia( String nazwa,Double powierzchnia, String marka, String model, String typSilnika, int pojemnoscSilnika, int iloscOsob ) {

        super(nazwa,powierzchnia,marka, model, typSilnika, pojemnoscSilnika, iloscOsob);
        Menu.listPojazd.add(this);
    }

    public void schowajKola(){
        super.iloscKola = 0;
    }

    public void odkryjKola(){
        super.iloscKola = 4;
    }




    @Override
    public void plywac() {
        schowajKola();
        super.ruszasie = true;
    }

    @Override
    public void Klakson() {
        System.out.println("TRRRRRRRR");

    }

    @Override
    public void jechac() {
        odkryjKola();
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
