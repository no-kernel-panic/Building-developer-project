package com.company;

public  class Przedmioty implements Comparable<Przedmioty> {

    String nazwa;
    double powierzchnia;
    boolean jestWgarazu;

    public Przedmioty(String nazwa, double powierzchnia) {
        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;
        jestWgarazu = false;
        Menu.listPrzedmiot.add(this);
    }

    @Override
    public int compareTo(Przedmioty o) {
        if (this.powierzchnia>o.powierzchnia)
            return -1;
            else if (this.powierzchnia<o.powierzchnia)
                return 1;
                    return this.nazwa.compareTo(o.nazwa);

    }

    @Override
    public String toString() {
        return nazwa;
    }
}
