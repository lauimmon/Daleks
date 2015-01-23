/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.daleks;

import java.util.*;


public class Pelilauta {
    
    private List<Liikkuva> hahmot;
    private int kokoX;
    private int kokoY;

    public Pelilauta(int xkoko, int ykoko) {
        this.hahmot = new ArrayList<Liikkuva>();
        kokoX = xkoko;
        kokoY = ykoko;
    }

    public int getKokoX() {
        return kokoX;
    }

    public int getKokoY() {
        return kokoY;
    }
    
    public boolean onkoRuutuLaudalla(Ruutu ruutu) {
         return ruutu.getX() <= kokoX &&  ruutu.getY() <= kokoY
                && ruutu.getX() > 0 && ruutu.getY() > 0;
    }
    
    public void lisaaHahmoLaudalle(Liikkuva hahmo) {
        if (onkoRuutuLaudalla(hahmo.getRuutu())) {
            hahmot.add(hahmo);
        }
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
    
    public void liikuta(Liikkuva liikkuva, int x, int y){
        Ruutu ruutu = new Ruutu(liikkuva.getRuutu().getX() + x, liikkuva.getRuutu().getY() + y);
        if (onkoRuutuLaudalla(ruutu)) {
            liikkuva.liiku(ruutu);
        }
    }
    
}
