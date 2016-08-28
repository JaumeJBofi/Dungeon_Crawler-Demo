/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.Dibujador;
import Controllers.DungeonManager;
import Foundation.CellInformation;
import Foundation.DIRECTIONS;
import Models.Avatar;
import Foundation.Options;
import Foundation.Options.ACTION;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {               
        DungeonManager myManager = new DungeonManager();                        
        
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(30, 30,0.5,0),10,10);
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken = new Options(ACTION.INTERACT);
        
        CellInformation nextCellInformation;
        
        while((ACTION.EXIT!=((Renderer.mostrarMenu(choiceTaken)).taken)))
        {            
            Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
            switch(choiceTaken.taken){
                case MOVE:{
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        player.Move(choiceTaken.path, 1);                    
                    }else{
                        System.out.println("\n\nNo puedes moverte en esa Direccion");
                        System.out.println("");
                    }                    
                }break;
                case INTERACT:{  
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        CellInformation.CELLOBJECT var_object = nextCellInformation.GetObject();
                        switch(var_object){
                            case WEAPON:
                            case POTION:
                            {
                                myManager.Interactuar(player.GetPosition(),choiceTaken.path); // borra el artefacto de la matriz
                                //player.AddArtefact();
                                System.out.println("\n\n INTERACT CORRECTO");
                                System.out.println("");
                                break;
                            }
                            default: {
                                System.out.println("\n\nNo puedes interactuar con esa casilla");
                                System.out.println("");
                                break;
                            }
                        }
                    }else{
                        System.out.println("\n\nNo puedes interactuar con esa pared");
                        System.out.println("");
                    }
                }break;
            }            
        }
    }
    
}
