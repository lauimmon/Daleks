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
    public void konstruktoriLuoTyhjanListanHahmoista() {
        Pelilauta lauta = new Pelilauta();
        
        assertEquals(lauta.hahmot, new ArrayList<Liikkuva>());
    }
    
    @Test
    public void lisattyHahmoLoytyyLaudalta() {
        Pelilauta lauta = new Pelilauta();
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        
        assertTrue(lauta.onkoHahmoLaudalla(dalek));
    }
    
    
    @Test
    public void pelilaudallaOlevanHahmonRuutuMuuttuuKunSitaLiikutetaan() {
        Pelilauta lauta = new Pelilauta();
        Dalek dalek = new Dalek(new Ruutu(1,1));
        lauta.lisaaHahmoLaudalle(dalek);
        dalek.liiku(1, 1);
        
        assertTrue(lauta.onkoHahmoLaudalla(dalek));
    }
    
    
}
