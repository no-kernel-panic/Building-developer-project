package com.company;

import java.util.ArrayList;

public class Mieszkanie extends Pomieszczenie {


    int numerIdOsiedle;



    public Mieszkanie(Osiedle osiedle, double rozmiarPowierzchni)  {

        super.numerIdentifikacyjne = MieszkaniacounternumerIdentifikacyjne;
        MieszkaniacounternumerIdentifikacyjne++;
        numerIdOsiedle = osiedle.numerID;
        super.rozmiarPowierzchni = rozmiarPowierzchni;
        osiedle.listpomieszczenie.add(this);
        super.jestPismo = false;
        super.zameldowani = new ArrayList<>();
        super.akta = new ArrayList<>();


    }

    public Mieszkanie(Osiedle osiedle, double  dlugosc, double wysokosc, double szerokosc)  {

        super.numerIdentifikacyjne = MieszkaniacounternumerIdentifikacyjne;//działą
        MieszkaniacounternumerIdentifikacyjne++;
        numerIdOsiedle = osiedle.numerID;
        super.rozmiarPowierzchni = dlugosc*wysokosc*szerokosc;
        osiedle.listpomieszczenie.add(this);
        super.jestPismo = false;
        super.zameldowani = new ArrayList<>();
        super.akta = new ArrayList<>();


    }

    @Override
    public void wyczyscicPomiesczenie() {

        this.najemca.anulowacWynajem(this);
        this.akta.add(this.najemca.pismo_file);
    }
}



