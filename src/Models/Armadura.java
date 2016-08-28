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
public class Armadura extends Artefacto {
    private int defensa;
    public Armadura(String varNombre,int varDefensa){
        super(varNombre);
        SetDefensa(varDefensa);
    }
    
    final public void SetDefensa(int def){
        defensa = def;
    }
    
    public int GetDefensa(){
        return defensa;
    }
}