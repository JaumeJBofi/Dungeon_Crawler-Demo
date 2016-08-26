/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Options mostrarMenu(){        
        System.out.println("\nDefinir accion:\t");        
        String input = scanner.nextLine();    
                
        if( input.equalsIgnoreCase("salir") ){
            System.out.println("\n\nSesionFinalizada");
            return new Options(Options.ACTION.EXIT);
        }
        
        if( input.toLowerCase().toLowerCase().startsWith("mover") ){
            if(input.toLowerCase().endsWith("derecha") ){
                return new Options(ACTION.MOVE,DIRECTIONS.RIGHT);
            }
            else if(input.toLowerCase().endsWith("izquierda") ){
                return new Options(ACTION.MOVE,DIRECTIONS.LEFT);
            }
            else if(input.toLowerCase().endsWith("abajo") ){
                return new Options(ACTION.MOVE,DIRECTIONS.BOT);
            }
            else if(input.toLowerCase().endsWith("arriba") ){
                return new Options(ACTION.MOVE,DIRECTIONS.TOP);
            }
        }
        
        if( input.toLowerCase().startsWith("interactuar") ){
            return new Options(ACTION.INTERACT);
        }
        else{
            return new Options(ACTION.NULA);
        }                        
    }
    
    public void mostrarLaberinto(Dungeon theDungeon,Avatar player){
        
        theDungeon.Render(player.GetX(),player.GetY(),player.GetTamShowX(),player.GetTamShowY());        
    }   

    private boolean mostrarStats(){
        return true;
    }        
}
