/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daleks.kayttoliittyma;

import daleks.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
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
    private final int sivunPituus;
    private Piirtoalusta piirtoalusta;
    private int dalekienMaara;
    private boolean peliKaynnissa;
    private boolean pysyPaikoillaan;
    private boolean havisiko;
    private boolean voittiko;
    private int kierros;
    private int pisteet;
    
    public Kayttoliittyma() {
        this.dalekienMaara = 20;
        this.peli= new Peli(30, 20, dalekienMaara, 1, 1);
        this.sivunPituus = 24;
        peliKaynnissa = true;
        pysyPaikoillaan = false;
        havisiko = false;
        voittiko = false;
        kierros = 1;
        pisteet = 0;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Daleks");
        int korkeus = (peli.getLauta().getKokoY()+2)*sivunPituus+10;
        int leveys = (peli.getLauta().getKokoX()+1)*sivunPituus-20;
       
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setResizable(false);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
  
        frame.pack();
        frame.setVisible(true);
        
        while (true) {
            while (peliKaynnissa) {
                piirtoalusta.piirra();
                odota(200);
                if (pysyPaikoillaan) {
                    pysyPaikoillaanLoppuunAsti();
                }
                if (voittiko || havisiko) {
                    peliKaynnissa = false;
                }
            }
            if (voittiko) {
                uusiPeli(voittiko);
                voittiko = false;
                peliKaynnissa = true;
            } else if (havisiko) {
                uusiPeli(voittiko);
                havisiko = false;
                peliKaynnissa = true;
            }
        }
    }
    
    public void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(peli, sivunPituus, kierros, pisteet);
        container.add(piirtoalusta);
        frame.addKeyListener(this);
    }
    
    private void uusiPeli(boolean uusiKierros) {
        if (uusiKierros) {
            pisteet += dalekienMaara*50;
            lisaaDalekienMaaraa();
            kierros++;
            peli = new Peli(peli.getLauta().getKokoX(), peli.getLauta().getKokoY(), dalekienMaara, peli.getPommit()+1, peli.getTeleportit()+1);
        } else {
            pisteet = 0;
            dalekienMaara = 20;
            kierros = 1;
            peli = new Peli(peli.getLauta().getKokoX(), peli.getLauta().getKokoY(), dalekienMaara, 1, 1);
        }
        
        piirtoalusta = new Piirtoalusta(peli, sivunPituus, kierros, pisteet);
        frame.getContentPane().add(piirtoalusta);
        frame.pack();
        frame.setVisible(true);
    }

    private void lisaaDalekienMaaraa() {
        if (kierros < 3) {
            dalekienMaara += 5;
        } else if (kierros < 8) {
            dalekienMaara += 4;
        } else if (dalekienMaara < 78) {
            dalekienMaara += 3;
        }
        
    }

    private void pysyPaikoillaanLoppuunAsti() {
        while (!(havisiko || voittiko)) {
            odota(600);
            paivitaLauta();
        }
        pysyPaikoillaan = false;
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
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) 
            peli.liikutaPelaajaa(-1, 0);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8)
            peli.liikutaPelaajaa(0, -1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2)
            peli.liikutaPelaajaa(0, 1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6)
            peli.liikutaPelaajaa(1, 0);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7)
            peli.liikutaPelaajaa(-1, -1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9)
            peli.liikutaPelaajaa(1, -1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3)
            peli.liikutaPelaajaa(1, 1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1)
            peli.liikutaPelaajaa(-1, 1);
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {}
        else if (e.getKeyCode() == KeyEvent.VK_A) 
            peli.liikutaPelaajaa(-1, 0);
        else if (e.getKeyCode() == KeyEvent.VK_W)
            peli.liikutaPelaajaa(0, -1);
        else if (e.getKeyCode() == KeyEvent.VK_X)
            peli.liikutaPelaajaa(0, 1);
        else if (e.getKeyCode() == KeyEvent.VK_D)
            peli.liikutaPelaajaa(1, 0);
        else if (e.getKeyCode() == KeyEvent.VK_Q)
            peli.liikutaPelaajaa(-1, -1);
        else if (e.getKeyCode() == KeyEvent.VK_E)
            peli.liikutaPelaajaa(1, -1);
        else if (e.getKeyCode() == KeyEvent.VK_C)
            peli.liikutaPelaajaa(1, 1);
        else if (e.getKeyCode() == KeyEvent.VK_Z)
            peli.liikutaPelaajaa(-1, 1);
        else if (e.getKeyCode() == KeyEvent.VK_S) {}
        else if (e.getKeyCode() == KeyEvent.VK_R) 
            paivita = peli.rajaytaPommi();
        else if (e.getKeyCode() == KeyEvent.VK_T)
            paivita = peli.teleporttaaPelaaja();
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            pysyPaikoillaan = true;
            paivita = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_O) {
            tulostaOhjeet();
            paivita = false;
        } else paivita = false;
        if (paivita) paivitaLauta();
    }

    private void paivitaLauta() {
        havisikoPelaaja();
        if (havisiko) return;
        peli.liikutaDalekejaPelaajaaPain();
        piirtoalusta.piirra();
        havisikoPelaaja();
        if (havisiko) return;
        voittikoPelaaja();
    }

    private void havisikoPelaaja() {
        if (peli.havisikoPelaaja()) {
            Object[] kyllaEi = {"Kyllä", "Ei"};
            
            int vastaus = JOptionPane.showOptionDialog(frame, "Hävisit pelin!\n\nUusi yritys?", "Daleks", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, kyllaEi, kyllaEi[0]);
            if (vastaus == 1) {
                System.exit(0);
            }
            havisiko = true;
        }
    }

    private void voittikoPelaaja() {
        if (peli.voittikoPelaaja()) {
            JOptionPane.showMessageDialog(frame, "Voitit pelin!\n\nAloita seuraava kierros painamalla OK", "Daleks", 
                    JOptionPane.INFORMATION_MESSAGE);
            voittiko = true;
        }
    }
    
    private void tulostaOhjeet() {
        String ohjeet = "Tervetuloa pelaamaan Daleks-peliä!\n\n"
                + "Pakene dalekeja ja yritä saada ne törmäämään toisiinsa.\n"
                + "Dalekit kuolevat jos ne törmäävät toisiinsa ja sinä kuolet jos osut dalekiin.\n"
                + "Voit tuhota vieressäsi olevat dalekit myös räjäyttämällä pommin.\n"
                + "Voitat pelin kun kaikki dalekit kuolevat.\n\n"
                + "Ohjaus:\n\nLiikkuminen numeronäppäimistä.\n"
                + "8 ylös, 2 alas, 4 vasemmalle, 6 oikealle, 5 pysy paikallaan ja loput vinottaisiin suuntiin.\n"
                + "Tai vaihtoehtoisesti kirjainnäppäimillä QWE,ASD,ZXC\n"
                + "P näppäimellä voit jäädä paikallesi loppuajaksi.\n\n"
                + "R pommin räjäytys\n"
                + "T teleportti";
        JOptionPane.showMessageDialog(frame, ohjeet);
    }

    private void odota(int aika) {
        try {
            Thread.sleep(aika);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}