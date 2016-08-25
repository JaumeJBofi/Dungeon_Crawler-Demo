/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Foundation.CellInformation;

/**
 *
 * @author Jauma
 */
public class Dungeon extends MazeBase{
    
    private CellInformation dungeonStatus;
    private Chamber[][] layOutChamber;
    
    public Dungeon(double varprcEnemies,double varlvlEnemies,CellInformation.CELLMODE mode,CellInformation.CELLTYPE type){
        // Momentaneamente el Laberinto no posee dimensiones
        super(0,0, varprcEnemies, varlvlEnemies);
        dungeonStatus = new CellInformation();
        dungeonStatus.SetMode(mode);
        dungeonStatus.SetType(type);
    }
            
}
