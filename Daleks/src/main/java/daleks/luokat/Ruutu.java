/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import java.util.Comparator;

/**
 *
 * @author lauimmon
 */
public class Ruutu implements Comparator<Ruutu>, Comparable<Ruutu> {
    
    private int x;
    private int y;

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
    
    

    @Override
    public int compareTo(Ruutu r) {
        if (this.x == r.getX()) {
            return this.y - r.getY();
        }
        return this.x - r.getX();
    }

    @Override
    public int compare(Ruutu r1, Ruutu r2) {
        if (r1.getX() == r2.getX()) {
            return r1.getY() - r2.getY();
        }
        return r1.getX() - r2.getX();
    }
    
}
