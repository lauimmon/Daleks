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
public class LiikuvaTest {
    
    public LiikuvaTest() {
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
    public void liikkuvatJarjestyvatRuutujensaMukaanOikein() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        Pelaaja pelaaja = new Pelaaja(new Ruutu(2,2));
        
        Liikkuva[] liikkuvat = new Liikkuva[2];
        liikkuvat[0] = dalek;
        liikkuvat[1] = pelaaja;
        
        Arrays.sort(liikkuvat);
        
        assertEquals(liikkuvat[0], dalek);
    }
}
