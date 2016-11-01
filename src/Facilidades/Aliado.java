/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facilidades;

import Artefactos.Artefacto;
import Foundation.Coordinate;
import Models.Entity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import Foundation.Tip;
import java.awt.Graphics;

/**
 *
 * @author alulab14
 */

//Preg 1 Lab2
public class Aliado extends Entity{
    
    public ArrayList<Tip> Consejos;
    public int currentAdvice;
    private List<Artefacto> saco;
    private int _maxInventorio = 5;
    private int _maxConsejos = 5;    
          
    public Aliado(Coordinate position, String varNombre) {
        super(position, varNombre, 100, 5, 5,1);
        hp = 100; // digamos q sea 100        
        tamShowX = 15;
        tamShowY = 15;        
        SetNombre(varNombre);
        Consejos = new ArrayList<>();
        saco = new ArrayList();        
        
        currentAdvice = 0;        
        _maxConsejos = 5;
        _maxInventorio = 5;
    }

    public Aliado(Coordinate position, String varNombre, int vida,int nivel, int varStrength, int varArmor,int maxInventorio,int maxConsejos) {
        super(position, varNombre, vida, varStrength, varArmor,1);        
        tamShowX = 15;
        tamShowY = 15;                
        _maxConsejos = maxConsejos;
        _maxInventorio = maxInventorio;
        currentAdvice = 0;
        saco = new ArrayList();        
    }        
    
    public Aliado(Coordinate position, String varNombre, int vida,int nivel, int varStrength, int varArmor,int maxInventorio,int maxConsejos,ArrayList<Tip> _consejos) {
        super(position, varNombre, vida, varStrength, varArmor,1);        
        tamShowX = 15;
        tamShowY = 15;                
        _maxConsejos = maxConsejos;
        _maxInventorio = maxInventorio;
        currentAdvice = 0;
        Consejos = new ArrayList<Tip>();
        
        for(Tip myTip:_consejos){
            Consejos.add(myTip.copiar());
        }        
        saco = new ArrayList();        
    }
    
    public void GiveAdvice()
    {
        System.out.println("Hola, me llamo " + GetNombre() +" y este es mi consejo:");
        System.out.println(Consejos.get(currentAdvice).GetAdvice());        
        System.out.println("Nos vemos... Suerte!");
        currentAdvice = (currentAdvice + 1)%Consejos.size();        
    }
    
    public void AddArtefact(Artefacto a) {
        // Solo añade un artefacto al saco
        saco.add(a);
    }
    
    public int GetMaxInventory()
    {
        return _maxInventorio;
    }
    
    public int GetMaxHints()
    {
        return _maxConsejos;
    }
    
    // Preg 2 Lab 3 Aca se ordena!...
    public void AddAdvice(Tip tip)
    {
        int numConsejos = Consejos.size();
        int i = 0;
        while(i<numConsejos)
        {
            if(Consejos.get(i).GetNivel()<tip.GetNivel())
            {
                i++;
            }else
            {
                break;
            }
        }
        Consejos.add(i,tip);        
    }
    
    public void AddAdvice(ArrayList<String> advices)
    {
        for(String advice: advices)
        {
            Consejos.add(new Tip(advice,1));
        }        
    }
    
    public Aliado copiar()
    {
        return new Aliado(GetPosition().GetPoint(), GetNombre(),GetVida(),GetNivel(),GetStrength(),GetArmor(),GetMaxInventory(),GetMaxHints(),Consejos);
    }

    public Integer getSizeSaco() {
        return saco.size();
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        
    }
    
    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        
    }

    @Override
    public void Render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
