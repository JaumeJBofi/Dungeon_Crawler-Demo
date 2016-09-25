/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Artefactos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Arthuro
 */
public class Arma extends Artefacto{
    private int danho_min;
    private int danho_max;
    
    
    public Arma(String varNombre,int varDanoMin,int varDanoMax){
        super(varNombre);
        danho_min = varDanoMin;
        danho_max = varDanoMax;        
    }
    
    public void SetDanhoMin(int min){
        danho_min = min;
    }
    
    public void SetDanhoMax(int max){
        danho_max = max;
    }
    
    public int GetDanhoMin(){
        return danho_min;
    }
    
    public int GetDanhoMax(){
        return danho_max;
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        try {
            fr.write("W," + this.GetNombre() + ',' + danho_min + ',' +danho_max + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        
    }
    
       
     
    //Añadido por mi
    @Override
    public void Render() {
        System.out.format("%-25s (%3d - %2d ATK)",this.GetNombre(), this.danho_min ,this.danho_max);
    }
      
    @Override
    public void RenderStats()
    {
        System.out.format(" (%3d - %2d ATK)%-25s",this.danho_min ,this.danho_max," ");
    }
    
    @Override
    public void guardar_artefacto(FileWriter fr) {
        try {
            fr.write("W," + this.GetNombre() + ',' + danho_min + ',' +danho_max + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Arma copiar() {
        Arma nueva_arma = new Arma(this.GetNombre(), danho_min, danho_max);
        return nueva_arma;
    }
}
