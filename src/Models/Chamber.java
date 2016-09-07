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
    
    private Enemy chamberEnemy;
    private Artefacto chamberArtefact;
    
    public Chamber(Enemy enemyResident,Artefacto artefactResident){
        SetArtefact(chamberArtefact);
        SetEnemy(chamberEnemy);
    }
    
    final public void SetEnemy(Enemy varEnemy){
        chamberEnemy = varEnemy;
    }
    
    public Enemy GetEnemy(){
        return chamberEnemy;
    }
    
    final public void SetArtefact(Artefacto varArtefact){
        chamberArtefact = varArtefact;
    }
    
    public Artefacto GetArtefacto(){
        return chamberArtefact;
    }
            
}
