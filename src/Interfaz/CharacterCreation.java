/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.Game;
import static Controllers.Game.chosenPlayer;
import Models.IDibujable;
import Models.Sprite;
import Models.Stage;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jauma
 */
public class CharacterCreation extends Stage implements IDibujable {
   
    private Stage _owner;  
    
    private Sprite backGround;
    private Sprite option1;
    private Sprite option2;
    private Sprite option3;
    private volatile Sprite current = null;    
    
    public CharacterCreation(Stage owner){  
    super(owner);
    _owner = owner;    
    loadCCreationFile();    
    _movements = new Thread(new Runnable() {
        @Override
        public void run() {    
            while(true)
            {
                if(current!=null)
                {
                    try {  

                        Thread.sleep(500);
                        current.nextImg();                        
                                           
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CharacterCreation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }            
            }
        }
    });         
    }
     
    final public void LoadComponents(String[] spriteInfo) {
    // Formato de la configuracion
    // BackGround;Numero Imagenes;ITamaño x;Tamaño y;n        
        option1 = new Sprite();        
        option1.ProcessSpriteInfo(spriteInfo[0]);
        option1.SetHeight(128);
        option1.SetWidth(128);
        option1.Resize();
        
        option2 = new Sprite();        
        option2.ProcessSpriteInfo(spriteInfo[1]);
        option2.SetHeight(128);
        option2.SetWidth(128);
        option2.Resize();
        
        option3 = new Sprite();        
        option3.ProcessSpriteInfo(spriteInfo[2]);
        option3.SetHeight(128);
        option3.SetWidth(128);
        option3.Resize();
        
        backGround = new Sprite();
        backGround.ProcessSpriteInfo(spriteInfo[3]);
        backGround.SetHeight(680);
        backGround.SetWidth(1366);
        backGround.Resize();
 
    }
     
    public void loadCCreationFile()
    {
         try {            
            FileReader fr = new FileReader("CCreation.txt");
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
    public void UpdateStage() {      
    }

    @Override
    public void RenderStage(Graphics g) {        
        Render(g);
    }

    @Override
    public void InitStage() {
        _movements.start();
    }

    @Override
    public void interactionUserBox() {       
    }

    @Override
    public void clearInteraccion() {       
    }

    @Override
    public void Render(Graphics g) {
        backGround.paint(g);
        option1.paint(g);
        option2.paint(g);
        option3.paint(g);
    }
    
     @Override
    public void mouseClicked(MouseEvent e) 
    {
        System.err.println("X: " + e.getX() + " " + "Y: " + e.getY());
        /*
        X: 288  Y: 76
        X: 491  Y: 75
        X: 241  Y: 654
        X: 517  Y: 655
        */
        if(CheckClicked(e,288,74,200,580))
        {
            current = option1;
            return;
        }
        /*
        X: 591  Y: 61
        X: 852  Y: 65
        X: 587  Y: 660
        X: 857  Y: 651
        */
        if(CheckClicked(e,591,61,200,580))
        {
            current = option2;
            return;
        }
        /*
        X: 945  Y: 82
        X: 1211  Y: 81
        X: 937  Y: 654
        X: 1251  Y: 654 
        */
         if(CheckClicked(e,945,61,200,580))
        {
            current = option3;
            return;
        }
        // Width 65
        // Height 595
        
        
    }   
    public boolean CheckClicked(MouseEvent e,int posX,int posY,int width,int height)
    {
        int posXVar = e.getX();
        int posYVar = e.getY();
        return ((posXVar>=posX&&posXVar<=(posX+width))&&(posYVar>=posY&&posYVar<=(posY+height)));
    }

    @Override
    public void LoadComponents(String spriteInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
