/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.logiikka;

import daleks.luokat.*;
import daleks.kayttoliittyma.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Peli-luokka sisältää kaiken pelin logiikan. Se hoitaa Liikuvien liikuttelun ym. toiminnot.
 * 
 * @author lauimmon
 */
public class Peli {
    
    private Pelilauta lauta;
    private List<Liikkuva> hahmot;
    private int pommit;
    private int teleportit;

    /**
     * Pelille asetetaan luotaessa lauta, yksi pelaaja-olio ja haluttu määrä dalekeja.
     * Pelaajan ja dalekien ruudut arvotaan.
     * Pommien ja teleporttien määrä asetetaan ykköseksi.
     * 
     * @param pelilauta pelille asetettava lauta
     * @param dalekienMaara eli kuinka monta dalekia peliin luodaan
     */
    public Peli(Pelilauta pelilauta, int dalekienMaara) {
        lauta = pelilauta;
        hahmot = new ArrayList<Liikkuva>();
        pommit = 1;
        teleportit = 1;
        
        Random random = new Random();
        lisaaHahmo(new Pelaaja(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        while (hahmot.size() != dalekienMaara + 1) {
            lisaaHahmo(new Dalek(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        }
        
        tulostaOhjeet();
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
     * Metodi lisää Liikkuva-olion peliin
     * 
     * @param hahmo Liikkuva, joka halutaan lisätä
     */
    public void lisaaHahmo(Liikkuva hahmo) {
        if (lauta.onkoRuutuLaudalla(hahmo.getRuutu())) {
            hahmot.add(hahmo);
        }
    }
    /**
     * Metodi poistaa Liikkuva-olion pelistä
     * 
     * @param hahmo Liikkuva, joka halutaan poistaa
     */
    public void poistaHahmo(Liikkuva hahmo) {
        hahmot.remove(hahmo);
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
        }
        osuukoPelaajaDalekiin();
    }
    
    /**
     * Metodi liikuttaa pelissä olevaa pelaajaa.
     * 
     * @param ruutu, mihin ruutuun pelaaja halutaan liikuttaa
     */
    public void liikutaPelaajaa(Ruutu ruutu){
        Liikkuva pelaaja = getPelaaja();
        if (lauta.onkoRuutuLaudalla(ruutu) && !mikaTyyppiRuudussa(ruutu).equals(Tyyppi.KUOLLUTDALEK)) {
            pelaaja.liiku(ruutu);
        }
        osuukoPelaajaDalekiin();
    }
    
    /**
     * Metodi selvittää onko pelaaja jonkun dalekin kanssa samassa ruudussa.
     * Jos pelaajan ruudussa on myös dalek, pelaaja kuolee.
     */
    private void osuukoPelaajaDalekiin() {
        Liikkuva pelaaja = getPelaaja();
        for (Liikkuva hahmo : getHahmot()) {
            if (hahmo.getRuutu().equals(pelaaja.getRuutu()) && !hahmo.getTyyppi().equals(Tyyppi.PELAAJA)) {
                pelaaja.kuole();
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
     * @throws IllegalArgumentException kun teleportteja ei ole jäljellä
     */
    public void teleporttaaPelaaja() {
        if (teleportit != 0) {
            teleportit--;
            Random random = new Random();
            while (true) {
                Ruutu ruutu = new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()));
                if (mikaTyyppiRuudussa(ruutu).equals(Tyyppi.TYHJA)) {
                    getPelaaja().liiku(ruutu);
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Ei teleportteja jäljellä!");
        }
        
    }

    /**
     * Metodi räjäyttää pommin, joka hävittää kaikki dalekit, jotka ovat
     * jossain pelaajan ruudun viereisistä ruuduista.
     * Metodi ei räjäytä pommia, jos pommit ovat pelissä loppu.
     * Pommin räjäyttäminen vähentää pommien määrä yhdellä
     * 
     * @throws IllegalArgumentException kun pommeja ei ole jäljellä
     */
    public void rajaytaPommi() throws IllegalArgumentException {
        if (pommit != 0) {
            pommit--;
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
        } else {
            throw new IllegalArgumentException("Ei pommeja jäljellä!");
        }
        
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
        tapaTormanneetDalekit();
        osuukoPelaajaDalekiin();
    }
    
    /**
     * Metodi, jota liikutaDalekejaPelaajaaPain käyttää.
     * Metodi käy läpi listan pelin Liikkuvista ja tappaa dalekit,
     * jotka ovat samassa ruudussa.
     */
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
    
    /**
     * 
     */
    public void tulostaTilanne() {
        Map<Ruutu, Tyyppi> ruudut = getRuudut();
        for (int i = 0; i < lauta.getKokoX(); i++) {
            for (int j = 0; j < lauta.getKokoY(); j++) {
                if (ruudut.containsKey(new Ruutu(i, j))) {
                    System.out.print(ruudut.get(new Ruutu(i, j)).toString());
                } else  System.out.print(Tyyppi.TYHJA.toString());
            }
            System.out.println();
        }
        System.out.println("pommeja: "+pommit);
        System.out.println("teleportteja: "+teleportit);
    }

    public void pysyPaikoillaan() {
        while (true) {
            liikutaDalekejaPelaajaaPain();
            tulostaTilanne();
            if (havisikoPelaaja()) break;
            if (voittikoPelaaja()) break;
            
            odotaSekunti();
        }
    }

    public boolean havisikoPelaaja() {
        Ruutu pelaajanRuutu = getPelaaja().getRuutu();
        List<Liikkuva> hahmot = getHahmot();
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(pelaajanRuutu) && !liikkuva.getTyyppi().equals(Tyyppi.PELAAJA)) {
                System.out.println("Hävisit pelin!");
                return true;
            }
        }
        return false;
    }

    public boolean voittikoPelaaja() {
        Map<Ruutu, Tyyppi> ruudut = getRuudut();
        for (Tyyppi tyyppi : ruudut.values()) {
            if (tyyppi.equals(Tyyppi.DALEK)) {
                return false;
            }
        }
        System.out.println("Voitit pelin!");
        return true;
    }
    
    public boolean paivitaTilanne() {
        tulostaTilanne();
        if (havisikoPelaaja()) {
            getPelaaja().kuole();
            return false;
        }
        liikutaDalekejaPelaajaaPain();
        odotaSekunti();
        tulostaTilanne();
        if (havisikoPelaaja()) {
            getPelaaja().kuole();
            return false;
        }
        if (voittikoPelaaja()) {
            return false;
        }
        return true;
    }

    private void odotaSekunti() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Peli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tulostaOhjeet() {
        System.out.println("Ohjeet: \nTavoitteenasi pelaajana (P) on paeta dalekeja (@) ja saada ne törmäämään toisiinsa tai kuolleisiin dalekeihin (#), jolloin ne kuolevat. \nVoitat kun kaikki dalekit kuolevat ja häviät jos dalek saa sinut kiinni. Kuolleet dalekit eivät liiku. \n\nOhjaus:\nYlös W, alas X, vasemmalle A, oikealle D. Vinottain liikkuminen Q, E, Z ja C. Paikallaan pysyminen S. \n\nPommin räjäytys R, teleporttaus T. \n\nNäppäimellä P voit jäädä paikalleen koko loppuajaksi.\n\n");
    }
    
}
