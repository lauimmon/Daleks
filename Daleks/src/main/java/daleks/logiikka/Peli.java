/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.logiikka;

import daleks.luokat.*;
import java.util.*;

/**
 * Peli-luokka sisältää kaiken pelin logiikan. Se hoitaa Liikuvien liikuttelun ym. toiminnot.
 * 
 * @author lauimmon
 */
public class Peli {
    
    private final Pelilauta lauta;
    private List<Liikkuva> hahmot;
    private int pommit;
    private int teleportit;

    /**
     * Pelille asetetaan luotaessa lauta, yksi pelaaja-olio ja haluttu määrä dalekeja.
     * Pelaajan ja dalekien ruudut arvotaan.
     * Jos dalekienMaara on suurempi tai yhtä suuri kuin laudan ruutujen
     * määrä, lopetetaan dalekien luominen kun lauta on täynnä.
     * 
     * @param leveys pelille asetettavan laudan leveys
     * @param korkeus laudan korkeus
     * @param dalekienMaara eli kuinka monta dalekia peliin luodaan
     * @param pommit pommien määrä
     * @param teleportit teleporttien määrä
     */
    public Peli(int leveys, int korkeus, int dalekienMaara, int pommit, int teleportit) {
        lauta = new Pelilauta(leveys, korkeus);
        hahmot = new ArrayList<Liikkuva>();
        this.pommit = pommit;
        this.teleportit = teleportit;
        
        Random random = new Random();
        lisaaHahmo(new Pelaaja(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        while (hahmot.size() != Math.min(dalekienMaara + 1, lauta.getKokoX()*lauta.getKokoY())) {
            lisaaHahmo(new Dalek(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        }
    }
    
    public Pelilauta getLauta() {
        return lauta;
    }
    
    public List<Liikkuva> getHahmot() {
        return hahmot;
    }

    public int getPommit() {
        return pommit;
    }

    public int getTeleportit() {
        return teleportit;
    }
    
    /**
     * Metodi hakee Pelissä olevien Liikkuvien joukosta Pelaajan.
     * 
     * @return palauttaa pelaajan
     */
    public Liikkuva getPelaaja() {
        for (Liikkuva hahmo : hahmot) {
            if (hahmo.getTyyppi().equals(Tyyppi.PELAAJA) || hahmo.getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA)) {
                return hahmo;
            }
        }
        return null;
    }
    
    /**
     * Metodi käy kaikki pelin Liikkuvat läpi ja järjestää ne ruutujensa mukaan.
     * 
     * @return palauttaa mapin, johon Liikkuvat on järjestetty ruutujensa suuruusjärjestyksen mukaan
     */
    public Map<Ruutu, Tyyppi> getRuudut() {
        Map<Ruutu, Tyyppi> tulos = new TreeMap<Ruutu, Tyyppi>();
        for (Liikkuva liikkuva : hahmot) {
            tulos.put(liikkuva.getRuutu(), liikkuva.getTyyppi());
        }
        return tulos;
    }

    /**
     * Metodi lisää Liikkuva-olion peliin. Pelissä ei voi olla kaksi pelaajaa.
     * 
     * @param hahmo Liikkuva, joka halutaan lisätä
     */
    public void lisaaHahmo(Liikkuva hahmo) {
        if (lauta.onkoRuutuLaudalla(hahmo.getRuutu()) && mikaTyyppiRuudussa(hahmo.getRuutu()).equals(Tyyppi.TYHJA)) {
            Tyyppi hahmonTyyppi = hahmo.getTyyppi();
            if ((hahmonTyyppi.equals(Tyyppi.PELAAJA) || hahmonTyyppi.equals(Tyyppi.KUOLLUTPELAAJA))) {
                if (getPelaaja() == null) {
                    hahmot.add(hahmo);
                }
            } else {
                hahmot.add(hahmo);
            }
        }
    }
    /**
     * Metodi poistaa Liikkuva-olion pelistä.
     * 
     * @param hahmo Liikkuva, joka halutaan poistaa
     */
    public void poistaHahmo(Liikkuva hahmo) {
        hahmot.remove(hahmo);
    }
    
    /**
     * Metodi poistaa listan Liikkuva-olioita.
     * 
     * @param lista poistettavat Liikkuva-oliot
     */
    public void poistaMontaHahmoa(List<Liikkuva> lista) {
        List<Liikkuva> poistettavat = new ArrayList<Liikkuva>();
        for (Liikkuva hahmo : getHahmot()) {
            poistettavat.add(hahmo);
        }
        for (Liikkuva hahmo : poistettavat) {
            poistaHahmo(hahmo);
        }
    }
    
    /**
     * Metodilla selvitetään onko laudan tietyssä ruudussa Liikkuva-oliota vai ei.
     * 
     * @param ruutu, minkä tyyppi halutaan selvittää
     * @return palauttaa ruudussa olevan Liikkuvan tyypin tai tyhjän tyypin, 
     * jos ruudussa ei ole ketään
     * 
     */
    public Tyyppi mikaTyyppiRuudussa(Ruutu ruutu) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(ruutu)) {
                return liikkuva.getTyyppi();
            }
        }
        return Tyyppi.TYHJA;
    }

    /**
     * Metodi liikuttaa pelissä olevaa pelaajaa.
     * 
     * @param x, miten monta ruutua pelaajaa siirretään korkeussuunnassa
     * @param y, miten monta ruutua pelaajaa siirretään leveyssuunnassa
     */
    public void liikutaPelaajaa(int x, int y){
        Liikkuva pelaaja = getPelaaja();
        Ruutu ruutu = new Ruutu(pelaaja.getRuutu().getX() + x, pelaaja.getRuutu().getY() + y);
        if (lauta.onkoRuutuLaudalla(ruutu) && !mikaTyyppiRuudussa(ruutu).equals(Tyyppi.KUOLLUTDALEK)) {
            pelaaja.liiku(ruutu);
            osuukoPelaajaDalekiin();
        }
    }
    
    /**
     * Metodi selvittää onko pelaaja jonkun dalekin kanssa samassa ruudussa.
     * Jos pelaajan ruudussa on myös dalek, pelaaja kuolee.
     */
    private void osuukoPelaajaDalekiin() {
        Liikkuva pelaaja = getPelaaja();
        for (Liikkuva hahmo : getHahmot()) {
            if (hahmo.getRuutu().equals(pelaaja.getRuutu()) &&
                    hahmo.getTyyppi().equals(Tyyppi.DALEK)) {
                pelaaja.kuole();
                return;
            }
        }
    }
    
    /**
     * Metodi siirtää pelaajan satunnaiseen ruutuun laudalla.
     * Arvottu ruutu ei voi olla sellainen, jossa on jo joku, mutta
     * se voi olla ruutu, jonka viereisessä ruudussa on dalek.
     * Metodi ei siirrä pelaajaa jos teleportit ovat pelissä loppu.
     * Teleporttaaminen vähentää teleporttien määrä yhdellä.
     * 
     * @return teleportattiinko pelaaja vai ei
     * @throws IllegalArgumentException kun teleportteja ei ole jäljellä
     */
    public boolean teleporttaaPelaaja() {
        if (teleportit > 0) {
            teleportit--;
            Random random = new Random();
            int yrityskerta = 0;
            while (true) {
                boolean suoritetaan = true;
                Ruutu ruutu = new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()));
                List<Ruutu> ymparoivat = this.lauta.ymparoivatRuudut(ruutu);
                for (Liikkuva hahmo : hahmot) {
                    if (hahmo.getTyyppi().equals(Tyyppi.DALEK) && ymparoivat.contains(hahmo.getRuutu())) {
                        if (yrityskerta < 500) {
                            suoritetaan = false;
                        }
                        yrityskerta++;
                    }
                }
                if (suoritetaan && mikaTyyppiRuudussa(ruutu).equals(Tyyppi.TYHJA)) {
                    getPelaaja().liiku(ruutu);
                    return true;
                }
            }
        }
        return false;
        
    }

    /**
     * Metodi räjäyttää pommin, joka hävittää kaikki dalekit, jotka ovat
     * jossain pelaajan ruudun viereisistä ruuduista.
     * Metodi ei räjäytä pommia, jos pommit ovat pelissä loppu.
     * Pommin räjäyttäminen vähentää pommien määrä yhdellä
     * 
     * @return räjäytettiinkö pommi vai ei
     * @throws IllegalArgumentException kun pommeja ei ole jäljellä
     */
    public boolean rajaytaPommi() {
        if (pommit > 0) {
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
            pommit--;
            return true;
        }
        return false;
    }
    
    /**
     * Metodi liikuttaa kaikkia pelissä olevia eläviä dalekeja 
     * yhden ruudun pelaajaa päin.
     * Jos dalekit törmäävät liikkumisen seurauksena toisiinsa,
     * ne kuolevat.
     * Lopuksi tarkistetaan onko joku dalek liikkunut pelaajan ruutuun.
     */
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
        osuukoPelaajaDalekiin();
        tapaTormanneetDalekit();
    }
    
    /**
     * Metodi, jota liikutaDalekejaPelaajaaPain käyttää.
     * Metodi käy läpi listan pelin Liikkuvista ja tappaa dalekit,
     * jotka ovat samassa ruudussa.
     */
    private void tapaTormanneetDalekit() {
        for (int i = 0; i < hahmot.size(); i++) {
            for (int j = i+1; j < hahmot.size(); j++) {
                if (hahmot.get(i).getRuutu().equals(hahmot.get(j).getRuutu()) && 
                        !hahmot.get(i).getTyyppi().equals(Tyyppi.PELAAJA) && !hahmot.get(j).getTyyppi().equals(Tyyppi.PELAAJA) &&
                        !hahmot.get(i).getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA) && !hahmot.get(j).getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA)) {
                    hahmot.get(i).kuole();
                    hahmot.get(j).kuole();
                }
            }
        }
    }
    
    

    /**
     * Metodi tarkistaa onko pelaaja hävinnyt pelin, eli onko
     * pelaaja samassa ruudussa kuin joku dalek.
     * 
     * @return true jos pelaaja hävisi, false jos ei hävinnyt
     */
    public boolean havisikoPelaaja() {
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        List<Liikkuva> hahmot = this.hahmot;
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(pelaajanRuutu) && !liikkuva.getTyyppi().equals(Tyyppi.PELAAJA)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa onko pelaaja voittanut pelin, eli onko
     * kaikki dalekit kuolleet.
     * 
     * @return true jos pelaaja voitti, false jos pelaaja ei voittanut
     */
    public boolean voittikoPelaaja() {
        Map<Ruutu, Tyyppi> ruudut = getRuudut();
        for (Tyyppi tyyppi : ruudut.values()) {
            if (tyyppi.equals(Tyyppi.DALEK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodi laskee elossa olevien dalekien maaran
     * 
     * @return elossa olevien dalekien määrä
     */
    public int elavienDalekienMaara() {
        int maara = 0;
        for (Liikkuva hahmo : hahmot) {
            if (hahmo.getTyyppi().equals(Tyyppi.DALEK)) {
                maara++;
            }
        }
        return maara;
    }
}
