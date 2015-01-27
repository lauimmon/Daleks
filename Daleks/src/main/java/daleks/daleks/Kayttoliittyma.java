/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;

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
        Random random = new Random();
        while (lauta.getHahmot().size() != dalekienMaara) {
            lauta.lisaaHahmoLaudalle(new Dalek(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
        }
        lauta.lisaaHahmoLaudalle(new Pelaaja(new Ruutu(random.nextInt(lauta.getKokoX()), random.nextInt(lauta.getKokoY()))));
    }
    
    public void run() {
        while (true) {
            pommit = 1;
            teleportit = 1;

            lauta.tulostaHahmot();
            lauta.tulostaLauta();

//            Scanner lukija = new Scanner(System.in);
//            String kasky = lukija.nextLine();
            
            suoritaKasky("r");
            
            lauta.liikutaDalekejaPelaajaaPain();
        }
    }

    private void suoritaKasky(String kasky) {
        if (kasky.equals("r")) lauta.rajaytaPommi(); 
        else if (kasky.equals("t")) lauta.teleporttaaPelaaja();
        else if (kasky.equals("q")) lauta.liikutaPelaajaa(-1, -1);
        else if (kasky.equals("w")) lauta.liikutaPelaajaa(-1, 0);
        else if (kasky.equals("e")) lauta.liikutaPelaajaa(-1, 1);
        else if (kasky.equals("a")) lauta.liikutaPelaajaa(0, -1);
        else if (kasky.equals("d")) lauta.liikutaPelaajaa(0, 1);
        else if (kasky.equals("z")) lauta.liikutaPelaajaa(1, -1); 
        else if (kasky.equals("x")) lauta.liikutaPelaajaa(1, 0);
        else if (kasky.equals("c")) lauta.liikutaPelaajaa(1, 1);
            
    }

    
    
    
    
    
}
