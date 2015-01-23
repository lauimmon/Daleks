/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

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
public class PelaajaTest {
    
    public PelaajaTest() {
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
    public void pelaajanKonstruktoriAsettaaRuudunOikein() {
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,2));
        
        assertEquals(new Ruutu(1,2), pelaaja.getRuutu());
    }
    
    @Test
    public void pelaajanKonstruktoriAsettaaTyypinOikein() {
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,2));
        
        assertEquals(Tyyppi.PELAAJA, pelaaja.getTyyppi());
    }
    
    @Test
    public void pelaajaLiikkuuOikein1() {
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,2));
        pelaaja.liiku(0, 1);
        
        assertEquals(new Ruutu(1,3), pelaaja.getRuutu());
    }
    
    @Test
    public void pelaajaLiikkuuOikein2() {
        Pelaaja pelaaja = new Pelaaja(new Ruutu(1,2));
        pelaaja.liiku(new Ruutu(10,10));
        
        assertEquals(new Ruutu(10,10), pelaaja.getRuutu());
    }
    
    
}
