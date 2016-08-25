/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Arthuro
 */
public class Enemy extends Entity {
    int nivel;
    public Enemy(int M,int N){
        super(M, N);
        nivel = 1;
    }
    
    public void SetNivel(int n){
        nivel = n;
    }
    
    public int GetNivel(){
        return nivel;
    }
}
