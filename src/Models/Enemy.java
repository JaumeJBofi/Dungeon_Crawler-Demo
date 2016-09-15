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
    private int daño;
    
    //Coordinate position;
    public Enemy(Coordinate varPosition){
        super(varPosition);
        SetLookDirection(DIRECTIONS.BOT);
        nivel = 1;
        daño = 15;
    }
    
    public void SetNivel(int n){
        nivel = n;
        daño *= nivel;
    }
    
    public int GetNivel(){
        return nivel;
    }
                
    //Modif
    public Enemy(Coordinate varPosition, String nomb, int vida, int lvl) {
        super(varPosition, nomb, vida);
        nivel = lvl;
        daño = 15;
    }

    public Enemy copiar() {
        Enemy nuevo_enemigo = new Enemy(this.GetPosition(), this.GetNombre(), this.GetVida(), nivel);
        return nuevo_enemigo;
    }
    
    public int GetEnemyDamage(){
        return daño;
    }
}
