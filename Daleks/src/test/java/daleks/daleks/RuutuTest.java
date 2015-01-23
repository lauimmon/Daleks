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
    public void ruudutJarjestyvatOikeinKunEriXt() {
        Ruutu ruutu1 = new Ruutu(1,2);
        Ruutu ruutu2 = new Ruutu(2,2);
        
        Ruutu[] ruudut = new Ruutu[2];
        ruudut[0] = ruutu1;
        ruudut[1] = ruutu2;
        
        Arrays.sort(ruudut);
        
        assertEquals(ruudut[0], ruutu1);
    }
    
    @Test
    public void ruudutJarjestyvatOikeinKunEriYt() {
        Ruutu ruutu1 = new Ruutu(1,2);
        Ruutu ruutu2 = new Ruutu(1,1);
        
        Ruutu[] ruudut = new Ruutu[2];
        ruudut[0] = ruutu1;
        ruudut[1] = ruutu2;
        
        Arrays.sort(ruudut);
        
        assertEquals(ruudut[0], ruutu2);
    }
    
    @Test
    public void ruudutJarjestyvatOikeinKunEriXtJaYt() {
        Ruutu ruutu1 = new Ruutu(3,3);
        Ruutu ruutu2 = new Ruutu(2,5);
        
        Ruutu[] ruudut = new Ruutu[2];
        ruudut[0] = ruutu1;
        ruudut[1] = ruutu2;
        
        Arrays.sort(ruudut);
        
        assertEquals(ruudut[0], ruutu2);
    }
    
    @Test
    public void ruutujenEtaisyysOikein() {
        Ruutu ruutu1 = new Ruutu(0,1);
        Ruutu ruutu2 = new Ruutu(3,1);
        
        assertTrue(3 == ruutu1.etaisyysRuudusta(ruutu2));
    }
}
