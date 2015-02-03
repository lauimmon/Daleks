/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.logiikka;

import daleks.luokat.Pelilauta;
import daleks.logiikka.Peli;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lauimmon
 */
public class PeliTest {
    
    private Peli peli;
    
    public PeliTest() {
        peli = new Peli(new Pelilauta(10,20), 0);
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

//    @Test
//    public void konstruktoriLuoTyhjanListanHahmoista() {
//        assertEquals(peli.getHahmot(), new ArrayList<Liikkuva>());
//    }
//    
//    @Test
//    public void getPelaajaPalauttaaPelaajan() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
//        peli.lisaaHahmo(pelaaja);
//        
//        assertEquals(peli.getPelaaja(), pelaaja);
//    }
//    
//    @Test
//    public void getPelaajaPalauttaaNullJosPelaajaaEiOleLaudalla() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
//        
//        assertEquals(peli.getPelaaja(), null);
//    }
//    
//    @Test
//    public void getPelaajaPalauttaaKuolleenPelaajan() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,1));
//        peli.lisaaHahmo(pelaaja);
//        pelaaja.kuole();
//        
//        assertEquals(peli.getPelaaja(), pelaaja);
//    }
//    
//    @Test
//    public void getRuudutPalauttaaOikeinKaikkiRuudutJoissaLiikkuva() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        Map<Ruutu, Tyyppi> ruudut = peli.getRuudut();
//        
//        assertTrue(ruudut.size() == 1 && ruudut.containsKey(new Ruutu(1,1)) && ruudut.containsValue(Tyyppi.DALEK));
//    }
//    
//    @Test
//    public void lisattyHahmoLoytyy() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        
//        assertTrue(peli.getHahmot().contains(dalek));
//    }
//    
//    @Test
//    public void poistaHahmoLaudaltaPoistaaOikenHahmon() {
//        Dalek dalek1 = new Dalek(new Ruutu(1,1));
//        Dalek dalek2 = new Dalek(new Ruutu(2,1));
//        peli.lisaaHahmo(dalek1);
//        peli.lisaaHahmo(dalek2);
//        peli.poistaHahmo(dalek1);
//        
//        assertTrue(peli.getHahmot().contains(dalek2) && !peli.getHahmot().contains(dalek1));
//    }
//    
//    @Test
//    public void hahmoaEiLisataJosSenRuutuLaudanUlkopuolella() {
//        Dalek dalek = new Dalek(new Ruutu(11,1));
//        peli.lisaaHahmo(dalek);
//        
//        assertFalse(peli.getHahmot().contains(dalek));
//    }
//    
//    @Test
//    public void pelissaOlevanHahmonRuutuMuuttuuKunSitaLiikutetaan() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        dalek.liiku(1, 1);
//        
//        assertTrue(peli.getHahmot().contains(dalek));
//    }
//    
//    @Test
//    public void palauttaaRuudussaOlevanDalekinTyypinOikein() {
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        
//        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.DALEK);
//    }
//    
//    @Test
//    public void palauttaaRuudussaOlevanPelaajanTyypinOikein() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
//        peli.lisaaHahmo(pelaaja);
//        
//        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.PELAAJA);
//    }
//    
//    @Test
//    public void palauttaaTyhjanRuudunTyypinOikein() {
//        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.TYHJA);
//    }
//    
//    @Test
//    public void eiLiikutaHahmoaJosTavoiteruutuOnLaudanUlkopuolella() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
//        peli.lisaaHahmo(pelaaja);
//        peli.liikutaPelaajaa( -1, 0);
//        
//        assertEquals(pelaaja.getRuutu(), new Ruutu(0,1));
//    }
//    
//    @Test
//    public void pelaajaKuoleeKunOsuuDalekiin() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        peli.liikutaPelaajaa(1, 0);
//        
//        assertEquals(pelaaja.getTyyppi(), Tyyppi.KUOLLUTPELAAJA);
//    }
//    
//    @Test
//    public void pelaajaEiLiikuSamaanRuutuunKuolleenDalekinKanssa() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
//        Ruutu aloitusruutu = pelaaja.getRuutu();
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        dalek.kuole();
//        peli.liikutaPelaajaa(1, 0);
//        
//        assertEquals(pelaaja.getRuutu(), aloitusruutu);
//    }
//    
//    @Test
//    public void pomminRajaytysHavittaaYmparoivatDalekit() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek1 = new Dalek(new Ruutu(2,2));
//        peli.lisaaHahmo(dalek1);
//        Dalek dalek2 = new Dalek(new Ruutu(1,3));
//        peli.lisaaHahmo(dalek2);
//        peli.rajaytaPommi();
//        
//        assertTrue(peli.getHahmot().contains(pelaaja) &&  !peli.getHahmot().contains(dalek1) && peli.getHahmot().contains(dalek2));
//    }
//    
//    @Test
//    public void pomminRajaytysEiHavittaYmparoiviaKuolleitaDalekkeja() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(2,2));
//        peli.lisaaHahmo(dalek);
//        dalek.kuole();
//        peli.rajaytaPommi();
//        
//        assertTrue(peli.getHahmot().contains(pelaaja) &&  peli.getHahmot().contains(dalek));
//    }
//    
//    @Test
//    public void pomminRajaytysToimiiLaudanReunalla() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        peli.rajaytaPommi();
//        
//        assertTrue(peli.getHahmot().contains(pelaaja) &&  !peli.getHahmot().contains(dalek));
//    }
//    
//    @Test
//    public void pelaajaTeleporttaaAinaTyhjaanRuutuun() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek1 = new Dalek(new Ruutu(0,1));
//        peli.lisaaHahmo(dalek1);
//            
//        List<Ruutu> loppuRuudut = new ArrayList<Ruutu>();
//        
//        
//        for (int i = 0; i < 1000; i++) {
//            peli.teleporttaaPelaaja();
//            loppuRuudut.add(pelaaja.getRuutu());
//            pelaaja.liiku(new Ruutu(0,0));
//        }
//        
//        assertFalse(loppuRuudut.contains(new Ruutu(0,0)) || loppuRuudut.contains(new Ruutu(0,1)));
//    }
//    
//    @Test
//    public void dalekLiikkuuPelaajaaPain1() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(2,2));
//        peli.lisaaHahmo(dalek);
//        peli.liikutaDalekejaPelaajaaPain();
//        
//        assertEquals(dalek.getRuutu(), new Ruutu(1,1));
//    }
//    
//    @Test
//    public void dalekLiikkuuPelaajaaPain2() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(5,5));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek = new Dalek(new Ruutu(1,1));
//        peli.lisaaHahmo(dalek);
//        peli.liikutaDalekejaPelaajaaPain();
//        
//        assertEquals(dalek.getRuutu(), new Ruutu(2,2));
//    }
//    
//    @Test
//    public void tormaavatDalekitKuolevat() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek1 = new Dalek(new Ruutu(0,2));
//        peli.lisaaHahmo(dalek1);
//        Dalek dalek2 = new Dalek(new Ruutu(1,2));
//        peli.lisaaHahmo(dalek2);
//        
//        peli.liikutaDalekejaPelaajaaPain();
//        
//        assertTrue(dalek1.getTyyppi() == Tyyppi.KUOLLUTDALEK && dalek2.getTyyppi() == Tyyppi.KUOLLUTDALEK);
//    }
//    
//    @Test
//    public void dalekEiKuoleTormatessaanPelaajaan() {
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        Dalek dalek1 = new Dalek(new Ruutu(0,1));
//        peli.lisaaHahmo(dalek1);
//        
//        peli.liikutaDalekejaPelaajaaPain();
//        
//        assertTrue(dalek1.getTyyppi() == Tyyppi.DALEK);
//    }
//    
//    @Test
//    public void dalekEiKuolePelaajanTormatessaSiihen() {
//        Dalek dalek1 = new Dalek(new Ruutu(0,1));
//        peli.lisaaHahmo(dalek1);
//        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
//        peli.lisaaHahmo(pelaaja);
//        
//        peli.liikutaPelaajaa(0, 1);
//        
//        assertTrue(dalek1.getTyyppi() == Tyyppi.DALEK);
//    }
}
