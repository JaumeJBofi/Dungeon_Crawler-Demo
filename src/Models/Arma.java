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
}
