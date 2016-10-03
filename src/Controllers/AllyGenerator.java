/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Facilidades.Aliado;
import Foundation.Coordinate;
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
    final private ArrayList<String> tipsPool;
    final private ArrayList<String> namesPool;
    final private Random randomManager;
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
    
    public void FillFriend(Aliado myFriend,ObjectGenerator objGen) {
        // Que tenga un invetorio de 5 ... Definido Aca...
        int N = 5;
        // Tiene pociones para ayudarme. Puede ser cualquier cosa.
        for (int i = 0; i < N; i++) {
            myFriend.AddArtefact(objGen.GetRandomObject(objGen.GetRandomArtefactType(1),100,-1,-1));
        }

        // Que guarde 10 Consejos;
        N = 10;        
        myFriend.AddAdvice(GiveHints(N));        
    }
    
    public Aliado GetAlly(Coordinate position,ObjectGenerator objGen)
    {
        Aliado friend = new Aliado(position.GetPoint(), GetName());
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
