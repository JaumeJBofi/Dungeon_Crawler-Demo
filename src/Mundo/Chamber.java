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
import Models.IDibujable;
import Models.Sprite;
import java.awt.Graphics;

/**
 *
 * @author Jauma
 */
public class Chamber extends Sprite implements IDibujable{
    private CellInformation chamberStatus;        
    // The constructer will be used when creating the matrix of the maze and 
    // setting the CellType will be our initial concern
    
    // Aca podemos guardar info de enemigos, items, etc.
    
    private Enemy chamberEnemy;
    private Aliado chamberAlly;
    private Artefacto chamberArtefact;       
       
    
    public Chamber(String spriteInfo,int _width,int _height)
    {
        super(_width,_height);
        LoadComponents(spriteInfo);
    }

    public Chamber(Sprite base,int _width,int _height)
    {
        super(_width,_height);
        copySprite(base);
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
    
    final public void SetArtefact(Artefacto varArtefact){
        chamberArtefact = varArtefact;
    }          
    
    public Enemy GetEnemy(){
        return chamberEnemy;
    }
    
    public Aliado GetAliado()
    {
        return chamberAlly;
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
    
    public Artefacto ReleaseArtifact()
    {
        Artefacto varArtefact = chamberArtefact;
        chamberArtefact = null;
        return  varArtefact;
    }
    
    public void GasEnemy(){
        chamberEnemy = null;
    }
    
    public void GasAlly()
    {
        chamberAlly = null;
    }
    
    public Sprite GetInhabitant()
    {
        if(chamberEnemy!=null) return chamberEnemy;
        if(chamberArtefact!=null) return chamberArtefact;
        if(chamberAlly!=null) return chamberAlly;
        return null;
    }
     
    
    public Artefacto GetArtefacto(){
        return chamberArtefact;
    }

    @Override
    public void Render(Graphics g) {
        paint(g);
    }
    
   
    @Override
    public void LoadComponents(String spriteInfo) {
        
       ProcessSpriteInfo(spriteInfo);      
    }

    @Override
    public void Dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
