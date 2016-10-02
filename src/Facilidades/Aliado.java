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

/**
 *
 * @author alulab14
 */

//Preg 1 Lab2
public class Aliado extends Entity{
    
    public ArrayList<String> Consejos;
    public int currentAdvice;
    private List<Artefacto> saco;
          
    public Aliado(Coordinate position, String varNombre) {
        super(position, varNombre, 100, 5, 5);
        hp = 100; // digamos q sea 100        
        tamShowX = 15;
        tamShowY = 15;        
        SetNombre(varNombre);
        Consejos = new ArrayList<>();
        
        currentAdvice = 0;
        saco = new ArrayList();        
    }

    public Aliado(Coordinate position, int varTamShowX, int varTamShowY, int vida, String varNombre, int varStrength, int varArmor) {
        super(position, varNombre, vida, varStrength, varArmor);        
        tamShowX = varTamShowX;
        tamShowY = varTamShowY;        
        SetNombre(varNombre);
        
        currentAdvice = 0;
        saco = new ArrayList();        
    }        
    
    public void GiveAdvice()
    {
        System.out.println("Hola, me llamo " + GetNombre() +" y este es mi consejo:");
        System.out.println(Consejos.get(currentAdvice));        
        System.out.println("Nos vemos... Suerte!");
        currentAdvice = (currentAdvice + 1)%Consejos.size();        
    }
    
    public void AddArtefact(Artefacto a) {
        // Solo añade un artefacto al saco
        saco.add(a);
    }
    
    public void AddAdvice(String tip)
    {
        Consejos.add(tip);        
    }
    
    public void AddAdvice(ArrayList<String> advices)
    {
        Consejos = advices;
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
}
