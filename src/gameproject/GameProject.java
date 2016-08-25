/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.DungeonManager;
import Foundation.CellInformation;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {               
        DungeonManager myManager = new DungeonManager(0, 0, CellInformation.CELLMODE.NORMAL, CellInformation.CELLTYPE.AFUERA);
        myManager.CreateDungeonDistribution(10, 10);
    }
    
}
