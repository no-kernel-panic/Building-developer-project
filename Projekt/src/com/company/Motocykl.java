package com.company;

public class Motocykl extends Pojazdy {

    boolean jestPodlaczoneWozekBoczny;

    public Motocykl(String nazwa, double powierszchnia) {


        super.nazwa = nazwa;
        super.powierzchnia = powierszchnia;
        super.iloscKola = 2;
        super.iloscOsob = 2;
        super.ruszasie = false;
        super.jestWgarazu = false;
        this.jestPodlaczoneWozekBoczny = false;
        Menu.listPojazd.add(this);
    }

    public void podlacWozekBoczny(){
        jestPodlaczoneWozekBoczny = true;
        super.iloscOsob = 3;
    }
    @Override
    public String toString() {
        return super.nazwa;
    }
}
