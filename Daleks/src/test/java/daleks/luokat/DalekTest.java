package daleks.luokat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daleks.luokat.Tyyppi;
import daleks.luokat.Dalek;
import daleks.luokat.Ruutu;
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
    
    private Dalek dalek;
    
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
        Dalek dalek = new Dalek(new Ruutu(1,2));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void dalekinKonstruktoriAsettaaRuudunOikein() {
        assertEquals(new Ruutu(1,2), dalek.getRuutu());
    }
    
    @Test
    public void dalekinKonstruktoriAsettaaTyypinOikein() {
        assertEquals(Tyyppi.DALEK, dalek.getTyyppi());
    }
    
    @Test
    public void dalekLiikkuuOikein1() {
        dalek.liiku(1, 0);
        
        assertEquals(new Ruutu(2,2), dalek.getRuutu());
    }
    
    @Test
    public void dalekLiikkuuOikein2() {
        dalek.liiku(new Ruutu(10,10));
        
        assertEquals(new Ruutu(10,10), dalek.getRuutu());
    }
    
    @Test
    public void dalekinTyyppiMuuttuuKuolleeksiDalekiksiKunDalekKuolee() {
        dalek.kuole();
        
        assertEquals(Tyyppi.KUOLLUTDALEK, dalek.getTyyppi());
    }
    
    @Test
    public void kuollutDalekEiLiiku1() {
        Ruutu aloitusruutu = dalek.getRuutu();
        dalek.kuole();
        dalek.liiku(10,0);
        
        assertEquals(aloitusruutu, dalek.getRuutu());
    }
    
    @Test
    public void kuollutDalekEiLiiku2() {
        Ruutu aloitusruutu = dalek.getRuutu();
        dalek.kuole();
        dalek.liiku(new Ruutu(10,10));
        
        assertEquals(aloitusruutu, dalek.getRuutu());
    }
    
    
}