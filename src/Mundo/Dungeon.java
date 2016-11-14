/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Artefactos.Arma;
import Artefactos.Armadura;
import Artefactos.Artefacto;
import Artefactos.Pocion;
import Controllers.AllyGenerator;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Controllers.ObjectGenerator;
import java.util.List;
import Controllers.EnemyGenerator;
import Foundation.DIRECTIONS;
import Foundation.ISavable;
import Facilidades.Aliado;
import Foundation.ObjectConverter;
import Models.Avatar;
import Models.Enemy;
import Models.IDibujable;
import Models.Player;
import Models.Sprite;
import java.util.ArrayList;
import java.util.Scanner;
import Foundation.Options;
import Models.Stage;
import static Controllers.Game.alternativas;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
<<<<<<< HEAD
import java.util.Timer;

import javax.swing.*;

=======

import javax.swing.*;
>>>>>>> origin/master

import java.io.BufferedReader;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

<<<<<<< HEAD

// Graphics imports begin

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.TimerTask;
import javafx.util.Pair;
=======
// Graphics imports begin
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
>>>>>>> origin/master

// Graphics imports end
/**
 *
 * @author Jauma
 */
public class Dungeon implements IDibujable, ISavable {

    private int M;
    private int N;
    private int tileSizeX;
    private int tileSizeY;
    private int visionTileSizeX;
    private int visionTileSizeY;
<<<<<<< HEAD
    
=======

>>>>>>> origin/master
    private int dungeonNumber;

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
    private int numAliados;
<<<<<<< HEAD
    private int numArtifacts;    
    private int activePlayer;
    
    private Timer myTime;
    public volatile int TimeCount = 0;
       
=======
    private int numArtifacts;
    private int activePlayer;

>>>>>>> origin/master
    //private CellInformation dungeonStatus;    
    private Chamber[][] layOutChamber;
    private CellInformation[][] dungeonAccess;
    private ObjectGenerator objManager;

    private List<Enemy> lista_enemigos;
    private List<Aliado> lista_aliados;
    private List<Artefacto> lista_artefactos;
<<<<<<< HEAD
    
    private List<Player> lista_Players;
    
=======

    private List<Player> lista_Players;

>>>>>>> origin/master
    private EnemyGenerator enemygen;
    private AllyGenerator allyGen;
    
    private HashMap<String,Sprite> tilesSpriteBase;
    
    public long tStart;

<<<<<<< HEAD
    public long tEnd;
    public long tDelta;
    public double elapsedSeconds;

    public Dungeon(double varprcEnemies, int varlvlEnemies, double varPrcItems,int _M,int _N,int WIDTH,int HEIGHT,int tamShowX,int tamShowY) {
=======
<<<<<<< HEAD
    public Dungeon(double varprcEnemies, int varlvlEnemies, double varPrcItems,int _M,int _N,int WIDTH,int HEIGHT,int tamShowX,int tamShowY) {
=======
    private HashMap<CellInformation.CELLMODE, String> tilesInfo;

    public Dungeon(double varprcEnemies, int varlvlEnemies, double varPrcItems, int _M, int _N, int WIDTH, int HEIGHT) {
>>>>>>> origin/master
>>>>>>> origin/master
        // Momentaneamente el Laberinto no posee dimensiones       
        M = 0;
        N = 0;
        long tStart = System.currentTimeMillis();    

        myTime = new Timer();
    
        
        myTime.schedule(new TimerTask() {
            @Override
            public void run() {                
                TimeCount++;
            }
        }, 0, 1000);
                
        SetLvlEnemies(varlvlEnemies);
        SetPrcEnemies(varprcEnemies);
        SetPrcItems(varPrcItems);
<<<<<<< HEAD
        SetUpMapSize(_M,_N,WIDTH,HEIGHT,tamShowX,tamShowY);
        
        objManager = new ObjectGenerator(varlvlEnemies,tileSizeX,tileSizeY);
=======
<<<<<<< HEAD
        SetUpMapSize(_M,_N,WIDTH,HEIGHT,tamShowX,tamShowY);
        
        objManager = new ObjectGenerator(varlvlEnemies,tileSizeX,tileSizeY);
=======
        SetUpMapSize(_M, _N, WIDTH, HEIGHT);

        objManager = new ObjectGenerator(varlvlEnemies, tileSizeX, tileSizeY);
>>>>>>> origin/master
>>>>>>> origin/master
        Scanner in = new Scanner(System.in);

        enemygen = new EnemyGenerator(tileSizeX,tileSizeY);
        lista_enemigos = new ArrayList();
        numEnemies = 0;

        // Ponemos memoria en allyGenerator cuando se nos pone el numero de nuestro Dungeon        
        lista_aliados = new ArrayList();
        numAliados = 0;

        // Lista iría aca
        lista_artefactos = new ArrayList<>();
        numArtifacts = 0;
<<<<<<< HEAD
        
        lista_Players = new ArrayList<>();    
        tilesSpriteBase = new HashMap<>();
=======

        lista_Players = new ArrayList<>();
        tilesInfo = new HashMap<>();
>>>>>>> origin/master
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
    
    public void AddPlayer(Player p)
    {
        lista_Players.add(p);
        // Dibujamos el ultimo agregado (Podemos setearlo y desahabilitar este comportamiento (Lo cambiamos cada vez
        // que cambiamos el contexto del laberinto
        activePlayer = lista_Players.size() - 1;
    }
    
    public void SetActivePlayer(int i)
    {
        activePlayer = i;
    }
    
    public int GetActivePlayer()
    {
        return activePlayer;
    }

    public void AddPlayer(Player p) {
        lista_Players.add(p);
        // Dibujamos el ultimo agregado (Podemos setearlo y desahabilitar este comportamiento (Lo cambiamos cada vez
        // que cambiamos el contexto del laberinto
        activePlayer = lista_Players.size() - 1;
    }

    public void SetActivePlayer(int i) {
        activePlayer = i;
    }

    public int GetActivePlayer() {
        return activePlayer;
    }

    public void SetDungeonNumber(int varNum) {
<<<<<<< HEAD
        
        allyGen = new AllyGenerator("Allies_" + Integer.toString(varNum),tileSizeX,tileSizeY);
        dungeonNumber = varNum;   
        LoadTiles(varNum);                
=======

        allyGen = new AllyGenerator("Allies_" + Integer.toString(varNum));
        dungeonNumber = varNum;
        LoadTiles(varNum);
    }

    public void LoadTiles(int varNum) {
        // Cargamos un txt por cada laberinto en que carga Sprites al Main Hash
        // dependiendo del los 4 tipos de Modos (PARED;NORMAL:ANTERIOR ;SIGUIENTE)
        try {
            FileReader fr = new FileReader("Dungeon_" + Integer.toString(varNum) + ".txt");
            BufferedReader in = new BufferedReader(fr);
            String buffer;
            while ((buffer = in.readLine()) != null) {
                ObjectConverter ob = new ObjectConverter(buffer);
                ob.SetDelimiter("#");

                String spriteInfo;

                tilesInfo.put(ob.GetNextPartMODE(), (spriteInfo = ob.GetNextPart()));
                new Sprite().ProcessSpriteInfo(spriteInfo, true);
            }
        } catch (Exception e) {
            System.err.println("Error Archivo: " + "Dungeon_" + Integer.toString(varNum) + ".txt" + " no encontrado");
        }
>>>>>>> origin/master
    }
    
    public void LoadTiles(int varNum)
    {
        // Cargamos un txt por cada laberinto en que carga Sprites al Main Hash
        // dependiendo del los 4 tipos de Modos (PARED;NORMAL:ANTERIOR ;SIGUIENTE)
         try {            
            FileReader fr = new FileReader("Dungeon_" + Integer.toString(varNum) + ".txt");
            BufferedReader in = new BufferedReader(fr);                                              
            String buffer;
            while((buffer = in.readLine())!=null)
            {
                ObjectConverter ob = new ObjectConverter(buffer);
                ob.SetDelimiter("#");
                                                
                String tileName = ob.GetNextPart();
                String spriteInfo = ob.GetNextPart();
                Sprite tileSprite = new Sprite();
                tileSprite.ProcessSpriteInfo(spriteInfo, true);
                tilesSpriteBase.put(tileName,tileSprite);                    
            }                     
        } catch (Exception e) {
            System.err.println("Error Archivo: " + "Dungeon_" + Integer.toString(varNum) + ".txt" + " no encontrado");
        }                                
    }
    
    public int GetDungeonNumber() {
        return dungeonNumber;
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

    public void SetNumArtifacts(int varNumArtifacts) {
        if (varNumArtifacts > 0) {
            numArtifacts = varNumArtifacts;
        }
    }

    public int GetNumArtifacts() {
        return numArtifacts;
    }

    public int GetNumAllies() {
        return numAliados;
    }

    public void SetNumAllies(int varNumAliados) {
        numAliados = varNumAliados;
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

    // Llamada que se encarga de llenar una posicion y incrementar su
    // lista
    public void SetEntityInChamber(CellInformation.CELLTYPE type, Coordinate myCoordinate, int level) {
        dungeonAccess[myCoordinate.GetX()][myCoordinate.GetY()].SetType(type);
        switch (type) {
            case ENEMY: {
                addenemy(myCoordinate);
            }
            break;
            case ARTIFACT: {
                CellInformation.CELLOBJECT obj = objManager.GetRandomArtefactType(prcItems);
                if (obj != CellInformation.CELLOBJECT.EMPTY) {
                    // No deberia de no pasar;
                    dungeonAccess[myCoordinate.GetX()][myCoordinate.GetY()].SetObject(obj);
                }
                AddArtifact(myCoordinate, level);
            }
            break;
            case FRIEND: {
                AddAlly(myCoordinate);
            }
            break;
            default: {
            }
        }
    }

    public Artefacto getObject(int x, int y) {
        dungeonAccess[x][y].SetType(CellInformation.CELLTYPE.ADENTRO);
        numArtifacts--;
        return layOutChamber[x][y].ReleaseArtifact();
        // Deberiamos liberar la memoria de layout
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
    public void Interactuar(Avatar player, Coordinate pos) {
        ///// deberia actualizar el access y borrar el objeto de la matriz
        player.AddArtefact(getObject(pos.GetX(), pos.GetY()));
    }

    public void GetFriendAdvice(Coordinate pose) {
        layOutChamber[pose.GetX()][pose.GetY()].GetAliado().GiveAdvice();
    }
    
    protected JFrame frame = new JFrame();
    public static String[] alternativas = {"Atacar", "Huir", "Usar"};

    public boolean BattleGraphic(Avatar player, Coordinate pos,Graphics g) {

        int playerHp = player.GetVida();
        int playerMax = player.GetVidaMaxima();

        Enemy currentEnemy = layOutChamber[pos.GetX()][pos.GetY()].GetEnemy();
        int enemyHp = currentEnemy.GetVida();

        System.out.format("Comienza el Encuentro!\nNombre de Enemigo: %s\nDescripcion de Enemigo: %s\nAtaque: ( %d ATK)\n", currentEnemy.GetNombre(),
                currentEnemy.GetDescription(), currentEnemy.GetStrength());
        //Scanner in = new Scanner(System.in);

        while (true) {         
            int count = TimeCount;
            int sec = count % 60 ;
            int min = (int)(count / 60);
            g.drawString("T Transcurrido: " + "Min: " + String.valueOf(min) + " Seg: " + String.valueOf(sec),1175,20);
            String respuesta = (String) JOptionPane.showInputDialog(frame,
                    "Que deseas hacer?",
                    "Batalla",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    alternativas,
                    alternativas[0]);
            System.out.println("\n\n");
            System.out.println(player.GetNombre() + ": " + player.GetVida() + "/" + player.GetVidaMaxima() + " HP");
            System.out.println(currentEnemy.GetNombre() + ": " + currentEnemy.GetVida() + " HP");
            System.out.print("Opciones: atacar, huir, usar.\n");
            System.out.print("Comando: ");
            if (respuesta.toLowerCase().startsWith("atacar")) {
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
            } else if (respuesta.toLowerCase().startsWith("huir")) {
                System.out.print("Huiste exitosamente.\n");
                return true;
            } else if (respuesta.toLowerCase().startsWith("usar")) {
                JFrame frame2 = new JFrame();
                String numero = JOptionPane.showInputDialog(
                        frame2,
                        "Ingresa el numero del item a usar",
                        "Escoger item",
                        JOptionPane.WARNING_MESSAGE
                );
                int num = 0;
                try {
                    num = Integer.parseInt(numero);
                } catch (NumberFormatException except) {
                    System.out.println("Indice no valido.");
                }

                if (num > 0) {
                    if (num <= player.getSizeSaco()) {
                        player.EquipItem(num - 1);
                    } else {
                        System.out.println("\nNo tienes es indice de item en tu saco\n\n");
                    }
                } else {
                    System.out.println("\nIngresaste Indice negativo en el saco\n\n");
                }
            } else {
                System.out.println("Acción inválida.");
            }
            if (player.GetVida() == 0) {
                return false;
            }
            if (currentEnemy.GetVida() == 0) {
                System.out.println("El enemigo ha sido derrotado.\n");
                dungeonAccess[pos.GetX()][pos.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                lista_enemigos.remove(currentEnemy);
                layOutChamber[pos.GetX()][pos.GetY()].GasEnemy();
                numEnemies--;
                return true;
            }
        }
    }


    protected JFrame frame = new JFrame();
    public static String[] alternativas = {"Atacar", "Huir", "Usar"};

    public boolean BattleGraphic(Avatar player, Coordinate pos, Graphics g, BufferStrategy bs) {

        int playerHp = player.GetVida();
        int playerMax = player.GetVidaMaxima();

        Enemy currentEnemy = layOutChamber[pos.GetX()][pos.GetY()].GetEnemy();
        int enemyHp = currentEnemy.GetVida();

        System.out.format("Comienza el Encuentro!\nNombre de Enemigo: %s\nDescripcion de Enemigo: %s\nAtaque: ( %d ATK)\n", currentEnemy.GetNombre(),
                currentEnemy.GetDescription(), currentEnemy.GetStrength());
        //Scanner in = new Scanner(System.in);

        while (true) {
            this.Render(g);
            player.Mostrar_BarraInfo(g, 20);
            bs.show();
            String respuesta = (String) JOptionPane.showInputDialog(frame,
                    "Que deseas hacer?",
                    "Batalla",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    alternativas,
                    alternativas[0]);
            System.out.println("\n\n");
            System.out.println(player.GetNombre() + ": " + player.GetVida() + "/" + player.GetVidaMaxima() + " HP");
            System.out.println(currentEnemy.GetNombre() + ": " + currentEnemy.GetVida() + " HP");
            System.out.print("Opciones: atacar, huir, usar.\n");
            System.out.print("Comando: ");
            if (respuesta.toLowerCase().startsWith("atacar")) {
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
            } else if (respuesta.toLowerCase().startsWith("huir")) {
                System.out.print("Huiste exitosamente.\n");
                return true;
            } else if (respuesta.toLowerCase().startsWith("usar")) {
                JFrame frame2 = new JFrame();
                String numero = JOptionPane.showInputDialog(
                        frame2,
                        "Ingresa el numero del item a usar",
                        "Escoger item",
                        JOptionPane.WARNING_MESSAGE
                );
                int num = 0;
                try {
                    num = Integer.parseInt(numero);
                } catch (NumberFormatException except) {
                    System.out.println("Indice no valido.");
                }

                if (num > 0) {
                    if (num <= player.getSizeSaco()) {
                        player.EquipItem(num - 1);
                    } else {
                        System.out.println("\nNo tienes es indice de item en tu saco\n\n");
                    }
                } else {
                    System.out.println("\nIngresaste Indice negativo en el saco\n\n");
                }
            } else {
                System.out.println("Acción inválida.");
            }
            if (player.GetVida() == 0) {
                return false;
            }
            if (currentEnemy.GetVida() == 0) {
                System.out.println("El enemigo ha sido derrotado.\n");
                dungeonAccess[pos.GetX()][pos.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                lista_enemigos.remove(currentEnemy);
                layOutChamber[pos.GetX()][pos.GetY()].GasEnemy();
                numEnemies--;
                return true;
            }
        }
    }

    public boolean Battle(Avatar player, Coordinate pos) {

        int playerHp = player.GetVida();
        int playerMax = player.GetVidaMaxima();

        Enemy currentEnemy = layOutChamber[pos.GetX()][pos.GetY()].GetEnemy();
        int enemyHp = currentEnemy.GetVida();

        System.out.format("Comienza el Encuentro!\nNombre de Enemigo: %s\nDescripcion de Enemigo: %s\nAtaque: ( %d ATK)\n", currentEnemy.GetNombre(),
                currentEnemy.GetDescription(), currentEnemy.GetStrength());
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n");
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
                dungeonAccess[pos.GetX()][pos.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                lista_enemigos.remove(currentEnemy);
                layOutChamber[pos.GetX()][pos.GetY()].GasEnemy();
                numEnemies--;
                return true;
            }
        }
    }

    //Modif
    public void addenemy(Coordinate pos) {
        Enemy enemigo = enemygen.generar_enemigo();
        enemigo.SetPosition(pos.GetPoint());
        enemigo.SetPositionDraw(pos.GetX()*tileSizeX, pos.GetY()*tileSizeY);        
        lista_enemigos.add(enemigo);
    }

    public void AddAlly(Coordinate pos) {
        lista_aliados.add(allyGen.GetAlly(pos, objManager));
        numAliados++;
    }

    public void AddArtifact(Coordinate pos, int level) {
        lista_artefactos.add(objManager.GetRandomObject(dungeonAccess[pos.GetX()][pos.GetY()].GetObject(), level, pos.GetX(), pos.GetY()));
        numArtifacts++;
    }

    public void KillInChamber(Coordinate pos) {
        Enemy currentEnemy = layOutChamber[pos.GetX()][pos.GetY()].GetEnemy();
        dungeonAccess[pos.GetX()][pos.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
        lista_enemigos.remove(currentEnemy);
        layOutChamber[pos.GetX()][pos.GetY()].GasEnemy();
        numEnemies--;
    }

    public void MoveEnemiesInteligente(int playerX, int playerY) {
        Enemy varEnemy;
        DIRECTIONS current;
        for (Enemy currEnemy : lista_enemigos) {
            if ((current = currEnemy.RandomMoveInteligente(dungeonAccess, 1, playerX, playerY, M, N)) != DIRECTIONS.STAY) {
                dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                layOutChamber[currEnemy.GetX()][currEnemy.GetY()].GasEnemy();
                currEnemy.Move(current, 1);
                currEnemy.NextPosition(current);
                dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ENEMY);
                layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
            } else {
                if ((current = currEnemy.RandomMove(dungeonAccess, 1, playerX, playerY, M, N)) != DIRECTIONS.STAY) {

                    // Primero elimino de dungeon acess y lo saco de su cuarto. Luego muevo el enemigo y lo pongo
                    // en una nueva locación
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].GasEnemy();                  
                    currEnemy.Move(current, 1);
                    currEnemy.NextPosition(current);
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ENEMY);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
                }
            }
        }
    }

    public void MoveEnemies(int playerX, int playerY) {
        Enemy varEnemy;
        DIRECTIONS current;
        // #Pregunta  1 y 2
        for (Enemy currEnemy : lista_enemigos) {
            if (Math.random() <= 0.75) {
                // Aca pueden presentarse errores.... Pero en el tiempo limita dificil que salga perfecto                
                // Puede ser que no tenga donde moverse asi que se queda en el mismo lugar
                if ((current = currEnemy.RandomMove(dungeonAccess, 1, playerX, playerY, M, N)) != DIRECTIONS.STAY) {

                    // Primero elimino de dungeon acess y lo saco de su cuarto. Luego muevo el enemigo y lo pongo
                    // en una nueva locación
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].GasEnemy();                    
                    currEnemy.Move(current, 1);
                    currEnemy.NextPosition(current);
                    dungeonAccess[currEnemy.GetX()][currEnemy.GetY()].SetType(CellInformation.CELLTYPE.ENEMY);
                    layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
                }
            }
        }
    }

    //Preg 1 Lab2
    public void MoveAllies(int playerX, int playerY) {
        DIRECTIONS current;
        for (Aliado currAliado : lista_aliados) {
            if ((current = currAliado.RandomMove(dungeonAccess, 1, playerX, playerY, M, N)) != DIRECTIONS.STAY) {
                // Primero elimino de dungeon acess y lo saco de su cuarto. Luego muevo el enemigo y lo pongo
                // en una nueva locación
                dungeonAccess[currAliado.GetX()][currAliado.GetY()].SetType(CellInformation.CELLTYPE.ADENTRO);

                //Aun no esta en layout
                layOutChamber[currAliado.GetX()][currAliado.GetY()].GasAlly();
                currAliado.Move(current, 1);
                currAliado.NextPosition(current);
                dungeonAccess[currAliado.GetX()][currAliado.GetY()].SetType(CellInformation.CELLTYPE.FRIEND);

                layOutChamber[currAliado.GetX()][currAliado.GetY()].SetAlly(currAliado);
                //Aun no esta en layout            
            }
        }
    }
<<<<<<< HEAD
    
    public void MovePlayersPressed(KeyEvent e)
    {
        for(Player p:lista_Players)
        {
            p.keyPressed(e);
        }
    }
    
    public void MovePlayersReleased(KeyEvent e)
    {        
        for(Player p:lista_Players)
        {
            p.keyReleased(e);
        }        
    }
          
    
    public void act()
    {       
        
        for(Player p: lista_Players)
        {
            p.act(dungeonAccess);
        }
    }
    
    public void CheckConsistency()
    {
        for(int i = 0;i<M;i++)
        {
            for(int j = 0;j<N;j++)
            {
                switch(dungeonAccess[i][j].GetType())
                {
                    case FRIEND:
                    {
                        if(layOutChamber[i][j]==null)
                        {
=======

    public void MovePlayersPressed(KeyEvent e) {
        for (Player p : lista_Players) {
            p.keyPressed(e);
        }
    }

    public void MovePlayersReleased(KeyEvent e) {
        for (Player p : lista_Players) {
            p.keyReleased(e);
        }
    }

    public void act() {

        for (Player p : lista_Players) {
            p.act(dungeonAccess);
        }
    }

    public void CheckConsistency() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                switch (dungeonAccess[i][j].GetType()) {
                    case FRIEND: {
                        if (layOutChamber[i][j] == null) {
>>>>>>> origin/master
                            System.out.println("Error Null Chamber: Position: " + i + " " + j);
                        } else {
                            if (layOutChamber[i][j].GetAliado() == null) {
                                System.out.println("Error Null Ally: Position: " + i + " " + j);
                            }
                        }
                    }
                    break;
                    case ENEMY: {
                        if (layOutChamber[i][j] == null) {
                            System.out.println("Error Null Chamber: Position: " + i + " " + j);
                        } else {
                            if (layOutChamber[i][j].GetEnemy() == null) {
                                System.out.println("Error Null Enemy: Position: " + i + " " + j);
                            }
                        }
                    }
                    break;
                    case ARTIFACT: {
                        if (layOutChamber[i][j] == null) {
                            System.out.println("Error Null Chamber: Position: " + i + " " + j);
                        } else {
                            if (layOutChamber[i][j].GetArtefacto() == null) {
                                System.out.println("Error Null Artifact: Position: " + i + " " + j);
                            } else {
                                switch (dungeonAccess[i][j].GetObject()) {
                                    case WEAPON: {
                                        if (!(layOutChamber[i][j].GetArtefacto() instanceof Arma)) {
                                            System.out.println("Error Mistach Artifact: Arma: " + i + " " + j);
                                        }
                                    }
                                    break;
                                    case POTION: {
                                        if (!(layOutChamber[i][j].GetArtefacto() instanceof Pocion)) {
                                            System.out.println("Error Mistach Artifact: Pocion: " + i + " " + j);
                                        }

                                    }
                                    break;
                                    case ARMOR: {
                                        if (!(layOutChamber[i][j].GetArtefacto() instanceof Armadura)) {
                                            System.out.println("Error Mistach Artifact: Armadura: " + i + " " + j);
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                    }
                    break;
                }

            }
        }
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
    
    public void SetUpMapSize(int _M,int _N,int WIDTH,int HEIGHT,int tamShowX,int tamShowY)
    {
        SetM(_M);
        SetN(_N);        
        tileSizeX = (int)WIDTH/tamShowX;
        tileSizeY = (int)HEIGHT/tamShowY;        
<<<<<<< HEAD
=======
=======

    public void SetUpMapSize(int _M, int _N, int WIDTH, int HEIGHT) {
        SetM(_M);
        SetN(_N);
        tileSizeX = (int) WIDTH / M;
        tileSizeY = (int) HEIGHT / N;
>>>>>>> origin/master
>>>>>>> origin/master
    }

    // Preg 2
    public void SetUpChamber() {
        layOutChamber = new Chamber[M][N];
        for (int i = 0; i < M; i++) {
            // ACA ES DONDE CREO QUE SE CAE EL CODIGO!!!
            for (int j = 0; j < N; j++) {
<<<<<<< HEAD
                //Esta linea hace que se caiga. La Comentada                       
                // Depending on the position change the current Frame
                Chamber newChamb = null;
                if(dungeonAccess[i][j].GetMode()==CellInformation.CELLMODE.ANTERIOR||
                        dungeonAccess[i][j].GetMode()==CellInformation.CELLMODE.SIGUENTE)
                {
                    newChamb = new Chamber(tilesSpriteBase.get("ANTSIG"),tileSizeX,tileSizeY);  
                    if(dungeonAccess[i][j].GetMode()==CellInformation.CELLMODE.SIGUENTE)
                    {
                        newChamb.SetCurrentFrame(0);
                    }
                }else
                {
                    newChamb = new Chamber(tilesSpriteBase.get("DTILES"),tileSizeX,tileSizeY);                    
                    newChamb.SetCurrentFrame(getTileFrame(i, j));
                    newChamb.SetPositionDraw(i*tileSizeX, j*tileSizeY);                
                }     
                newChamb.SetPositionDraw(i*tileSizeX, j*tileSizeY);
                layOutChamber[i][j] = newChamb;                
=======
                //Esta linea hace que se caiga. La Comentada       
                CellInformation.CELLMODE a = dungeonAccess[i][j].GetMode();
                layOutChamber[i][j] = new Chamber(tilesInfo.get(a), tileSizeX, tileSizeY);
                layOutChamber[i][j].SetPositionDraw(i * tileSizeX, j * tileSizeY);
>>>>>>> origin/master
                //layOutChamber[i][j] = new Chamber();
            }
        }
        for (Enemy currEnemy : lista_enemigos) {
            layOutChamber[currEnemy.GetX()][currEnemy.GetY()].SetEnemy(currEnemy);
        }
        for (Aliado currAliado : lista_aliados) {
            layOutChamber[currAliado.GetX()][currAliado.GetY()].SetAlly(currAliado);
        }
        int i = 0;
<<<<<<< HEAD
        for(Artefacto currArtifact: lista_artefactos){                     
=======
        for (Artefacto currArtifact : lista_artefactos) {
>>>>>>> origin/master
            layOutChamber[currArtifact.x][currArtifact.y].SetArtefact(currArtifact);
        }     
    }
    
    public void GetDirection(int i,int j,int [] myDir)
    {
        // 0 LEFT
        // 1 RIGHT
        // 2 TOP
        // 3 BOT
        
        if(i==-1&&j==0)
        {
            myDir[0] = 1;
            return;
        }
        if(i==1&&j==0)
        {
            myDir[1] = 1;
            return;
        }
        if(j==-1&&i==0)
        {
            myDir[2] = 1;
            return;
        }
        if(j==1&&i==0)
        {
            myDir[3] = 1;                           
        }
        int y = 0;
    }
    
    public int getTileFrame(int i,int j)
    {
        if(dungeonAccess[i][j].GetMode()==CellInformation.CELLMODE.NORMAL) return 0;
        int nFree = 0;    
        int[] myDir = new int[4];
        
//        for(int m = i - 1;m<=i+1;m++)
//        {
//            for(int n = j - 1;n<=j+1;n++)
//            {
//                if(m<0||m>=M||n<0||n>=N) continue;
//                if(m==i&&n==j) continue;
//                if(dungeonAccess[m][n].GetMode()==CellInformation.CELLMODE.PARED) 
//                {
//                    nFree++;
//                    GetDirection(m-i, n-j, myDir);
//                }
//            }
//        }
        if(!(i-1<0)&&dungeonAccess[i-1][j].GetMode()==CellInformation.CELLMODE.PARED) 
        {
            nFree++;
            GetDirection(-1,0, myDir);
        }
         
        if(!(i+1>=M)&&dungeonAccess[i+1][j].GetMode()==CellInformation.CELLMODE.PARED) 
        {
            nFree++;
            GetDirection(1,0, myDir);
        }
          
        if(!(j-1<0)&&dungeonAccess[i][j-1].GetMode()==CellInformation.CELLMODE.PARED) 
        {
            nFree++;
            GetDirection(0,-1, myDir);
        }
           
        if(!(j+1>=N)&&dungeonAccess[i][j+1].GetMode()==CellInformation.CELLMODE.PARED) 
        {
            nFree++;
            GetDirection(0,1, myDir);
        }
        switch(nFree)
        {
            case 1:
            {               
                if(myDir[0]==1) return 12;              
                if(myDir[1]==1) return 14;
                if(myDir[2]==1) return 11;
                if(myDir[3]==1) return 13;
                
            }
            case 2:
            {
                if(myDir[0]==1)
                {
                    if(myDir[1]==1)
                    {
                        return 10;
                    }else
                    {
                        if(myDir[2]==1)
                        {
                            return 4;
                        }else
                        {
                            return 2;
                        }                        
                    }
                }
                if(myDir[1]==1)
                {
                    if(myDir[2]==1)
                    {
                        return 3;
                    }else
                    {
                        return 1;
                    }                    
                }                
                if(myDir[2]==1)
                {
                     if(myDir[3]==1)
                    {
                        return 5;
                    }                    
                }                
            }case 3:
            {
                if(myDir[0]==0) return 6;
                if(myDir[1]==0) return 7;
                if(myDir[2]==0) return 9;
                if(myDir[3]==0) return 8;
            }
        }
        return 0;
    }

    public void TeleportPlayer(Avatar player, int x, int y) {
<<<<<<< HEAD
        player.SetPosition(x, y);        
=======
        player.SetPosition(x, y);
>>>>>>> origin/master
    }
    
    

    // Implementacion de ISavable!
    @Override
    public void Save(FileWriter fw) {
        try {
            fw.write("" + this.M + "," + this.N + "," + this.minshowY + "," + this.maxshowY
                    + "," + this.minshowX + "," + this.maxshowX + "," + this.prcEnemies + ","
                    + this.lvlEnemies + "," + this.prcItems + "," + this.lista_enemigos.size() + "\r\n");

            for (int i = 0; i < this.lista_enemigos.size(); i++) {
                this.lista_enemigos.get(i).Save(fw);
            }
            this.Render(fw);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Cargar_Render(FileReader lector, BufferedReader buffer) {
        try {
            dungeonAccess = new CellInformation[M][N];
            String linea;
            //String space20 = new String(new char[40]).replace('\0', ' ');
            for (int j = 0; j < N; j++) {
                linea = buffer.readLine();
                //String[] arr1 = linea.split(",");
                // System.out.print(space20);
                for (int i = 0; i < M; i++) {
                    CellInformation auxCelda = new CellInformation();
                    switch (linea.charAt(i)) {
                        case '-':
                            Coordinate auxAnt = new Coordinate(M, N);
                            auxAnt.SetX(i);
                            auxAnt.SetY(j);
                            antPos = auxAnt;
                            auxCelda.SetMode(CellInformation.CELLMODE.ANTERIOR);
                            break;
                        case '+':
                            Coordinate auxSig = new Coordinate(M, N);
                            auxSig.SetX(i);
                            auxSig.SetY(j);
                            sigPos = auxSig;
                            auxCelda.SetMode(CellInformation.CELLMODE.SIGUENTE);
                            break;
                        default:
                            auxCelda.SetMode(CellInformation.CELLMODE.NORMAL);
                            switch (linea.charAt(i)) {
                                case '#':
                                    auxCelda.SetType(CellInformation.CELLTYPE.PARED);
                                    break;
                                case 'A':
                                    auxCelda.SetType(CellInformation.CELLTYPE.ARTIFACT);
                                    break;
                                case 'E':
                                    auxCelda.SetType(CellInformation.CELLTYPE.ENEMY);
                                    break;
                                default:
                                    auxCelda.SetType(CellInformation.CELLTYPE.ADENTRO);
                                    break;
                            }
                            break;
                    }
                    dungeonAccess[i][j] = auxCelda;
                }
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
            this.M = Integer.parseInt(arr1[0]);
            this.N = Integer.parseInt(arr1[1]);
            this.minshowY = Integer.parseInt(arr1[2]);
            this.maxshowY = Integer.parseInt(arr1[3]);
            this.minshowX = Integer.parseInt(arr1[4]);
            this.maxshowX = Integer.parseInt(arr1[5]);
            this.prcEnemies = Double.parseDouble(arr1[6]);
            this.lvlEnemies = Integer.parseInt(arr1[7]);
            this.prcItems = Double.parseDouble(arr1[8]);
            numEnemies = Integer.parseInt(arr1[9]);
            for (int i = 0; i < numEnemies; i++) {
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //Coordinate varPosition, String nomb,
                //int vida, int lvl, int initStrength,
                //String varDescription, int varArmor
                Coordinate auxCoord = new Coordinate(M, N);
                auxCoord.SetX(Integer.parseInt(arr2[2]));
                auxCoord.SetY(Integer.parseInt(arr2[3]));
                Enemy auxEnemy = new Enemy(auxCoord, arr2[0],
                        Integer.parseInt(arr2[4]), Integer.parseInt(arr2[5]),
                        Integer.parseInt(arr2[7]), arr2[1], Integer.parseInt(arr2[6]));
                lista_enemigos.add(auxEnemy);
            }
            this.SetUpChamber();
            this.Cargar_Render(lector, buffer);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     private void inicializarDatosMostrarMapa(int posY, int posX, int tamShowX, int tamShowY) {
        tamShowX = (int) tamShowX / 2;
        tamShowY = (int) tamShowY / 2;
      
        
        minshowX = 0;
        minshowY = 0;
        maxshowX = 0;
        maxshowY = 0;              
        
        if ((posY - tamShowY) > 0) {
            minshowY += posY - tamShowY;
        } else {
            minshowY = 0; 
            maxshowY -= (posY - tamShowY);
        }
<<<<<<< HEAD

        if ((posX - tamShowX) > 0) {
            minshowX += posX - tamShowX;
        } else {
            minshowX = 0;
            maxshowX -= (posX - tamShowX);
        }

=======

        if ((posX - tamShowX) > 0) {
            minshowX += posX - tamShowX;
        } else {
            minshowX = 0;
            maxshowX -= (posX - tamShowX);
        }

>>>>>>> origin/master
        if ((posY + tamShowY) < N) {
            maxshowY += posY + tamShowY;
        } else {
            maxshowY = N;
            minshowY -= (N - (posY + tamShowY));
        }

        if ((posX + tamShowX) < M) {
            maxshowX += posX + tamShowX;
        } else {
            maxshowX = M;
            minshowX -= (M - (posX + tamShowX));
<<<<<<< HEAD
=======
        }
    }


    // Implementacion de IDibujable  
    public void Render(Graphics g) {
<<<<<<< HEAD
      
        
=======

>>>>>>> origin/master
        visionTileSizeX = tileSizeX;
        visionTileSizeY = tileSizeY;

        int posX = lista_Players.get(activePlayer).GetX();
<<<<<<< HEAD
        int posY = lista_Players.get(activePlayer).GetY();                           
        inicializarDatosMostrarMapa(posY, posX, lista_Players.get(activePlayer).GetTamShowX(), lista_Players.get(activePlayer).GetTamShowY());
        g.setColor(Color.BLACK);    
        for (int j = minshowY; j < maxshowY; j++) {       
            for (int i = minshowX; i < maxshowX; i++) {
                CellInformation factor = dungeonAccess[i][j];
                    layOutChamber[i][j].SetPositionDraw((i-minshowX)*tileSizeX, (j-minshowY)*tileSizeY);
                    layOutChamber[i][j].Render(g);                
                    switch (factor.GetType()) {                              
                            case ENEMY:      
                                g.setColor(Color.RED);
                                g.fillOval((i-minshowX)*tileSizeX,  (j-minshowY)*tileSizeY, tileSizeX, tileSizeY);                                    
                                break;
                            case FRIEND:                                   
                                g.setColor(Color.YELLOW);
                                g.fillOval((i-minshowX)*tileSizeX,  (j-minshowY)*tileSizeY, tileSizeX,tileSizeY);                                    
                                break;
                            }
                    if(layOutChamber[i][j].GetArtefacto()!=null)
                    {
                        layOutChamber[i][j].GetArtefacto().SetPositionDraw((i-minshowX)*tileSizeX, (j-minshowY)*tileSizeY);
                        layOutChamber[i][j].GetArtefacto().Render(g);
                    }
                if ((i == posX) && (j == posY)) {
                     g.setColor(Color.BLACK);
                     g.fillOval((i-minshowX)*tileSizeX,  (j-minshowY)*tileSizeY, tileSizeX, tileSizeY);
                } 
            }          
=======
        int posY = lista_Players.get(activePlayer).GetY();

        g.setColor(Color.BLACK);
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < M; i++) {
                CellInformation factor = dungeonAccess[i][j];
                layOutChamber[i][j].Render(g);
                switch (factor.GetType()) {
                    case ENEMY:
                        g.setColor(Color.RED);
                        g.fillOval(i * visionTileSizeX, j * visionTileSizeY, 16, 16);
                        break;
                    case FRIEND:
                        g.setColor(Color.YELLOW);
                        g.fillOval(i * visionTileSizeX, j * visionTileSizeY, 16, 16);
                        break;
//                            case ARTIFACT:
//                                layOutChamber[i][j].GetArtefacto().Render(g);
                }
                if (layOutChamber[i][j].GetArtefacto() != null) {
                    layOutChamber[i][j].GetArtefacto().Render(g);
                }
                if ((i == posX) && (j == posY)) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i * visionTileSizeX, j * visionTileSizeY, 16, 16);
                }
            }
>>>>>>> origin/master
>>>>>>> origin/master
        }
    }


    // Implementacion de IDibujable  
    public void Render(Graphics g) {
              
        visionTileSizeX = tileSizeX;
        visionTileSizeY = tileSizeY;     
        
        int posX = lista_Players.get(activePlayer).GetX();
        int posY = lista_Players.get(activePlayer).GetY();                           
        inicializarDatosMostrarMapa(posY, posX, lista_Players.get(activePlayer).GetTamShowX(), lista_Players.get(activePlayer).GetTamShowY());      
        
        g.setColor(Color.BLACK);    
        for (int j = minshowY; j < maxshowY; j++) {       
            for (int i = minshowX; i < maxshowX; i++) {
                CellInformation factor = dungeonAccess[i][j];
                    layOutChamber[i][j].SetPositionDraw((i-minshowX)*tileSizeX, (j-minshowY)*tileSizeY);
                    layOutChamber[i][j].Render(g);                
                                 
                    Sprite renderEntity = layOutChamber[i][j].GetInhabitant();
                    
                    if(renderEntity!=null)
                    {
                        renderEntity.SetPositionDraw((i-minshowX)*tileSizeX, (j-minshowY)*tileSizeY);
                        renderEntity.paint(g);
                    }                                      
            }          
        }
        // LABORATORIO 4 - PREG 1
        // Pintamos al aliado dependiendo de los valores del delta.
        lista_Players.get(activePlayer).SetPositionDraw((posX-minshowX)*tileSizeX + lista_Players.get(activePlayer).movXDelta, 
                (posY-minshowY)*tileSizeY + lista_Players.get(activePlayer).movYDelta);        
        lista_Players.get(activePlayer).Render(g);
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
                                case FRIEND:
                                    fw.write("F");
                                    break;
                            }
                        }
                        break;
                    }
                }
                //System.out.print("\n");
                fw.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
  
=======

>>>>>>> origin/master
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardar(FileWriter fw) {
        try {

            fw.write("" + this.M + "," + this.N + "," + this.minshowY + "," + this.maxshowY
                    + "," + this.minshowX + "," + this.maxshowX + "," + this.prcEnemies + ","
                    + this.lvlEnemies + "," + this.prcItems + "," + this.lista_enemigos.size() + "\r\n");

            for (int i = 0; i < this.lista_enemigos.size(); i++) {
                this.lista_enemigos.get(i).guardar_enemigo(fw);
            }
            this.Guardar_Render(fw);
            //fw.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LoadComponents(String spriteInfo) {
        // Load BackGround and stuff
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
