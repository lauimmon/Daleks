/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.kayttoliittyma;


import daleks.luokat.Pelilauta;
import daleks.logiikka.Peli;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lauimmon
 */

public class Kayttoliittyma {
    
    private Peli peli;

    public Kayttoliittyma(int korkeus, int leveys, int dalekienMaara) {
        peli = new Peli(new Pelilauta(korkeus, leveys), dalekienMaara);
    }
    
    public void run() {
        peli.tulostaTilanne();
        while (true) {
            otaPelaajanSyote();
            
            if (liikutaDalekejaJaTarkistaLoppuukoPeli()) break;
        }
    }

    private void otaPelaajanSyote() throws IllegalArgumentException {
        boolean suoritettu = false;
        while (!suoritettu) {
            Scanner lukija = new Scanner(System.in);
            System.out.print(">");
            String kasky = lukija.nextLine();
            
            suoritettu = suoritaKasky(kasky);
        }
    }
    
    private boolean liikutaDalekejaJaTarkistaLoppuukoPeli() {
        peli.tulostaTilanne();
        if (loppuukoPeli()) return true;
        peli.liikutaDalekejaPelaajaaPain();
        odotaSekunti();
        peli.tulostaTilanne();
        if (loppuukoPeli()) return true;
        return false;
    }

    private boolean loppuukoPeli() {
        if (peli.havisikoPelaaja()) {
            peli.getPelaaja().kuole();
            System.out.println("Hävisit pelin!");
            return true;
        }
        if (peli.voittikoPelaaja()) {
            System.out.println("Voitit pelin!");
            return true;
        }
        return false;
    }

    private void odotaSekunti() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Peli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean suoritaKasky(String kasky) throws IllegalArgumentException {
        try {
            if (kasky.equals("r")) peli.rajaytaPommi();
            else if (kasky.equals("t")) peli.teleporttaaPelaaja();
            else if (kasky.equals("q")) peli.liikutaPelaajaa(-1, -1);
            else if (kasky.equals("w")) peli.liikutaPelaajaa(-1, 0);
            else if (kasky.equals("e")) peli.liikutaPelaajaa(-1, 1);
            else if (kasky.equals("a")) peli.liikutaPelaajaa(0, -1);
            else if (kasky.equals("d")) peli.liikutaPelaajaa(0, 1);
            else if (kasky.equals("z")) peli.liikutaPelaajaa(1, -1); 
            else if (kasky.equals("x")) peli.liikutaPelaajaa(1, 0);
            else if (kasky.equals("c")) peli.liikutaPelaajaa(1, 1);
            else if (kasky.equals("s")) {}
            else if (kasky.equals("p")) peli.pysyPaikoillaan();
            else throw new IllegalArgumentException("Väärä syöte!");
        } catch(IllegalArgumentException i) {
            System.out.println(i.getMessage());
            return false;
        }
        return true;
    }
}
