/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.ObjectConverter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Jauma
 */
public class Sprite {
    
    public static boolean drawBounds = false;
    
    protected double x,y;
    
    //Profundidad del objecto
    protected int z;
    
    protected int width,height;
    
    protected String[] spritesNames;
    
    protected String mainSheet;
    
    protected BufferedImage currentImage;
    
    protected int imgIndex;
    
    protected boolean visible;
    
    protected boolean active;
    
    protected ArrayList<Rectangle> bounds;
    
    public static void putInMainSheet(String info) throws IOException
    {
        ObjectConverter sheetInfo = new ObjectConverter(info);
        sheetInfo.SetDelimiter("-");
        
        String name = sheetInfo.GetNextPart();
        String fileName = sheetInfo.GetNextPart();
                                                
        BufferedImage bi = ImageIO.read(new File(fileName));        
        
        int transparency = bi.getColorModel().getTransparency();
        BufferedImage img = IDibujable.gc.createCompatibleImage(
                bi.getWidth(), bi.getHeight(), transparency);

        Graphics2D g = img.createGraphics();
        g.drawImage(bi, 0, 0, null);
        g.dispose();
        IDibujable.spriteHash.put(name, img);        
    }
    public Sprite()
    {
        x = 0;
        y = 0;
        z = 0;
        
        width = 0;
        height = 0;
        
        visible = true;
        active = true;        
        imgIndex = 0;        
        bounds = new ArrayList<Rectangle>();               
    }
    
    public boolean nextImg() {
        // Actualizamos en �ndice para que apunte a la
        // siguiente imagen.
        imgIndex = (imgIndex+1)%spritesNames.length;
        setImage(spritesNames[imgIndex]);
        // Si es la �ltima imagen del array imgNames...
        if (imgIndex == spritesNames.length-1) {
                return true;
        }
        return false;
    }
    
    public void ProcessSpriteInfo(String infoString)
    {
        // FORMAT : POS_X|POS_Y|POS_Z|WIDTH|HEIGHT|MAINSHEET|INI_X|INI_Y|UNIQUE:NUMSPRITES|SPRITENAME|SPRITELOCATION|SPRITENAME|SPRITELOCATION
        String[] tokens = infoString.split(":");
        ObjectConverter mainSettings = new ObjectConverter(tokens[0]);
        ObjectConverter spriteSettings = new ObjectConverter(tokens[1]);
        
        mainSettings.SetDelimiter("-");
        spriteSettings.SetDelimiter("-");
        
        x = Integer.parseInt(mainSettings.GetNextPart());
        y = Integer.parseInt(mainSettings.GetNextPart());
        z = Integer.parseInt(mainSettings.GetNextPart());
        
        width = Integer.parseInt(mainSettings.GetNextPart());
        height = Integer.parseInt(mainSettings.GetNextPart());
        
        mainSheet = mainSettings.GetNextPart();
        
        int posX = Integer.parseInt(mainSettings.GetNextPart());
        int posY = Integer.parseInt(mainSettings.GetNextPart());
        
        boolean unique = Boolean.parseBoolean(mainSettings.GetNextPart());
                                
        BufferedImage sheet = IDibujable.spriteHash.get(mainSheet);
        
        int maxX = sheet.getWidth()/width;
        int maxY = sheet.getHeight()/height;
                
        int numSprites = Integer.parseInt(spriteSettings.GetNextPart());
        spritesNames = new String[numSprites];
        
        int row = 0;
        int col = 0;
        
        for(int i = 0;i<numSprites;i++)
        {            
            spritesNames[i] = spriteSettings.GetNextPart();
            BufferedImage newSprite = IDibujable.gc.createCompatibleImage(width, height,sheet.getColorModel().getTransparency());
            Graphics g;
            g = newSprite.getGraphics();            
            g.drawImage(sheet, posX, posY, width, height, null);    
            
            IDibujable.spriteHash.put(spritesNames[i],newSprite);
            g.dispose();
            
            // If there is only one spriteSheet per Sprite delete it.
            setImage(spritesNames[i]);
            if(unique) IDibujable.spriteHash.remove(mainSheet);                                   
            
            posX += width;
             
            if(posX==maxX)
            {
                posX = 0;
                posY += height;                
            }           
        }                
    }        
    public void setImage(String name)
    {        
        currentImage = IDibujable.spriteHash.get(name);
    }
    
    public void paint(Graphics g) {
        if (visible) {
                g.drawImage(currentImage, (int)x, (int)y, width, height, null);
        }
    }

    public void paint(Graphics g, double x, double y) {
        if (visible) {
                g.drawImage(currentImage, (int)x, (int)y, width, height, null);
        }
    }
    
    public boolean CheckClicked(MouseEvent e)
    {
        int posX = e.getX();
        int posY = e.getY();
        return ((posX>=x&&posX<=(x+width))&&(posY>=y&&posY<=(y+height)));
    }
}
