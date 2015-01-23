/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.Arrays;

/**
 *
 * @author lauimmon
 */
public class Main {

    
    public static void main(String[] args) {
        Dalek dalek = new Dalek(new Ruutu(1,2));
        Ruutu ruutu = dalek.getRuutu();
//        dalek.kuole();
        dalek.liiku(10,0);
        
        System.out.println(ruutu.toString());
        System.out.println(dalek.getRuutu().toString());
        
        Dalek dalek1 = new Dalek(new Ruutu(1,2));
        Ruutu ruutu1 = dalek1.getRuutu();
        System.out.println(ruutu1.toString());
        dalek1.liiku(new Ruutu(2,2));
        
        System.out.println(ruutu1.toString());
    }
    
}
