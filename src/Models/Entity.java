/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.CellInformation;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;
import Foundation.ISavable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

/**
 *
 * @author Jauma
 */
public abstract class Entity implements ISavable {

    private Coordinate position;
    public int hp; // vida actual
    private String nombre;
    public static Random generator;
    protected int tamShowX;
    protected int tamShowY;
    private DIRECTIONS lookDirection;
    private int strength;
    private int base_armor;
    protected int nivel;

    //Modif
    public Entity(Coordinate varPosition, String nomb, int vida, int varStrength, int varArmor,int _nivel) {
        hp = vida;
        position = varPosition;
        generator = new Random();
        nombre = nomb;
        strength = varStrength;
        base_armor = varArmor;
        nivel = _nivel;
    }

    public int GetArmor() {
        return base_armor;
    }

    final public int GetStrength() {
        return strength;
    }

    final public void SetStrength(int varStrength) {
        strength = varStrength;
    }

    public Coordinate GetPosition() {
        return position.GetPoint();
    }

    public void SetPosition(int x, int y) {
        position.SetX(x);
        position.SetY(y);
    }

    public void SetPosition(Coordinate newCoordinate) {
        position = newCoordinate;
    }

    public int GetX() {
        return position.GetX();
    }

    final public void SetArmor(int varArmor) {
        base_armor = varArmor;
    }

    public int GetY() {
        return position.GetY();
    }

    public void SetX(int varX) {
        position.SetX(varX);
    }

    public void SetY(int varY) {
        position.SetY(varY);
    }

    public void SetVida(int v) {
    }

    public int GetVida() {
        return hp;
    }
    
    final public void SetNivel(int n) {
        nivel = n;
        SetStrength(GetStrength() * nivel);
    }

    public int GetNivel() {
        return nivel;
    }

    final public void SetNombre(String nombre) {
        this.nombre = nombre;
    }

    public String GetNombre() {
        return nombre;
    }

    public DIRECTIONS GetLookDirection() {
        return lookDirection;
    }

    final public void SetLookDirection(DIRECTIONS varDirection) {
        lookDirection = varDirection;
    }

    //Pregunta 2
    public void ReciveDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void Move(DIRECTIONS way, int steps) {
        try {
            int xFactor = 0, yFactor = 0;
            switch (way) {
                case BOT: {
                    yFactor += steps;
                    break;
                }
                
                case TOP: {
                    yFactor -= steps;
                    break;
                }
                
                case LEFT: {
                    xFactor -= steps;
                    break;
                }
                
                case RIGHT: {
                    xFactor += steps;
                    break;
                }
            }
            position.SetX(position.GetX() + xFactor);
            position.SetY(position.GetY() + yFactor);
        } catch (IndexOutOfBoundsException e) {
           // System.err.println("Move out of bounds");
            System.exit(1);
        }
    }

    public void ThinkMove(Coordinate coord, DIRECTIONS dir, int steps) {
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
            break;
        }
        coord.SetX(coord.GetX() + xFactor);
        coord.SetY(coord.GetY() + yFactor);
    }

    public DIRECTIONS RandomMoveInteligente(CellInformation[][] dungeonAccess, int steps, int playerX, int playerY,int M, int N) {
        List<DIRECTIONS> validDir = new ArrayList();
        Coordinate varPointX  = new Coordinate(M,N);
        Coordinate varPointY  = new Coordinate(M,N);        
        varPointX.SetX(position.GetX());
        varPointX.SetY(position.GetY());
        varPointY.SetX(position.GetX());
        varPointY.SetY(position.GetY());

        int x_location = varPointX.GetX() - playerX;
        int y_location = varPointX.GetY() - playerY;
        if (x_location >= 0) {
            // el enemigo esta en un X mayor al del heroe
            ThinkMove(varPointX, DIRECTIONS.LEFT, steps);
            if ((varPointX.InRange() ) && 
                (dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                     && (!varPointX.IsEqual(playerX, playerY))) {
                validDir.add(DIRECTIONS.LEFT);
            }
            if (y_location >= 0) {
                // segundo cuadrante 

                ThinkMove(varPointY, DIRECTIONS.TOP, steps);
                if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
                    validDir.add(DIRECTIONS.TOP);
                }
            } else {
                // tercer cuadrante
                ThinkMove(varPointY, DIRECTIONS.BOT, steps);
                if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
                    validDir.add(DIRECTIONS.BOT);
                }

            }
        } else {
            ThinkMove(varPointX, DIRECTIONS.RIGHT, steps);
            if ((varPointX.InRange() ) && 
                (dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                     && (!varPointX.IsEqual(playerX, playerY))) {
                validDir.add(DIRECTIONS.RIGHT);
            }
            if (y_location >= 0) {
                // primer cuadrante
                ThinkMove(varPointY, DIRECTIONS.TOP, steps);
                if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
                    validDir.add(DIRECTIONS.TOP);
                }
            } else {
                //cuarto cuadrante
                ThinkMove(varPointY, DIRECTIONS.BOT, steps);
                if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
                    validDir.add(DIRECTIONS.BOT);
                }
            }
        }


        if (validDir.isEmpty()) {
            return DIRECTIONS.STAY;
        }
        if (validDir.contains(lookDirection)) {
            // Feo pero es lo mas facil de hacer. Agregar objetos para aumentar
            // la probabilidad
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            //Collections.shuffle(validDir, new Random());
        }
        return validDir.get(generator.nextInt(validDir.size()));
    }

    public DIRECTIONS RandomMove(CellInformation[][] dungeonAccess, int steps, int playerX, int playerY, int M, int N) {
        List<DIRECTIONS> validDir = new ArrayList();
        Coordinate varPointX  = new Coordinate(M,N);
        Coordinate varPointY  = new Coordinate(M,N);
        varPointX.SetX(position.GetPoint().GetX());
        varPointX.SetY(position.GetPoint().GetY());
        varPointY.SetX(position.GetPoint().GetX());
        varPointY.SetY(position.GetPoint().GetY());

        ThinkMove(varPointX, DIRECTIONS.LEFT, steps);
        if ((varPointX.InRange() ) && 
            (dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO) 
                && (!varPointX.IsEqual(playerX, playerY)) ) {
            validDir.add(DIRECTIONS.LEFT);
        }

        ThinkMove(varPointX, DIRECTIONS.RIGHT, steps * 2);
        if ((varPointX.InRange() ) && 
            (dungeonAccess[varPointX.GetX()][varPointX.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO) 
                && (!varPointX.IsEqual(playerX, playerY))) {
            validDir.add(DIRECTIONS.RIGHT);
        }

        ThinkMove(varPointY, DIRECTIONS.TOP, steps);
        if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
            validDir.add(DIRECTIONS.TOP);
        }

        ThinkMove(varPointY, DIRECTIONS.BOT, steps * 2);
        if ((varPointY.InRange() ) && 
                (dungeonAccess[varPointY.GetX()][varPointY.GetY()].GetType() == CellInformation.CELLTYPE.ADENTRO)
                         && (!varPointY.IsEqual(playerX, playerY))) {
            validDir.add(DIRECTIONS.BOT);
        }

        if (validDir.isEmpty()) {
            return DIRECTIONS.STAY;
        }
        if (validDir.contains(lookDirection)) {
            // Feo pero es lo mas facil de hacer. Agregar objetos para aumentar
            // la probabilidad
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            validDir.add(lookDirection);
            //Collections.shuffle(validDir, new Random());
        }
        return validDir.get(generator.nextInt(validDir.size()));
    }

    public abstract void Save(FileWriter fr);

    public abstract void Load(FileReader flectura, BufferedReader buffer);
}
