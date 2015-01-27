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
        if (onkoRuutuLaudalla(hahmo.getRuutu()) && minkaTyyppinenHahmoOnRuudussa(hahmo.getRuutu()).equals(Tyyppi.TYHJA)) {
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
    
    public void tulostaLauta() {
        Map<Ruutu, Tyyppi> ruudut = getRuudut();
        for (int i = 0; i < kokoX; i++) {
            for (int j = 0; j < kokoY; j++) {
                if (ruudut.containsKey(new Ruutu(i, j))) {
                    System.out.print(ruudut.get(new Ruutu(i, j)).toString());
                } else  System.out.print(Tyyppi.TYHJA.toString());
            }
            System.out.println();
        }
    }

    public List<Liikkuva> getHahmot() {
        return hahmot;
    }
    
    public Map<Ruutu, Tyyppi> getRuudut() {
        Map<Ruutu, Tyyppi> tulos = new TreeMap<Ruutu, Tyyppi>();
        for (Liikkuva liikkuva : hahmot) {
            tulos.put(liikkuva.getRuutu(), liikkuva.getTyyppi());
        }
        return tulos;
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
    
    public void liikutaPelaajaa(int x, int y){
        Ruutu ruutu = new Ruutu(getPelaaja().getRuutu().getX() + x, getPelaaja().getRuutu().getY() + y);
        if (onkoRuutuLaudalla(ruutu)) {
            getPelaaja().liiku(ruutu);
        }
    }
    
    public void liikutaPelaajaa(Ruutu ruutu){
        if (onkoRuutuLaudalla(ruutu)) {
            getPelaaja().liiku(ruutu);
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
        List<Liikkuva> poistettavat = new ArrayList<Liikkuva>();
        for (Liikkuva liikkuva : hahmot) {
            if (ruudut.contains(liikkuva.getRuutu())) {
                poistettavat.add(liikkuva);
            }
        }
        for (Liikkuva liikkuva : poistettavat) {
            poistaHahmoLaudalta(liikkuva);
        }
    }

    public List<Ruutu> ymparoivatRuudut(Ruutu ruutu) {
        List<Ruutu> ruudut = new ArrayList<Ruutu>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Ruutu ruutu1 = new Ruutu(ruutu.getX()+i, ruutu.getY()+j);
                if (!(i == 0 && j == 0) && onkoRuutuLaudalla(ruutu1)) {
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
    
    public void liikutaDalekejaPelaajaaPain() {
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getTyyppi().equals(Tyyppi.DALEK)) {
                List<Ruutu> dalekinYmparoivatRuudut = ymparoivatRuudut(liikkuva.getRuutu());
                Ruutu parasRuutu = dalekinYmparoivatRuudut.get(0);
                for (Ruutu ruutu : dalekinYmparoivatRuudut) {
                    if (ruutu.etaisyysRuudusta(pelaajanRuutu) < parasRuutu.etaisyysRuudusta(pelaajanRuutu)) {
                        parasRuutu = ruutu;
                    }
                }
                liikkuva.liiku(parasRuutu);
            }
        }
    }
    
}
