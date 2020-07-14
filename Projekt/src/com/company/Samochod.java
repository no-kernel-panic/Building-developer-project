package com.company;

public abstract class Samochod extends Pojazdy {


    public String marka;
    public String model;
    String typSilnika;
    int pojemnoscSilnika;

public Samochod (String nazwa, Double powierszchnia, String marka, String model, String typSilnika, int pojemnoscSilnika, int iloscOsob){

    this.marka = marka;
    this.model = model;
    this.typSilnika = typSilnika;
    this.pojemnoscSilnika = pojemnoscSilnika;
    super.nazwa = nazwa;
    super.powierzchnia = powierszchnia;
    super.iloscKola = 4;
    super.iloscOsob = iloscOsob;
    super.ruszasie = false;
    super.jestWgarazu = false;


}

    public abstract void Klakson();

    public abstract void jechac();

    public abstract  void zatrzymacsie();


}
