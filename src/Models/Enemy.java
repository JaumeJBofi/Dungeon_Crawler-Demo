/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;
import Foundation.DIRECTIONS;

/**
 *
 * @author Arthuro
 */
public class Enemy extends Entity {
    private int nivel;    
    
    //Coordinate position;
    public Enemy(Coordinate varPosition){
        super(varPosition,"Lament",15,5);
        SetLookDirection(DIRECTIONS.BOT);
        nivel = 1;        
    }
    
    public void SetNivel(int n){
        nivel = n;
        SetStrength(GetStrength()*nivel);
    }
    
    public int GetNivel(){
        return nivel;
    }
                
    //Modif
    public Enemy(Coordinate varPosition, String nomb, int vida, int lvl,int initStrength) {
        super(varPosition, nomb, vida,initStrength);    
        SetLookDirection(DIRECTIONS.BOT);    
        SetNivel(lvl);
    }

    public Enemy copiar() {
        Enemy nuevo_enemigo = new Enemy(this.GetPosition(), this.GetNombre(), this.GetVida(), nivel,GetStrength());
        return nuevo_enemigo;
    }
    
    // Podemos extender de aqui.
    public int GetEnemyDamage(){
        return GetStrength();
    }
}
