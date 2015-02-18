/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.logiikka;

import daleks.luokat.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class PeliTest {
    
    private Peli peli;
    
    public PeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli(10,20, 1, 1, 1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoOikeanMaaranDalekejaKunAnnetaanDalekienMaaraksiYksi() {
        assertEquals(peli.getHahmot().size(), 2);
    }
    
    @Test
    public void konstruktoriLuoOikeanMaaranDalekejaKunAnnetaanDalekienMaaraksiNolla() {
        peli = new Peli(10,20,0,1,1);
        assertEquals(peli.getHahmot().size(), 1);
    }
    
    @Test
    public void konstruktoriLuoOikeanMaaranDalekejaKunAnnetaanDalekienMaaraksiPuoletLaudanRuutujenMaarasta() {
        peli = new Peli(10,20,100,1,1);
        assertEquals(peli.getHahmot().size(), 101);
    }
    
    @Test
    public void konstruktoriLuoOikeanMaaranDalekejaKunAnnetaanDalekienMaaraksiEnemmänKuinLaudanRuutujenMaara() {
        peli = new Peli(10,20,300,1,1);
        assertEquals(peli.getHahmot().size(), 200);
    }
    
    @Test
    public void getPelaajaPalauttaaPelaajan() {
        assertEquals(peli.getPelaaja().getTyyppi(), Tyyppi.PELAAJA);
    }
    
    @Test
    public void getPelaajaPalauttaaNullJosPelaajaaEiOleLaudalla() {
        peli.poistaHahmo(peli.getPelaaja());
        
        assertEquals(peli.getPelaaja(), null);
    }
    
    @Test
    public void getPelaajaPalauttaaKuolleenPelaajan() {
        peli.getPelaaja().kuole();
        
        assertEquals(peli.getPelaaja().getTyyppi(), Tyyppi.KUOLLUTPELAAJA);
    }
    
    @Test
    public void getRuudutPalauttaaOikeinKaikkiRuudutJoissaLiikkuva() {
        Map<Ruutu, Tyyppi> ruudut = peli.getRuudut();
        List<Liikkuva> hahmot = peli.getHahmot();
        Liikkuva dalek = new Dalek(new Ruutu(0,0));
        for (Liikkuva hahmo : hahmot) {
            if (hahmo.getTyyppi().equals(Tyyppi.DALEK)) {
                dalek = hahmo;
            }
        }
        
        assertTrue(ruudut.size() == 2 && 
                ruudut.containsKey(peli.getPelaaja().getRuutu()) && 
                ruudut.containsKey(dalek.getRuutu()));
    }
    
    @Test
    public void lisattyHahmoLoytyy() {
        Random rand = new Random();
        Dalek dalek = new Dalek(new Ruutu(1,1));
        while (true) {
            if (!peli.getRuudut().containsKey(dalek.getRuutu())) {
                peli.lisaaHahmo(dalek);
                break;
            }
            dalek.liiku(new Ruutu(rand.nextInt(peli.getLauta().getKokoX()), 
                    rand.nextInt(peli.getLauta().getKokoY())));
        }
        
        assertTrue(peli.getHahmot().contains(dalek));
    }
    
    @Test
    public void pelaajaaEiLisataJosPelissaOnJoPelaaja() {
        for (Liikkuva hahmo : peli.getHahmot()) {
            peli.poistaHahmo(hahmo);
        }
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        peli.lisaaHahmo(pelaaja);
        
        Pelaaja pelaaja2 = new Pelaaja(new Ruutu(2,2));
        peli.lisaaHahmo(pelaaja2);
        
        assertTrue(!peli.getHahmot().contains(pelaaja2));
    }
    
    @Test
    public void poistaHahmoLaudaltaPoistaaOikenHahmon() {
        peli.poistaHahmo(peli.getPelaaja());
        
        assertTrue(peli.getHahmot().size() == 1 && 
                !peli.getHahmot().contains(peli.getPelaaja()));
    }
    
    @Test
    public void kaikkienHahmojenPoistoOnnistuu() {
        peli.lisaaHahmo(new Dalek(new Ruutu(5,6)));
        List<Liikkuva> poistettavat = new ArrayList<Liikkuva>();
        peli.poistaMontaHahmoa(poistettavat);
        
        assertTrue(peli.getHahmot().isEmpty());
    }
    
    @Test
    public void hahmoaEiLisataJosSenRuutuLaudanUlkopuolella() {
        Dalek dalek = new Dalek(new Ruutu(11,1));
        peli.lisaaHahmo(dalek);
        
        assertFalse(peli.getHahmot().contains(dalek));
    }
    
    
    @Test
    public void palauttaaRuudussaOlevanDalekinTyypinOikein() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        Dalek dalek = new Dalek(new Ruutu(1,1));
        peli.lisaaHahmo(dalek);
        
        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.DALEK);
    }
    
    @Test
    public void palauttaaRuudussaOlevanPelaajanTyypinOikein() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        peli.lisaaHahmo(pelaaja);
        
        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.PELAAJA);
    }
    
    @Test
    public void palauttaaTyhjanRuudunTyypinOikein() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        assertEquals(peli.mikaTyyppiRuudussa(new Ruutu(1,1)), Tyyppi.TYHJA);
    }
    
    @Test
    public void liikuttaPelaajanOikeaanRuutuun1() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        peli.lisaaHahmo(pelaaja);
        peli.liikutaPelaajaa( 1, 1);
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(1,2));
    }
    
    @Test
    public void liikuttaPelaajanOikeaanRuutuun2() {
       peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(3,2));
        peli.lisaaHahmo(pelaaja);
        peli.liikutaPelaajaa(new Ruutu(5,5));
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(5,5));
    }
    
    @Test
    public void eiLiikutaPelaajaaJosTavoiteruutuOnLaudanUlkopuolella1() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        peli.lisaaHahmo(pelaaja);
        peli.liikutaPelaajaa( -1, 0);
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(0,1));
    }
    
    @Test
    public void eiLiikutaPelaajaaJosTavoiteruutuOnLaudanUlkopuolella2() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        peli.lisaaHahmo(pelaaja);
        peli.liikutaPelaajaa(new Ruutu(11,0));
        
        assertEquals(pelaaja.getRuutu(), new Ruutu(0,1));
    }
    
    @Test
    public void pelaajaKuoleeKunLiikkuuDalekinRuutuun() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        peli.lisaaHahmo(dalek);
        peli.liikutaPelaajaa(1, 0);
        
        assertEquals(pelaaja.getTyyppi(), Tyyppi.KUOLLUTPELAAJA);
    }
    
    @Test
    public void pelaajaKuoleeKunDalekLiikkuuPelaajanRuutuun() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        peli.lisaaHahmo(dalek);
        peli.liikutaDalekejaPelaajaaPain();
        
        assertEquals(pelaaja.getTyyppi(), Tyyppi.KUOLLUTPELAAJA);
    }
    
    @Test
    public void pelaajaEiLiikuSamaanRuutuunKuolleenDalekinKanssa() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,1));
        Ruutu aloitusruutu = pelaaja.getRuutu();
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        peli.lisaaHahmo(dalek);
        dalek.kuole();
        peli.liikutaPelaajaa(1, 0);
        
        assertEquals(pelaaja.getRuutu(), aloitusruutu);
    }
    
    @Test
    public void pomminRajaytysHavittaaYmparoivatDalekit() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(2,2));
        peli.lisaaHahmo(dalek1);
        Dalek dalek2 = new Dalek(new Ruutu(1,0));
        peli.lisaaHahmo(dalek2);
        Dalek dalek3 = new Dalek(new Ruutu(0,0));
        peli.lisaaHahmo(dalek3);
        peli.rajaytaPommi();
        
        assertTrue(peli.getHahmot().contains(pelaaja) &&  
                !peli.getHahmot().contains(dalek1) && 
                !peli.getHahmot().contains(dalek2) && 
                !peli.getHahmot().contains(dalek3));
    }
    
    @Test
    public void pomminRajaytysEiHavittaMuitaDalekeja() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(3,1));
        peli.lisaaHahmo(dalek1);
        peli.rajaytaPommi();
        
        assertTrue(peli.getHahmot().contains(pelaaja) &&  
                peli.getHahmot().contains(dalek1)); 
    }
    
    @Test
    public void pomminRajaytysEiHavittaYmparoiviaKuolleitaDalekkeja() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,1));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(2,2));
        peli.lisaaHahmo(dalek);
        dalek.kuole();
        peli.rajaytaPommi();
        
        assertTrue(peli.getHahmot().contains(pelaaja) &&  peli.getHahmot().contains(dalek));
    }
    
    @Test
    public void pomminRajaytysToimiiLaudanReunalla() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(1,1));
        peli.lisaaHahmo(dalek);
        peli.rajaytaPommi();
        
        assertTrue(peli.getHahmot().contains(pelaaja) &&  !peli.getHahmot().contains(dalek));
    }
    
    @Test
    public void pelaajaTeleporttaaAinaTyhjaanRuutuun() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,1));
        peli.lisaaHahmo(dalek1);
            
        List<Ruutu> loppuRuudut = new ArrayList<Ruutu>();
        peli.setTeleportit(1000);
        
        for (int i = 0; i < 1000; i++) {
            peli.teleporttaaPelaaja();
            loppuRuudut.add(pelaaja.getRuutu());
            pelaaja.liiku(new Ruutu(0,0));
        }
        
        assertFalse(loppuRuudut.contains(new Ruutu(0,0)) || loppuRuudut.contains(new Ruutu(0,1)));
    }
    
    @Test
    public void dalekLiikkuuPelaajaaPain() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek = new Dalek(new Ruutu(2,2));
        peli.lisaaHahmo(dalek);
        peli.liikutaDalekejaPelaajaaPain();
        
        assertEquals(dalek.getRuutu(), new Ruutu(1,1));
    }

    
    @Test
    public void toisiinsaTormaavatDalekitKuolevat() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,2));
        peli.lisaaHahmo(dalek1);
        Dalek dalek2 = new Dalek(new Ruutu(1,2));
        peli.lisaaHahmo(dalek2);
        
        peli.liikutaDalekejaPelaajaaPain();
        
        assertTrue(dalek1.getTyyppi() == Tyyppi.KUOLLUTDALEK && dalek2.getTyyppi() == Tyyppi.KUOLLUTDALEK && dalek1.getRuutu().equals(new Ruutu(0,1)));
    }
    
    @Test
    public void dalekEiKuoleTormatessaanPelaajaan() {
        peli.poistaMontaHahmoa(peli.getHahmot());
        
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        Dalek dalek1 = new Dalek(new Ruutu(0,1));
        peli.lisaaHahmo(dalek1);
        
        peli.liikutaDalekejaPelaajaaPain();
        
        assertTrue(dalek1.getTyyppi() == Tyyppi.DALEK);
    }
    
    @Test
    public void dalekEiKuolePelaajanTormatessaSiihen() {
        peli.poistaMontaHahmoa(peli.getHahmot());
       
        Dalek dalek = new Dalek(new Ruutu(0,1));
        peli.lisaaHahmo(dalek);
        Pelaaja pelaaja = new Pelaaja(new Ruutu(0,0));
        peli.lisaaHahmo(pelaaja);
        
        peli.liikutaPelaajaa(0, 1);
        
        assertTrue(dalek.getTyyppi() == Tyyppi.DALEK);
    }
}
