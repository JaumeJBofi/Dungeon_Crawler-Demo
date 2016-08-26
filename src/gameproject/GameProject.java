/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.DungeonManager;

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
        myManager.CreateDungeonDistribution(25, 25,0,0);
    }
    
}
