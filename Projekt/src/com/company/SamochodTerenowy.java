package com.company;

public class SamochodTerenowy extends Samochod {

    boolean naped4x4;
    boolean  kontrolaZjazduZeWzniesienia;

    public SamochodTerenowy(String nazwa, Double powierszchnia, String marka, String model, String typSilnika, int pojemnoscSilnika, int iloscOsob,   String numerRejestracyjne, String numerKartePaliwowej) {

        super(nazwa,powierszchnia,marka, model, typSilnika, pojemnoscSilnika, iloscOsob);
        naped4x4 = false;
        kontrolaZjazduZeWzniesienia = false;
        Menu.listPojazd.add(this);

    }
    public void kontrolaZjazduZeWzniesienia(){
        if (!kontrolaZjazduZeWzniesienia) {
            System.out.println("Kontrola on!");
            kontrolaZjazduZeWzniesienia = true;
        } else
            kontrolaZjazduZeWzniesienia = false;
    }



    @Override
    public void Klakson() {
        System.out.println("TUUUUUUUUUU");
    }

    @Override
    public void jechac() {
        if(!naped4x4) {
            System.out.println("4x4 on!");
            naped4x4 = true;
            super.ruszasie = true;
        }
    }

    @Override
    public void zatrzymacsie() {
        naped4x4 = false;
        super.ruszasie = false;
    }

    @Override
    public String toString() {
        return super.nazwa;
    }
}
