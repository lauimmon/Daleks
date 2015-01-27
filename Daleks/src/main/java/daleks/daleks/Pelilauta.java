/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;


public class Pelilauta {
    
    private List<Liikkuva> hahmot;
    private final int kokoX;
    private final int kokoY;

    public Pelilauta(int xkoko, int ykoko) {
        this.hahmot = new ArrayList<Liikkuva>();
        kokoX = xkoko;
        kokoY = ykoko;
    }

    public int getKokoX() {
        return kokoX;
    }

    public int getKokoY() {
        return kokoY;
    }
    
    public boolean onkoRuutuLaudalla(Ruutu ruutu) {
         return ruutu.getX() < kokoX &&  ruutu.getY() < kokoY
                && ruutu.getX() >= 0 && ruutu.getY() >= 0;
    }
    
    public void lisaaHahmoLaudalle(Liikkuva hahmo) {
        if (onkoRuutuLaudalla(hahmo.getRuutu())) {
            hahmot.add(hahmo);
        }
    }
    
    public void poistaHahmoLaudalta(Liikkuva hahmo) {
        hahmot.remove(hahmo);
    }
    
    public void tulostaHahmot() {
        for (Liikkuva liikkuva : hahmot) {
            System.out.println(liikkuva);
        }
    }

    public List<Liikkuva> getHahmot() {
        return hahmot;
    }
    
    public boolean onkoHahmoLaudalla(Liikkuva hahmo) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.equals(hahmo)) {
                return true;
            }
        }
        return false;
    }
    
    public Tyyppi minkaTyyppinenHahmoOnRuudussa(Ruutu ruutu) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(ruutu)) {
                return liikkuva.getTyyppi();
            }
        }
        return Tyyppi.TYHJA;
    }
    
    public void liikuta(Liikkuva liikkuva, int x, int y){
        Ruutu ruutu = new Ruutu(liikkuva.getRuutu().getX() + x, liikkuva.getRuutu().getY() + y);
        if (onkoRuutuLaudalla(ruutu)) {
            liikkuva.liiku(ruutu);
        }
    }
    
    public Liikkuva getPelaaja() {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getTyyppi().equals(Tyyppi.PELAAJA)) {
                return liikkuva;
            }
        }
        return null;
    }
    
    public void rajaytaPommi(){
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        List<Ruutu> ruudut = ymparoivatRuudut(pelaajanRuutu);
        for (Liikkuva liikkuva : hahmot) {
            if (ruudut.contains(liikkuva.getRuutu())) {
                poistaHahmoLaudalta(liikkuva);
            }
        }
    }

    public List<Ruutu> ymparoivatRuudut(Ruutu ruutu) {
        List<Ruutu> ruudut = new ArrayList<Ruutu>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Ruutu ruutu1 = new Ruutu(ruutu.getX()+i, ruutu.getY()+j);
                if (i != 0 && j != 0 && onkoRuutuLaudalla(ruutu1)) {
                    ruudut.add(ruutu1);
                }
            }
        }
        return ruudut;
    }
    
    public void teleporttaaPelaaja() {
        Random random = new Random();
        while (true) {
            Ruutu ruutu = new Ruutu(random.nextInt(kokoX), random.nextInt(kokoY));
            if (minkaTyyppinenHahmoOnRuudussa(ruutu).equals(Tyyppi.TYHJA)) {
                getPelaaja().liiku(ruutu);
                return;
            }
        }
    }
    
}
