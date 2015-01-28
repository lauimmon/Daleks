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
        int korkeus = 10;
        int leveys = 20;
        int dalekienMaara = 10;
        Kayttoliittyma kali = new Kayttoliittyma(new Pelilauta(korkeus,leveys),dalekienMaara);
        kali.run();    
        
        
    }
    
}
