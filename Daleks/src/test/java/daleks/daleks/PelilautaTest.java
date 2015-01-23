/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lauimmon
 */
public class PelilautaTest {
    
    public PelilautaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaLaudanXKoonOikein() {
        Pelilauta lauta = new Pelilauta(10,20);
        
        assertEquals(10, lauta.getKokoX());
    }
    
    @Test
    public void konstruktoriAsettaaLaudanYKoonOikein() {
        Pelilauta lauta = new Pelilauta(10,20);
        
        assertEquals(20, lauta.getKokoY());
    }

    @Test
    public void konstruktoriLuoTyhjanListanHahmoista() {
        Pelilauta lauta = new Pelilauta(10,10);
        
        assertEquals(lauta.getHahmot(), new ArrayList<Liikkuva>());
    }
    
    @Test
    public void tunnistaaJosRuutuLaudanUlkopuolella() {
        Pelilauta lauta = new Pelilauta(10,10);
        Ruutu ruutu = new Ruutu(20,1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void lisattyHahmoLoytyyLaudalta() {
        Pelilauta lauta = new Pelilauta(10,10);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertTrue(lauta.onkoHahmoLaudalla(dalek));
    }
    
    @Test
    public void hahmoaEiLisataJosSenRuutuLaudanUlkopuolella() {
        Pelilauta lauta = new Pelilauta(10,10);
        Dalek dalek = new Dalek(new Ruutu(11,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertFalse(lauta.onkoHahmoLaudalla(dalek));
    }
    
    @Test
    public void pelilaudallaOlevanHahmonRuutuMuuttuuKunSitaLiikutetaan() {
        Pelilauta lauta = new Pelilauta(10,10);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        dalek.liiku(1, 1);
        
        assertTrue(lauta.onkoHahmoLaudalla(dalek));
    }
    
    @Test
    public void palauttaaRuudussaOlevanDalekinTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,10);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertEquals(lauta.minkaTyyppinenHahmoOnRuudussa(new Ruutu(1,1)), Tyyppi.DALEK);
    }
    
    @Test
    public void palauttaaRuudussaOlevanPelaajanTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,10);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        
        assertEquals(lauta.minkaTyyppinenHahmoOnRuudussa(new Ruutu(1,1)), Tyyppi.PELAAJA);
    }
    
    @Test
    public void palauttaaTyhjanRuudunTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,10);
        
        assertEquals(lauta.minkaTyyppinenHahmoOnRuudussa(new Ruutu(1,1)), Tyyppi.TYHJA);
    }
    
    @Test
    public void eiLiikutaHahmoaJosTavoiteruutuOnLaudanUlkopuolella() {
        Pelilauta lauta = new Pelilauta(10,10);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        lauta.liikuta(pelaaja, -1, 0);
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(1,1));
    }
    
}
