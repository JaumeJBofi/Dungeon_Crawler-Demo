/*
 * Clase que se encarga de dibujar una matriz
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
    //La direccion hacia la cual mira el jugador
    private DIRECTIONS direccion;
    //scanner sirve para leer
    Scanner scanner;
    
    //Constructor
    public Dibujador(){
        //Inicializo Scanner
        scanner = new Scanner(System.in);
    }
    
    //funcion que muestra el menu, devuelve la accion que se realizo
    public Options mostrarMenu(Options choice){
        //Se pide una accion a realizar
        System.out.println("\nDefinir accion:\t");        
        String input = scanner.nextLine();    
        //Si se pide salir se termina el programa
        if( input.equalsIgnoreCase("salir") ){
            System.out.println("\n\nSesionFinalizada");
            //Indico que la accion que devuelva sea salir
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
    //funcion que recibe un Dungeon y los datos del jugador
    public void mostrarLaberinto(Dungeon theDungeon,Avatar player){
        //Muestro el calabozo
        //Doy las coordenadas del jugador y el tamaño que debe mostrar
        theDungeon.Render(player.GetX(),player.GetY(),player.GetTamShowX(),player.GetTamShowY()); 
        //Muestro los datos del jugador
        player.Render();
    }
    
    //????
    private boolean mostrarStats(){
        return true;
    }
}
