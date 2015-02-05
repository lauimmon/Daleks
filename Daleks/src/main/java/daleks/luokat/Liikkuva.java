/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import java.util.*;

/**
 * Abstrakti luokka Liikkuva edustaa kaikkia pelilaudalla liikkuvia olioita.
 * Luokat Pelaaja ja Dalek perivät Liikkuva-luokan.
 * Kaikki liikkuvat toteuttavat metodit liiku ja kuole.
 * Liiku-metodiin voi antaa kahdenlaisia syötteitä, joko ruudun mihin
 * halutaan tai ruutumäärät mitä korkeus- ja leveyssuunnassa halutaan liikkua.
 * 
 * @author lauimmon
 */
public abstract class Liikkuva implements Comparator<Liikkuva>, Comparable<Liikkuva> {
    
    private Ruutu ruutu;
    private Tyyppi tyyppi;

    /**
     * Konstruktorissa Liikkuvalle asetetaan ruutu.
     * 
     * @param ruutu on Liikkuvalle asetettava ruutu
     */
    public Liikkuva(Ruutu ruutu) {
        this.ruutu = ruutu;
    }
    
    /**
     * Metodi liikuttaa Liikkuvan haluttuun ruutuun.
     * 
     * @param ruutu johon Liikkuva halutaan liikuttaa 
     */
    public void liiku(Ruutu ruutu) {
        this.ruutu = ruutu;
    }
    
    /**
     * Metodi liikuttaa Liikkuvaa halutun ruutumäärän korkeus- ja leveyssuunnassa.
     * 
     * @param x, miten monta ruutua liikutaan korkeussuunnassa
     * @param y, miten monta ruutua liikutaan leveyssuunnassa
     */
    public void liiku(int x, int y) {
        ruutu = new Ruutu(ruutu.getX()+x, ruutu.getY()+y);
    }

    public Ruutu getRuutu() {
        return ruutu;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi.toString() + " " + ruutu.toString();
    }


    @Override
    public int compare(Liikkuva l1, Liikkuva l2) {
        Ruutu ruutu1 = l1.getRuutu();
        Ruutu ruutu2 = l2.getRuutu();
        if (ruutu1.equals(ruutu2)) {
            return 0;
        }
        if (ruutu1.compareTo(ruutu2) < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public int compareTo(Liikkuva l) {
        Ruutu ruutu1 = this.getRuutu();
        Ruutu ruutu2 = l.getRuutu();
        if (ruutu1.equals(ruutu2)) {
            return 0;
        }
        if (ruutu1.compareTo(ruutu2) < 0) {
            return -1;
        }
        return 1;
    }
    
    /**
     * Kaikilla Liikkuvilla on metodi kuole, joka muuttaa Liikkuvan tyypin.
     */
    public abstract void kuole();
    
    
    
    
}
