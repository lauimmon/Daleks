/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import java.util.Comparator;

/**
 * Ruutu kuvaa pisteitä laudalla. 
 * Ruudulla on korkeus- ja leveyskoordinaatit.
 * 
 * @author lauimmon
 */
public class Ruutu implements Comparator<Ruutu>, Comparable<Ruutu> {
    
    private int x;
    private int y;

    /**
     * Konstruktori asettaa ruudun korkeus- ja leveyskoordinaatit
     * @param x korkeuskoordinaatti
     * @param y leveyskoordinaatti
     */
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
    
    /**
     * Metodi laskee ruudun etäisyyden toisesta 
     * ruudusta niiden koordinaattien perusteella.
     * 
     * @param ruutu, ruutu josta etäisyys halutaan mitata
     * @return etäisyys ruudusta
     */
    public double etaisyysRuudusta(Ruutu ruutu) {
        return Math.sqrt(Math.pow(this.x - ruutu.getX(), 2)
                + Math.pow(this.y - ruutu.getY(), 2));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }
    
    /**
     * Equals-metodia on muutettu niin, että kaksi ruutua ovat samat, 
     * jos niiden korkeus- ja leveyskoordinaatit ovat samat.
     * 
     * @param obj objekti johon ruutua verrataan
     * @return true jos objekti on sama, false jos se ei ole sama
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruutu other = (Ruutu) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    /**
     * compareTo-metodia on muutettu niin, että se vertaa ruutuja 
     * ensin korkeuskoordinaatin perusteella ja toissijaisesti 
     * leveyskoordinaattien perusteella.
     * 
     * @param r ruutu johon verrataan
     * @return 0, jos ruudut ovat samat, negatiivinen luku 
     * jos verrattava ruutu on laudalla myöhempi ja 
     * positiivinen jos verrattava ruutu on laudalla aiempi
     */
    @Override
    public int compareTo(Ruutu r) {
        if (this.x == r.getX()) {
            return this.y - r.getY();
        }
        return this.x - r.getX();
    }

    /**
     * compare-metodia on muutettu niin, että se vertaa ruutuja 
     * ensin korkeuskoordinaatin perusteella ja toissijaisesti 
     * leveyskoordinaattien perusteella.
     * 
     * @param r1 ensimmäinen ruutu
     * @param r2 toinen ruutu
     * @return 0, jos ruudut ovat samat, negatiivinen luku 
     * jos toinen ruutu on laudalla ensimmäistä myöhempi ja 
     * positiivinen jos toinen ruutu on laudalla ensimmäistä aiempi
     */
    @Override
    public int compare(Ruutu r1, Ruutu r2) {
        if (r1.getX() == r2.getX()) {
            return r1.getY() - r2.getY();
        }
        return r1.getX() - r2.getX();
    }
    
}
