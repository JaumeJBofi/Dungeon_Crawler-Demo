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
    int defensa;
    public Armadura(){
        defensa = 0;
    }
    
    public void SetDefensa(int def){
        defensa = def;
    }
    
    public int GetDefensa(){
        return defensa;
    }
}
