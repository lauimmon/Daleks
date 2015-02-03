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

<<<<<<< HEAD
            boolean suoritettu = false;
            while (!suoritettu) {
                Scanner lukija = new Scanner(System.in);
                String kasky = lukija.nextLine();

                suoritettu = suoritaKasky(kasky);
            }
=======
            Scanner lukija = new Scanner(System.in);
            System.out.print(">");
            String kasky = lukija.nextLine();
            
            suoritaKasky(kasky);
>>>>>>> 3caccf5ae8a0fcc44bf73476c61d100712062bee
            
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
        System.out.println();
        System.out.println("pommeja: "+pommit);
        System.out.println("teleportteja: "+teleportit);
    }

    private boolean suoritaKasky(String kasky) {
        try {
            if (kasky.equals("r")) {
                if (pommit != 0) {
                    lauta.rajaytaPommi();
                    pommit--;
                } else {
                    throw new IllegalArgumentException("Et voi käyttää pommia");
                }
            }
            else if (kasky.equals("t")) {
                if (teleportit != 0) {
                    lauta.teleporttaaPelaaja();
                    teleportit--;
                } else {
                    throw new IllegalArgumentException("Et voi käyttää teleporttia");
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
            else throw new IllegalArgumentException("Väärä syöte");
        } catch(IllegalArgumentException i) {
            System.out.println(i.getMessage());
            return false;
        }
        return true;
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
        System.out.println("Ohjeet: \nTavoitteenasi pelaajana (P) on paeta dalekeja (@) ja saada ne törmäämään toisiinsa tai kuolleisiin dalekeihin (#), jolloin ne kuolevat. \nVoitat kun kaikki dalekit kuolevat ja häviät jos dalek saa sinut kiinni. Kuolleet dalekit eivät liiku. \n\nOhjaus:\nYlös W, alas X, vasemmalle A, oikealle D. Vinottain liikkuminen Q, E, Z ja C. Paikallaan pysyminen S. \n\nPommin räjäytys R, teleporttaus T. \n\nNäppäimellä P voit jäädä paikalleen koko loppuajaksi.\n\n");
    }

}
