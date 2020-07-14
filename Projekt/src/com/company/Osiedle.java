package com.company;
import java.util.ArrayList;
import java.util.List;

public class Osiedle {

    Integer numerID;
    static int counternumerID = 0;
    List<Pomieszczenie> listpomieszczenie;


    public Osiedle() {


        numerID = counternumerID;
        counternumerID++;
        Menu.listOsiedli.add(this);// keeping a list for accessing through the menu
        listpomieszczenie = new ArrayList<>();

        }

    public Boolean hasApartment(Osoba o){
        for(Pomieszczenie p: listpomieszczenie){
            if (p.getClass().getName()=="com.company.Mieszkanie"){
                if (p.zameldowani.contains(o))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
      return numerID.toString();
    }
}


