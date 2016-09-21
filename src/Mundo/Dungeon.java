/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Artefactos.Arma;
import Artefactos.Artefacto;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Controllers.ObjectGenerator;
import java.util.List;
import Models.Enemy;
import Controllers.EnemyGenerator;
import Foundation.DIRECTIONS;
import Foundation.ISavable;
import Models.Avatar;
import Models.Enemy;
import Models.IDibujable;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jauma
 */
public class Dungeon implements IDibujable, ISavable{

    private int M;
    private int N;

    private int minshowY;
    private int maxshowY;
    private int minshowX;
    private int maxshowX;

    private double prcEnemies;
    private int lvlEnemies;
    private double prcItems;

    private Coordinate antPos;
    private Coordinate sigPos;

    private int numEnemies;

    //private CellInformation dungeonStatus;    
    private Chamber[][] layOutChamber;
    private CellInformation[][] dungeonAccess;
    private ObjectGenerator objManager;

    private List<Enemy> lista_enemigos;
    private EnemyGenerator enemygen;

    public Dungeon(double varprcEnemies, int varlvlEnemies, double varPrcItems) {
        // Momentaneamente el Laberinto no posee dimensiones       
        M = 0;
        N = 0;
        SetLvlEnemies(varlvlEnemies);
        SetPrcEnemies(varprcEnemies);
        SetPrcItems(varPrcItems);
        objManager = new ObjectGenerator(varlvlEnemies);
        Scanner in = new Scanner(System.in);
        in.nextLine();
        enemygen = new EnemyGenerator();
        lista_enemigos = new ArrayList();
    }

    public int GetM() {
        return M;
    }

    // Recordar que las dimensiones deben ser impares
    final public void SetM(int varM) {
        if (varM % 2 == 0) {
            M = varM + 1;
        } else {
            M = varM;
        }
    }

    final public int GetN() {
        return N;
    }

    final public void SetN(int varN) {
        if (varN % 2 == 0) {
            N = varN + 1;
        } else {
            N = varN;
        }
    }

    final public double GetPrcEnemies() {
        return prcEnemies;
    }

    final public void SetPrcEnemies(double varPrcEnemies) {
        if (varPrcEnemies >= 1) {
            // Lanzamos exepcion, la cantiddad del porcentajes de enemigos
            // no puede ser mas del 100%
            throw new UnknownError();
        } else {
            prcEnemies = varPrcEnemies;
        }
    }

    final public int GetLvlEnemies() {
        return lvlEnemies;
    }

    final public double GetLvlEnemies(double worldLvl) {
        // Implementamos un nivel que dependiendo del nivel del mundo aumente
        // el nivel que le corresponde
        return lvlEnemies;
    }

    final public void SetLvlEnemies(int varLvlEnemies) {
        //Logica de validacion para nivel de Enemigos
        lvlEnemies = varLvlEnemies;
    }

    final public void SetPrcItems(double varPrcItems) {
        prcItems = varPrcItems;
    }

    public double GetPrcItem() {
        return prcItems;
    }

    public int GetNumEnemies() {
        return numEnemies;
    }

    public void SetNumEnemies(int varNumEnemies) {
        if (varNumEnemies > 0) {
            numEnemies = varNumEnemies;
        }
    }

    public CellInformation GetCellInformation(int x, int y) {
        return dungeonAccess[x][y];
    }

    public Coordinate GetAntPos() {
        return antPos;
    }

    public void SetAntPos(Coordinate varAntPos) {
        //Me entrega memoria
        antPos = varAntPos;
    }

    public Coordinate GetSigPos() {
        return sigPos;
    }

    public void SetSigPos(Coordinate varSigPos) {
        sigPos = varSigPos;
    }

    public void SetAccess(CellInformation access[][]) {
        dungeonAccess = access;
    }

    public Artefacto getObject(int x, int y) {
        dungeonAccess[x][y].SetType(CellInformation.CELLTYPE.ADENTRO);
        switch (dungeonAccess[x][y].GetObject()) {
            case WEAPON: {
                return objManager.generar_arma();
            }
            case ARMOR: {
                return objManager.generar_armadura();
            }
            case POTION: {
                return objManager.generar_pocion();
            }
            default: {
                return objManager.generar_pocion();
            }
        }
        // Deberiamos liberar la memoria de layout
    }

    private void inicializarDatosMostrarMapa(int posY, int posX, int tamShowX, int tamShowY) {
        tamShowX = (int) tamShowX / 2;
        tamShowY = (int) tamShowY / 2;

        if ((posY - tamShowY) > 0) {
            minshowY = posY - tamShowY;
        } else {
            minshowY = 0;
        }

        if ((posX - tamShowX) > 0) {
            minshowX = posX - tamShowX;
        } else {
            minshowX = 0;
        }

        if ((posY + tamShowY) < N) {
            maxshowY = posY + tamShowY;
        } else {
            maxshowY = N;
        }

        if ((posX + tamShowX) < M) {
            maxshowX = posX + tamShowX;
        } else {
            maxshowX = M;
        }
    }

    public void printDebugInfo() {
        System.out.format("Numero de enemigos = %d\n", numEnemies);
        System.out.format("Nivel de los enemigos = %d\n", lvlEnemies);
        System.out.print("Informacion de puntos:\nAnterior:\n");
        antPos.PrintCoordinate();
        System.out.print("Siguiente:\n");
        sigPos.PrintCoordinate();
        System.out.println("");
    }

    /// agregado ////}
    public void Interactuar(Avatar player, DIRECTIONS path) {
        int xFactor = player.GetX(), yFactor = player.GetY();
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
        player.AddArtefact(getObject(xFactor, yFactor));
    }

    public boolean Battle(Avatar player, DIRECTIONS path) {
        // Preg 2
        int xFactor = player.GetX(), yFactor = player.GetY();
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
        // #Pregunta 2
        int playerHp = player.GetVida();
        int playerMax = player.GetVidaMaxima();

        Enemy currentEnemy = layOutChamber[xFactor][yFactor].GetEnemy();
        int enemyHp = currentEnemy.GetVida();

        System.out.format("Comienza el Encuentro!\nNombre de Enemigo: %s\nDescripcion de Enemigo: %s\nAtaque: ( %d ATK)\n", currentEnemy.GetNombre(),
                currentEnemy.GetDescription(), currentEnemy.GetStrength());
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(player.GetNombre() + ": " + player.GetVida() + "/" + player.GetVidaMaxima() + " HP");
            System.out.println(currentEnemy.GetNombre() + ": " + currentEnemy.GetVida() + " HP");
            System.out.print("Opciones: atacar, huir, usar.\n");
            System.out.print("Comando: ");
            String input = in.nextLine();
            if (input.toLowerCase().startsWith("atacar")) {
                int weaponDamage = player.GetEquipWeaponDamage() - currentEnemy.GetArmor();
                int enemyDamage = currentEnemy.GetEnemyDamage() - player.GetEquipArmorProtection();
                if (weaponDamage < 0) {
                    weaponDamage = 0;
                }
                if (enemyDamage < 0) {
                    enemyDamage = 0;
                }
                System.out.print("Recibes " + enemyDamage + " puntos de daño.\n");
                player.ReciveDamage(enemyDamage);
                System.out.print("El enemigo recibe " + weaponDamage + " puntos de daño.\n");
                currentEnemy.ReciveDamage(weaponDamage);
            } else if (input.toLowerCase().startsWith("huir")) {
                System.out.print("Huiste exitosamente.\n");
                return true;
            } else if (input.toLowerCase().startsWith("usar")) {
                input = input.trim();
                int num_pos = input.lastIndexOf(' ') + 1, num;
                String numero = input.substring(num_pos);
                try {
                    num = Integer.parseInt(numero);
                } catch (NumberFormatException except) {
                    System.out.println("Indice no valido.");
                    continue;
                }
                if (num > 0) {
                    if (num <= player.getSizeSaco()) {
                        player.EquipItem(num - 1);
                    } else {
                        System.out.println("\nIndice no valido\n\n");
                        in.nextLine();
                    }
                } else {
                    System.out.println("\nIndice no valido\n\n");
                    in.nextLine();
                }
            } else {
                System.out.println("Acción inválida.");
            }
            if (player.GetVida() == 0) {
                return false;
            }
            if (currentEnemy.GetVida() == 0) {
                System.out.println("El enemigo ha sido derrotado.\n");
                dungeonAccess[xFactor][yFactor].SetType(CellInformation.CELLTYPE.ADENTRO);
                lista_enemigos.remove(currentEnemy);
                layOutChamber[xFactor][yFactor].GasEnemy();
                numEnemies--;
                return true;
            }
        }
    }
    
    public void KillInChamber(int xFactor,int yFactor, DIRECTIONS path)
    {        
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
        
        Enemy currentEnemy = layOutChamber[xFactor][yFactor].GetEnemy();
        dungeonAccess[xFactor][yFactor].SetType(CellInformation.CELLTYPE.ADENTRO);
        lista_enemigos.remove(currentEnemy);
        layOutChamber[xFactor][yFactor].GasEnemy();
        numEnemies--;
        
    }

    //# Preg 1
    public void MoveEnemies(int playerX, int playerY) {
        Enemy varEnemy;
        DIRECTIONS current;
        // #Pregunta  1 y 2
        for (Enemy currEnemy : lista_enemigos) {
            if (Math.random() <= 0.75) {
                // Aca pueden presentarse errores.... Pero en el tiempo limita dificil que salga perfecto                
                // Puede ser que no tenga donde moverse asi que se queda en el mismo lugar
                if ((current = currEnemy.RandomMove(dungeonAccess, 1, playerX, playerY)) != DIRECTIONS.STAY) {

                    // Primero elimino de dungeon acess y lo saco de su cuarto. Luego muevo el enemigo y lo pongo
                    // en una nueva locación
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].GasEnemy();
                    currEnemy.Move(current, 1);
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ENEMY);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
                }
            }
        }
    }

    // Preg 2
    public void SetUpChamber() {
        layOutChamber = new Chamber[M][N];
        for (int i = 0; i < M; i++) {
            // ACA ES DONDE CREO QUE SE CAE EL CODIGO!!!
            for (int j = 0; j < N; j++) {
                //Esta linea hace que se caiga. La Comentada
                //if(!dungeonAccess[i][j].isWal()) layOutChamber[i][j] = new Chamber();
                layOutChamber[i][j] = new Chamber();
            }
        }
        for (Enemy currEnemy : lista_enemigos) {
            layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
        }
    }

    public void TeleportPlayer(Avatar player, int x, int y) {
        player.SetPosition(x, y);
        Enemy currentEnemy = layOutChamber[x][y].GetEnemy();
        dungeonAccess[x][y].SetType(CellInformation.CELLTYPE.ADENTRO);
        lista_enemigos.remove(currentEnemy);
        layOutChamber[x][y].GasEnemy();
        numEnemies--;
    }

    
    
    
    // Implementacion de ISavable!
    @Override
    public void Save(FileWriter fw)
    {
            try {
            fw.write("" + this.M + "," + this.N + "," + this.minshowY + "," + this.maxshowY
                    + "," + this.minshowX + "," + this.maxshowX + "," + this.prcEnemies + ","
                    + this.lvlEnemies + "," + this.prcItems + "," + this.lista_enemigos.size() + "\r\n");
            
            for(int i= 0; i < this.lista_enemigos.size(); i++){
                this.lista_enemigos.get(i).Save(fw);
            }
            this.Render(fw);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
     public void Cargar_Render(FileReader lector, BufferedReader buffer){
        try {
            dungeonAccess = new CellInformation[M][N];
            String linea;
            //String space20 = new String(new char[40]).replace('\0', ' ');
            for (int j = 0; j < N; j++) {
                linea = buffer.readLine();
                String[] arr1 = linea.split(",");
               // System.out.print(space20);
                for (int i = 0; i < M; i++) {
                    CellInformation auxCelda = new CellInformation();
                    switch( linea.charAt(i) ){
                        case '-':
                            Coordinate auxAnt = new Coordinate(M,N);
                            auxAnt.SetX(i);
                            auxAnt.SetY(j);
                            antPos = auxAnt;
                            auxCelda.SetMode(CellInformation.CELLMODE.ANTERIOR);
                            break;
                        case '+':
                            Coordinate auxSig = new Coordinate(M,N);
                            auxSig.SetX(i);
                            auxSig.SetY(j);
                            sigPos = auxSig;
                            auxCelda.SetMode(CellInformation.CELLMODE.SIGUENTE);
                            break;
                        default:
                            auxCelda.SetMode(CellInformation.CELLMODE.NORMAL);
                            switch( linea.charAt(i) ){
                                case '#':
                                    auxCelda.SetType(CellInformation.CELLTYPE.PARED);
                                    break;
                                case 'A':
                                    auxCelda.SetType(CellInformation.CELLTYPE.ARTIFACT);
                                    break;
                                case 'E':
                                    auxCelda.SetType(CellInformation.CELLTYPE.ENEMY);
                                   break;
                                default :
                                    auxCelda.SetType(CellInformation.CELLTYPE.ADENTRO);
                                    break;
                            }
                            break;
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void Load(FileReader lector, BufferedReader buffer)
    {
        try {
            String linea = buffer.readLine();
            String[] arr1 = linea.split(",");
            this.M = Integer.parseInt(arr1[0]);
            this.N = Integer.parseInt(arr1[1]);
            this.minshowY = Integer.parseInt(arr1[2]);
            this.maxshowY = Integer.parseInt(arr1[3]);
            this.minshowX = Integer.parseInt(arr1[4]);
            this.maxshowX = Integer.parseInt(arr1[5]);
            this.prcEnemies = Double.parseDouble(arr1[6]);
            this.lvlEnemies = Integer.parseInt(arr1[7]);
            this.prcItems = Double.parseDouble(arr1[8]);
            int numEnemies = Integer.parseInt(arr1[9]);
            for(int i = 0; i < numEnemies; i++){
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //Coordinate varPosition, String nomb,
                //int vida, int lvl, int initStrength,
                //String varDescription, int varArmor
                Coordinate auxCoord = new Coordinate( M , N );
                auxCoord.SetX( Integer.parseInt(arr2[2]) );
                auxCoord.SetY( Integer.parseInt(arr2[3]) );
                Enemy auxEnemy = new Enemy( auxCoord, arr2[0],
                    Integer.parseInt(arr2[4]) , Integer.parseInt(arr2[5]),
                    Integer.parseInt(arr2[7]), arr2[1], Integer.parseInt(arr2[6]));
                lista_enemigos.add(auxEnemy);
            }
            this.SetUpChamber();
            this.Cargar_Render(lector,buffer);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }            
    }
    
    // Implementacion de IDibujable
    
    public void Render() {
        String space20 = new String(new char[40]).replace('\0', ' ');
        for (int j = 0; j < N; j++) {
            System.out.print(space20);
            for (int i = 0; i < M; i++) {
                CellInformation factor = dungeonAccess[i][j];
                switch (factor.GetMode()) {
                    case SIGUENTE:
                        System.out.print("+");
                        break;
                    case ANTERIOR:
                        System.out.print("-");
                        break;
                    default: {
                        switch (factor.GetType()) {
                            case PARED:
                                System.out.print("#");
                                break;
                            case ADENTRO:
                                System.out.print(" ");
                                break;
                            case ARTIFACT:
                                System.out.print("A");
                                break;
                            case ENEMY:
                                System.out.print("E");
                                break;
                        }
                    }
                    break;
                }
            }
            System.out.print("\n");
        }
    }
    
    // Escribe a un archivo de Texto
    public void Render(FileWriter fw) {
         try {
            //String space20 = new String(new char[40]).replace('\0', ' ');
            for (int j = 0; j < N; j++) {
               // System.out.print(space20);
                for (int i = 0; i < M; i++) {
                    CellInformation factor = dungeonAccess[i][j];
                    switch (factor.GetMode()) {
                        case SIGUENTE:
                           // System.out.print("+");
                            fw.write("+");
                            break;
                        case ANTERIOR:
                            //System.out.print("-");
                            fw.write("-");
                            break;
                        default: {
                            switch (factor.GetType()) {
                                case PARED:
                                    //System.out.print("#");
                                    fw.write("#");
                                    break;
                                case ADENTRO:
                                    //System.out.print(" ");
                                    fw.write(" ");
                                    break;
                                case ARTIFACT:
                                    //System.out.print("A");
                                    fw.write("A");
                                    break;
                                case ENEMY:
                                    //System.out.print("E");
                                    fw.write("E");
                                    break;
                            }
                        }
                        break;
                    }
                }
                //System.out.print("\n");
                fw.write("\r\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void Render(int posX, int posY, int tamShowX, int tamShowY) {

        String space20 = new String(new char[35]).replace('\0', ' ');
        inicializarDatosMostrarMapa(posY, posX, tamShowX, tamShowY);
        for (int j = minshowY; j < maxshowY; j++) {
            System.out.print(space20);
            for (int i = minshowX; i < maxshowX; i++) {

                if ((i == posX) && (j == posY)) {
                    System.out.print("H");
                } else {
                    CellInformation factor = dungeonAccess[i][j];
                    switch (factor.GetMode()) {
                        case SIGUENTE:
                            System.out.print("+");
                            break;
                        case ANTERIOR:
                            System.out.print("-");
                            break;
                        default: {
                            switch (factor.GetType()) {
                                case PARED:
                                    System.out.print("#");
                                    break;
                                case ADENTRO:
                                    System.out.print(" ");
                                    break;
                                case ARTIFACT:
                                    System.out.print("A");
                                    break;
                                case ENEMY:
                                    System.out.print("E");
                                    break;
                            }
                        }
                        break;
                    }
                }
            }
            System.out.println(" ");
        }
    }

    public void LoadComponents() {

    }

    public void Dispose() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dungeonAccess[i][j] = null;
                layOutChamber[i][j] = null;
            }
        }
    }

    //Modif
    public void addenemy(Coordinate pos) {
        Enemy enemigo = enemygen.generar_enemigo();
        enemigo.SetPosition(pos.GetPoint());
        lista_enemigos.add(enemigo);
    }

    public void Guardar_Render(FileWriter fw) {

        try {
            //String space20 = new String(new char[40]).replace('\0', ' ');
            for (int j = 0; j < N; j++) {
               // System.out.print(space20);
                for (int i = 0; i < M; i++) {
                    CellInformation factor = dungeonAccess[i][j];
                    switch (factor.GetMode()) {
                        case SIGUENTE:
                           // System.out.print("+");
                            fw.write("+");
                            break;
                        case ANTERIOR:
                            //System.out.print("-");
                            fw.write("-");
                            break;
                        default: {
                            switch (factor.GetType()) {
                                case PARED:
                                    //System.out.print("#");
                                    fw.write("#");
                                    break;
                                case ADENTRO:
                                    //System.out.print(" ");
                                    fw.write(" ");
                                    break;
                                case ARTIFACT:
                                    //System.out.print("A");
                                    fw.write("A");
                                    break;
                                case ENEMY:
                                    //System.out.print("E");
                                    fw.write("E");
                                    break;
                            }
                        }
                        break;
                    }
                }
                //System.out.print("\n");
                fw.write("\r\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardar(FileWriter fw) {
        try {

            fw.write("" + this.M + "," + this.N + "," + this.minshowY + "," + this.maxshowY
                    + "," + this.minshowX + "," + this.maxshowX + "," + this.prcEnemies + ","
                    + this.lvlEnemies + "," + this.prcItems + "," + this.lista_enemigos.size() + "\r\n");
            
            for(int i= 0; i < this.lista_enemigos.size(); i++){
                this.lista_enemigos.get(i).guardar_enemigo(fw);
            }
            this.Guardar_Render(fw);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
