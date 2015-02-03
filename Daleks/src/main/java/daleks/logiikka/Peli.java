/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.logiikka;

import daleks.daleks.*;
import java.util.*;

/**
 *
 * @author lauimmon
 */
public class Peli {
    
    private Pelilauta lauta;
    private List<Liikkuva> hahmot;

    public Peli(Pelilauta lauta) {
        this.lauta = lauta;
        hahmot = new ArrayList<Liikkuva>();
    }
    
    public Pelilauta getLauta() {
        return lauta;
    }
    
    public List<Liikkuva> getHahmot() {
        return hahmot;
    }
    
    public Liikkuva getPelaaja() {
        for (Liikkuva hahmo : hahmot) {
            if (hahmo.getTyyppi().equals(Tyyppi.PELAAJA) || hahmo.getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA)) {
                return hahmo;
            }
        }
        return null;
    }
    
    public Map<Ruutu, Tyyppi> getRuudut() {
        Map<Ruutu, Tyyppi> tulos = new TreeMap<Ruutu, Tyyppi>();
        for (Liikkuva liikkuva : hahmot) {
            tulos.put(liikkuva.getRuutu(), liikkuva.getTyyppi());
        }
        return tulos;
    }

    public void lisaaHahmo(Liikkuva hahmo) {
        if (lauta.onkoRuutuLaudalla(hahmo.getRuutu())) {
            hahmot.add(hahmo);
        }
    }
    
    public void poistaHahmo(Liikkuva hahmo) {
        hahmot.remove(hahmo);
    }
    
    public Tyyppi mikaTyyppiRuudussa(Ruutu ruutu) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(ruutu)) {
                return liikkuva.getTyyppi();
            }
        }
        return Tyyppi.TYHJA;
    }

    public void liikutaPelaajaa(int x, int y){
        Liikkuva pelaaja = getPelaaja();
        Ruutu ruutu = new Ruutu(pelaaja.getRuutu().getX() + x, pelaaja.getRuutu().getY() + y);
        if (lauta.onkoRuutuLaudalla(ruutu) && !mikaTyyppiRuudussa(ruutu).equals(Tyyppi.KUOLLUTDALEK)) {
            pelaaja.liiku(ruutu);
        }
        osuukoPelaajaDalekiin();
    }
    
    public void liikutaPelaajaa(Ruutu ruutu){
        Liikkuva pelaaja = getPelaaja();
        if (lauta.onkoRuutuLaudalla(ruutu) && !mikaTyyppiRuudussa(ruutu).equals(Tyyppi.KUOLLUTDALEK)) {
            pelaaja.liiku(ruutu);
        }
        osuukoPelaajaDalekiin();
    }
    
    private void osuukoPelaajaDalekiin() {
        Liikkuva pelaaja = getPelaaja();
        for (Liikkuva hahmo : getHahmot()) {
            if (hahmo.getRuutu().equals(pelaaja.getRuutu()) && !hahmo.getTyyppi().equals(Tyyppi.PELAAJA)) {
                pelaaja.kuole();
            }
        }
    }
    
    public void teleporttaaPelaaja() {
        Random random = new Random();
        while (true) {
            Ruutu ruutu = new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()));
            if (mikaTyyppiRuudussa(ruutu).equals(Tyyppi.TYHJA)) {
                getPelaaja().liiku(ruutu);
                return;
            }
        }
    }

    public void rajaytaPommi(){
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        List<Ruutu> ruudut = lauta.ymparoivatRuudut(pelaajanRuutu);
        List<Liikkuva> poistettavat = new ArrayList<Liikkuva>();
        for (Liikkuva hahmo : getHahmot()) {
            if (ruudut.contains(hahmo.getRuutu()) && hahmo.getTyyppi().equals(Tyyppi.DALEK)) {
                poistettavat.add(hahmo);
            }
        }
        for (Liikkuva hahmo : poistettavat) {
            poistaHahmo(hahmo);
        }
    }
    
    public void liikutaDalekejaPelaajaaPain() {
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        for (Liikkuva hahmo : getHahmot()) {
            if (hahmo.getTyyppi().equals(Tyyppi.DALEK)) {
                List<Ruutu> dalekinYmparoivatRuudut = lauta.ymparoivatRuudut(hahmo.getRuutu());
                Ruutu parasRuutu = dalekinYmparoivatRuudut.get(0);
                for (Ruutu ruutu : dalekinYmparoivatRuudut) {
                    if (ruutu.etaisyysRuudusta(pelaajanRuutu) < parasRuutu.etaisyysRuudusta(pelaajanRuutu)) {
                        parasRuutu = ruutu;
                    }
                }
                hahmo.liiku(parasRuutu);
            }
        }
        tapaTormanneetDalekit();
        osuukoPelaajaDalekiin();
    }
    
    private void tapaTormanneetDalekit() {
        for (int i = 0; i < hahmot.size(); i++) {
            for (int j = i+1; j < hahmot.size(); j++) {
                if (hahmot.get(i).getRuutu().equals(hahmot.get(j).getRuutu()) && !hahmot.get(i).getTyyppi().equals(Tyyppi.PELAAJA) && !hahmot.get(j).getTyyppi().equals(Tyyppi.PELAAJA)) {
                    hahmot.get(i).kuole();
                    hahmot.get(j).kuole();
                }
            }
        }
    }

    public void tulostaHahmot() {
        for (Liikkuva liikkuva : hahmot) {
            System.out.println(liikkuva);
        }
    }
    
    public void tulostaLauta() {
        Map<Ruutu, Tyyppi> ruudut = getRuudut();
        for (int i = 0; i < lauta.getKokoX(); i++) {
            for (int j = 0; j < lauta.getKokoY(); j++) {
                if (ruudut.containsKey(new Ruutu(i, j))) {
                    System.out.print(ruudut.get(new Ruutu(i, j)).toString());
                } else  System.out.print(Tyyppi.TYHJA.toString());
            }
            System.out.println();
        }
    }

    

    
    
    
}
