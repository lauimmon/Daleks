/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;


public class Pelilauta {
    
    List<Liikkuva> hahmot;

    public Pelilauta() {
        this.hahmot = new ArrayList<Liikkuva>();
    }
    
    public void lisaaHahmoLaudalle(Liikkuva hahmo) {
        hahmot.add(hahmo);
    }
    
    public void tulostaHahmot() {
        for (Liikkuva liikkuva : hahmot) {
            System.out.println(liikkuva);
        }
    }

    public List<Liikkuva> getHahmot() {
        return hahmot;
    }
    
    public boolean onkoHahmoLaudalla(Liikkuva hahmo) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.equals(hahmo)) {
                return true;
            }
        }
        return false;
    }
    
    public Tyyppi minkaTyyppinenHahmoOnRuudussa(Ruutu ruutu) {
        for (Liikkuva liikkuva : hahmot) {
            if (liikkuva.getRuutu().equals(ruutu)) {
                return liikkuva.getTyyppi();
            }
        }
        return Tyyppi.TYHJA;
    }
    
}
