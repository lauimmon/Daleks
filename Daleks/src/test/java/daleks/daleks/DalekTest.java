package daleks.daleks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daleks.daleks.*;
import java.util.Arrays;
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
public class DalekTest {
    
    public DalekTest() {
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
    public void dalekinKonstruktoriAsettaaRuudunOikein() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        
        assertEquals(new Ruutu(1,2), dalek.getRuutu());
    }
    
    @Test
    public void dalekinKonstruktoriAsettaaTyypinOikein() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        
        assertEquals(Tyyppi.DALEK, dalek.getTyyppi());
    }
    
    @Test
    public void dalekLiikkuuOikein1() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        dalek.liiku(1, 0);
        
        assertEquals(new Ruutu(2,2), dalek.getRuutu());
    }
    
    @Test
    public void dalekLiikkuuOikein2() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        dalek.liiku(new Ruutu(10,10));
        
        assertEquals(new Ruutu(10,10), dalek.getRuutu());
    }
    
    @Test
    public void dalekinTyyppiMuuttuuKuolleeksiDalekiksiKunDalekKuolee() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        dalek.kuole();
        
        assertEquals(Tyyppi.KUOLLUTDALEK, dalek.getTyyppi());
    }
    
    @Test
    public void kuollutDalekEiLiiku1() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        Ruutu aloitusruutu = dalek.getRuutu();
//        dalek.kuole();
        dalek.liiku(10,0);
        
        assertEquals(aloitusruutu, dalek.getRuutu());
    }
    
    @Test
    public void kuollutDalekEiLiiku2() {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        Ruutu aloitusruutu = dalek.getRuutu();
        dalek.kuole();
        dalek.liiku(new Ruutu(10,10));
        
        assertEquals(aloitusruutu, dalek.getRuutu());
    }
    
    
}