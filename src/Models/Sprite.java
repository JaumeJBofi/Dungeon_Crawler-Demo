/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

<<<<<<< HEAD
import Foundation.DIRECTIONS;
=======
>>>>>>> origin/master
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
    
    protected double posX,posY;
    
    //Profundidad del objecto
    protected int z;
    
    protected int width,height;
    
    protected String[] spritesNames;
    
    protected String mainSheet;
    
    public BufferedImage currentImage;
    
    protected int imgIndex;
    
    protected boolean visible;
    
    protected boolean active;
    
    protected ArrayList<Rectangle> bounds;
    
<<<<<<< HEAD
    public boolean resize = false;
    
    public String spriteInfo;
    
    public HashMap<DIRECTIONS,java.util.List<String>> dirMap;  
    
    int currentInd = 0;
    
    int maxX;
    int maxY;
    
=======
    protected boolean resize = false;
    
    public String spriteInfo;
    
>>>>>>> origin/master
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
        posX = 0;
        posY = 0;
        z = 0;
        
        width = 0;
        height = 0;
        
        visible = true;
        active = true;        
        imgIndex = 0;        
<<<<<<< HEAD
        bounds = new ArrayList<Rectangle>();      
        dirMap = new HashMap();
=======
        bounds = new ArrayList<Rectangle>();          
>>>>>>> origin/master
        resize = false;
    }
    
     public Sprite(int _width,int _height)
    {
        posX = 0;
        posY = 0;
        z = 0;
<<<<<<< HEAD
                
=======
        
>>>>>>> origin/master
        width = _width;
        height = _height;
        
        resize = true;
        
        visible = true;
        active = true;        
        imgIndex = 0;        
<<<<<<< HEAD
        
        dirMap = new HashMap();
=======
>>>>>>> origin/master
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
    
    public void copySprite(Sprite se)
    {
        posX = se.posX;
        posY = se.posY;
        
        spriteInfo = se.spriteInfo;
        spritesNames = se.spritesNames;
<<<<<<< HEAD
        currentImage = se.currentImage;       
        dirMap = se.dirMap;
        maxX = se.maxX;
        maxY = se.maxY;
=======
        currentImage = se.currentImage;
                
    
>>>>>>> origin/master
        
    }
    public void ProcessSpriteInfo(String infoString)
    {
        // FORMAT : POS_X|POS_Y|POS_Z|WIDTH|HEIGHT|MAINSHEET|INI_X|INI_Y|UNIQUE|RESIZE:NUMSPRITES|SPRITENAME|SPRITELOCATION|SPRITENAME|SPRITELOCATION
        spriteInfo = infoString;
        String[] tokens = infoString.split(":");
        ObjectConverter mainSettings = new ObjectConverter(tokens[0]);
        ObjectConverter spriteSettings = new ObjectConverter(tokens[1]);
        
<<<<<<< HEAD
        // Mas Generico
        java.util.List<DIRECTIONS> dirSheet = new ArrayList<>();
        
        dirSheet.add(DIRECTIONS.TOP);
        dirSheet.add(DIRECTIONS.RIGHT);
        dirSheet.add(DIRECTIONS.BOT);
        dirSheet.add(DIRECTIONS.LEFT);
        
=======
>>>>>>> origin/master
        mainSettings.SetDelimiter("-");
        spriteSettings.SetDelimiter("-");
        
        posX = Integer.parseInt(mainSettings.GetNextPart());
        posY = Integer.parseInt(mainSettings.GetNextPart());
        z = Integer.parseInt(mainSettings.GetNextPart());
        
        if(!resize)
        {
            width = Integer.parseInt(mainSettings.GetNextPart());
            height = Integer.parseInt(mainSettings.GetNextPart());            
        }else{
            Integer.parseInt(mainSettings.GetNextPart());
            Integer.parseInt(mainSettings.GetNextPart());
        }        
        
        mainSheet = mainSettings.GetNextPart();
        
        int col = Integer.parseInt(mainSettings.GetNextPart());
        int row = Integer.parseInt(mainSettings.GetNextPart());
<<<<<<< HEAD
        int iniCol = col;
        int iniRow = row;
=======
>>>>>>> origin/master
        
        boolean unique = Boolean.parseBoolean(mainSettings.GetNextPart());                
        
        BufferedImage sheet = IDibujable.spriteHash.get(mainSheet);
        
<<<<<<< HEAD
        int specificMaxX = Integer.parseInt(mainSettings.GetNextPart());
        int specificMaxY = Integer.parseInt(mainSettings.GetNextPart());
        if(specificMaxX==0&&specificMaxY==0)
        {
            maxX = sheet.getWidth()/width;
            maxY = sheet.getHeight()/height;            
        }else
        {
            maxX = specificMaxX;
            maxY = specificMaxY;            
        }
      
                
        int numSprites = Integer.parseInt(spriteSettings.GetNextPart());
        spritesNames = new String[numSprites];    
              
        
            
        String baseName = spriteSettings.PeekNextPart();
        String convertName = "Default";
        boolean repeatName = false;
        if(baseName.startsWith("*"))
        {
            repeatName = true;
            convertName = baseName.substring(1);
        }
                
        java.util.List<String> dirList = new ArrayList<>();
        for(int i = 0;i<numSprites;i++)
        {                           
           if(!repeatName)
           {
              spritesNames[i] = spriteSettings.GetNextPart();              
           }else
           {
               spritesNames[i] = convertName + Integer.toString(i);
           }            
                                
            dirList.add(spritesNames[i]);
=======
        int maxX = sheet.getWidth()/width;
        int maxY = sheet.getHeight()/height;
                
        int numSprites = Integer.parseInt(spriteSettings.GetNextPart());
        spritesNames = new String[numSprites];    
        
        for(int i = 0;i<numSprites;i++)
        {            
            spritesNames[i] = spriteSettings.GetNextPart();
>>>>>>> origin/master
            if(!IDibujable.spriteHash.containsKey(spritesNames[i]))
            {
                BufferedImage newSprite = IDibujable.gc.createCompatibleImage(width, height,sheet.getColorModel().getTransparency());
                Graphics g;
                g = newSprite.getGraphics();            
                g.drawImage(sheet, 0, 0, width, height,
			col*width, row*height, (col*width)+width, (row*height)+height, null);      

                IDibujable.spriteHash.put(spritesNames[i],newSprite);
                g.dispose();                            
            }
            
            // If there is only one spriteSheet per Sprite delete it.
            setImage(spritesNames[i]);
<<<<<<< HEAD
            if(unique) IDibujable.spriteHash.remove(mainSheet);                                               
            col++;             
            if(numSprites!=1&&(col==(maxX+iniCol)))
            {                
                dirMap.put(dirSheet.get(row-iniRow), dirList);
                dirList = new ArrayList<>();
                col = iniCol;
=======
            if(unique) IDibujable.spriteHash.remove(mainSheet);                                   
            
            col++;
             
            if(col==maxX)
            {
                col = 0;
>>>>>>> origin/master
                row++;                
            }           
        }                
    }        
<<<<<<< HEAD
        
    public void NextPosition(DIRECTIONS d)
    {
        currentImage = IDibujable.spriteHash.get(dirMap.get(d).get(imgIndex));
        imgIndex++;     
        if(imgIndex==maxX) imgIndex = 0;
    }
=======
    
>>>>>>> origin/master
    public void ProcessSpriteInfo(String infoString,boolean load)
    {
        // FORMAT : POS_X|POS_Y|POS_Z|WIDTH|HEIGHT|MAINSHEET|INI_X|INI_Y|UNIQUE|LOADED:NUMSPRITES|SPRITENAME|SPRITELOCATION|SPRITENAME|SPRITELOCATION
        spriteInfo = infoString;
        String[] tokens = infoString.split(":");
        ObjectConverter mainSettings = new ObjectConverter(tokens[0]);
        ObjectConverter spriteSettings = new ObjectConverter(tokens[1]);
        
<<<<<<< HEAD
           
        java.util.List<DIRECTIONS> dirSheet = new ArrayList<>();
        dirSheet.add(DIRECTIONS.TOP);
        dirSheet.add(DIRECTIONS.RIGHT);
        dirSheet.add(DIRECTIONS.BOT);
        dirSheet.add(DIRECTIONS.LEFT);
        
=======
>>>>>>> origin/master
        mainSettings.SetDelimiter("-");
        spriteSettings.SetDelimiter("-");
        
        posX = Integer.parseInt(mainSettings.GetNextPart());
        posY = Integer.parseInt(mainSettings.GetNextPart());
        z = Integer.parseInt(mainSettings.GetNextPart());
        
        width = Integer.parseInt(mainSettings.GetNextPart());
        height = Integer.parseInt(mainSettings.GetNextPart());              
        
        mainSheet = mainSettings.GetNextPart();
        
        int col = Integer.parseInt(mainSettings.GetNextPart());
        int row = Integer.parseInt(mainSettings.GetNextPart());
<<<<<<< HEAD
        int iniCol = col;
        int iniRow = row;
          
=======
        
>>>>>>> origin/master
        boolean unique = Boolean.parseBoolean(mainSettings.GetNextPart());                
                                       
        
        BufferedImage sheet = IDibujable.spriteHash.get(mainSheet);
        
<<<<<<< HEAD
        int specificMaxX = Integer.parseInt(mainSettings.GetNextPart());
        int specificMaxY = Integer.parseInt(mainSettings.GetNextPart());
        if(specificMaxX==0&&specificMaxY==0)
        {
            maxX = sheet.getWidth()/width;
            maxY = sheet.getHeight()/height;            
        }else
        {
            maxX = specificMaxX;
            maxY = specificMaxY;            
        }
=======
        int maxX = sheet.getWidth()/width;
        int maxY = sheet.getHeight()/height;
>>>>>>> origin/master
                
        int numSprites = Integer.parseInt(spriteSettings.GetNextPart());
        spritesNames = new String[numSprites];           
        
<<<<<<< HEAD
        
        String baseName = spriteSettings.PeekNextPart();
        String convertName = "Default";
        boolean repeatName = false;
        if(baseName.startsWith("*"))
        {
            repeatName = true;
            convertName = baseName.substring(1);
        }
        
        java.util.List<String> dirList = new ArrayList<>();
        for(int i = 0;i<numSprites;i++)
        {            
            if(!repeatName)
            {
               spritesNames[i] = spriteSettings.GetNextPart();              
            }else
            {
                spritesNames[i] = convertName + Integer.toString(i);
            }       
            
            dirList.add(spritesNames[i]);
=======
        for(int i = 0;i<numSprites;i++)
        {            
            spritesNames[i] = spriteSettings.GetNextPart();
>>>>>>> origin/master
            if(!IDibujable.spriteHash.containsKey(spritesNames[i])||load)
            {
                BufferedImage newSprite = IDibujable.gc.createCompatibleImage(width, height,sheet.getColorModel().getTransparency());
                Graphics g;
                g = newSprite.getGraphics(); 
               g.drawImage(sheet, 0, 0, width, height,
			col*width, row*height, (col*width)+width, (row*height)+height, null);            

                IDibujable.spriteHash.put(spritesNames[i],newSprite);
                g.dispose();                
            }                        
            // If there is only one spriteSheet per Sprite delete it.
            setImage(spritesNames[i]);
            if(unique) IDibujable.spriteHash.remove(mainSheet);                                   
            
            col++;
             
<<<<<<< HEAD
            if(numSprites!=1&&(col==(maxX+iniCol)))
            {              
                dirMap.put(dirSheet.get(row-iniRow), dirList);                
                dirList = new ArrayList<>();
                col = iniCol;
=======
            if(col==maxX)
            {
                col = 0;
>>>>>>> origin/master
                row++;                
            }           
        }                
    }        
    
<<<<<<< HEAD
    public void SetCurrentFrame(int frame)
    {
        imgIndex = (frame)%spritesNames.length;
        setImage(spritesNames[imgIndex]);
        // Si es la �ltima imagen del array imgNames...       
    }
    
=======
>>>>>>> origin/master
    public void setImage(String name)
    {   
        currentImage = IDibujable.spriteHash.get(name);
        if(resize)
        {
            Image img = currentImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);  
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            currentImage = bimage;
        }        
    }
    
<<<<<<< HEAD
    public void Resize()
    {
         Image img = currentImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);  
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        currentImage = bimage;
        
    }
    
    public void paint(Graphics g) {    
=======
    public void paint(Graphics g) {
        if(currentImage==null) 
        {
            int a = 5;
        }
>>>>>>> origin/master
        if (visible) {
                g.drawImage(currentImage, (int)posX, (int)posY, width, height, null);
        }
    }

    public void paint(Graphics g, double x, double y) {
        if (visible) {
                g.drawImage(currentImage, (int)x, (int)y, width, height, null);
        }
    }
        
    public boolean CheckClicked(MouseEvent e)
    {
        int posXVar = e.getX();
        int posYVar = e.getY();
<<<<<<< HEAD
        return ((posXVar>=posX&&posXVar<=(posX+width))&&(posYVar>=posY&&posYVar<=(posY+height)));
=======
        return ((posXVar>=posX&&posX<=(posX+width))&&(posYVar>=posY&&posY<=(posY+height)));
>>>>>>> origin/master
    }
    
    public void SetPositionDraw(int _x,int _y)
    {
        posX = _x;
        posY = _y;
    }
    
    public void SetWidth(int _width)
    {
        width = _width;
    }
    
    public void SetHeight(int _height)
    {
        height = _height;
    }
    
}
