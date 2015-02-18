/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.kayttoliittyma;

import daleks.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author lauimmon
 */
public class Kayttoliittyma implements Runnable, KeyListener {
    
    private JFrame frame;
    private Peli peli;   
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;
    private int dalekienMaara;
    
    public Kayttoliittyma(int leveys, int korkeus, int dalekienMaara) {
        this.dalekienMaara = dalekienMaara;
        this.peli= new Peli(leveys, korkeus, dalekienMaara);
        this.sivunPituus = 20;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Daleks");
        int korkeus = (peli.getLauta().getKokoY()+2)*sivunPituus+20;
        int leveys = (peli.getLauta().getKokoX()+1)*sivunPituus-5;
        
        frame.setPreferredSize(new Dimension(leveys, korkeus));
  
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  
        luoKomponentit(frame.getContentPane());
  
        frame.pack();
        frame.setVisible(true);
    }
  
    public void luoKomponentit(Container container) {
        // Huom! Luo ensin piirtoalusta jonka lisäät container-olioon
        // Luo vasta tämän jälkeen näppäimistönkuuntelija, jonka lisäät frame-oliolle
        piirtoalusta = new Piirtoalusta(peli, sivunPituus);
        container.add(piirtoalusta);
        getFrame().addKeyListener(this);
    }
  
    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }
  
    public JFrame getFrame() {
        return frame;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        boolean paivita = true;
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
            peli.liikutaPelaajaa(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
            peli.liikutaPelaajaa(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
            peli.liikutaPelaajaa(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
            peli.liikutaPelaajaa(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
            peli.liikutaPelaajaa(-1, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
            peli.liikutaPelaajaa(1, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
            peli.liikutaPelaajaa(1, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            peli.liikutaPelaajaa(-1, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            peli.rajaytaPommi();
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            peli.teleporttaaPelaaja();
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            while (true) {
                paivitaLauta();
                if (voittikoPelaaja() || havisikoPelaaja()) {
                    break;
                }
            }
            paivita = false;
        } else if (e.getKeyCode() == KeyEvent.VK_O) {
            tulostaOhjeet();
            paivita = false;
        }
        if (paivita) {
            paivitaLauta();
        }
    }

    private void paivitaLauta() {
        if (!voittikoPelaaja() && !havisikoPelaaja()) {
            piirtoalusta.paivita();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
            }
            peli.liikutaDalekejaPelaajaaPain();
            piirtoalusta.paivita();
            voittikoPelaaja();
            havisikoPelaaja();
        }
        
    }

    private boolean havisikoPelaaja() throws HeadlessException {
        if (peli.havisikoPelaaja()) {
            Object[] kyllaEi = {"Kyllä", "Ei"};
            
            int vastaus = JOptionPane.showOptionDialog(frame, "Hävisit pelin!\n\nUusi yritys?", "Daleks", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, kyllaEi, kyllaEi[0]);
            if (vastaus == 1) {
                System.exit(0);
            }
            if (vastaus == 0) {
                peli = new Peli(peli.getLauta().getKokoX(), peli.getLauta().getKokoY(), dalekienMaara);
                
            }
            return true;
        }
        return false;
    }

    private boolean voittikoPelaaja() throws HeadlessException {
        if (peli.voittikoPelaaja()) {
            
            JOptionPane.showMessageDialog(frame, "Voitit pelin!\n\nAloita seuraava kierros painamalla OK", "Daleks", 
                    JOptionPane.INFORMATION_MESSAGE);
            peli = new Peli(peli.getLauta().getKokoX(), peli.getLauta().getKokoY(), dalekienMaara);
            
            return true;
        }
        return false;
    }
    
    private void tulostaOhjeet() {
        String saannot = "Tervetuloa pelaamaan Daleks-peliä!\n\n"
                + "Pakene dalekeja ja yritä saada ne törmäämään toisiinsa.\n"
                + "Dalekit kuolevat jos ne törmäävät toisiinsa ja sinä kuolet jos osut dalekiin.\n"
                + "Voit tuhota vieressäsi olevat dalekit myös räjäyttämällä pommin.\n"
                + "Voitat pelin kun kaikki dalekit kuolevat.\n\n"
                + "Ohjaus:\n\nLiikkuminen numeronäppäimistä.\n"
                + "8 ylös, 2 alas, 4 vasemmalle, 6 oikealle, 5 pysy paikallaan \nja loput vinottaisiin suuntiin.\n\n"
                + "R pommin räjäytys\n"
                + "T teleportti";
        JOptionPane.showMessageDialog(frame, saannot);
    }
    
}