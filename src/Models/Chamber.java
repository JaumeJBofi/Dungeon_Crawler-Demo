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
public class Chamber {
    private CellInformation chamberStatus;        
    // The constructer will be used when creating the matrix of the maze and 
    // setting the CellType will be our initial concern
    
    // Aca podemos guardar info de enemigos, items, etc.
    
    public Chamber(CellInformation.CELLMODE mode,CellInformation.CELLTYPE type){
        chamberStatus.SetMode(mode);
        chamberStatus.SetType(type);
    }
}
