/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import daleks.luokat.Liikkuva;
import daleks.luokat.Pelaaja;
import daleks.luokat.Dalek;
import daleks.luokat.Ruutu;
import java.util.*;
import junit.framework.Assert;
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
public class LiikkuvaTest {
    
    public LiikkuvaTest() {
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
    public void konstruktoriAsettaaRuudunOikein() {
        Liikkuva dalek = new Dalek(new Ruutu(1,2));
        
        assertEquals(dalek.getRuutu(), new Ruutu(1,2));
    }
    
    @Test
    public void vertaileeLiikkuviaOikeinKunSamatRuudut() {
        Liikkuva dalek = new Dalek(new Ruutu(1,2));
        Liikkuva pelaaja = new Pelaaja(new Ruutu(1,2));
        
        
        assertTrue(dalek.compareTo(pelaaja)==0);
    }
    
    @Test
    public void vertaileeLiikkuviaOikeinKunSamaY() {
        Liikkuva dalek = new Dalek(new Ruutu(1,2));
        Liikkuva pelaaja = new Pelaaja(new Ruutu(2,2));
        
        
        assertTrue(dalek.compareTo(pelaaja)<0);
    }
    
    @Test
    public void vertaileeLiikkuviaOikeinKunSamaX() {
        Liikkuva dalek = new Dalek(new Ruutu(2,1));
        Liikkuva pelaaja = new Pelaaja(new Ruutu(2,2));
        
        
        assertTrue(dalek.compareTo(pelaaja)<0);
    }
    
    @Test
    public void vertaileeLiikkuviaOikeinKunEriXJaY() {
        Liikkuva dalek = new Dalek(new Ruutu(0,7));
        Liikkuva pelaaja = new Pelaaja(new Ruutu(1,2));
        
        
        assertTrue(dalek.compareTo(pelaaja)<0);
    }
    
    @Test
    public void vertaileeLiikkuviaOikeinEqualsMetodilla() {
        Liikkuva dalek = new Dalek(new Ruutu(0,7));
        Liikkuva pelaaja = new Pelaaja(new Ruutu(1,2));
        
        
        assertFalse(pelaaja.equals(dalek));
    }
    
    
    @Test
    public void liikkuvaLiikkuuOikein() {
        Liikkuva dalek = new Dalek(new Ruutu(0,7));
        dalek.liiku(new Ruutu(1,1));
        
        assertEquals(dalek.getRuutu(), new Ruutu(1,1));
    }
    
}
