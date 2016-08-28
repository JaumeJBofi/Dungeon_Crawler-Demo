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
import Models.Dungeon;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;

import Foundation.CellInformation;
import Foundation.CellInformation.CELLTYPE;
import Foundation.CellInformation.CELLMODE;



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

    public DungeonManager() {

        randomManager = new Random();
        dungeons = new ArrayList();
        activeDungeon = dungeons.size();
        currenCellInfo = new CellInformation();

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

    /// agregado ////}
    public void Interactuar(Coordinate point, DIRECTIONS path) {
        int xFactor = point.GetX(), yFactor = point.GetY();
        switch (path) {
            case BOT: {
                yFactor += 1;
            }
            break;
            case TOP: {
                yFactor -= 1;
            }
            break;
            case LEFT: {
                xFactor -= 1;
            }
            break;
            case RIGHT: {
                xFactor += 1;
            }
        }
        ///// deberia actualizar el access y borrar el objeto de la matriz
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

    public Coordinate CreateDungeonDistribution(int M, int N, double worldprcEnemies, double worldlvlEnemies) {
        Dungeon theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies);
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
        int x = randomManager.nextInt(M - 2) + 1;
        int y = randomManager.nextInt(N - 2) + 1;

        currentPoint.SetX(x);
        currentPoint.SetY(y);

        Coordinate playerPoint = currentPoint.GetPoint();

        Stack<Coordinate> myStack = new Stack();

        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);
        dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.ANTERIOR);

        myStack.push(currentPoint.GetPoint());
        boolean firstPop = true;
        int numCellAdentro = 1;
        while (!myStack.isEmpty()) {
            currentPoint = myStack.peek();
            if (checkAdjCells(currentPoint)) {

                // Marcar camino y a V
                advanceInDirection(currentPoint, currentDirections, 1);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);

                advanceInDirection(currentPoint, currentDirections, 1);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);


                // agregado
                numCellAdentro += 2;
                // ////
                myStack.push(currentPoint.GetPoint());
            } else {
                if (firstPop) {
                    dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.SIGUENTE);
                    firstPop = false;
                }
                myStack.pop();
            }

        }

        /// agregado ////
        int numEnemies = 0;
        int out = 0;
        float porcEnemy = (float)(worldprcEnemies * 100) ;
        int porc =Math.round(porcEnemy);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!(dungeonAccess[i][j].isWall())) {
                    int randomObject = randomManager.nextInt(porc);
                    switch (randomObject) {
                        case 0: {
                            dungeonAccess[i][j].SetObject(CellInformation.CELLOBJECT.ENEMY);
                            break;
                        }
                        default: {
                            randomObject = randomManager.nextInt(3);
                            switch (randomObject) {
                                case 0: {
                                    dungeonAccess[i][j].SetObject(CellInformation.CELLOBJECT.EMPTY);
                                    break;
                                }
                                case 1: {
                                    dungeonAccess[i][j].SetObject(CellInformation.CELLOBJECT.POTION);
                                    break;
                                }
                                case 2: {
                                    dungeonAccess[i][j].SetObject(CellInformation.CELLOBJECT.WEAPON);
                                    break;
                                }
                            }
                        }
                    }
                }

                numEnemies++;
            }
        }

        //// 
        dungeons.add(theDungeon);
        theDungeon.SetAccess(dungeonAccess);

        return playerPoint;
    }
}
