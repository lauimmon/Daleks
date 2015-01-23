/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;

/**
 *
 * @author lauimmon
 */
public class Main {

    
    public static void main(String[] args) {
        Pelilauta lauta = new Pelilauta();
        
        Dalek dalek = new Dalek(new Ruutu(1,2));
        Dalek dalek1 = new Dalek(new Ruutu(2,2));
        
        lauta.lisaaHahmoLaudalle(dalek);
        lauta.lisaaHahmoLaudalle(dalek1);
        
        List<Liikkuva> hahmot = lauta.getHahmot();
        
    }
    
}
