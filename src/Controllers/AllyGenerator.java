/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Facilidades.Aliado;
import Foundation.Coordinate;
import Foundation.ObjectConverter;
import Models.Enemy;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import Foundation.Tip;

/**
 *
 * @author Jauma
 */
final public class AllyGenerator {
    
    private String fileName;
    final private ArrayList<String> tipsPool;
    private ArrayList<Aliado> allyPool;
    final private Random randomManager;
    private int numTips;
    private int numNames;
      
    private int tileSizeX;
    private int tileSizeY;
    
    public  AllyGenerator(String varFileName)
    {
        fileName = varFileName;
        tipsPool = new ArrayList<>();               
        numTips = 0;
        numNames = 0;
        randomManager = new Random();    
        
        LoadHints();     
        LoadAlliesFromTxt();
                
//        try {
//        XStream xs = new XStream();
//        FileReader fr = new FileReader(fileName +"XML.xml");
//        allyPool = (ArrayList<Aliado>)xs.fromXML(fr);       
//        fr.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());          
//        }                       
    }
    
     public  AllyGenerator(String varFileName,int _tileSizeX,int _tileSizeY)
    {
        fileName = varFileName;
        tipsPool = new ArrayList<>();               
        numTips = 0;
        numNames = 0;
        randomManager = new Random();    
        
        tileSizeX = _tileSizeX;
        tileSizeY = _tileSizeY;
        
        LoadHints();     
        LoadAlliesFromTxt();
                
//        try {
//        XStream xs = new XStream();
//        FileReader fr = new FileReader(fileName +"XML.xml");
//        allyPool = (ArrayList<Aliado>)xs.fromXML(fr);       
//        fr.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());          
//        }                       
    }
    
    public void LoadHints()
    {
        try {            
            FileReader fr = new FileReader(fileName + "Hints.txt");
            BufferedReader in = new BufferedReader(fr);
            
            String buffer;            
            while((buffer = in.readLine())!=null)
            {
                tipsPool.add(buffer);
                numTips++;
            }                     
        } catch (Exception e) {
            System.err.println("Error Archivo: " + fileName + " no encontrado");
        }                                
    }       
    
    // Pregunta 1 Lab3
    public void LoadAlliesFromTxt()
    {               
        allyPool = new ArrayList<>();
        try {            
        FileReader fr = new FileReader(fileName + ".txt");
        BufferedReader in = new BufferedReader(fr);
        String buffer;
        
        buffer  = in.readLine();
        String[] tokens = buffer.split(":");
        numNames = Integer.parseInt(tokens[1]);
        Coordinate newCoord = new Coordinate(0, 0);
        String spriteInfo;
        
        //Pregunta 1.
        for(int i = 0;i<numNames;i++)
        {
            buffer  = in.readLine();     
            spriteInfo = in.readLine();
            tokens = buffer.split("ALIADO:");
            buffer = tokens[1];
            tokens = buffer.split("/CONSEJOS:");            
            Aliado newAlly = new Aliado(newCoord,tokens[0],spriteInfo,tileSizeX,tileSizeY);         
            //Aliado newAlly = new Aliado(newCoord,tokens[0]);   
            buffer = tokens[1];
                                                   
            tokens = buffer.split("\\.");
            String[] smallTokens;
            for(int j = 0;j<tokens.length;j++)
            {                             
                smallTokens = tokens[j].split("@");
                newAlly.AddAdvice(new Tip(smallTokens[1],Integer.parseInt(smallTokens[0])));                      
            }                                     
            allyPool.add(newAlly);
        }         
        } catch (Exception e) {
            System.err.println("Error Archivo: " + fileName + " no encontrado");
        }   
        
        XStream xs = new XStream();
        try {
        FileWriter fw = new FileWriter(fileName + "XML.xml");        
        String temp = xs.toXML(allyPool);
        fw.write(temp);
        fw.close();
        } catch (IOException e) {
        System.out.println(e.toString());
        }        
    }
      
    public ArrayList<String> GiveHints(int size)
    {
        // No tan elegante. Puede haber repetidos pero ehh...
        ArrayList<String> hints = new ArrayList<>();
        
        for(int i = 0;i<size;i++)
        {
            hints.add(tipsPool.get(randomManager.nextInt(numTips)));
        }
        return hints;
    }
    
    public void FillFriend(Aliado myFriend,ObjectGenerator objGen) {
        // Que tenga un invetorio de 5 ... Definido Aca...
        int N = myFriend.GetMaxInventory();
        // Tiene pociones para ayudarme. Puede ser cualquier cosa.
        
        //Preg 3
        for (int i = 0; i < N; i++) {
            myFriend.AddArtefact(objGen.GetRandomObject(objGen.GetRandomArtefactType(1),100,-1,-1));
        }       
        //myFriend.AddAdvice(GiveHints(myFriend.GetMaxHints()));        
    }
    
    public Aliado GetAlly(Coordinate position,ObjectGenerator objGen)
    {
        int size = allyPool.size();
        Aliado friend = allyPool.get(randomManager.nextInt(size)).copiar();
        friend.SetWidth(tileSizeX);
        friend.SetHeight(tileSizeY);
        friend.SetPosition(position.GetPoint());
        FillFriend(friend, objGen);
        return friend;
    }
    
    public String GetFileName()
    {
        return fileName;
    }
    
    public void SetFileName(String varFileName)
    {
        fileName = varFileName;
    }
}
