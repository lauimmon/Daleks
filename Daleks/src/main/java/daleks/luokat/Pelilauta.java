/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.luokat;

import java.util.*;


public class Pelilauta {
    
    private final int kokoX;
    private final int kokoY;

    public Pelilauta(int xkoko, int ykoko) {
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
         return ruutu.getX() < kokoX &&  ruutu.getY() < kokoY
                && ruutu.getX() >= 0 && ruutu.getY() >= 0;
    }

    public List<Ruutu> ymparoivatRuudut(Ruutu ruutu) {
        List<Ruutu> ruudut = new ArrayList<Ruutu>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Ruutu ruutu1 = new Ruutu(ruutu.getX()+i, ruutu.getY()+j);
                if (!(i == 0 && j == 0) && onkoRuutuLaudalla(ruutu1)) {
                    ruudut.add(ruutu1);
                }
            }
        }
        return ruudut;
    }
}
