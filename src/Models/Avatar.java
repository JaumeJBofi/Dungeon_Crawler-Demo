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
    private Arma arma_equip;
    private Armadura armadura_equip;
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
    
     
    public boolean EquipItem(int number) 
    {
        if(saco.size()<number) return false;
        Artefacto selectedObj = saco.get(number);
        
        if(selectedObj instanceof Arma){
            arma_equip = (Arma)selectedObj;
        }else if(selectedObj instanceof Armadura){
            armadura_equip = (Armadura)selectedObj;
        }else
        {
            int healCant = ((Pocion)selectedObj).GetValor();
            hp += healCant;
            if(hp>vidaMaxima) hp = vidaMaxima;
            saco.remove(number);
        }
        return true;            
    }
    
    //Modificado por mi
    public void Render(){
        
        String space20 = new String(new char[22]).replace('\0', ' ');
        String space19 = new String(new char[21]).replace('\0', ' ');
        int tamanho = saco.size();
        
        System.out.println("");
        System.out.format("%-40s","     ------ STATS ------");
        System.out.print(space20);
        System.out.println("---------------- ITEMS -----------------");
              
        //Stats
        System.out.format("%-40s","     "+this.GetNombre());
        
        //Arma
         if (tamanho == 0){
            System.out.print(space20);
            System.out.format("%-40s\n",". . . . . . . . .Vacio. . . . . . . . . ");
        }else{             
             if(!(saco.get(0)==arma_equip)&&!(saco.get(0)==armadura_equip)) System.out.print(space20);
             else 
             {                 
                System.out.print(space19);                
                System.out.print(">");
             }
             saco.get(0).Render();    
             System.out.format("%10s","(1)\n");
             
        }        
        
        System.out.format("%-20s%-7d / %-8d  ","     HP: ",this.hp,this.vidaMaxima);
        
        if(tamanho>=2)
        {
           
            if(!(saco.get(1)==arma_equip)&&!(saco.get(1)==armadura_equip)) System.out.print(space20);
            else 
            {                 
                System.out.print(space19);
                System.out.print(">");
            }
            saco.get(1).Render();    
            System.out.format("%10s","(2)\n");
        }else{
             System.out.printf("\n");
        }
       
        
        System.out.printf("     Arma: ");
        if (arma_equip == null) System.out.format("%-29s","Ninguno"); else arma_equip.Render();
        
        if(tamanho>=3)
        {           
            if(!(saco.get(2)==arma_equip)&&!(saco.get(2)==armadura_equip)) System.out.print(space20);
             else 
             {                 
                System.out.print(space19);                
                System.out.print(">");
             }
            saco.get(2).Render();       
            System.out.format("%10s","(3)\n");
        }else{
             System.out.printf("\n");
        }       
        
        System.out.print("     Armadura: ");
        if (armadura_equip == null) System.out.format("%-40s","Ninguno"); else armadura_equip.Render(); 
        Artefacto art;
        
         if(tamanho>=4)
        {            
            if(!(saco.get(3)==arma_equip)&&!(saco.get(3)==armadura_equip)) System.out.print(space20);
             else 
             {                 
                System.out.print(space19);
                System.out.print(">");
             }
            saco.get(3).Render(); 
            System.out.format("%10s","(4)\n");
        }else{
             System.out.printf("\n");
        }                               
        
        for (int i= 4; i <tamanho ; i++){
            art = saco.get(i);
            System.out.format("%-30s"," ");           
            if(!(saco.get(i)==armadura_equip)&&!(saco.get(i)==armadura_equip)) System.out.print(space20);
            else 
            {                 
                System.out.print(space19);                
                System.out.print(">");
            }
            art.Render();
            System.out.format("%8s%d)","(",i+1);
            System.out.printf("\n");
            //System.out.println();
        }
    }
    
    public void LoadComponents(){
        
    }
    
    public void Dispose(){
        
    }
    
}
