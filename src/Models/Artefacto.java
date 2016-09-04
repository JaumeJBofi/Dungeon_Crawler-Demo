/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Arthuro
 */
public class Artefacto {

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

    public String GetNombre() {
        return nombre;
    }    
}
