/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.Game;
import Foundation.Options;
import Models.IDibujable;
import Models.Sprite;
import Models.Stage;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SERGIO
 */
public final class Lore extends Stage implements IDibujable{
                
    private BufferedImage diplay;
    private String loreConfiguration;
    
    private Sprite background;    
    private Sprite buttomStart;        
    
    private Game _owner;
    
    public Lore(Game owner){  
        super(owner);
        _owner = owner;
        loadLoreFile();    
    }         
    @Override
    public void Render(Graphics g) {                
        background.paint(g);
        buttomStart.paint(g);                
    }
     
    final public void LoadComponents(String[] spriteInfo) {
        // Formato de la configuracion
        // BackGround;Numero Imagenes;ITamaño x;Tamaño y;n        
        background = new Sprite();        
        background.ProcessSpriteInfo(spriteInfo[0]);
        buttomStart = new Sprite();
        buttomStart.ProcessSpriteInfo(spriteInfo[1]);
    }
    
    public void loadLoreFile()
    {
         try {            
            FileReader fr = new FileReader("Lore.txt");
            BufferedReader in = new BufferedReader(fr);
            
            String buffer; 
            String value = "";
            int numLines = Integer.parseInt(in.readLine());
            
            String[] tokens = new String[numLines];
            int i = 0;
            while((buffer = in.readLine())!=null)
            {            
               tokens[i++] = buffer;
            }                 
            LoadComponents(tokens);
        } catch (Exception e) {
            System.err.println("Error Archivo: " +"Lore.txt" + " no encontrado");
        }                         
    }
    
     @Override
    public void windowClosing(WindowEvent e) 
    {	   
        running = false;
        System.exit(0);        
    }

    @Override
    public void Dispose() {        
    }

    @Override
    public void UpdateStage() {
        
    }

    @Override
    public void RenderStage(Graphics g) {
        Render(g);
    }

    @Override
    public void InitStage() {        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(buttomStart.CheckClicked(e))
        {
            ChangeStage(_owner);
        }
    }        

    @Override
    public void LoadComponents(String spriteInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void interactionUserBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearInteraccion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetInteraccion(Options c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
