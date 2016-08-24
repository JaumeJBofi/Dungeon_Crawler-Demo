/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Foundation.CellInformation;
import Models.Dungeon;
import Foundation.Coordinate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.List;

/**
 *
 * @author Jauma
 */
public class DungeonManager extends ManagerBase{
    private int[][] dungeonAccess;
    final private Dungeon theDungeon;   
    private ManagerBase.DIRECTIONS currentDirections;
    final private Random randomManager;
    
    
    public DungeonManager(double worldprcEnemies,double worldlvlEnemies,CellInformation.CELLMODE mode,CellInformation.CELLTYPE type){
        theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies, mode, type);
        randomManager = new Random();
    }
    
    private boolean inRange(int i,int j,int M,int N)
    {
        return ((i>=0&&i<M)&&(j>=0&&j<N));
    }
    
    private boolean checkAdjCells(Coordinate point,int M,int N){                      
        List <ManagerBase.DIRECTIONS> validDir = new ArrayList();
       
        int factor = -1;
        if(inRange(point.GetX(), point.GetY()+factor, M, N)&&dungeonAccess[point.GetX()][point.GetY()+factor]==0) validDir.add(ManagerBase.DIRECTIONS.TOP);
        if(inRange(point.GetX()+factor, point.GetY(), M, N)&&dungeonAccess[point.GetX()+factor][point.GetY()]==0) validDir.add(ManagerBase.DIRECTIONS.LEFT);                        
        
        factor = 1;
        if(inRange(point.GetX(), point.GetY()+factor, M, N)&&dungeonAccess[point.GetX()][point.GetY()+factor]==0) validDir.add(ManagerBase.DIRECTIONS.BOT);
        if(inRange(point.GetX()+factor, point.GetY(), M, N)&&dungeonAccess[point.GetX()+factor][point.GetY()]==0) validDir.add(ManagerBase.DIRECTIONS.RIGHT);
        
        if(validDir.isEmpty()) return false;
        
        currentDirections = validDir.get(randomManager.nextInt(validDir.size()));   
        return true;
    }
    
    public void CreateDungeonDistribution(int M,int N){
        dungeonAccess = new int[M][N];
        theDungeon.SetM(M);
        theDungeon.SetN(N);                
        
        // Capaz podemos hacer que las conexiones agregen info a la matriz, como
        // el numero de conexiones. De esta manera podemos saber si es que es una habitacion.
        Coordinate currentCoord = new Coordinate(M,N);
        currentCoord.SetX(randomManager.nextInt(M));
        currentCoord.SetY(randomManager.nextInt(N));
        
        Stack<Coordinate> myStack = new Stack();
        
        dungeonAccess[currentCoord.GetX()][currentCoord.GetY()] = 1;
        myStack.push(currentCoord.GetCoordinate());
        
        while(!myStack.isEmpty()){
            currentCoord = myStack.peek();
            if(checkAdjCells(currentCoord,theDungeon.GetM(),theDungeon.GetN())){
                
                // Marcar camino y a V
                advanceInDirection(currentCoord, currentDirections,1);   
                dungeonAccess[currentCoord.GetX()][currentCoord.GetY()] = 1;                                           
                advanceInDirection(currentCoord, currentDirections,1);   
                dungeonAccess[currentCoord.GetX()][currentCoord.GetY()] = 1;
                                
                myStack.push(currentCoord.GetCoordinate());
            }else
            {
                myStack.pop();
            }
        }
    }
    
    // Metodo momentaneo
    public void Draw()
    {
        for(int i = 0;i<theDungeon.GetM();i++)
        {
            for(int j = 0;i<theDungeon.GetN();j++)
            {
                if(dungeonAccess[i][j]==1)
                {
                    System.err.print("0");
                }
            }
            System.err.println(" ");
        }
    }
}
