/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

/**
 *
 * @author lauimmon
 */
public class Pelaaja extends Liikkuva {

    public Pelaaja(Ruutu ruutu) {
        super(ruutu);
        setTyyppi(Tyyppi.PELAAJA);
    }

    public void kuole() {
        System.out.println("Hävisit pelin!");
    }

    
}
