/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ruudunKonstruktoriAsettaaXnOikein() {
        Ruutu ruutu = new Ruutu(1,2);
        
        assertEquals(1, ruutu.getX());
    }
    
    @Test
    public void ruudunKonstruktoriAsettaaYnOikein() {
        Ruutu ruutu = new Ruutu(1,2);
        
        assertEquals(2, ruutu.getY());
    }
    
    @Test
    public void asettaaXnOikein() {
        Ruutu ruutu = new Ruutu(1,2);
        ruutu.setX(3);
        
        assertEquals(3, ruutu.getX());
    }
    
    @Test
    public void asettaaYnOikein() {
        Ruutu ruutu = new Ruutu(1,2);
        ruutu.setY(3);
        
        assertEquals(3, ruutu.getY());
    }
    
    @Test
    public void vertaaRuutujaOikeinKunSamatRuudut() {
        Ruutu ruutu1 = new Ruutu(1,2);
        Ruutu ruutu2 = new Ruutu(1,2);
        
        assertTrue(ruutu2.compareTo(ruutu1)==0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriX() {
        Ruutu ruutu1 = new Ruutu(1,2);
        Ruutu ruutu2 = new Ruutu(2,2);
        
        assertTrue(ruutu2.compareTo(ruutu1)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriY() {
        Ruutu ruutu1 = new Ruutu(1,2);
        Ruutu ruutu2 = new Ruutu(1,3);
        
        assertTrue(ruutu2.compareTo(ruutu1)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinKunEriXJaY() {
        Ruutu ruutu1 = new Ruutu(1,3);
        Ruutu ruutu2 = new Ruutu(4,2);
        
        assertTrue(ruutu2.compareTo(ruutu1)>0);
    }
    
    @Test
    public void vertaaRuutujaOikeinEqualsMetodilla1() {
        Ruutu ruutu1 = new Ruutu(1,3);
        Ruutu ruutu2 = new Ruutu(4,2);
        
        assertFalse(ruutu1.equals(ruutu2));
    }
    
    @Test
    public void vertaaRuutujaOikeinEqualsMetodilla2() {
        Ruutu ruutu1 = new Ruutu(1,1);
        Ruutu ruutu2 = new Ruutu(1,1);
        
        assertTrue(ruutu1.equals(ruutu2));
    }
    
    
    @Test
    public void ruutujenEtaisyysOikeinKunSamaX() {
        Ruutu ruutu1 = new Ruutu(3,6);
        Ruutu ruutu2 = new Ruutu(3,2);
        
        assertTrue(4 == ruutu1.etaisyysRuudusta(ruutu2));
    }
    
    @Test
    public void ruutujenEtaisyysOikeinKunSamaY() {
        Ruutu ruutu1 = new Ruutu(0,1);
        Ruutu ruutu2 = new Ruutu(3,1);
        
        assertTrue(3 == ruutu1.etaisyysRuudusta(ruutu2));
    }
    
    @Test
    public void ruutujenEtaisyysOikeinKunEriXJaY() {
        Ruutu ruutu1 = new Ruutu(0,0);
        Ruutu ruutu2 = new Ruutu(2,2);
        
        assertTrue(Math.sqrt(8) == ruutu1.etaisyysRuudusta(ruutu2));
    }
    
    
}
