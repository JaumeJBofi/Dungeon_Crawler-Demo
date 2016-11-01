/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Artefactos.Arma;
import Artefactos.Pocion;
import Artefactos.Artefacto;
import Artefactos.Armadura;
import Foundation.Coordinate;
import Foundation.ISavable;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Arthuro
 */
public class Avatar extends Player {

    private int vidaMaxima;
    private Arma arma_equip;
    private Armadura armadura_equip;
    private List<Artefacto> saco;    
    private int xp;
    private int currentTop;

    public Avatar(Coordinate position, String varNombre) {
        super(position, varNombre);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        tamShowX = 15;
        tamShowY = 15;        
        createKeys();
    }
    
    @Override
    public void createKeys()
    {
        keyDown = KeyEvent.VK_DOWN;
        keyTop = KeyEvent.VK_UP;
        keyRight = KeyEvent.VK_RIGHT;
        keyLeft = KeyEvent.VK_LEFT;        
    }

    public Avatar(Coordinate position, int varTamShowX, int varTamShowY, int vida, String varNombre, int varStrength, int varArmor) {
        super(position, varTamShowX, varTamShowY, vida, varNombre, varStrength, varArmor);
         createKeys();
    }    

    @Override
    public void LoadComponents(String spriteInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
