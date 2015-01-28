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
public enum Tyyppi {
    
    PELAAJA, KUOLLUTPELAAJA, DALEK, KUOLLUTDALEK, TYHJA;

    @Override
    public String toString() {
        if (this.equals(DALEK)) {
            return "@";
        } if (this.equals(KUOLLUTDALEK)) {
            return "#";
        } if (this.equals(PELAAJA)) {
            return "P";
        } if (this.equals(KUOLLUTPELAAJA)) {
            return "+";
        }
        return ".";
    }
    
    
}
