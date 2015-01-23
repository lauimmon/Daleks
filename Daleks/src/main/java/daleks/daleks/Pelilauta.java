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
    
}
