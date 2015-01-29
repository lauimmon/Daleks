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
public class KayttoliittymaTest {
    
    public KayttoliittymaTest() {
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
    public void KonstruktoriLuoOikeanmaaranDalekejaLaudalle() {
        Pelilauta lauta = new Pelilauta(10,10);
        Kayttoliittyma kali = new Kayttoliittyma(lauta, 10);
        
        assertEquals(lauta.getHahmot().size(), 11);
    }
    
}
