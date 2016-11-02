/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.lang.Math; // include solo por round
import Models.Avatar;
import Facilidades.Aliado;

import java.io.FileWriter;
import java.io.IOException;

import Foundation.Coordinate;
import Foundation.DIRECTIONS;

import Foundation.CellInformation;
import Foundation.CellInformation.CELLTYPE;
import Foundation.CellInformation.CELLMODE;
import Foundation.ISavable;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Jauma
 */
// Puede tambien referirse al mundo del Juego.
public class DungeonManager implements ISavable {

    private CellInformation[][] dungeonAccess;
    private CellInformation currenCellInfo;

    // SuperFeo
    private volatile List<Dungeon> dungeons;
    private Dungeon textDungeon;
    private DIRECTIONS currentDirections;
    final private Random randomManager;
    private int activeDungeon;
    private int activePlayer;
    
    private int WIDTH;
    private int HEIGHT;
    

    private int totalDungeons;

    public DungeonManager(int varTotalDungeons,int _width,int _height) {

        randomManager = new Random();
        dungeons = new ArrayList();
        activeDungeon = dungeons.size();
        currenCellInfo = new CellInformation();
        totalDungeons = varTotalDungeons;
        WIDTH = _width;
        HEIGHT = _height;
    }

    public int GetTotalDungeons() {
        return totalDungeons;
    }

    public void SetTotalDungeons(int varTotalDungeons) {
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
            dungeons.get(activeDungeon).GetCellInformation(point.GetX(), point.GetY()).position = point;
            return dungeons.get(activeDungeon).GetCellInformation(point.GetX(), point.GetY());
        } else {
            currenCellInfo.SetType(CELLTYPE.PARED);
            currenCellInfo.position = point;
            return currenCellInfo;
        }
    }

    ////
    public Dungeon GetActiveDungeon() {
        return dungeons.get(activeDungeon);
    }

    public int GetActiveDungeonIndex() {
        return activeDungeon;
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

    public int ChangeDungeon(Avatar player, boolean isNext) {
        if (isNext) {
            if (activeDungeon == totalDungeons - 1) {
                return 1;
            }
            if (activeDungeon < dungeons.size() - 1) {
                activeDungeon++;
            } else {
                Dungeon currDungeon = dungeons.get(activeDungeon); //si no es el ultimo calabozo, crea otro cala
                CreateDungeonDistribution(randomManager.nextInt(50 - 25) + 25, randomManager.nextInt(50 - 25) + 25, currDungeon.GetPrcEnemies() + 0.075, currDungeon.GetLvlEnemies(),
                        currDungeon.GetPrcItem() + 0.025,player.GetNivel(),player.GetTamShowX(),player.GetTamShowY());                
                activeDungeon++;
                currDungeon.AddPlayer(player);
                GetActiveDungeon().SetDungeonNumber(activeDungeon);
                player.SetPosition(dungeons.get(activeDungeon).GetAntPos().GetPoint());
            }
        } else {
            if (activeDungeon == 0) {
                return 0;
            }
            activeDungeon--;
            player.SetPosition(dungeons.get(activeDungeon).GetSigPos().GetPoint());
        }
        return 0;
    }

    public void printDebugInfo(Avatar player) {
        System.out.println("Informacion manager actual:\n");
        System.out.format("Numero de dungeon activo: %d\n", activeDungeon);        
        System.out.println("Informacion laberinto actual:\n");
        dungeons.get(activeDungeon).printDebugInfo();
        System.out.println("Informacion de Jugador actual:\n");
        player.GetPosition().PrintCoordinate();
        System.out.println("");
    }
    public CELLTYPE GetRandomType(double prcEnemy)
    {
        if(Math.random() <= prcEnemy)
        {
            return CELLTYPE.ENEMY;
        }
        
        // Aca vemos la mayor probabilidad de los artefactos
        int typeGet = randomManager.nextInt(8);
        
        switch(typeGet)
        {
            case 0: return CELLTYPE.FRIEND;  
            case 1:return CELLTYPE.ARTIFACT;            
            case 2:return CELLTYPE.ARTIFACT;
            default: return CELLTYPE.ADENTRO;
        }        
    }        
    //Preg 1 Lab2
    public Coordinate CreateDungeonDistribution(int M, int N, double worldprcEnemies, int worldlvlEnemies, double varprcItems,int playerLvl,int tamShowX,int tamShowY) {
        Dungeon theDungeon = new Dungeon(worldprcEnemies, worldlvlEnemies, varprcItems,M,N,WIDTH,HEIGHT,tamShowX,tamShowY);        
        theDungeon.SetDungeonNumber(activeDungeon);
        
        // Inicializacion..
        
        M = theDungeon.GetM();
        N = theDungeon.GetN();

        dungeonAccess = new CellInformation[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dungeonAccess[i][j] = new CellInformation();
                dungeonAccess[i][j].SetType(CELLTYPE.PARED);
            }
        }        
        theDungeon.SetAccess(dungeonAccess);
        // Capaz podemos hacer que las conexiones agregen info a la matriz, como
        // el numero de conexiones. De esta manera podemos saber si es que es una habitacion.
        Coordinate currentPoint = new Coordinate(M, N);
        int x = randomManager.nextInt(M - 3) + 1;
        if (x % 2 == 0) {
            x++;
        }
        int y = randomManager.nextInt(N - 3) + 1;
        if (y % 2 == 0) {
            y++;
        }

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
        
        int numEnemies = 0;
        int numFriends = 0;
                
        while (!myStack.isEmpty()) {
            currentPoint = myStack.peek();
            if (checkAdjCells(currentPoint)) {

                // Marcar camino y a V
                advanceInDirection(currentPoint, currentDirections, 1);
                theDungeon.SetEntityInChamber(GetRandomType(theDungeon.GetPrcEnemies()),currentPoint,playerLvl);   
                //dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.NORMAL);
                
                advanceInDirection(currentPoint, currentDirections, 1);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetType(CELLTYPE.ADENTRO);
                dungeonAccess[currentPoint.GetX()][currentPoint.GetY()].SetMode(CELLMODE.NORMAL);
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
        theDungeon.SetUpChamber();        
        return playerPoint;
    }

    @Override
    public void Save(FileWriter fw) {
        try {
            fw.write("" + this.totalDungeons + "," + this.activeDungeon + "," + this.dungeons.size() + "\r\n");
            for (int i = 0; i < dungeons.size(); i++) {
                dungeons.get(i).Save(fw);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Load(FileReader lector, BufferedReader buffer) {
        try {
            String linea = buffer.readLine();
            String[] arr1 = linea.split(",");

            totalDungeons = Integer.parseInt(arr1[0]);
            activeDungeon = Integer.parseInt(arr1[1]);
            int sizeDungeon = Integer.parseInt(arr1[2]);
            for (int i = 0; i < sizeDungeon; i++) {
                //Dungeon auxDungeon = new Dungeon(0, 0, 0,1,1,1,1);
                //auxDungeon.Load(lector, buffer);
                //dungeons.add(auxDungeon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Guardar_dungeons(FileWriter fw) {
        try {
            fw.write("" + this.totalDungeons + "," + this.activeDungeon + "," + this.dungeons.size() + "\r\n");
            for (int i = 0; i < dungeons.size(); i++) {
                dungeons.get(i).guardar(fw);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
