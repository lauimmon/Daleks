/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

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
        setTyyppi(Tyyppi.KUOLLUTPELAAJA);
    }

    @Override
    public void liiku(int x, int y) {
        if (getTyyppi() == Tyyppi.PELAAJA) {
            super.liiku(x, y);
        }
    }

    @Override
    public void liiku(Ruutu ruutu) {
        if (getTyyppi() == Tyyppi.PELAAJA) {
            super.liiku(ruutu);
        }
    }
}
