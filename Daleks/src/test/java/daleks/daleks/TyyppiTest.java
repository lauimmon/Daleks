/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import daleks.luokat.Tyyppi;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author lauimmon
 */
public class TyyppiTest {
    
    @Test
    public void toStringToimiiOikeinKunTyyppiOnPelaaja() {
        Tyyppi tyyppi = Tyyppi.PELAAJA;
        
        assertEquals(tyyppi.toString(), "P");
    }
   
    @Test
    public void toStringToimiiOikeinKunTyyppiOnKuollutPelaaja() {
        Tyyppi tyyppi = Tyyppi.KUOLLUTPELAAJA;
        
        assertEquals(tyyppi.toString(), "+");
    }
    
    @Test
    public void toStringToimiiOikeinKunTyyppiOnDalek() {
        Tyyppi tyyppi = Tyyppi.DALEK;
        
        assertEquals(tyyppi.toString(), "@");
    }
    
    @Test
    public void toStringToimiiOikeinKunTyyppiOnKuollutDalek() {
        Tyyppi tyyppi = Tyyppi.KUOLLUTDALEK;
        
        assertEquals(tyyppi.toString(), "#");
    }
    
    @Test
    public void toStringToimiiOikeinKunTyyppiOnTyhja() {
        Tyyppi tyyppi = Tyyppi.TYHJA;
        
        assertEquals(tyyppi.toString(), ".");
    }
    
    
}
