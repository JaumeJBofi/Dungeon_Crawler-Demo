/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;

/**
 *
 * @author Arthuro
 */
public class Enemy extends Entity {
    int nivel;
    Coordinate position;
    public Enemy(Coordinate varPosition){
        super(varPosition);
        nivel = 1;
    }
    
    public void SetNivel(int n){
        nivel = n;
    }
    
    public int GetNivel(){
        return nivel;
    }
}
