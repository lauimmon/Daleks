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

    private final Peli peli;
    private final int sivu;
    private final int kierros;
    private final int pisteet;
    
    public Piirtoalusta(Peli peli, int ruudunSivunPituus, int kierros, int pisteet) {
        this.peli = peli;
        this.sivu = ruudunSivunPituus;
        this.kierros = kierros;
        this.pisteet = pisteet;
    }

    @Override
    public void piirra() {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.drawRect(-1, -1, sivu*peli.getLauta().getKokoX()+1, sivu*peli.getLauta().getKokoY()+1);
        
        for (Liikkuva l: peli.getHahmot()) {
            if (l.getTyyppi().equals(Tyyppi.DALEK)) {
                g.setColor(Color.BLACK);
                if (l.getRuutu().getX() < peli.getPelaaja().getRuutu().getX()) {
                    piirraDalekOikealle(g, sivu*l.getRuutu().getX(), sivu*l.getRuutu().getY());
                } else {
                    piirraDalekVasemmalle(g, sivu*l.getRuutu().getX(), sivu*l.getRuutu().getY());
                }
            } else if (l.getTyyppi().equals(Tyyppi.KUOLLUTDALEK)) {
                g.setColor(Color.GRAY);
                piirraKuollutDalek(g,sivu*l.getRuutu().getX(), sivu*l.getRuutu().getY());
            } else if (l.getTyyppi().equals(Tyyppi.PELAAJA)) {
                g.setColor(Color.BLUE);
                g.fillRect(sivu * l.getRuutu().getX(), sivu * l.getRuutu().getY(), sivu, sivu);
            } else if (l.getTyyppi().equals(Tyyppi.KUOLLUTPELAAJA)) {
                g.setColor(Color.RED);
                g.fillRect(sivu * l.getRuutu().getX(), sivu * l.getRuutu().getY(), sivu, sivu);
            }
        }
        g.drawString("(R)äjähteet: "+peli.getPommit()+"    (T)eleportit: "+peli.getTeleportit(), 5, (peli.getLauta().getKokoY()+2)*sivu);
        g.drawString("(O)hjeet", (peli.getLauta().getKokoX()+1)*sivu-80, (peli.getLauta().getKokoY()+2)*sivu-30);
        g.drawString("Kierros " + kierros, 5, (peli.getLauta().getKokoY()+2)*sivu-30);
        g.drawString("Pisteet: " + pisteet, 350, (peli.getLauta().getKokoY()+2)*sivu-30);
    }
    
    
    private void piirraDalekVasemmalle(Graphics g, int x, int y) {
        // 24x24 ruutuun
        g.fillRect(x+13, y+1, 6, 4);
        g.fillRect(x+7, y+2, 5, 1);
        
        g.fillRect(x+12, y+2, 1, 6);
        g.fillRect(x+19, y+2, 1, 6);
        g.fillRect(x+15, y+5, 1, 3);
        
        g.fillRect(x+11, y+4, 1, 1);
        g.fillRect(x+20, y+3, 1, 2);
        
        g.fillRect(x+11, y+5, 10, 1);
        g.fillRect(x+11, y+8, 10, 1);
        g.fillRect(x+11, y+12, 11, 1);
        
        for (int i = 0; i < 6; i++) {
            g.fillRect(x+11+2*i, y+9, 1, 3);
        }
        
        g.fillRect(x+10, y+9, 1, 5);
        g.fillRect(x+9, y+10, 1, 2);
        g.fillRect(x+2, y+10, 9, 1);
        g.fillRect(x+1, y+9, 1, 3);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRect(x+10+2*i, y+13+2*j, 1, 1);
                g.fillRect(x+11+2*i, y+14+2*j, 1, 1);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            g.fillRect(x+9, y+16+2*i, 1, 1);
        }
        
        for (int i = 0; i < 1; i++) {
            g.fillRect(x+8, y+19+2*i, 1, 1);
        }
        
        g.fillRect(x+7, y+21, 16, 2);
    }
    
    private void piirraDalekOikealle(Graphics g, int x, int y) {
        int max = peli.getLauta().getKokoX();
        g.fillRect(sivu+x-19, y+1, 6, 4);
        g.fillRect(sivu+x-12, y+2, 5, 1);
        
        g.fillRect(sivu+x-13, y+2, 1, 6);
        g.fillRect(sivu+x-20, y+2, 1, 6);
        g.fillRect(sivu+x-16, y+5, 1, 3);
        
        g.fillRect(sivu+x-12, y+4, 1, 1);
        g.fillRect(sivu+x-21, y+3, 1, 2);
        
        g.fillRect(sivu+x-21, y+5, 10, 1);
        g.fillRect(sivu+x-21, y+8, 10, 1);
        g.fillRect(sivu+x-22, y+12, 11, 1);
        
        for (int i = 0; i < 6; i++) {
            g.fillRect(sivu+x-12-2*i, y+9, 1, 3);
        }
        
        g.fillRect(sivu+x-11, y+9, 1, 5);
        g.fillRect(sivu+x-10, y+10, 1, 2);
        g.fillRect(sivu+x-11, y+10, 9, 1);
        g.fillRect(sivu+x-2, y+9, 1, 3);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRect(sivu+x-11-2*i, y+13+2*j, 1, 1);
                g.fillRect(sivu+x-12-2*i, y+14+2*j, 1, 1);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            g.fillRect(sivu+x-10, y+16+2*i, 1, 1);
        }
        
        for (int i = 0; i < 1; i++) {
            g.fillRect(sivu+x-9, y+19+2*i, 1, 1);
        }
        
        g.fillRect(sivu+x-23, y+21, 16, 2);
    }

    private void piirraKuollutDalek(Graphics g, int x, int y) {
        g.fillRect(x+7, y+21, 16, 2);
        
        for (int i = 0; i < 10; i++) {
            g.fillRect(x+4+i, y+21-i, 1, 1);
        }
        
        for (int i = 0; i < 8; i++) {
            g.fillRect(x+7+i, y+17-i, 1, 1);
        }
        
        for (int i = 0; i < 8; i++) {
            g.fillRect(x+8+i, y+18-i, 1, 1);
        }
        
        for (int i = 0; i < 6; i++) {
            g.fillRect(x+7+i, y+16-i, 1, 1);
        }
        
        for (int i = 0; i < 5; i++) {
            g.fillRect(x+7+i, y+15-i, 1, 1);
            g.fillRect(x+22-i, y+21-2*i, 1, 2);
        }
        
        for (int i = 0; i < 4; i++) {
            g.fillRect(x+7+i, y+14-i, 1, 1);
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRect(x+10+2*i, y+14+2*j, 1, 1);
                g.fillRect(x+11+2*i, y+15+2*j, 1, 1);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            g.fillRect(x+16+i, y+14-i, 1, 2);
        }
    }
}
