/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jauma
 */
final public class AllyGenerator {
    
    private String fileName;
    private ArrayList<String> tipsPool;
    private ArrayList<String> namesPool;
    private Random randomManager;
    private int numTips;
    private int numNames;
    
    public AllyGenerator(String varFileName)
    {
        fileName = varFileName;
        tipsPool = new ArrayList<>();
        namesPool = new ArrayList<>();
        numTips = 0;
        numNames = 0;
        LoadHints(fileName);     
        randomManager = new Random();        
    }
    
    public void LoadHints(String fileName)
    {
        try {            
            FileReader fr = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fr);
            
            String buffer;            
            while(!(buffer = in.readLine()).equals("****"))
            {
                tipsPool.add(buffer);
                numTips++;
            }
            
            while((buffer = in.readLine())!=null)
            {
                namesPool.add(buffer);
                numNames++;
            }
        } catch (Exception e) {
            System.err.println("Error Archivo: " + fileName + " no encontrado");
        }                                
    }       
    
    public String GetName()
    {
        return namesPool.get(randomManager.nextInt(numNames));
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
    
    public String GetFileName()
    {
        return fileName;
    }
    
    public void SetFileName(String varFileName)
    {
        fileName = varFileName;
    }
}
