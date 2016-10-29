/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaz.Dibujador;
import Mundo.DungeonManager;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Models.Avatar;
import Interfaz.Lore;
import Foundation.Options;
import Facilidades.Aliado;
import Models.Entity;
import java.util.Random;
import java.util.Scanner;
import Models.Stage;
import static Models.Stage.SCREENMODE;

// Graphics imports begin
import com.sun.media.sound.AudioFileSoundbankReader;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
// Graphics imports end

/**
 *
 * @author Jauma
 */
public class Game extends Stage{
    private Random randManager;
    private Scanner in;
 
    //Lore nos ayuda a narrar la historia
    private Lore historia;
    private DungeonManager myManager;
    
    public int varM;
    public int varN;
    
    Dibujador Renderer;

    Options choiceTaken;

    CellInformation nextCellInformation;
    
    // Puede ser un arreglo
    Avatar player;
    
    public CellInformation.CELLTYPE GetWall(int i,int j)
    {
        return myManager.GetActiveDungeon().GetCellInformation(i,j).GetType();
    }
    public void LoadNewGame()
    {
        randManager = new Random();
        in = new Scanner(System.in);
        historia = new Lore();
        myManager = new DungeonManager(randManager.nextInt(7 - 3) + 3,WIDTH,HEIGHT);
        varM = randManager.nextInt(50 - 25) + 25;
        varN = randManager.nextInt(35 - 25) + 25;
        
        Renderer = new Dibujador();

        choiceTaken = new Options(Options.ACTION.INTERACT);    
        
        String name = " ";
        //if(!name.equalsIgnoreCase("skip")&&!name.equalsIgnoreCase("")) historia.nacer();             
        player = new Avatar(myManager.CreateDungeonDistribution(varM, varN, 0.15, 5, 0.3,1), 10, 6, 100, name, 10, 5);   
                
    }
    
    public void LoadedStandar()
    {
        Renderer.scanner.close();
        Renderer.scanner = new Scanner(System.in);
        Entity.generator = new Random();        
    }
    
    public Game(){
        super(SCREENMODE.JFRAME);
        SetFPS(80);
        setSize(960-6, 640-6);
        randManager = new Random();
        historia = new Lore();
        
        myManager = new DungeonManager(randManager.nextInt(7 - 3) + 3,WIDTH,HEIGHT);
        varM = randManager.nextInt(50 - 25) + 25;
        varN = randManager.nextInt(35 - 25) + 25;
        
        Renderer = new Dibujador();
        
        choiceTaken = new Options(Options.ACTION.INTERACT);    
        nextCellInformation = new CellInformation();
        
        String name = " ";
        // Poner historia?? Opciones.
        player = new Avatar(null, 10, 6, 100, name, 10, 5);   
        setSize(960-6, 640-6);
    }
    
    @Override
    public synchronized void InitStage()
    {
        player.SetPosition(myManager.CreateDungeonDistribution(varM, varN, 0.15, 5, 0.3,1));        
        myManager.GetActiveDungeon().AddPlayer(player);
    }
    
    @Override
    public synchronized void UpdateStage()
    {
        // Now with Graphics
        myManager.GetActiveDungeon().MoveEnemiesInteligente(player.GetX(), player.GetY());
        myManager.GetActiveDungeon().act();        
    }
    
    @Override 
    public synchronized void RenderStage(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH,HEIGHT);
        myManager.GetActiveDungeon().Render(g,player.GetX(),player.GetY(),player.GetTamShowX(),player.GetTamShowY(),true);        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
        
    public Options.ACTION Run()
    {
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos               
        myManager.GetActiveDungeon().MoveEnemiesInteligente(player.GetX(), player.GetY());
        myManager.GetActiveDungeon().MoveAllies(player.GetX(), player.GetY());
        return Options.ACTION.MOVE;
//        Renderer.mostrarMenu(choiceTaken);
//        switch (choiceTaken.taken) {
//            case MOVE: {
                //if (!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(), choiceTaken.path)).isWall()
//                        && nextCellInformation.GetType() == CellInformation.CELLTYPE.ADENTRO) {
//                    if (nextCellInformation.isNext() || nextCellInformation.isPrevious()) {
//                        if (myManager.ChangeDungeon(player, nextCellInformation.isNext()) == 1) {
//                            historia.winGame(in);
//                        }
//                    } else {
//                        player.Move(choiceTaken.path, 1);
//                    }
//                    historia.writeNLines(20);
//                } else {
//                    historia.writeNLines(20);
//                    switch (nextCellInformation.GetType()) {
//                        case PARED:
//                            System.out.println("\n\nNo puedes atravezar paredes....aún...");
//                            break;
//                        case ARTIFACT:
//                            System.out.println("\n\nUn misterioso objeto bloquea tu camino. Puedes intentar cogerlo");
//                            break;
//                        case ENEMY:
//                            System.out.println("\n\nCuidado! Un enemigo? Preparate para una posible batalla");
//                            break;                            
//                    }
//                    System.out.println("");
//                }
//                //Esto hace que los enemigos se muevan
//                //# Preg 1
//                //Preg 1 Lab2
//                myManager.GetActiveDungeon().MoveEnemiesInteligente(player.GetX(), player.GetY());
//                myManager.GetActiveDungeon().MoveAllies(player.GetX(), player.GetY());
//            }
//            break;
//            case INTERACT: {
//                if (!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(), choiceTaken.path)).isWall()) {
//
//                    switch (nextCellInformation.GetType()) {
//                        case ARTIFACT: {
//                            myManager.GetActiveDungeon().Interactuar(player, nextCellInformation.position); // borra el artefacto de la matriz                                
//                            historia.writeNLines(20);
//                            System.out.println("\n\n INTERACT CORRECTO");
//                        }
//                        break;
//                        case ENEMY: {
//                            // #Preg 2:
//                            historia.writeNLines(20);
//                            // Podemos ponder en choice taken la posicion... luego podemos implementar eso
//                            if (myManager.GetActiveDungeon().Battle(player, nextCellInformation.position)) {
//                                // Gana la batalla... No muere
//                            } else {
//                                System.out.println("Fin del juego. Ha muerto. Presione Enter\n");
//                                in.nextLine();
//                                System.exit(0);
//                                // Fin del juego
//                            }
//                        }
//                        break;
//                        case FRIEND:
//                        {
//                            //Preg 1 Lab2
//                            myManager.GetActiveDungeon().GetFriendAdvice(nextCellInformation.position);
//                        }break;
//
//
//                        default: {
//                            historia.writeNLines(20);
//                            System.out.println("\n\nNo puedes interactuar con esa casilla");
//
//                        }
//                        break;
//                    }
//                    System.out.println("");
//                } else {
//                    historia.writeNLines(20);
//                    System.out.println("\n\nNo puedes interactuar con esa pared");
//                }
//                // Pregunta 1
//                //# Preg 1 Y todas las clases adentro ya estaban implementadas
//
//                //Preg 1 Lab2
//                myManager.GetActiveDungeon().MoveEnemiesInteligente(player.GetX(), player.GetY());
//                myManager.GetActiveDungeon().MoveAllies(player.GetX(), player.GetY());
//            }
//            break;
//            case DEBUG: {
//                historia.writeNLines(20);
//                myManager.printDebugInfo(player);
//                System.err.println("");
//            }
//            break;
//            case TELEPORT: {
//                int x = in.nextInt();
//                int y = in.nextInt();
//                myManager.GetActiveDungeon().TeleportPlayer(player, x, y);
//            }
//            case HELP: {
//                historia.writeNLines(20);
//                System.out.println("Comandos:\nMover [Derecha|Izquierda|Arriba|Abajo] : Te mueves a la dirección especificada si es posible. No es case sensitive\n"
//                        + "Interactuar: Interactuas con el objeto al que estas mirando. Donde miras es la ultima dirección a la que te moviste o intentaste\n"
//                        + "nEquipar: Te equipas una armadura o arma de tu inventorio\n"
//                        + "Help: Muestra la ayuda del juego\nSalir: Sale del Juego actual\n\nPresione cualquier tecla para continuar\n"
//                        + "Guardar: Guardas el estado del juego para que puedas comenzar donde te quedaste\n");
//                in.nextLine();
//                historia.writeNLines(20);
//            }
//            break;
//            case ATTACK: {
//                if (!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(), choiceTaken.path)).isWall()) {
//                    switch (nextCellInformation.GetType()) {
//                        case ENEMY: {
//                            // Random Text
//                            historia.writeNLines(20);
//                            System.out.println("\n\nHyaaa!!!\nTu ataque dio en el blanco...El enemigo se desvanece silenciosamente como si estuviera aliviado");                                
//                            myManager.GetActiveDungeon().KillInChamber(nextCellInformation.position);
//                        }
//                        break;
//                        default: {
//                            historia.writeNLines(20);
//                            System.out.println("\n\nNo hay absolutamente nada peligroso en esa dirección.");
//                        }
//                    }
//                } else {
//                    historia.writeNLines(20);
//                    System.out.println("\n\nEsa pared no te ha hecho nada. Disculpate, Las paredes tambien tienen sentimientos...");
//                }
//                System.out.println("");
//            }
//            break;
//            case EQUIP: {
//                // Falta Implementar
//                System.out.println();
//                Integer n = choiceTaken.indice_item;
//                if (n > 0) {
//                    if (n <= player.getSizeSaco()) {
//                        player.EquipItem(n - 1);
//                    } else {
//                        System.out.println("\nIndice no valido\n\n");
//                        in.nextLine();
//                    }
//                } else {
//                    System.out.println("\nIndice no valido\n\n");
//                    in.nextLine();
//                }
//                historia.writeNLines(20);
//            }
//            break;
//            case SAVE:
//            {
//                Renderer.scanner.reset();
//            }
//            case EXIT:{
//                 // Implementado por el callback
//                 return choiceTaken.taken;
//            }
//            default: {
//                historia.writeNLines(20);
//                System.out.println("\n\nAcción no definida");
//                System.out.println("");
//            }
//            break;
//        }
//        System.out.format("Mundo Nr° %d de %d\n", myManager.GetActiveDungeonIndex() + 1, myManager.GetTotalDungeons());
//        Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);            
//        historia.listaAcciones();
//
//        myManager.GetActiveDungeon().CheckConsistency();
//        //myManager.GetActiveDungeon().Render();
//        
//        return choiceTaken.taken;               
    }    
}
