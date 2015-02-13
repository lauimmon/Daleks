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
    
    public Kayttoliittyma(int leveys, int korkeus, int dalekienMaara) {
        this.peli= new Peli(leveys, korkeus, dalekienMaara);
        this.sivunPituus = 20;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Daleks");
        int korkeus = (peli.getLauta().getKokoY()+2)*sivunPituus+30;
        int leveys = (peli.getLauta().getKokoX()+1)*sivunPituus-8;
        
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
        }
        paivitaLauta();
    }

    private void paivitaLauta() {
        if (!voittikoPelaaja() && !havisikoPelaaja()) {
            peli.liikutaDalekejaPelaajaaPain();
            piirtoalusta.paivita();
            voittikoPelaaja();
            havisikoPelaaja();
        }
        
    }

    private boolean havisikoPelaaja() throws HeadlessException {
        if (peli.havisikoPelaaja()) {
            JOptionPane.showMessageDialog(frame, "Hävisit pelin!");
            return true;
        }
        return false;
    }

    private boolean voittikoPelaaja() throws HeadlessException {
        if (peli.voittikoPelaaja()) {
            JOptionPane.showMessageDialog(frame, "Voitit pelin!");
            return true;
        }
        return false;
    }
    
}