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

    public Kayttoliittyma(Pelilauta pelilauta, int dalekienMaara) {
        peli = new Peli(pelilauta, dalekienMaara);
    }
    
    public void run() {
        peli.tulostaTilanne();
        while (true) {
            boolean suoritettu = false;
            while (!suoritettu) {
                Scanner lukija = new Scanner(System.in);
                System.out.print(">");
                String kasky = lukija.nextLine();

                suoritettu = suoritaKasky(kasky);
            }
            
            if (!peli.paivitaTilanne()) break;
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
