/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Arthuro
 */
public class Avatar extends Entity implements IDibujable{

    private int vidaMaxima;
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
        // Solo añade un artefacto al saco
        saco.add(a);
    }
    
    public Integer getSizeSaco(){
        return saco.size();
    }
    public boolean EquipItem(int number) 
    {
        if(saco.size()<=number||number<=0)
            //Si no es un indice valido devuelve falso
            return false;
        Artefacto selectedObj = saco.get(number);
        // Item a equiparse

        
        if(selectedObj instanceof Arma){
            //si es arma se coloca en arma equipada
            arma_equip = (Arma)selectedObj;
        }else if(selectedObj instanceof Armadura){
            // si es armadura, se coloca en la armadura
            armadura_equip = (Armadura)selectedObj;
        }else
        {
            //Si es una pocion, se cura la cantidad especificada
            int healCant = ((Pocion)selectedObj).GetValor();
            hp += healCant;
            if(hp>vidaMaxima)
                //No puede curarse mas del maximo de su vida
                hp = vidaMaxima;
            //Se consume la pocion
            saco.remove(number);
        }
        return true;            
    }
    
    //#Preg 2
    // Para cuestiones de prueba hacemos que aunquesea haga 5 de daño;
    public int GetEquipWeaponDamage(){
        if(arma_equip==null) return 5;
        Random rand = new Random();
        return rand.nextInt(arma_equip.GetDanhoMax()-arma_equip.GetDanhoMin()) + arma_equip.GetDanhoMin() + 5;        
    }
    
    public void Render(boolean simple){
        // Son solo impresiones, nada brutal,
        //usa el polimorfismo en artefacto.
        //no imprime indices ni marca los items equipados
        int tamanho = saco.size();
        System.out.println("");
        System.out.println(" ------ STATS ------");
        System.out.print("  ");//Personaje: ");
        System.out.println(this.GetNombre());
        System.out.println("  HP: " + this.hp + "/" + this.vidaMaxima);
        System.out.print("  Arma: ");
        //Si no tiene arma equipada
        if (arma_equip == null) System.out.println("Ninguno"); else arma_equip.Render();
        //Si no tiene armadura equipada
        System.out.print("  Armadura: ");
        if (armadura_equip == null) System.out.println("Ninguno"); else armadura_equip.Render(); 
        Artefacto art;
        System.out.println(" ------ ITEMS ------");
        if (tamanho == 0) System.out.println("  Vacio");
        else for (int i= 0; i <tamanho ; i++){
            art = saco.get(i);
            //System.out.print("  ");
            System.out.print("  " + (i+1) + ")");
            //Modificado, ahora si imprime indices xd (Deberia :v)
            art.Render();
            System.out.println();
        }
    }
   
    
    //Modificado por mi
    public void Render() { //El nuevo render imprime 4 lineas por separado y el resto en un for

        String space20 = new String(new char[22]).replace('\0', ' '); //Cadenas de espacios en blanco
        String space19 = new String(new char[21]).replace('\0', ' ');
        int tamanho = saco.size();

        
        //Cabeceras
        System.out.println("");
        System.out.format("%-40s", "     ------ STATS ------");
        System.out.print(space20);
        System.out.println("---------------- ITEMS -----------------");

        //La primera linea
        System.out.format("%-40s", "     " + this.GetNombre()); //Imprime el nombre

       if (tamanho == 0) { //Si no hay items 
            System.out.print(space20);
            System.out.format("%-40s\n", ". . . . . . . . .Vacio. . . . . . . . . ");
        } else { //Si hay items
            if (!(saco.get(0) == arma_equip) && !(saco.get(0) == armadura_equip)) { //Si no esta equipado ese item
                System.out.print(space20);
            } else { // Si si lo esta imprime un >
                System.out.print(space19);
                System.out.print(">");
            }
            saco.get(0).Render(); //Muestra el item y luego coloca el indice
            System.out.format("%10s", "(1)\n");

        } //Es la misma logica para todos los bloques if que vienen a continuacion

        System.out.format("%-20s%-7d / %-8d  ", "     HP: ", this.hp, this.vidaMaxima); //Imprime la vida

        if (tamanho >= 2) {

            if (!(saco.get(1) == arma_equip) && !(saco.get(1) == armadura_equip)) {
                System.out.print(space20);
            } else {
                System.out.print(space19);
                System.out.print(">");
            }
            saco.get(1).Render();
            System.out.format("%10s", "(2)\n");
        } else {
            System.out.printf("\n");
        }

        System.out.printf("     Arma: "); //Imprime el arma equipada
        if (arma_equip == null) { // Si no tiene nada equipado
            System.out.format("%-29s", "Ninguno");
        } else {
            arma_equip.Render();
        }

        if (tamanho >= 3) {
            if (!(saco.get(2) == arma_equip) && !(saco.get(2) == armadura_equip)) {
                System.out.print(space20);
            } else {
                System.out.print(space19);
                System.out.print(">");
            }
            saco.get(2).Render();
            System.out.format("%10s", "(3)\n");
        } else {
            System.out.printf("\n");
        }

        System.out.print("     Armadura: "); //Ahora la armadura
        if (armadura_equip == null) { // Si no tiene nada equipado
            System.out.format("%-40s", "Ninguno");
        } else {
            armadura_equip.Render();
        }
        Artefacto art; //Declaracion aleatoria salvaje aparecio :v

        if (tamanho >= 4) {
            if (!(saco.get(3) == arma_equip) && !(saco.get(3) == armadura_equip)) {
                System.out.print(space20);
            } else {
                System.out.print(space19);
                System.out.print(">");
            }
            saco.get(3).Render();
            System.out.format("%10s", "(4)\n");
        } else {
            System.out.printf("\n");
        }

        for (int i = 4; i < tamanho; i++) { //El for que imprime de la linea 5 para adelante
            art = saco.get(i); //El item del saco en la posicion i
            System.out.format("%-30s", " "); //Espacios en blanco
            if (!(saco.get(i) == armadura_equip) && !(saco.get(i) == armadura_equip)) { //Lo mismo de antes
                System.out.print(space20);
            } else {
                System.out.print(space19);
                System.out.print(">");
            }
            art.Render(); //Imprime los datos del indice
            System.out.format("%8s%d)", "(", i + 1); //Imprime el indice
            System.out.printf("\n");
            //System.out.println();
        }
    }

    public void LoadComponents() {

    }

    public void Dispose() {

    }

}
