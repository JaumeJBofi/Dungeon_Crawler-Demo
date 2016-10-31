/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Jauma
 */
public interface IDibujable {
    // Maneja la implementacion de poder ser renderizado
    public void Render();
    public void Render(Graphics g);
    public void LoadComponents();
    public void Dispose();    
    
    public GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    public static HashMap<String,BufferedImage> spriteHash = new HashMap();
}
