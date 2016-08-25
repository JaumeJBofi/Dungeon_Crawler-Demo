/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.io.PrintWriter;
import Foundation.CellInformation;
import Models.Dungeon;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;
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
    private DIRECTIONS currentDirections;
    final private Random randomManager;
    
    
    public DungeonManager(double worldprcEnemies,double worldlvlEnemies,CellInformation.CELLMODE mode,CellInformation.CELLTYPE type){
        theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies, mode, type);                              
        randomManager = new Random();
    }
    
    private boolean inRange(int i,int j,int M,int N)
    {
        return ((i>0&&i<M)&&(j>0&&j<N));
    }
    
    private boolean checkAdjCells(Coordinate point){              
        List <DIRECTIONS> validDir = new ArrayList();
       
        int factor = -2;
        if(inRange(point.GetX(), point.GetY()+factor, point.GetM(), point.GetN())&&dungeonAccess[point.GetX()][point.GetY()+factor]!=1) validDir.add(DIRECTIONS.TOP);
        if(inRange(point.GetX()+factor,  point.GetY(), point.GetM(), point.GetN())&&dungeonAccess[point.GetX()+factor][point.GetY()]!=1) validDir.add(DIRECTIONS.LEFT);                        
        
        factor = 2;
        if(inRange(point.GetX(), point.GetY()+factor,  point.GetM(), point.GetN())&&dungeonAccess[point.GetX()+factor][point.GetY()+factor]!=1) validDir.add(DIRECTIONS.BOT);
        if(inRange(point.GetX()+factor, point.GetY(),  point.GetM(), point.GetN())&&dungeonAccess[point.GetX()+factor][point.GetY()]!=1) validDir.add(DIRECTIONS.RIGHT);
        
        if(validDir.isEmpty()) return false;
        
        currentDirections = validDir.get(randomManager.nextInt(validDir.size()));   
        return true;
    }
    
    public void CreateDungeonDistribution(int M,int N){        
        theDungeon.SetM(M);
        theDungeon.SetN(N);                
        M = theDungeon.GetM();
        N = theDungeon.GetN();
        dungeonAccess = new int[M][N];
        
        // Capaz podemos hacer que las conexiones agregen info a la matriz, como
        // el numero de conexiones. De esta manera podemos saber si es que es una habitacion.
        Coordinate currentPoint = new Coordinate(M,N);
        int x = 1;
        if(x%2==0) x++;
        int y = 9;
        if(y%2==0) y++;
        
        currentPoint.SetX(x);
        currentPoint.SetY(y);
        
        Stack<Coordinate> myStack = new Stack();
        
        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()] = 1;
        myStack.push(currentPoint.GetPoint());
        try {
            PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");                   
        
        
        while(!myStack.isEmpty()){
            currentPoint = myStack.peek();
            if(checkAdjCells(currentPoint)){
                
                // Marcar camino y a V
                advanceInDirection(currentPoint, currentDirections,1);   
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()] = 1;                                           
                advanceInDirection(currentPoint, currentDirections,1);   
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()] = 1;
                
                for(int i=0;i<M;i++)
                {
                    for(int j=0;j<N;j++)
                    {
                        if(dungeonAccess[i][j]==1) 
                        {
                             writer.print(" 0 ");
                        }else{
                             writer.print("   ");
                        }
                    }
                     writer.println(" ");
                }
                 writer.println("\n\n\n\n\n");
                                
                myStack.push(currentPoint.GetPoint());
            }else
            {
                myStack.pop();
            }
        }
        } catch (Exception e) {
            int a = 5;
        }
    }
}
