/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Probando
package Controllers;
import Models.Dungeon;
import Models.Avatar;
import Foundation.DIRECTIONS;
import java.util.*;
import Foundation.Options;
import Foundation.Options.ACTION;
/**
 *
 * @author alulab14
 */
public class Dibujador {

    private DIRECTIONS direccion;
     Scanner scanner;
    
    public Dibujador(){        
        scanner = new Scanner(System.in);
    }
 
    
    public Options mostrarMenu(Options choice){        
        System.out.println("\nDefinir accion:\t");        
        String input = scanner.nextLine();    
                
        if( input.equalsIgnoreCase("salir") ){
            System.out.println("\n\nSesionFinalizada");
            choice.SetAction(ACTION.EXIT);
        }else if( input.toLowerCase().toLowerCase().startsWith("mover") ){
          choice.SetAction(ACTION.MOVE);          
            if(input.toLowerCase().endsWith("derecha") ){
                choice.SetPath(DIRECTIONS.RIGHT);
            }
            else if(input.toLowerCase().endsWith("izquierda") ){
                choice.SetPath(DIRECTIONS.LEFT);
            }
            else if(input.toLowerCase().endsWith("abajo") ){
                choice.SetPath(DIRECTIONS.BOT);
            }
            else if(input.toLowerCase().endsWith("arriba") ){
                choice.SetPath(DIRECTIONS.TOP);
            }else{
                choice.SetAction(ACTION.NULA);
            } 
        }else if( input.toLowerCase().startsWith("interactuar") ){
           choice.SetAction(ACTION.INTERACT);
           if(input.toLowerCase().endsWith("derecha") ){
                choice.SetPath(DIRECTIONS.RIGHT);
            }
            else if(input.toLowerCase().endsWith("izquierda") ){
                choice.SetPath(DIRECTIONS.LEFT);
            }
            else if(input.toLowerCase().endsWith("abajo") ){
                choice.SetPath(DIRECTIONS.BOT);
            }
            else if(input.toLowerCase().endsWith("arriba") ){
                choice.SetPath(DIRECTIONS.TOP);
            }
        }else if(input.compareToIgnoreCase("debug")==0){
            choice.SetAction(ACTION.DEBUG);            
        }else if(input.toLowerCase().startsWith("sudo teleport")){
            choice.SetAction(ACTION.TELEPORT);
        }else if(input.toLowerCase().startsWith("atacar")){
            choice.SetAction(ACTION.ATTACK);
        }else if(input.toLowerCase().compareTo("help")==0){
            choice.SetAction(ACTION.HELP);
        }else if(input.toLowerCase().compareTo("equip")==0){
            choice.SetAction(ACTION.EQUIP);
        }else{
            choice.SetAction(ACTION.NULA);
        }            
        return choice;
    }
    
    public void mostrarLaberinto(Dungeon theDungeon,Avatar player){
        

        theDungeon.Render(player.GetX(),player.GetY(),player.GetTamShowX(),player.GetTamShowY()); 
        player.Render();

    }   

    private boolean mostrarStats(){
        return true;
    }        
}
