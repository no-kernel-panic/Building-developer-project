package com.company;
import java.io.File;
import java.util.List;

public abstract class Pomieszczenie {

    int dataRozpoczecia;
    int dataZakonczenia;
    int numerIdOsiedle;
    Osoba najemca;
    Integer numerIdentifikacyjne;
    Double rozmiarPowierzchni;
    static int MieszkaniacounternumerIdentifikacyjne;
    static int ParkingcounternumerIdentifikacyjne;
    boolean jestPismo;
    List<Osoba> zameldowani;
    List<File> akta;

    @Override
    public String toString() {
        return this.numerIdentifikacyjne.toString();
    }

   public abstract void wyczyscicPomiesczenie();
}
