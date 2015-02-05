/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import daleks.luokat.Pelilauta;
import daleks.luokat.Ruutu;
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
    
    private Pelilauta lauta;
    
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
        Pelilauta lauta = new Pelilauta(10,20);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaLaudanXKoonOikein() {
        assertEquals(10, lauta.getKokoX());
    }
    
    @Test
    public void konstruktoriAsettaaLaudanYKoonOikein() {
        assertEquals(20, lauta.getKokoY());
    }
    
    @Test
    public void ruutuEiLaudallaJosLiianSuuriX() {
        Ruutu ruutu = new Ruutu(20,1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosNegatiivinenX() {
        Ruutu ruutu = new Ruutu(-1,1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosLiianSuuriY() {
        Ruutu ruutu = new Ruutu(1,20);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
    
    @Test
    public void ruutuEiLaudallaJosNegatiivinenY() {
        Ruutu ruutu = new Ruutu(7,-1);
        
        assertFalse(lauta.onkoRuutuLaudalla(ruutu));
    }
}
