/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.lang.Math ; // include solo por round
import Models.Avatar;



import Models.Dungeon;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;

import Foundation.CellInformation;
import Foundation.CellInformation.CELLTYPE;
import Foundation.CellInformation.CELLMODE;

/**
 *
 * @author Jauma
 */

// Puede tambien referirse al mundo del Juego.
public class DungeonManager {

    private CellInformation[][] dungeonAccess;
    private CellInformation currenCellInfo;

    private List<Dungeon> dungeons;
    private DIRECTIONS currentDirections;
    final private Random randomManager;
    private int activeDungeon;
    
    private int totalDungeons;

    public DungeonManager(int varTotalDungeons) {

        randomManager = new Random();
        dungeons = new ArrayList();
        activeDungeon = dungeons.size();
        currenCellInfo = new CellInformation();
        totalDungeons = varTotalDungeons;

    }

    //Podemos usar el mismo point.
    private boolean inRange(int i, int j, int M, int N) {
        return ((i > 0 && i < M) && (j > 0 && j < N));
    }

    public boolean SetActiveDungeon(int n) {
        if (dungeons.size() <= n) {
            return false;
        }
        activeDungeon = n;
        return true;
    }

    public CellInformation ValidMoveAndChange(Coordinate point, DIRECTIONS path) {
        advanceInDirection(point, path, 1);
        if (point.InRange()) {
            return dungeons.get(activeDungeon).GetCellInformation(point.GetX(), point.GetY());
        } else {
            currenCellInfo.SetType(CELLTYPE.PARED);
            return currenCellInfo;
        }
    }


    ////
    public Dungeon GetActiveDungeon() {
        return dungeons.get(activeDungeon);
    }

    private boolean checkAdjCells(Coordinate point) {
        List<DIRECTIONS> validDir = new ArrayList();
        Coordinate varPointX = point.GetPoint();
        Coordinate varPointY = point.GetPoint();

        advanceInDirection(varPointX, DIRECTIONS.TOP, 2);
        if (varPointX.InRange() && dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()) {
            validDir.add(DIRECTIONS.TOP);
        }

        advanceInDirection(varPointX, DIRECTIONS.BOT, 4);
        if (varPointX.InRange() && dungeonAccess[varPointX.GetX()][varPointX.GetY()].isWall()) {
            validDir.add(DIRECTIONS.BOT);
        }

        advanceInDirection(varPointY, DIRECTIONS.LEFT, 2);
        if (varPointY.InRange() && dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()) {
            validDir.add(DIRECTIONS.LEFT);
        }

        advanceInDirection(varPointY, DIRECTIONS.RIGHT, 4);
        if (varPointY.InRange() && dungeonAccess[varPointY.GetX()][varPointY.GetY()].isWall()) {
            validDir.add(DIRECTIONS.RIGHT);
        }

        if (validDir.isEmpty()) {
            return false;
        }

        currentDirections = validDir.get(randomManager.nextInt(validDir.size()));
        return true;
    }

    public void advanceInDirection(Coordinate coord, DIRECTIONS dir, int steps) {
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
    
    
    public int ChangeDungeon(Avatar player,boolean isNext){
        if(isNext)
        {
            if(activeDungeon==totalDungeons-1) {
                return 1;
                
            }
            Dungeon currDungeon = dungeons.get(activeDungeon);
            CreateDungeonDistribution(randomManager.nextInt(50-25)+25,randomManager.nextInt(50-25)+25,currDungeon.GetPrcEnemies()+0.075,currDungeon.GetLvlEnemies(),
                    currDungeon.GetPrcItem()+0.025);
            activeDungeon++;
            player.SetPosition(dungeons.get(activeDungeon).GetAntPos().GetPoint());
        }else
        {
            if(activeDungeon==0) return 0;
            activeDungeon--;
            player.SetPosition(dungeons.get(activeDungeon).GetSigPos().GetPoint());           
        }        
        return 0;
    }
    
    public void printDebugInfo(Avatar player)
    {
        System.out.println("Informacion manager actual:\n");
        System.out.format("Numero de dungeon activo: %d\n",activeDungeon);
        dungeons.get(activeDungeon).Render();
        System.out.println("Informacion laberinto actual:\n");
        dungeons.get(activeDungeon).printDebugInfo();
        System.out.println("Informacion de Jugador actual:\n");
        player.GetPosition().PrintCoordinate();
        System.out.println("");
    }

    public Coordinate CreateDungeonDistribution(int M, int N, double worldprcEnemies, int worldlvlEnemies,double varprcItems) {
        Dungeon theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies,varprcItems);
        theDungeon.SetM(M);
        theDungeon.SetN(N);
        M = theDungeon.GetM();
        N = theDungeon.GetN();

        dungeonAccess = new CellInformation[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dungeonAccess[i][j] = new CellInformation();
            }
        }

        // Capaz podemos hacer que las conexiones agregen info a la matriz, como
        // el numero de conexiones. De esta manera podemos saber si es que es una habitacion.
        Coordinate currentPoint = new Coordinate(M, N);
        int x = randomManager.nextInt(M - 3) + 1;
        if(x%2==0) x++;
        int y = randomManager.nextInt(N - 2) + 1;
        if(y%2==0) y++;

        currentPoint.SetX(x);
        currentPoint.SetY(y);

        Coordinate playerPoint = currentPoint.GetPoint();

        Stack<Coordinate> myStack = new Stack();

        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);
        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.ANTERIOR);
        
        //Seteo la posicion del Anterior punto
        theDungeon.SetAntPos(currentPoint.GetPoint());
        
        myStack.push(currentPoint.GetPoint());
        boolean firstPop = true;
        int numCellAdentro = 1;
        int numEnemies = 0;
        
        while (!myStack.isEmpty()) {
            currentPoint = myStack.peek();
            if (checkAdjCells(currentPoint)) {

                // Marcar camino y a V
                advanceInDirection(currentPoint, currentDirections, 1);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);
                
                if(Math.random()<=theDungeon.GetPrcEnemies()){
                    dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CellInformation.CELLTYPE.ENEMY);
                    /////////////////////////    MI LINEA :v   ////////////////////////7
                    theDungeon.addenemy(currentPoint);
                    numEnemies++;
                }else
                {
                    if(Math.random()<=theDungeon.GetPrcItem()){
                        int randomObject = randomManager.nextInt(2);
                            switch (randomObject) {                          
                            case 0: {
                                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetObject(CellInformation.CELLOBJECT.POTION);
                                break;
                            }
                            case 1: {
                                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetObject(CellInformation.CELLOBJECT.WEAPON);
                                break;
                            }                        
                        }
                    dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ARTIFACT);
                    }else{
                        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetObject(CellInformation.CELLOBJECT.EMPTY);
                    }
                }   
                advanceInDirection(currentPoint, currentDirections, 1);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);     
                myStack.push(currentPoint.GetPoint());
            } else {
                if (firstPop) {
                    dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.SIGUENTE);
                    theDungeon.SetSigPos(currentPoint.GetPoint());
                    firstPop = false;
                }
                myStack.pop();
            }
        }       
        theDungeon.SetNumEnemies(numEnemies);
        dungeons.add(theDungeon);
        theDungeon.SetAccess(dungeonAccess);

        return playerPoint;
    }
}
