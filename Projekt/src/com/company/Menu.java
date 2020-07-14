package com.company;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class Menu {

    public static Osoba wybraneOsoba;
    public static Osiedle wybraneOsiedle;
    public static Pomieszczenie wybranePomieszczenie;
    public static MiejsceParkingowe wybraneMiejsceParkingowe;
    public static Mieszkanie wybraneMieszkanie;
    public static String numerPeselu;
    public static List<Osiedle> listOsiedli = new ArrayList<>();
    public static List<Osoba> listOsoby = new ArrayList<>();
    public static List<Pojazdy> listPojazd = new ArrayList<>();
    public static List<Przedmioty> listPrzedmiot = new ArrayList<>();


    public static void start() {

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("        Main menu:");
        System.out.println("a: Select person  q: Exit");

        switch (scanner.nextLine()) {

            case "q":
                System.exit(0);
                break;
            case "a":
                System.out.println("Insert the PESEL number below " + listOsoby);
                numerPeselu = scanner.nextLine();
                for (Osoba osoba : listOsoby)
                    if (numerPeselu.equals(osoba.pesel)) {
                        wybraneOsoba = osoba;
                    }

                if (wybraneOsoba != null) {

                    System.out.println("You chose " + wybraneOsoba);
                    System.out.println("a: Print info about the person");
                    System.out.println("b: Main menu");
                    System.out.println("c: Show free apartments and garages");
                    System.out.println("d: Rent an apartment or garage");
                    System.out.println("e: Export the person's current status to a file");
                    System.out.println("f: Manage apartment");
                    System.out.println("g: Manage garage");
                    System.out.println("q: Exit ");
                    switch (scanner.nextLine()) {
                        case "e":
                            wybraneOsoba.rejestrStanu();
                            System.out.println("The Information was written.");
                            break;

                        case "b":
                            start();

                        case "c":

                            System.out.println("Select a building from the list: ");
                            System.out.println(listOsiedli);
                            Integer numerId = null;
                            try {
                                numerId = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Search failed. You put a letter instead of a number");
                                start();
                            }

                            //beggining of internal switch for choosing building
                            for (Osiedle osiedle : listOsiedli)
                                if (osiedle.numerID.equals(numerId)) {
                                    wybraneOsiedle = osiedle;
                                    System.out.println("You selected the building number: " + osiedle);
                                    System.out.println("These are the available apartments: ");
                                    for (Pomieszczenie mieszkanie : osiedle.listpomieszczenie)
                                        if (mieszkanie.getClass().getName() == "com.company.Mieszkanie" && mieszkanie.najemca == null)
                                            System.out.print(mieszkanie + ", ");
                                    System.out.println();
                                    System.out.println("These are the available garages: ");
                                    for (Pomieszczenie miejsceparkingowe : osiedle.listpomieszczenie)
                                        if (miejsceparkingowe.getClass().getName() == "com.company.MiejsceParkingowe" && miejsceparkingowe.najemca == null)
                                            System.out.print(miejsceparkingowe + ", ");
                                    System.out.println();
                                }
                            start();


                        case "a":
                            wybraneOsoba.wypiszDane();
                            start();
                            break;


                        case "d":
                            System.out.println("Please choose a building from the list below:");
                            System.out.println(listOsiedli);

                            Integer numerIdOsiedle = null;
                            try {
                                numerIdOsiedle = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Search failed. You put a letter instead of a number");
                                start();
                            }

                            System.out.println("You have selected the building with number: " + numerIdOsiedle);
                            for (Osiedle osiedle : listOsiedli)
                                if (osiedle.numerID.equals(numerIdOsiedle))
                                    wybraneOsiedle = osiedle;

                            System.out.println("a: Rent a garage");
                            System.out.println("b: rent an apartment");
                            System.out.println("c: Main menu");

                            switch (scanner.nextLine()) {
                                case "b":

                                    System.out.println("Select an apartment from the ones below:");
                                    try {
                                        for (Pomieszczenie mieszkanie : wybraneOsiedle.listpomieszczenie)
                                            if (mieszkanie.getClass().getName() == "com.company.Mieszkanie" && mieszkanie.najemca == null)
                                                System.out.print(mieszkanie + ", ");
                                        System.out.println();
                                    } catch (NullPointerException e) {
                                        System.out.println("Rental failed. The building you chose doesn't exist");
                                        start();
                                    }
                                    Integer wybraneMieszkanieID = null;
                                    try {
                                        wybraneMieszkanieID = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Rental failed. You put a letter instead of a number");
                                        start();
                                    }
                                    System.out.println("Please indicate how many days you would like to rent it: ");
                                    Integer iloscdniMieszkanie = null;

                                    try {
                                        iloscdniMieszkanie = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Rental failed. You put a letter instead of a number");
                                        start();
                                    }

                                    for (Pomieszczenie mieszkanie : wybraneOsiedle.listpomieszczenie)
                                        if (mieszkanie.getClass().getName() == "com.company.Mieszkanie" && mieszkanie.numerIdentifikacyjne.equals(wybraneMieszkanieID)) {
                                            try {
                                                wybraneOsoba.wynajmowacMieszkanie(mieszkanie, iloscdniMieszkanie, WatekCzas.getData());
                                            } catch (ProblematicTenantException | TooManyRentalsException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    break;

                                case "a":
                                    System.out.println("Select a garage from the ones below:");
                                    try {
                                        for (Pomieszczenie garage : wybraneOsiedle.listpomieszczenie)
                                            if (garage.getClass().getName() == "com.company.MiejsceParkingowe" && garage.najemca == null)
                                                System.out.print(garage + ", ");
                                        System.out.println();
                                    } catch (NullPointerException e) {
                                        System.out.println("Rental failed. The building you chose doesn't exist");
                                        start();
                                    }
                                    Integer wybraneGarageID = null;
                                    try {
                                        wybraneGarageID = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Rental failed. You put a letter instead of a number");
                                        start();
                                    }
                                    System.out.println("Please indicate how many days you would like to rent it: ");
                                    Integer iloscdniGarage = null;
                                    try {
                                        iloscdniGarage = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Rental failed. You put a letter instead of a number");
                                        start();
                                    }
                                    for (Pomieszczenie garage : wybraneOsiedle.listpomieszczenie)
                                        if (garage.getClass().getName() == "com.company.MiejsceParkingowe" && garage.numerIdentifikacyjne.equals(wybraneGarageID)) {
                                            try {
                                                wybraneOsoba.wynajmowacMiescaParkingowe(wybraneOsiedle, garage, iloscdniGarage, WatekCzas.getData());
                                            } catch (ProblematicTenantException | TooManyRentalsException | ParkingAlreadyRentedException | NoApartmenException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    break;
                                case "c":
                                    start();

                            }
                            start();
                            break;

                        case "g":

                            System.out.println("these are the current garages of " + wybraneOsoba + ":");
                            for (Pomieszczenie garage : wybraneOsoba.wynajetePomieszczenia)
                                if (garage.getClass().getName() == "com.company.MiejsceParkingowe")
                                    System.out.print(garage.numerIdentifikacyjne + ", ");
                            System.out.println();
                            System.out.println("Select a garage number: ");

                            Integer numerIdGarage = null;
                            try {
                                numerIdGarage = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Search failed. You put a letter instead of a number");
                                start();
                            }
                            try {
                                for (Pomieszczenie garage : wybraneOsoba.wynajetePomieszczenia)
                                    if (garage.numerIdentifikacyjne == numerIdGarage && garage.getClass().getName() == "com.company.MiejsceParkingowe")
                                        wybraneMiejsceParkingowe = (MiejsceParkingowe) garage;

                                System.out.println();
                                System.out.println("These are the list of objects inside: " + wybraneMiejsceParkingowe.przedmioty.toString());
                                System.out.println("These are the cars inside: " + wybraneMiejsceParkingowe.pojazdy.toString());
                                System.out.println();
                                System.out.println("a: Put object in garage");
                                System.out.println("b: Take object from garage");
                                System.out.println("c: Put car in garage");
                                System.out.println("d: Take car from garage");
                                System.out.println("e: Inspect for free objects nearby");
                                System.out.println("f: Inspect for free vehicles nearby");
                                System.out.println("g: Renew rental");
                                System.out.println("h: Cancel rental");
                                System.out.println("i: Back to main menu");
                                System.out.println("q: Exit the program");
                            } catch (NullPointerException e) {
                                System.out.println("Garage not found");
                                start();
                            }
                            java.util.Scanner scanner1 = new java.util.Scanner(System.in); //Scanner doesn't work anymore, opened Scanner1
                            switch (scanner1.nextLine()) {

                                case "a":
                                    System.out.println("Insert the name of the object: ");
                                    String nazwaPrzedmiotu = scanner1.nextLine();

                                    for (Przedmioty przedmiot : listPrzedmiot)
                                        if (!przedmiot.jestWgarazu && przedmiot.nazwa.equals(nazwaPrzedmiotu)) {
                                            try {

                                                wybraneOsoba.przychowacPrzedmiot(wybraneMiejsceParkingowe, przedmiot);
                                            } catch (TooManyThingsException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    break;
                                case "b":
                                    System.out.println("Insert the name of the object: ");
                                    String nazwaPrzedmiotu1 = scanner1.nextLine();


                                    Przedmioty doUsuniecia = null;//avoiding concurrent modificationException
                                    for (Przedmioty przedmiot : wybraneMiejsceParkingowe.przedmioty) {
                                        if (przedmiot.nazwa.equals(nazwaPrzedmiotu1))
                                            doUsuniecia = przedmiot;
                                    }
                                    wybraneOsoba.wyjmowacPrzedmiot(wybraneMiejsceParkingowe, doUsuniecia);
                                    break;

                                case "c":

                                    System.out.println("Insert the name of the vehicle: ");
                                    String nazwaPojazd = scanner1.nextLine();

                                    for (Pojazdy pojazd : listPojazd)
                                        if (!pojazd.jestWgarazu && pojazd.nazwa.equals(nazwaPojazd)) {
                                            try {

                                                wybraneOsoba.przychowcPojazd(wybraneMiejsceParkingowe, pojazd);
                                            } catch (TooManyThingsException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    break;
                                case "d":
                                    System.out.println("Insert the name of the vehicle: ");
                                    String nazwaPojazd1 = scanner1.nextLine();
                                    Pojazdy doUsuniecia1 = null;//avoiding concurrent modificationException
                                    for (Pojazdy pojazd : wybraneMiejsceParkingowe.pojazdy) {
                                        if (pojazd.nazwa.equals(nazwaPojazd1))
                                            doUsuniecia1 = pojazd;
                                        System.out.println("");
                                    }
                                    wybraneOsoba.wyjmowacPojazdy(wybraneMiejsceParkingowe, doUsuniecia1);
                                    break;
                                case "e":
                                    System.out.println("These are the free objects : ");
                                    for (Przedmioty przedmiot : listPrzedmiot)
                                        if (!przedmiot.jestWgarazu)
                                            System.out.print(przedmiot + ", ");
                                    System.out.println("");
                                    break;

                                case "f":
                                    System.out.println("These are the free vehicles : ");
                                    for (Pojazdy pojazd : listPojazd)
                                        if (!pojazd.jestWgarazu)
                                            System.out.print(pojazd + ", ");
                                    System.out.println("");
                                    break;

                                case "g":
                                    System.out.println("Insert for how many days you will renew the garage");

                                    Integer iloscdni = null;
                                    try {
                                        iloscdni = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Renewal failed. You put a letter instead of a number");
                                        start();
                                    }
                                    wybraneOsoba.odnowicWynajem(wybraneMiejsceParkingowe, iloscdni);
                                    break;
                                case"h":
                                    wybraneOsoba.anulowacWynajem(wybraneMiejsceParkingowe);
                                case "i":
                                    start();
                                case "q":
                                    System.exit(0);

                            } //end of inside garage switch
                            start();

                        case "q":
                            System.exit(0);
                            break;

                        case "f":
                            System.out.println("these are the current apartments of " + wybraneOsoba + ":");
                            for (Pomieszczenie mieszkanie : wybraneOsoba.wynajetePomieszczenia)
                                if (mieszkanie.getClass().getName() == "com.company.Mieszkanie"){
                                    System.out.print(mieszkanie.numerIdentifikacyjne + ", ");
                            System.out.println();
                            System.out.println("Select an apartment number: ");

                            Integer numerIdApartment = null;
                            try {
                                numerIdApartment = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Search failed. You put a letter instead of a number");
                                start();
                            }
                            try {
                                for (Pomieszczenie mieszkanie1 : wybraneOsoba.wynajetePomieszczenia)
                                    if (mieszkanie.numerIdentifikacyjne == numerIdApartment && mieszkanie1.getClass().getName() == "com.company.Mieszkanie")
                                        wybraneMieszkanie = (Mieszkanie) mieszkanie1;

                                System.out.println();
                                System.out.println("These are the people currently living in this apartment : " + wybraneMieszkanie.zameldowani.toString());
                                System.out.println("a: Register another person to live in");
                                System.out.println("b: Remove a person currently living in");
                                System.out.println("c: Cancel the rental");
                                System.out.println("d: Renew the rental");
                                System.out.println("e: Main menu");
                                System.out.println("q: Exit");

                            } catch (NullPointerException e) {
                                System.out.println("Apartment not found");
                                start();
                            }

                            java.util.Scanner scanner2 = new java.util.Scanner(System.in);
                            switch (scanner2.nextLine()) {
                                case "a":
                                    System.out.println("Insert the pesel number of the person you want to register");
                                    String numerPesel = null;

                                    numerPesel = scanner2.nextLine();


                                    for (Osoba osoba : listOsoby)
                                        if (osoba.pesel.equals(numerPesel))
                                            wybraneOsoba.zameldowacOsoba(wybraneMieszkanie, osoba);

                                    break;
                                case "b":

                                    System.out.println("Insert the Pesel number of the person you wish to remove: ");
                                    numerPesel = scanner2.nextLine();

                                    Osoba doUsuniecia = null;//avoiding concurrent modificationException
                                    for (Osoba osoba : wybraneMieszkanie.zameldowani) {
                                        if (osoba.pesel.equals(numerPesel) && !osoba.pesel.equals(wybraneOsoba.pesel))
                                            doUsuniecia = osoba;
                                    }
                                    wybraneOsoba.wymeldowacOsoba(wybraneMieszkanie, doUsuniecia);
                                    break;


                                case "c":
                                    wybraneOsoba.anulowacWynajem(wybraneMieszkanie);
                                    break;
                                case "d":
                                    System.out.println("Insert for how many days you will renew the apartment");

                                    Integer iloscdni = null;
                                    try {
                                        iloscdni = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Renewal failed. You put a letter instead of a number");
                                        start();
                                    }
                                    wybraneOsoba.odnowicWynajem(wybraneMieszkanie, iloscdni);
                                    break;

                                case "e":
                                    start();
                                case "q":
                                    System.exit(0);
                            }
                    }else System.out.println("The person is not renting any apartment");start();

                    }//end of garage check content switch

                } else {
                    System.out.println("Couldn't find that person.");
                    start();
                }//if osobawybrana != null

            default:
                start();

        }//end of menu switch

    }

}



