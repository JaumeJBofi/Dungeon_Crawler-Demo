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
public abstract class Entity {

    private Coordinate position;
    int hp; // vida actual
    private String nombre;
    static Random generator;
    protected int tamShowX;
    protected int tamShowY;
    private DIRECTIONS lookDirection;
    private int strength;
       
  
    public Entity(Coordinate varPosition,String varNombre,int varStrength){
        hp = 100;
        position = varPosition;    
        generator = new Random();
        nombre = varNombre;
        strength = varStrength;
    }
    
    final public int GetStrength()
    {
        return strength;        
    }
    
    final public void SetStrength(int varStrength)
    {
        strength = varStrength;
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
    
    //Pregunta 2
    public void ReciveDamage(int dmg){
        hp -= dmg;
        if(hp<0) hp = 0;
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
            }break;
        }
        coord.SetX(coord.GetX() + xFactor);
        coord.SetY(coord.GetY() + yFactor);    
    }  
    
    public DIRECTIONS RandomMove(CellInformation[][] dungeonAccess,int steps,int playerX,int playerY){
        List<DIRECTIONS> validDir = new ArrayList();
        Coordinate varPointX = position.GetPoint();
        Coordinate varPointY = position.GetPoint();

        ThinkMove(varPointX, DIRECTIONS.LEFT, steps);
        if (varPointX.InRange() && !dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()&&dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT
                &&dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType()!=CellInformation.CELLTYPE.ENEMY&&!varPointX.IsEqual(playerX, playerY)) {
            validDir.add(DIRECTIONS.LEFT);
        }

        ThinkMove(varPointX, DIRECTIONS.RIGHT, steps*2);
        if (varPointX.InRange() && !dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()&&dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT
                &&dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType()!=CellInformation.CELLTYPE.ENEMY&&!varPointX.IsEqual(playerX, playerY)) {
            validDir.add(DIRECTIONS.RIGHT);
        }

        ThinkMove(varPointY, DIRECTIONS.TOP, steps);
        if (varPointY.InRange() && !dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT
                &&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ENEMY&&!varPointY.IsEqual(playerX, playerY)) {
            validDir.add(DIRECTIONS.TOP);
        }

        ThinkMove(varPointY, DIRECTIONS.BOT, steps*2);
        if (varPointY.InRange() && !dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()&&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ARTIFACT
                &&dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType()!=CellInformation.CELLTYPE.ENEMY&&!varPointY.IsEqual(playerX, playerY)) {
            validDir.add(DIRECTIONS.BOT);
        }

        if (validDir.isEmpty()) {
            return DIRECTIONS.STAY;
        }
        if(validDir.contains(lookDirection)){
            // Feo pero es lo mas facil de hacer. Agregar objetos para aumentar
            // la probabilidad
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            //Collections.shuffle(validDir, new Random());
        }
        return validDir.get(generator.nextInt(validDir.size()));     
    }
    
     //Modif
    public Entity(Coordinate varPosition, String nomb, int vida,int varStrength) {
        hp = vida;
        generator = new  Random();
        nombre = nomb;
        position = varPosition;
        strength = varStrength;
    }
}
