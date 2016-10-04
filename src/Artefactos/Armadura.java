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
public class Armadura extends Artefacto {
   
    public Armadura(String varNombre, int varDefensa) {
        super(varNombre);
        SetProteccion(varDefensa);
    }
    
    public Armadura(Artefacto art){
        super(art);
    }   
    
     public Armadura(String _nombre,double _prcAparicion,int _nivel,int _vida,int _ataque,int _proteccion,int precio,int _x,int _y)
    {
        super(_nombre, _prcAparicion, _nivel, _vida, _ataque, _proteccion, precio, _x, _y);        
    }
    
    //Añadido por mi
    @Override
    public void Render() {
        System.out.format("%-30s (%-3d DEF)", this.GetNombre(),GetProteccion());
    }
    
    @Override
    public void RenderStats() {
        System.out.format("(%-3d DEF) %-30s",GetProteccion()," ");
    }

    public Armadura copiar() {
        Armadura nueva_armadura = new Armadura(this.GetNombre(), GetProteccion());
        return nueva_armadura;
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        try {
            fr.write("A," + this.GetNombre() + ',' + GetProteccion() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        
    }
    
    
    @Override
    public void guardar_artefacto(FileWriter fr) {
        try {
            fr.write("A," + this.GetNombre() + ',' + GetProteccion() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
