/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthuro
 */
public class Avatar extends Entity implements IDibujable{

    private int vidaMaxima;
    private int tamShowX;
    private int tamShowY;
    private List<Artefacto> saco;
    
    public Avatar(Coordinate position,String varNombre) {
        super(position);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        tamShowX = 15;
        tamShowY = 15;
        saco = new ArrayList(); 
        SetNombre(varNombre);
    }
    
    public Avatar(Coordinate position,int varTamShowX,int varTamShowY,String varNombre) {
        super(position);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
//        SetTamShowX(tamShowX);
//        SetTamShowY(tamShowY);
          tamShowX = varTamShowX;
          tamShowY = varTamShowY;
          saco = new ArrayList();
          SetNombre(varNombre);
    }
    

    
    public void SetVidaMaxima(int v) {
        vidaMaxima = v;
    }
   
    public int GetVidaMaxima() {
        return vidaMaxima;
    }
    
    public int GetTamShowX(){
        return tamShowX;
    }
    
    final public void SetTamShowX(int tamShow){
        if(tamShow>=GetPosition().GetM()){
             tamShowX = 15;
        }else{
            tamShowX = tamShow;
        }           
    }
    
   final public void SetTamShowY(int tamShow){
       if(tamShow>=GetPosition().GetN()){
           tamShowY = 15;
       }else{
           tamShowY = tamShow;
       }
   }    
    public int GetTamShowY(){
        return tamShowY;
    }
    
    /// AGREGADO ////////////
    public void AddArtefact(Artefacto a){
        saco.add(a);
    }
    public void Render(){
        int tamanho = saco.size();
        System.err.println("");
        Artefacto art;
        System.out.println(" ----- SACO ------");
        for (int i= 0; i <tamanho ; i++){
            art = saco.get(i);
            art.Render();
            System.out.println();
        }
    }
    
    public void LoadComponents(){
        
    }
    
    public void Dispose(){
        
    }
    
}
