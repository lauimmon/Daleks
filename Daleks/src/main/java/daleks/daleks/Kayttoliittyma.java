/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lauimmon
 */

public class Kayttoliittyma {
    
    private Pelilauta lauta;
    private int pommit;
    private int teleportit;

    public Kayttoliittyma(Pelilauta pelilauta, int dalekienMaara) {
        
        lauta = pelilauta;
        pommit = 1;
        teleportit = 1;
        
        Random random = new Random();
        lauta.lisaaHahmoLaudalle(new Pelaaja(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        while (lauta.getHahmot().size() != dalekienMaara + 1) {
            lauta.lisaaHahmoLaudalle(new Dalek(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        }
    }
    
    public void run() {
        tulostaOhjeet();
        while (true) {

            tulostaTilanne();

            Scanner lukija = new Scanner(System.in);
            String kasky = lukija.nextLine();
            
            suoritaKasky(kasky);
            
            if (havisikoPelaaja()) {
                tulostaTilanne();
                System.out.println("Hävisit pelin!");
                lauta.getPelaaja().kuole();
                break;
            }
            
            lauta.liikutaDalekejaPelaajaaPain();
            
            if (havisikoPelaaja()) {
                tulostaTilanne();
                System.out.println("Hävisit pelin!");
                lauta.getPelaaja().kuole();
                break;
            }
            
            
            if (voittikoPelaaja()) {
                tulostaTilanne();
                System.out.println("Voitit pelin!");
                break;
            }
        }
    }

    private void tulostaTilanne() {
        lauta.tulostaHahmot();
        lauta.tulostaLauta();
        System.out.println("pommeja: "+pommit);
        System.out.println("teleportteja: "+teleportit);
    }

    private void suoritaKasky(String kasky) {
        if (kasky.equals("r")) {
            if (pommit != 0) {
                lauta.rajaytaPommi();
                pommit--;
            }
        } 
        else if (kasky.equals("t")) {
            if (teleportit != 0) {
                lauta.teleporttaaPelaaja();
                teleportit--;
            }
        }
        else if (kasky.equals("q")) lauta.liikutaPelaajaa(-1, -1);
        else if (kasky.equals("w")) lauta.liikutaPelaajaa(-1, 0);
        else if (kasky.equals("e")) lauta.liikutaPelaajaa(-1, 1);
        else if (kasky.equals("a")) lauta.liikutaPelaajaa(0, -1);
        else if (kasky.equals("d")) lauta.liikutaPelaajaa(0, 1);
        else if (kasky.equals("z")) lauta.liikutaPelaajaa(1, -1); 
        else if (kasky.equals("x")) lauta.liikutaPelaajaa(1, 0);
        else if (kasky.equals("c")) lauta.liikutaPelaajaa(1, 1);
        else if (kasky.equals("p")) pysyPaikoillaan();
            
    }

    private void pysyPaikoillaan() {
        while (true) {
            lauta.liikutaDalekejaPelaajaaPain();
            tulostaTilanne();
            if (havisikoPelaaja()) {
                System.out.println("Hävisit pelin!");
                break;
            }
            if (voittikoPelaaja()) {
                System.out.println("Voitit pelin!");
                break;
            }
            
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean havisikoPelaaja() {
        Ruutu pelaajanRuutu = lauta.getPelaaja().getRuutu();
        List<Liikkuva> hahmot = lauta.getHahmot();
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(pelaajanRuutu) && !liikkuva.getTyyppi().equals(Tyyppi.PELAAJA)) {
                return true;
            }
        }
        return false;
    }

    private boolean voittikoPelaaja() {
        Map<Ruutu, Tyyppi> ruudut = lauta.getRuudut();
        for (Tyyppi tyyppi : ruudut.values()) {
            if (tyyppi.equals(Tyyppi.DALEK)) {
                return false;
            }
        }
        return true;
    }

    private void tulostaOhjeet() {
        System.out.println("Ohjeet: \n\nYlös W, alas X, vasemmalle A, oikealle D. \nVinottain liikkuminen Q, E, Z ja C. Paikallaan pysyminen S. \n\nPommin räjäytys R, teleporttaus T. \n\nNäppäimellä P voit jäädä paikalleen koko loppuajaksi\n");
    }

}
