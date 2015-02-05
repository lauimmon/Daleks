/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

/**
 * Dalekit on pelissä pelaajaa päin liikkuvia vihollisia.
 * Dalek perii Liikkuva-luokan ja sen mukana metodit liiku ja kuole.
 *
 * @author lauimmon
 */
public class Dalek extends Liikkuva {

    /**
     * Konstruktorissa dalek asetetaan eläväksi ja sille asetetaan ruutu.
     * Ruudun asettamiseen käytetään Liikkuvan konstruktoria.
     * 
     * @param ruutu on dalekille asetettava ruutu
     */
    public Dalek(Ruutu ruutu) {
        super(ruutu);
        setTyyppi(Tyyppi.DALEK);
    }
    
    /**
     * Metodi muuttaa dalekin tyypin kuolleeksi dalekiksi.
     */
    public void kuole() {
        setTyyppi(Tyyppi.KUOLLUTDALEK);
    }
    
    /**
     * Liiku-metodi liikuttaa dalekia vain jos se on elossa. 
     * Metodi suorittaa Liikkuva-luokan metodin liiku(x,y).
     * 
     * @param x on ruutumäärä joka liikutaan korkeussuunnassa
     * @param y on ruutumäärä joka liikutaan leveyssuunnassa
     */
    @Override
    public void liiku(int x, int y) {
        if (getTyyppi() == Tyyppi.DALEK) {
            super.liiku(x, y);
        }
    }

    /**
     * Liiku-metodi liikuttaa dalekia vain jos se on elossa. 
     * Metodi suorittaa Liikkuva-luokan metodin liiku(ruutu).
     * 
     * @param ruutu, tavoiteruutu mihin halutaan
     */
    @Override
    public void liiku(Ruutu ruutu) {
        if (getTyyppi() == Tyyppi.DALEK) {
            super.liiku(ruutu);
        }
    }
    
   
    
    
    
}
