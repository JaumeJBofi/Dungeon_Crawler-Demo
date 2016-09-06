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
    
    public Pocion copiar() {
        Pocion nueva_pocion = new Pocion(this.GetNombre(), valor);
        return nueva_pocion;
    }
}
