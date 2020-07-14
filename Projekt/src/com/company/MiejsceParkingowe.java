package com.company;
import java.util.*;


public class MiejsceParkingowe extends Pomieszczenie  {


    double dostepnaPowierzchnia;
    List<Pojazdy> pojazdy;
    List<Przedmioty> przedmioty;


    public MiejsceParkingowe(Osiedle osiedle, double rozmiarPowierzchni) {


        super.numerIdentifikacyjne = ParkingcounternumerIdentifikacyjne;
        ParkingcounternumerIdentifikacyjne++;
        numerIdOsiedle = osiedle.numerID;
        super.rozmiarPowierzchni = rozmiarPowierzchni;
        osiedle.listpomieszczenie.add(this);
        dostepnaPowierzchnia = super.rozmiarPowierzchni;
        super.jestPismo = false;
        super.akta = new ArrayList<>();
        przedmioty = new ArrayList<>();
        pojazdy= new ArrayList<>();

    }


    public MiejsceParkingowe(Osiedle osiedle, double  dlugosc, double wysokosc, double szerokosc) {

        super.numerIdentifikacyjne = ParkingcounternumerIdentifikacyjne;
        ParkingcounternumerIdentifikacyjne++;
        numerIdOsiedle = osiedle.numerID;
        super.rozmiarPowierzchni = dlugosc*wysokosc*szerokosc;
        osiedle.listpomieszczenie.add(this);
        dostepnaPowierzchnia = super.rozmiarPowierzchni;
        super.jestPismo = false;
        super.akta = new ArrayList<>();
        przedmioty = new ArrayList<>();
        pojazdy= new ArrayList<>();
    }



    @Override
    public void wyczyscicPomiesczenie() {

        if (pojazdy != null) {
            pojazdy.clear();
            this.dataZakonczenia += 60;
        } else if (przedmioty != null) {
            przedmioty.clear();
            this.najemca.wynajetePomieszczenia.remove(this);
            this.najemca = null;
            this.akta.add(this.najemca.pismo_file);
        }
        else {
            this.najemca.wynajetePomieszczenia.remove(this);
            this.najemca = null;
            this.akta.add(this.najemca.pismo_file);
        }

    }
}











