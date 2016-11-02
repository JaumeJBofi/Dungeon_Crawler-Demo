/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Artefactos;

import java.awt.Graphics;
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
    
    public Arma(Artefacto artBase,int varDanoMin,int varDanoMax){
        super(artBase);
        copySprite(artBase);
        danho_max = varDanoMax;
        danho_min = varDanoMin;        
    }
    
    public Arma(String _nombre,double _prcAparicion,int _nivel,int _vida,int _ataque,int _proteccion,int precio,int _x,int _y,
            String spriteInfo,int _SizeX,int _SizeY)
    {
        super(_nombre, _prcAparicion, _nivel, _vida, _ataque, _proteccion, precio, _x, _y,_SizeX,_SizeY);
        ProcessSpriteInfo(spriteInfo);
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
        Arma nueva_arma = new Arma(this, danho_min, danho_max);
        return nueva_arma;
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
