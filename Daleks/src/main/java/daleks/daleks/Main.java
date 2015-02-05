/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import daleks.kayttoliittyma.*;
import daleks.luokat.Pelilauta;

/**
 *
 * @author lauimmon
 */
public class Main {

    
    public static void main(String[] args) {
        int korkeus = 10;
        int leveys = 20;
        int dalekienMaara = 10;
        Kayttoliittyma kali = new Kayttoliittyma(korkeus,leveys,dalekienMaara);
        kali.run();
    }
    
}
