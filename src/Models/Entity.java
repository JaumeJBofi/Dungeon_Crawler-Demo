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
 * @author Jauma
 */
public class Entity {

    protected Coordinate position;
    int hp; // vida actual

    public Entity(int M,int N){
        hp = 0;
        position = new Coordinate(M, N);        
    }
    
    public Coordinate GetPosition()
    {
        return position;
    }

    public void SetVida(int v) {
        hp = v;
    }

    public int GetVida() {
        return hp;
    }
    
    public void Move(DIRECTIONS way,int steps){
        try{
        int xFactor = 0, yFactor = 0;
        switch(way) {
                    case BOT:{ yFactor+=steps;                        
                    }break;
                    case TOP:{ yFactor-=steps;                        
                    }break;
                    case LEFT:{ xFactor-=steps;                        
                    }break;
                    case RIGHT:{ xFactor+=steps;                        
                    }
                }
        position.SetX(position.GetX()+xFactor);
        position.SetY(position.GetY()+yFactor);                        
        } catch (IndexOutOfBoundsException e){
            System.err.println("Move out of bounds");
            System.exit(1);
        }                
    }
    
}
