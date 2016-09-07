/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;


/**
 *
 * @author Jauma
 */
public class Entity {

    private Coordinate position;
    int hp; // vida actual
    private String nombre;
    static Random generator;
    protected int tamShowX;
    protected int tamShowY;
    private DIRECTIONS lookDirection;
  
    public Entity(Coordinate varPosition){
        hp = 100;
        position = varPosition;    
        generator = new Random();
    }
    
    public Coordinate GetPosition(){
        return position.GetPoint();
    }
    
    public void SetPosition(int x,int y){
        position.SetX(x);
        position.SetY(y);
    }
    
    public void SetPosition(Coordinate newCoordinate){
        position = newCoordinate;
    }
            
    
    public int GetX(){
        return position.GetX();
    }
    
    public int GetY(){
        return position.GetY();
    }
    
    public void SetVida(int v) {
        hp = v;
    }

    public int GetVida() {
        return hp;
    }
    
    final public void SetNombre(String nombre){
      this.nombre = nombre;
    }
    
    public String GetNombre(){
        return nombre;
    }
    
    public DIRECTIONS GetLookDirection(){
        return lookDirection;
    }
    
    final public void SetLookDirection(DIRECTIONS varDirection){
        lookDirection = varDirection;
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
    
    public void ThinkMove(Coordinate coord, DIRECTIONS dir,int steps){
         int xFactor = 0, yFactor = 0;
        switch (dir) {
            case BOT: {
                yFactor += steps;
            }
            break;
            case TOP: {
                yFactor -= steps;
            }
            break;
            case LEFT: {
                xFactor -= steps;
            }
            break;
            case RIGHT: {
                xFactor += steps;
            }
        }
        coord.SetX(coord.GetX() + xFactor);
        coord.SetY(coord.GetY() + yFactor);    
    }  
    
    public boolean RandomMove(CellInformation[][] dungeonAccess,int steps){
        List<DIRECTIONS> validDir = new ArrayList();
        Coordinate varPointX = position.GetPoint();
        Coordinate varPointY = position.GetPoint();

        ThinkMove(varPointX, DIRECTIONS.TOP, steps);
        if (varPointX.InRange() && !dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT) {
            validDir.add(DIRECTIONS.TOP);
        }

        ThinkMove(varPointX, DIRECTIONS.BOT, steps*2);
        if (varPointX.InRange() && !dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT) {
            validDir.add(DIRECTIONS.BOT);
        }

        ThinkMove(varPointY, DIRECTIONS.LEFT, steps);
        if (varPointY.InRange() && !dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT) {
            validDir.add(DIRECTIONS.LEFT);
        }

        ThinkMove(varPointY, DIRECTIONS.RIGHT, steps*2);
        if (varPointY.InRange() && !dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT) {
            validDir.add(DIRECTIONS.RIGHT);
        }

        if (validDir.isEmpty()) {
            return false;
        }
        if(validDir.contains(lookDirection)){
            // Feo pero es lo mas facil de hacer. Agregar objetos para aumentar
            // la probabilidad
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            Collections.shuffle(validDir, new Random());
        }
        lookDirection = validDir.get(generator.nextInt(validDir.size()));
        Move(lookDirection,steps);
        return true;
    }
    
     //Modif
    public Entity(Coordinate varPosition, String nomb, int vida) {
        hp = vida;
        nombre = nomb;
        position = varPosition;
    }
}
