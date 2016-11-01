/*
 * Clase que se encarga de dibujar una matriz
 */
package Interfaz;

import Mundo.Dungeon;
import Models.Avatar;
import Foundation.DIRECTIONS;
import java.util.*;
import Foundation.Options;
import Foundation.Options.ACTION;

// Graphics imports begin
import com.sun.media.sound.AudioFileSoundbankReader;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
// Graphics imports end

/**
 *
 * @author alulab14
 */
public class Dibujador {

    //La direccion hacia la cual mira el jugador
    private DIRECTIONS direccion;
    //scanner sirve para leer
    public Scanner scanner;

    //Constructor
    public Dibujador() {
        //Inicializo Scanner
        scanner = new Scanner(System.in);
    }

    //funcion que muestra el menu, devuelve la accion que se realizo
    public Options mostrarMenu(Options choice) {
        //Se pide una accion a realizar        
        System.out.println("\nDefinir accion:\t");
        String input = scanner.nextLine();
        //Si se pide salir se termina el programa
        if (input.equalsIgnoreCase("salir")) {
            System.out.println("\n\nSesionFinalizada");
            //Indico que la accion que devuelva sea salir
            choice.SetAction(ACTION.EXIT);
        } //Si la instruccion comienza com mover
        else if (input.toLowerCase().startsWith("mover")) {
            choice.SetAction(ACTION.MOVE);
            //Se define la direccion a la cual se mueve
            if (!modificarDireccion(input, choice)) //Si no se modifico la direccion se entiende
            //que no se dio bien la instruccion
            {
                choice.SetAction(ACTION.NULA);
            }
        } //Si la instruccion es una interaccion
        else if (input.toLowerCase().startsWith("interactuar")) {
            choice.SetAction(ACTION.INTERACT);
            //No es necesario ver si en verdad la direccion que se
            //da es correcta
            modificarDireccion(input, choice);
        } //En caso que se desee debugear
        else if (input.compareToIgnoreCase("sudo debug") == 0) {
            choice.SetAction(ACTION.DEBUG);
        } //En caso que use teleport
        else if (input.toLowerCase().startsWith("sudo teleport")) {
            choice.SetAction(ACTION.TELEPORT);
        } //En caso que se ataque
        else if (input.toLowerCase().startsWith("sudo atacar")) {
            choice.SetAction(ACTION.ATTACK);
        } //En caso que desee ver el manual
        else if (input.toLowerCase().compareTo("ayuda") == 0) {
            choice.SetAction(ACTION.HELP);
        } //En caso que se desee equipar un objeto
        else if (input.toLowerCase().startsWith("usar")) {
            input = input.trim();
            int num_pos = input.lastIndexOf(' ') + 1, num;
            String numero = input.substring(num_pos);
            try {
                num = Integer.parseInt(numero);
            } catch (NumberFormatException except) {
                System.out.println("Indice no valido.");
                choice.SetAction(ACTION.NULA);
                return choice;
            }
            choice.indice_item = num;
            choice.SetAction(ACTION.EQUIP);
        } //Si no es ninguna de las anteriores se entiende que
        //no se escribio una instruccion valida
        else if (input.toLowerCase().startsWith("guardar")) {
            choice.SetAction(ACTION.SAVE);
        } else {
            choice.SetAction(ACTION.NULA);
        }
        //Devuelve choice
        return choice;
    }

    //define la direccion
    private boolean modificarDireccion(String input, Options choice) {
        //Revisa en que direccion debe ir
        if (input.toLowerCase().endsWith("derecha")) {
            choice.SetPath(DIRECTIONS.RIGHT);
        } else if (input.toLowerCase().endsWith("izquierda")) {
            choice.SetPath(DIRECTIONS.LEFT);
        } else if (input.toLowerCase().endsWith("abajo")) {
            choice.SetPath(DIRECTIONS.BOT);
        } else if (input.toLowerCase().endsWith("arriba")) {
            choice.SetPath(DIRECTIONS.TOP);
        } //En caso que no de ninguna direccion devuelve falso
        else {
            return false;
        }
        //Se llego a definir una direccion
        return true;
    }
    
    private boolean mostrarStats() {
        return true;
    }
}
