/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Artefactos;

import Foundation.ISavable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Arthuro
 */
public abstract class Artefacto implements ISavable{

    protected String nombre;

    public Artefacto(String varNombre) {
        nombre = varNombre;
    }

    // agregado 27/08/16 
    public void Render() {
        System.out.println("Nombre = " + nombre);
    }

    public void SetNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract void guardar_artefacto(FileWriter fr);
    
    public abstract void RenderStats();
    
    @Override
    public abstract void Save(FileWriter fr);
    
    @Override
    public  abstract void Load(FileReader flectura, BufferedReader buffer);

    public String GetNombre() {
        return nombre;
    }    
}
