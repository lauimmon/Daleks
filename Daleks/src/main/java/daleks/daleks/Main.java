/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import daleks.kayttoliittyma.*;

/**
 *
 * @author lauimmon
 */
public class Main {

    
    public static void main(String[] args) {
        int korkeus = 20;
        int leveys = 30;
        int dalekienMaara = 50;  // min 20 max 80
        Kayttoliittyma kali = new Kayttoliittyma();
        kali.run();
    }
    
}
