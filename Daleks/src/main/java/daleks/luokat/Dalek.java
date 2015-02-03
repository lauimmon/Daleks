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
public class Dalek extends Liikkuva {

    public Dalek(Ruutu ruutu) {
        super(ruutu);
        setTyyppi(Tyyppi.DALEK);
    }
    
    public void kuole() {
        setTyyppi(Tyyppi.KUOLLUTDALEK);
    }
    
    @Override
    public void liiku(int x, int y) {
        if (getTyyppi() == Tyyppi.DALEK) {
            super.liiku(x, y);
        }
    }

    @Override
    public void liiku(Ruutu ruutu) {
        if (getTyyppi() == Tyyppi.DALEK) {
            super.liiku(ruutu);
        }
    }
    
   
    
    
    
}
