/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.Dibujador;
import Controllers.DungeonManager;
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
        
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(25, 25,0,0));
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken;
        
        while((ACTION.EXIT!=((choiceTaken=Renderer.mostrarMenu()).taken)))
        {
            Renderer.mostrarLaberinto(myManager.dungeons.get(0), player);
            switch(choiceTaken.taken){
                case MOVE:{
                    
                }break;
                case INTERACT:{
                    
                }break;
            }
            
        }
    }
    
}
