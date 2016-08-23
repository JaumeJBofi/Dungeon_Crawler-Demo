/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Foundation.CellInformation;
import Models.Dungeon;
import java.util.AbstractMap;
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
    
    
    public DungeonManager(int varM,int varN,double worldprcEnemies,double worldlvlEnemies,CellInformation.CELLMODE mode,CellInformation.CELLTYPE type){
        theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies, mode, type);
        randomManager = new Random();
    }
    
    private boolean inRange(int i,int j,int M,int N)
    {
        return ((i>=0&&i<M)&&(j>=0&&j<N));
    }
    
    private boolean checkAdjCells(int i,int j,int M,int N){        
        int[] a = {-1,1};
        
        List <ManagerBase.DIRECTIONS> validDir = new ArrayList();
       
        int factor = -1;
        if(inRange(i, j+factor, M, N)) validDir.add(ManagerBase.DIRECTIONS.TOP);
        if(inRange(i+factor, j, M, N)) validDir.add(ManagerBase.DIRECTIONS.LEFT);                        
        
        factor = 1;
        if(inRange(i, j+factor, M, N)) validDir.add(ManagerBase.DIRECTIONS.BOT);
        if(inRange(i+factor, j, M, N)) validDir.add(ManagerBase.DIRECTIONS.RIGHT);
        
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
        AbstractMap.SimpleEntry<Integer,Integer> currentPair = new AbstractMap.SimpleEntry(randomManager.nextInt(M),randomManager.nextInt(N));
        AbstractMap.SimpleEntry<Integer,Integer> nextPair;
        Stack<AbstractMap.SimpleEntry<Integer,Integer>> myStack = new Stack();
        
        dungeonAccess[currentPair.getKey()][currentPair.getValue()] = 1;
        myStack.push(currentPair);
        
        while(!myStack.isEmpty()){
            currentPair = myStack.peek();
            if(checkAdjCells(currentPair.getKey(),currentPair.getValue(),theDungeon.GetM(),theDungeon.GetN())){
                
                // Marcar camino y a V
                nextPair = advanceInDirection(currentPair, currentDirections,1);   
                dungeonAccess[nextPair.getKey()][nextPair.getValue()] = 1;                                           
                nextPair = advanceInDirection(currentPair, currentDirections,2);   
                dungeonAccess[nextPair.getKey()][nextPair.getValue()] = 1;
                                
                myStack.push(nextPair);
            }else
            {
                myStack.pop();
            }
        }
    }
}
