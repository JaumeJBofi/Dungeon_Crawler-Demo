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
public class Pocion extends Artefacto {
    
    private int valor; // por el momento es el valor de cura digamos
    public Pocion(String varNombre,int varValor){ 
        super(varNombre);
        valor = varValor;
    }
    public void SetValor(int v){
        valor = v;
    }
    public int GetValor(){
        return valor;
    }
            
    //Añadido por mi
    @Override
    public void Render() {
        System.out.format("%-30s (%-3d HP) ", this.GetNombre(),this.valor);
    }
    
    @Override
    public void RenderStats() {
        System.out.format(" (%-3d HP)%-30s",this.valor," ");
    }
    
    public Pocion copiar() {
        Pocion nueva_pocion = new Pocion(this.GetNombre(), valor);
        return nueva_pocion;
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
            fr.write("P," + this.GetNombre() + ',' + valor + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
