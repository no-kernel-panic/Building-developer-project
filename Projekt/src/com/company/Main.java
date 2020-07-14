package com.company;


public class Main {

    public static void main(String[] args) {

        Runnable czas = () -> {
            WatekCzas.uplywCzasu();
        };
        new Thread(czas).start();

        Runnable czas1 = () -> {
            WatekCzas.streamcheck();
        };
        new Thread(czas1).start();

        Osiedle osiedle = new Osiedle();
        Osiedle osiedle1 = new Osiedle();

        Mieszkanie mieszkanie0 = new Mieszkanie(osiedle, 90);
        Mieszkanie mieszkanie1 = new Mieszkanie(osiedle, 80);
        Mieszkanie mieszkanie2 = new Mieszkanie(osiedle, 65);
        Mieszkanie mieszkanie3 = new Mieszkanie(osiedle1, 100);
        Mieszkanie mieszkanie4 = new Mieszkanie(osiedle1, 85);
        Mieszkanie mieszkanie5 = new Mieszkanie(osiedle1, 120);

        MiejsceParkingowe miejsceParkingowe0=  new MiejsceParkingowe(osiedle, 30);
        MiejsceParkingowe miejsceParkingowe1 = new MiejsceParkingowe(osiedle, 40);
        MiejsceParkingowe miejsceParkingowe2 = new MiejsceParkingowe(osiedle1, 25);
        MiejsceParkingowe miejsceParkingowe3= new MiejsceParkingowe(osiedle1, 25);

        Przedmioty przedmiot = new Przedmioty("Thor hammer",5);
        Przedmioty przedmiot1 = new Przedmioty("Holy grail",4);
        Przedmioty przedmiot2= new Przedmioty("Teleporter",24);
        Przedmioty przedmiot3 = new Przedmioty("Dark matter telescope",24);
        Pojazdy Unimog = new SamochodTerenowy("Destroyer",30.0, "Ford","Unimog", "Exreme",60, 6, "23454", "23432");
        Pojazdy amfibia = new Amfibia("Yellow Submarine", 10.0, "beatle", "dual", "triple", 40, 4);
        Pojazdy lodz = new Lodz("Alma", 10.0, 3);

        Osoba osoba1 = new Osoba("Anakin", "Skywalker", "93012115617", "Somewhere in the galaxy", "tooOldtoregister");
        Osoba osoba2 = new Osoba("Lord", "Voldemort", "1", "Not in Hogwarts", "230");
        Osoba osoba3 = new Osoba("Hernestito", "Santos", "33043321212", "esteban quito 40", "230195");
        Osoba osoba4 = new Osoba("Stephen", "Hawkins", "430432441234", "el melenero 22", "301260");
        Osoba osoba5 = new Osoba("Chulipan", "Santos", "5457824542", "Cantinflas y su banda 300", "201083");
        Osoba osoba6 = new Osoba("Fredy", "oneill", "46789976543", "Wall street 23", "231289");


        Menu menu= new Menu();
        Menu.start();

    }

}











