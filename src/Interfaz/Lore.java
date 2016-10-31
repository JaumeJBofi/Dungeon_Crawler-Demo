/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Models.IDibujable;
import Models.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author SERGIO
 */
public final class Lore implements IDibujable{
                
    private BufferedImage diplay;
    private String loreConfiguration;
    
    private Sprite background;    
    private Sprite buttomStart;        
    
    public Lore(){       
        loadLoreFile();    
    }      
    @Override
    public void Render(Graphics g) {                
        background.paint(g);
        buttomStart.paint(g);
        
        
    }

    @Override
    public void Render() {
        
    }

    @Override
    final public void LoadComponents() {
        // Formato de la configuracion
        // BackGround;Numero Imagenes;ITamaño x;Tamaño y;n        
        background = new Sprite();                        
        buttomStart = new Sprite();
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
    public void Dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
