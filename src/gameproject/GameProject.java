/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.Dibujador;
import Controllers.DungeonManager;
import Foundation.CellInformation;
import Models.Avatar;
import Controllers.Lore;
import Foundation.Options;
import Foundation.Options.ACTION;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
               
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos
        Random randManager = new Random();
        Scanner in = new Scanner(System.in);
        
        Lore historia = new Lore();
        
        String name;
        if(in.nextLine().compareToIgnoreCase("re")==0) name = historia.nacer();
        else name = historia.IntroMenu(in);
        
        DungeonManager myManager = new DungeonManager(randManager.nextInt(7-3)+3);
        //DungeonManager myManager = new DungeonManager(2);
        
        int varM = randManager.nextInt(50-25)+25;
        int varN = randManager.nextInt(35-25)+25;                
        
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(varM,varN,0.15,5,0.3),10,6,name);
        //Avatar player = new Avatar(myManager.CreateDungeonDistribution(40,5,0.5,0),40,10);
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken = new Options(ACTION.INTERACT);
        
        CellInformation nextCellInformation;
        
        historia.writeNLines(20);
        Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        
        while((ACTION.EXIT!=((Renderer.mostrarMenu(choiceTaken)).taken)))
        {                        
            switch(choiceTaken.taken){
                case MOVE:{
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()&&nextCellInformation.GetType()==CellInformation.CELLTYPE.ADENTRO)
                            {
                                if(nextCellInformation.isNext()||nextCellInformation.isPrevious())
                                {
                                   if(myManager.ChangeDungeon(player,nextCellInformation.isNext())==1){
                                       historia.winGame(in);                                       
                                   }
                                }else{
                                    player.Move(choiceTaken.path, 1);                                        
                                }                       
                        historia.writeNLines(20);
                    }else{
                        historia.writeNLines(20);
                        switch(nextCellInformation.GetType())
                        {
                            case PARED:
                                System.out.println("\n\nNo puedes atravezar paredes....aún...");
                            break;
                            case ARTIFACT:
                                System.out.println("\n\nUn misterioso objeto bloquea tu camino. Puedes intentar cogerlo");
                            break;
                            case ENEMY:
                                System.out.println("\n\nCuidado! Un enemigo? Preparate para una posible batalla");
                            break;                            
                        }         
                        System.out.println("");                       
                    }                    
                     myManager.GetActiveDungeon().MoveEnemies();
                }break;
                case INTERACT:{  
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        
                        
                        switch(nextCellInformation.GetType())
                        {
                            case ARTIFACT:
                            {                                
                                myManager.GetActiveDungeon().Interactuar(player,choiceTaken.path); // borra el artefacto de la matriz                                
                                historia.writeNLines(20);                              
                                System.out.println("\n\n INTERACT CORRECTO");                               
                            }break;
                            case ENEMY:
                            {
                                historia.writeNLines(20);                              
                                System.out.println("\n\n Ehh...No se vee muy amigable. Huyes antes que su ataque te alcanze");

                            }break;
                            default:
                            {
                                historia.writeNLines(20);                              
                                System.out.println("\n\nNo puedes interactuar con esa casilla"); 
 
                            }break;                            
                        }                        
                        System.out.println("");
                    }else{
                        historia.writeNLines(20);
                        System.out.println("\n\nNo puedes interactuar con esa pared");                      
                    }
                }break;
                case DEBUG:
                {
                    historia.writeNLines(20);                    
                    myManager.printDebugInfo(player);
                    System.err.println("");
                }break;
                case TELEPORT:
                {                   
                    int x = in.nextInt();
                    int y = in.nextInt();
                    myManager.GetActiveDungeon().TeleportPlayer(player, x, y);                            
                }case HELP:
                {
                    historia.writeNLines(20);
                    System.out.println("Comandos:\nMover [Derecha|Izquierda|Arriba|Abajo] : Te mueves a la dirección especificada si es posible. No es case sensitive\n"
                            + "Interactuar: Interactuas con el objeto al que estas mirando. Donde miras es la ultima dirección a la que te moviste o intentaste\n"
                            + "Atacar: Eliminas al enemigo al que estas mirando\nEquipar: Te equipas una armadura o arma de tu inventorio\n"
                            + "Help: Muestra la ayuda del juego\nSalir: Sale del Juego actual\n\nPresione cualquier tecla para continuar\n");
                             in.nextLine();
                    historia.writeNLines(20);     
                }break;
                case ATTACK:
                {
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        switch(nextCellInformation.GetType())
                        {
                            case ENEMY:
                            {
                                // Random Text
                                historia.writeNLines(20);
                                System.out.println("\n\nHyaaa!!!\nTu ataque dio en el blanco...El enemigo se desvanece silenciosamente como si estuviera aliviado");      
                                myManager.GetActiveDungeon().Battle(player.GetX(),player.GetY(),choiceTaken.path);
                            }break;
                            default:
                            {
                                historia.writeNLines(20);                             
                                System.out.println("\n\nNo hay absolutamente nada peligroso en esa dirección.");      
                            }
                        }
                    }else
                    {
                        historia.writeNLines(20);
                        System.out.println("\n\nEsa pared no te ha hecho nada. Disculpate, Las paredes tambien tienen sentimientos...");      
                    }
                    System.out.println("");
                }break;
                case EQUIP:
                {                    
                     // Falta Implementar
                    System.out.println();              
                    System.out.println("\n\nIngrese el indice del item a equipar o usar\n");
                    String input = in.nextLine();
                    try {
                        Integer n = Integer.parseInt(input);       
                        player.EquipItem(n-1);
                    } catch (NumberFormatException e) {
                        System.out.println("Mal input. Presione Enter para continuar\n");
                        in.nextLine();                        
                        int a;
                    }                             
                    historia.writeNLines(20);
                }break;
                default:{
                    historia.writeNLines(20);
                    System.out.println("\n\nAcción no definida");
                    System.out.println("");                    
                }break;
            }            
            Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
            //myManager.GetActiveDungeon().Render();
        }
    }
    
}
