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
        Pelilauta lauta = new Pelilauta(10,20);
        
        assertEquals(lauta.getHahmot(), new ArrayList<Liikkuva>());
    }
    
    @Test
    public void getPelaajaPalauttaaPelaajan() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        
        assertEquals(lauta.getPelaaja(), pelaaja);
    }
    
    @Test
    public void getPelaajaPalauttaaNullJosPelaajaaEiOleLaudalla() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
        
        assertEquals(lauta.getPelaaja(), null);
    }
    
    @Test
    public void getPelaajaPalauttaaKuolleenPelaajan() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        pelaaja.kuole();
        
        assertEquals(lauta.getPelaaja(), pelaaja);
    }
    
    @Test
    public void getRuudutPalauttaaOikeinKaikkiRuudutJoissaLiikkuva() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        Map<Ruutu, Tyyppi> ruudut = lauta.getRuudut();
        
        assertTrue(ruudut.size() == 1 && ruudut.containsKey(new Ruutu(1,1)) && ruudut.containsValue(Tyyppi.DALEK));
    }
    
    @Test
    public void ruutuEiLaudallaJosLiianSuuriX() {
        Pelilauta lauta = new Pelilauta(10,20);
        Ruutu ruutu = new Ruutu(20,1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosNegatiivinenX() {
        Pelilauta lauta = new Pelilauta(10,20);
        Ruutu ruutu = new Ruutu(-1,1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosLiianSuuriY() {
        Pelilauta lauta = new Pelilauta(10,20);
        Ruutu ruutu = new Ruutu(1,20);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosNegatiivinenY() {
        Pelilauta lauta = new Pelilauta(10,20);
        Ruutu ruutu = new Ruutu(7,-1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    
    @Test
    public void lisattyHahmoLoytyyLaudalta() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertTrue(lauta.getHahmot().contains(dalek));
    }
    
    @Test
    public void poistaHahmoLaudaltaPoistaaOikenHahmon() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek1 = new Dalek(new Ruutu(1,1));
        Dalek dalek2 = new Dalek(new Ruutu(2,1));
        lauta.lisaaHahmoLaudalle(dalek1);
        lauta.lisaaHahmoLaudalle(dalek2);
        lauta.poistaHahmoLaudalta(dalek1);
        
        assertTrue(lauta.getHahmot().contains(dalek2) && !lauta.getHahmot().contains(dalek1));
    }
    
    @Test
    public void hahmoaEiLisataJosSenRuutuLaudanUlkopuolella() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(11,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertFalse(lauta.getHahmot().contains(dalek));
    }
    
    @Test
    public void pelilaudallaOlevanHahmonRuutuMuuttuuKunSitaLiikutetaan() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        dalek.liiku(1, 1);
        
        assertTrue(lauta.getHahmot().contains(dalek));
    }
    
    @Test
    public void palauttaaRuudussaOlevanDalekinTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,20);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertEquals(lauta.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.DALEK);
    }
    
    @Test
    public void palauttaaRuudussaOlevanPelaajanTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        
        assertEquals(lauta.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.PELAAJA);
    }
    
    @Test
    public void palauttaaTyhjanRuudunTyypinOikein() {
        Pelilauta lauta = new Pelilauta(10,20);
        
        assertEquals(lauta.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.TYHJA);
    }
    
    @Test
    public void eiLiikutaHahmoaJosTavoiteruutuOnLaudanUlkopuolella() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        lauta.liikutaPelaajaa( -1, 0);
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(0,1));
    }
    
    @Test
    public void pelaajaKuoleeKunOsuuDalekiin() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        lauta.liikutaPelaajaa(1, 0);
        
        assertEquals(pelaaja.getTyyppi(), Tyyppi.KUOLLUTPELAAJA);
    }
    
    @Test
    public void pelaajaEiLiikuSamaanRuutuunKuolleenDalekinKanssa() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        Ruutu aloitusruutu = pelaaja.getRuutu();
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        dalek.kuole();
        lauta.liikutaPelaajaa(1, 0);
        
        assertEquals(pelaaja.getRuutu(), aloitusruutu);
    }
    
    @Test
    public void pomminRajaytysHavittaaYmparoivatDalekit() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(2,2));
        lauta.lisaaHahmoLaudalle(dalek1);
        Dalek dalek2 = new Dalek(new Ruutu(1,3));
        lauta.lisaaHahmoLaudalle(dalek2);
        lauta.rajaytaPommi();
        
        assertTrue(lauta.getHahmot().contains(pelaaja) &&  !lauta.getHahmot().contains(dalek1) && lauta.getHahmot().contains(dalek2));
    }
    
    @Test
    public void pomminRajaytysEiHavittaYmparoiviaKuolleitaDalekkeja() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(2,2));
        lauta.lisaaHahmoLaudalle(dalek);
        dalek.kuole();
        lauta.rajaytaPommi();
        
        assertTrue(lauta.getHahmot().contains(pelaaja) &&  lauta.getHahmot().contains(dalek));
    }
    
    @Test
    public void pomminRajaytysToimiiLaudanReunalla() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        lauta.rajaytaPommi();
        
        assertTrue(lauta.getHahmot().contains(pelaaja) &&  !lauta.getHahmot().contains(dalek));
    }
    
    @Test
    public void pelaajaTeleporttaaAinaTyhjaanRuutuun() {
        Pelilauta lauta = new Pelilauta(1,3);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,1));
        lauta.lisaaHahmoLaudalle(dalek1);
            
        List<Ruutu> loppuRuudut = new ArrayList<Ruutu>();
        
        
        for (int i = 0; i < 1000; i++) {
            lauta.teleporttaaPelaaja();
            loppuRuudut.add(pelaaja.getRuutu());
            pelaaja.liiku(new Ruutu(0,0));
        }
        
        assertFalse(loppuRuudut.contains(new Ruutu(0,0)) || loppuRuudut.contains(new Ruutu(0,1)));
    }
    
    @Test
    public void dalekLiikkuuPelaajaaPain1() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(2,2));
        lauta.lisaaHahmoLaudalle(dalek);
        lauta.liikutaDalekejaPelaajaaPain();
        
        assertEquals(dalek.getRuutu(), new Ruutu(1,1));
    }
    
    @Test
    public void dalekLiikkuuPelaajaaPain2() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(5,5));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        lauta.liikutaDalekejaPelaajaaPain();
        
        assertEquals(dalek.getRuutu(), new Ruutu(2,2));
    }
    
    @Test
    public void tormaavatDalekitKuolevat() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,2));
        lauta.lisaaHahmoLaudalle(dalek1);
        Dalek dalek2 = new Dalek(new Ruutu(1,2));
        lauta.lisaaHahmoLaudalle(dalek2);
        
        lauta.liikutaDalekejaPelaajaaPain();
        
        assertTrue(dalek1.getTyyppi() == Tyyppi.KUOLLUTDALEK && dalek2.getTyyppi() == Tyyppi.KUOLLUTDALEK);
    }
    
    @Test
    public void dalekEiKuoleTormatessaanPelaajaan() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,1));
        lauta.lisaaHahmoLaudalle(dalek1);
        
        lauta.liikutaDalekejaPelaajaaPain();
        
        assertTrue(dalek1.getTyyppi() == Tyyppi.DALEK);
    }
    
    @Test
    public void dalekEiKuolePelaajanTormatessaDalekiin() {
        Pelilauta lauta = new Pelilauta(10,20);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        lauta.lisaaHahmoLaudalle(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,1));
        lauta.lisaaHahmoLaudalle(dalek1);
        
        lauta.liikutaPelaajaa(0, 1);
        
        assertTrue(dalek1.getTyyppi() == Tyyppi.DALEK);
    }
}
