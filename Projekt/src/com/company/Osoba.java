package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Osoba{

    String imie, nazwisko, pesel, adres, dataurodz;
    List<File> pisma; // keeping the files assigned
    public List<Pomieszczenie> wynajetePomieszczenia; // rented houses
    File pismo_file;
    File stanOsoba;
    Map<String, String> mapFile; // used for writing the file


    public Osoba(String imie, String nazwisko, String pesel, String adres, String dataurodz) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.dataurodz = dataurodz;
        this.wynajetePomieszczenia = new ArrayList<>();
        this.pisma = new ArrayList<>();
        Menu.listOsoby.add(this);

    }

    public void wynajmowacMieszkanie(Pomieszczenie mieszkanie, int iloscdni, int data) throws ProblematicTenantException,TooManyRentalsException {

        if (wynajetePomieszczenia.size() >= 5) throw new TooManyRentalsException();
        else {
            if (this.pisma.size() >= 3)
                throw new ProblematicTenantException(this, wynajetePomieszczenia.toString());// Checking if the user has more than 3 files assigned
            else if (mieszkanie.najemca == null) {
                mieszkanie.najemca = this;
                mieszkanie.zameldowani.add(this);
                mieszkanie.dataRozpoczecia = data;
                mieszkanie.dataZakonczenia = data + iloscdni;
                wynajetePomieszczenia.add(mieszkanie); //keeping track of the spaces rented
                WatekCzas.wynajetePomieszczenia.add(mieszkanie); // in this list it will be filtered for the purpose of controlling expiry date
            }
        }
        System.out.println("Apartment rented. The date of termination of rental is : "+mieszkanie.dataZakonczenia);
    }




    public void wynajmowacMiescaParkingowe(Osiedle osiedle, Pomieszczenie miejsceParkingowe, int iloscdni, int data) throws ProblematicTenantException,TooManyRentalsException,ParkingAlreadyRentedException,NoApartmenException{


        if (miejsceParkingowe.najemca != null) throw new ParkingAlreadyRentedException(); // checking if noone is renting this
        else if (wynajetePomieszczenia.size() >= 5) throw new TooManyRentalsException(); //this Osoba is not renting more than 5 pomieszczenia
            else if (this.pisma.size() >= 3)      //this Person has less than 3 files
                    throw new ProblematicTenantException(this, wynajetePomieszczenia.toString());
                else if(osiedle.hasApartment(this)) {   //checking if this person has already rented an apartment in this osiedle
                        miejsceParkingowe.najemca = this;
                        miejsceParkingowe.dataRozpoczecia = data;
                        miejsceParkingowe.dataZakonczenia = data + iloscdni;
                        wynajetePomieszczenia.add(miejsceParkingowe);
                        WatekCzas.wynajetePomieszczenia.add(miejsceParkingowe); // in this list it will be filtered for the purpose of controlling expiry date

                    }
                else throw new NoApartmenException();
        System.out.println("Garage rented. The date of termination of rental is : "+miejsceParkingowe.dataZakonczenia);
    }


    public void anulowacWynajem(Pomieszczenie pomieszczenie) {
        if (pomieszczenie.najemca == this) {
            if (pomieszczenie.jestPismo) {
                pomieszczenie.jestPismo = false;
                this.pisma.remove(pismo_file);
            }
            pomieszczenie.najemca = null;
            wynajetePomieszczenia.remove(pomieszczenie);
            WatekCzas.wynajetePomieszczenia.remove(pomieszczenie);
          if(pomieszczenie.zameldowani!=null)
            pomieszczenie.zameldowani.clear();
            System.out.println("Rental cancelled");

        }

    }

    public void odnowicWynajem(Pomieszczenie pomieszczenie, int iloscdni) {
        pomieszczenie.dataZakonczenia = pomieszczenie.dataZakonczenia + iloscdni;
        if (pomieszczenie.jestPismo) {
            pomieszczenie.jestPismo = false;
            this.pisma.remove(pismo_file);
        }
            System.out.println("Rental renewed, the new date of termination of rental is "+ pomieszczenie.dataZakonczenia);


    }


    public void zameldowacOsoba(Mieszkanie mieszkanie, Osoba osoba) {
        if (mieszkanie.najemca == this)
            mieszkanie.zameldowani.add(osoba);
        System.out.println("Person registered");
    }


    public void wymeldowacOsoba(Mieszkanie mieszkanie, Osoba osoba) {
        if (mieszkanie.najemca == this)
            mieszkanie.zameldowani.remove(osoba);
        System.out.println("Person removed");
    }


    public void przychowcPojazd(MiejsceParkingowe miejsceParkingowe, Pojazdy pojazd) throws TooManyThingsException {

        if (miejsceParkingowe.najemca == this && miejsceParkingowe.dostepnaPowierzchnia >= pojazd.powierzchnia) {
                miejsceParkingowe.pojazdy.add(pojazd);
                miejsceParkingowe.dostepnaPowierzchnia -= pojazd.powierzchnia;
            System.out.println("The car has been placed");
            pojazd.jestWgarazu = true;
            } else throw new TooManyThingsException();
        }


    public void wyjmowacPojazdy(MiejsceParkingowe miejsceParkingowe, Pojazdy pojazd) {
        if (miejsceParkingowe.najemca == this && miejsceParkingowe.pojazdy.contains(pojazd)) {
            miejsceParkingowe.pojazdy.remove(pojazd);
            miejsceParkingowe.dostepnaPowierzchnia += pojazd.powierzchnia;
            pojazd.jestWgarazu = false;
            System.out.println("The car has been removed");
        }
    }

    public void przychowacPrzedmiot(MiejsceParkingowe miejsceParkingowe, Przedmioty przedmiot) throws TooManyThingsException {
        if (miejsceParkingowe.najemca == this && miejsceParkingowe.dostepnaPowierzchnia >= przedmiot.powierzchnia) {
                miejsceParkingowe.przedmioty.add(przedmiot);
                miejsceParkingowe.dostepnaPowierzchnia -= przedmiot.powierzchnia;
                przedmiot.jestWgarazu = true;
            System.out.println("Object placed in the garage");
            } else throw new TooManyThingsException();

        }


    public void wyjmowacPrzedmiot(MiejsceParkingowe miejsceParkingowe, Przedmioty przedmiot) {
        if (miejsceParkingowe.najemca == this && miejsceParkingowe.przedmioty.contains(przedmiot)) {
            miejsceParkingowe.przedmioty.remove(przedmiot);
            miejsceParkingowe.dostepnaPowierzchnia += przedmiot.powierzchnia;
            przedmiot.jestWgarazu = false;
        }
    }

    public void rejestrStanu() {


        stanOsoba = new File("..\\Projekt\\src\\com\\company\\stanOsoba\\Stan_"+this.nazwisko+".txt");
        mapFile = new HashMap<>();

        wynajetePomieszczenia.sort((r1, r2) -> (int) (r1.rozmiarPowierzchni - r2.rozmiarPowierzchni));

        List<MiejsceParkingowe> miejsceParkingowe = wynajetePomieszczenia.stream().
                filter(x -> x.getClass().getName() == "com.company.MiejsceParkingowe").
                map(x -> (MiejsceParkingowe)x).
                collect(Collectors.toList());

        mapFile.put("Imie", imie );
        mapFile.put("Nazwisko", nazwisko);
        mapFile.put("Pesel", pesel);
        mapFile.put("Adres", adres);
        mapFile.put("Data urodzenia",dataurodz );
        mapFile.put("Pomieszczenia", wynajetePomieszczenia.toString());

       for (MiejsceParkingowe mp: miejsceParkingowe) {
           Collections.sort(mp.przedmioty);
           System.out.println(mp);
           mapFile.put("Zawartosc Pomiesczenia", mp.przedmioty.toString());
       }

        try {
            FileWriter stan = new FileWriter(stanOsoba);
            stan.write(convertWithStream(mapFile));
            stan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String convertWithStream(Map<String, String> map) {  //this idea was taken from https://www.baeldung.com/java-map-to-string-conversion
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }



        @Override
        public String toString() {
            return  " "+this.imie +" "+this.nazwisko+": PESEL number -> " + pesel+ " " ;
        }

        public void wypiszDane(){
            System.out.println("Imie: "+this.imie+", Nazwisko: "+this.nazwisko+ ", pesel: "+this.pesel
            + ", adres: "+ this.adres + ", data urodzenia: "+this.dataurodz);
            System.out.println("Wynajete pomieszczenia: "+this.wynajetePomieszczenia.toString());
            System.out.println("Warning files assigned: "+ this.pisma.toString());;
        }

    }
