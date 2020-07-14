package com.company;

public class Lodz extends Pojazdy {



    public Lodz(String nazwa, double powierzchnia, int iloscOsob) {

        super.nazwa = nazwa;
        super.powierzchnia = powierzchnia;
        super.iloscKola = 0;
        super.iloscOsob = iloscOsob;
        super.ruszasie = false;
        super.jestWgarazu = false;
        Menu.listPojazd.add(this);

    }

    public void plywac(){
        super.ruszasie =true;
    }

    public void zatrzymacSie() {
        super.ruszasie = false;
    }

    @Override
    public String toString() {
        return super.nazwa;
    }

}


