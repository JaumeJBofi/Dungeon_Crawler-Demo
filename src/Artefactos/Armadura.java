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

    private int defensa;

    public Armadura(String varNombre, int varDefensa) {
        super(varNombre);
        SetDefensa(varDefensa);
    }

    final public void SetDefensa(int def) {
        defensa = def;
    }

    public int GetDefensa() {
        return defensa;
    }

    //Añadido por mi
    @Override
    public void Render() {
        System.out.format("%-30s (%-3d DEF)", this.GetNombre(),this.defensa);
    }
    
    @Override
    public void RenderStats() {
        System.out.format("(%-3d DEF) %-30s",this.defensa," ");
    }

    public Armadura copiar() {
        Armadura nueva_armadura = new Armadura(this.GetNombre(), defensa);
        return nueva_armadura;
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
    public void guardar_artefacto(FileWriter fr) {
        try {
            fr.write("A," + this.GetNombre() + ',' + defensa + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
