/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.kayttoliittyma;

import daleks.logiikka.Peli;
import daleks.luokat.*;
import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author lauimmon
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private int sivu;
    
    public Piirtoalusta(Peli peli, int ruudunSivunPituus) {
        this.peli = peli;
        this.sivu = ruudunSivunPituus;
    }

    @Override
    public void piirra() {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, sivu*peli.getLauta().getKokoX(), sivu*peli.getLauta().getKokoY());
        
        for (Liikkuva l: peli.getHahmot()) {
            if (l.getTyyppi().equals(Tyyppi.DALEK)) {
                g.setColor(Color.BLACK);
            } else if (l.getTyyppi().equals(Tyyppi.KUOLLUTDALEK)) {
                g.setColor(Color.GRAY);
            } else if (l.getTyyppi().equals(Tyyppi.PELAAJA)) {
                g.setColor(Color.BLUE);
            } else if (l.getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA)) {
                g.setColor(Color.RED);
            }
            
            g.fill3DRect(sivu * l.getRuutu().getX(), sivu * l.getRuutu().getY(), sivu, sivu, true);
        }
        g.drawString("(R)äjähteet: "+peli.getPommit()+"    (T)eleportit: "+peli.getTeleportit(), 5, (peli.getLauta().getKokoY()+2)*sivu-20);
        g.drawString("(O)hjeet", (peli.getLauta().getKokoX()+1)*sivu-80, (peli.getLauta().getKokoY()+2)*sivu-20);
    }
    
}
