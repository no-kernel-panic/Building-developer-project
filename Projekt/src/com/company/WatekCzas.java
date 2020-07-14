package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Thread.sleep;

public class WatekCzas {

    private static int data; //current date
    public static  List<Pomieszczenie> wynajetePomieszczenia = new ArrayList<>();
    public static   List<Pomieszczenie> filtrowanePomieszczenia = new ArrayList<>();//Keeping the apartments which date is overdue


    synchronized public static void uplywCzasu() {
        //time simulation
        while (true) {
            try {
                sleep(5000);
                data++;
                //System.out.println(data);
                wyslijPismo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public static  void streamcheck() {
        while (true)
            try {
                sleep(10000);
                filtrowanePomieszczenia = wynajetePomieszczenia.stream()
                        .filter(x -> x != null && x.dataZakonczenia + 30 < data && x.jestPismo)
                        .collect(Collectors.toList());

                for (Pomieszczenie p : filtrowanePomieszczenia) {
                    p.wyczyscicPomiesczenie();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }


    public static int getData() {
        return data;
    }


    public static void wyslijPismo () {

                     wynajetePomieszczenia.stream()
                    .filter(x -> x.dataZakonczenia < data && !x.jestPismo) //if some space due date expired and doesnt have a file assigned
                    .forEach(x -> {
                        pisacPismo(x.najemca); // Writing the file
                        x.jestPismo = true; // the file has been assigned
                        x.najemca.pisma.add(x.najemca.pismo_file);
                        //  assigning the file
                    });
    }

        public static void pisacPismo (Osoba o){
            File pismo_file;
            try {
                pismo_file = new File("..\\Projekt\\src\\com\\company\\pisma\\" + o.nazwisko + ".txt");
                FileWriter pismo = new FileWriter(pismo_file);
                pismo.write(o.imie);
                pismo.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
