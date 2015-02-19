/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

/**
 * Pelissä pelaaja liikuttaa Pelaaja-luokan oliota.
 * Pelaaja perii Liikkuva-luokan ja sen kuole ja liiku -metodit.
 * 
 * @author lauimmon
 */
public class Pelaaja extends Liikkuva {

    /**
     * Konstruktorissa pelaaja asetetaan eläväksi ja sille asetetaan ruutu.
     * Ruudun asettamiseen käytetään Liikkuvan konstruktoria.
     * 
     * @param ruutu on pelaajalle asetettava ruutu
     */
    
    public Pelaaja(Ruutu ruutu) {
        super(ruutu);
        setTyyppi(Tyyppi.PELAAJA);
    }

    /**
     * Metodi muuttaa pelaajan tyypin kuolleeksi.
     */
    public void kuole() {
        setTyyppi(Tyyppi.KUOLLUTPELAAJA);
    }

    /**
     * Metodi liikuttaa pelaajaa vain jos se on elossa.
     * Metodi suorittaa Liikkuva-luokan liiku(ruutu) -metodin.
     * 
     * @param ruutu tavoiteruutu johon halutaan liikkua
     */
    @Override
    public void liiku(Ruutu ruutu) {
        if (getTyyppi() == Tyyppi.PELAAJA) {
            super.liiku(ruutu);
        }
    }
}
