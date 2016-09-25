/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;
import Artefactos.Artefacto;
import Foundation.CellInformation;
import Models.Enemy;
import Facilidades.Aliado;

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
    private Aliado chamberAlly;
    private Artefacto chamberArtefact;
   
    public Chamber(){
        
    }
    public Chamber(Enemy enemyResident,Artefacto artefactResident){
        SetArtefact(chamberArtefact);
        SetEnemy(chamberEnemy);
    }
    
    final public void SetEnemy(Enemy varEnemy){
        chamberEnemy = varEnemy;
    }
    
    final public void SetAlly(Aliado varAlly)
    {
        chamberAlly = varAlly;        
    }
    
    public Enemy GetEnemy(){
        return chamberEnemy;
    }
    
    //Pregunta 2    
    public Enemy ReleaseEnemy(){
        Enemy varEnemy = chamberEnemy;
        chamberEnemy = null;
        return varEnemy;
    }
    
    public Aliado ReleaseAliado()
    {
        Aliado varAlly = chamberAlly;
        chamberAlly = null;
        return varAlly;
    }
    
    public void GasEnemy(){
        chamberEnemy = null;
    }
    
    public void GasAlly()
    {
        chamberAlly = null;
    }
    
    final public void SetArtefact(Artefacto varArtefact){
        chamberArtefact = varArtefact;
    }
    
    public Artefacto GetArtefacto(){
        return chamberArtefact;
    }
            
}
