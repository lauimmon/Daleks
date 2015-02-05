/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import daleks.luokat.Liikkuva;
import daleks.luokat.Pelaaja;
import daleks.luokat.Ruutu;
import java.util.*;
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
public class RuutuTest {
    
    private Ruutu ruutu;
    
    public RuutuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ruutu = new Ruutu(1,2);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ruudunKonstruktoriAsettaaXnOikein() {
        assertEquals(1, ruutu.getX());
    }
    
    @Test
    public void ruudunKonstruktoriAsettaaYnOikein() {
        assertEquals(2, ruutu.getY());
    }
    
    @Test
    public void setXAsettaaXnOikein() {
        ruutu.setX(3);
        
        assertEquals(3, ruutu.getX());
    }
    
    @Test
    public void setYasettaaYnOikein() {
        ruutu.setY(3);
        
        assertEquals(3, ruutu.getY());
    }
    
    @Test
    public void equalsVertaaRuutujaOikeinKunRuudutSamat() {
        Ruutu ruutu2 = new Ruutu(1,2);
        
        assertTrue(ruutu.equals(ruutu2));
    }
    
    @Test
    public void equalsVertaaRuutujaOikeinKunRuudutEri() {
        Ruutu ruutu2 = new Ruutu(2,2);
        
        assertFalse(ruutu.equals(ruutu2));
    }
    
    @Test
    public void equalsVertaaRuutuaOikeinNullinKanssa() {
        assertFalse(ruutu.equals(null));
    }
    
    @Test
    public void equalsVertaaRuutuaOikeinToisenLuokanOlioon() {
        Liikkuva pelaaja = new Pelaaja(new Ruutu(1,2));
        
        assertFalse(ruutu.equals(pelaaja));
    }
    
    @Test
    public void hashcodetSamatKunKaksiSamaaRuutua() {
        Ruutu ruutu2 = new Ruutu(1,2);
        
        assertTrue(ruutu.hashCode() == ruutu2.hashCode());
    }
    
    @Test
    public void hashcodetEriKunKaksiEriRuutua() {
        Ruutu ruutu2 = new Ruutu(2,1);
        
        assertFalse(ruutu.hashCode() == ruutu2.hashCode());
    }
    
   
    
    @Test
    public void vertaaRuutujaOikeinKunSamatRuudut() {
        Ruutu ruutu2 = new Ruutu(1,2);
        
        assertTrue(ruutu2.compareTo(ruutu)==0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriX() {
        Ruutu ruutu2 = new Ruutu(2,2);
        
        assertTrue(ruutu2.compareTo(ruutu)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriY() {
        Ruutu ruutu2 = new Ruutu(1,3);
        
        assertTrue(ruutu2.compareTo(ruutu)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriXJaY() {
        Ruutu ruutu2 = new Ruutu(4,1);
        
        assertTrue(ruutu2.compareTo(ruutu)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinEqualsMetodilla1() {
        Ruutu ruutu2 = new Ruutu(4,1);
        
        assertFalse(ruutu.equals(ruutu2));
    }
    
    @Test
    public void vertaaRuutujaOikeinEqualsMetodilla2() {
        Ruutu ruutu2 = new Ruutu(1,2);
        
        assertTrue(ruutu.equals(ruutu2));
    }
    
    
    @Test
    public void ruutujenEtaisyysOikeinKunSamaX() {
        Ruutu ruutu2 = new Ruutu(1,5);
        
        assertTrue(3 == ruutu.etaisyysRuudusta(ruutu2));
    }
    
    @Test
    public void ruutujenEtaisyysOikeinKunSamaY() {
        Ruutu ruutu2 = new Ruutu(3,2);
        
        assertTrue(2 == ruutu.etaisyysRuudusta(ruutu2));
    }
    
    @Test
    public void ruutujenEtaisyysOikeinKunEriXJaY() {
        Ruutu ruutu2 = new Ruutu(3,4);
        
        assertTrue(Math.sqrt(8) == ruutu.etaisyysRuudusta(ruutu2));
    }
    
    
}
