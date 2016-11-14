/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Artefactos.Arma;
import Artefactos.Pocion;
import Artefactos.Artefacto;
import Artefactos.Armadura;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Foundation.DIRECTIONS;
import Foundation.ISavable;
import Foundation.Options;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Arthuro
 */
public abstract class Player extends Entity implements ISavable {

    private int vidaMaxima;
    private Arma arma_equip;
    private Armadura armadura_equip;
    private List<Artefacto> saco;    
    private int xp;
    private int currentTop;
<<<<<<< HEAD
    private int tileSizeX,tileSizeY;
    
    // Problemas con el primer movimeinto
    public DIRECTIONS lastMoved = DIRECTIONS.BOT;
    public int movXDelta;
    public int movYDelta;
    public DIRECTIONS currentDirection = DIRECTIONS.BOT;
    
    int tryTimes = 0;
    public DIRECTIONS currentTry = DIRECTIONS.BOT;
    
=======

>>>>>>> origin/master
    public Player(Coordinate position, String varNombre) {
        super(position, varNombre, 100, 5, 5,1);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        tamShowX = 15;
        tamShowY = 15;
        saco = new ArrayList();
        SetNombre(varNombre);
        xp = 0;
        currentTop = 100;
    }
<<<<<<< HEAD
    
    public void SetTileSize(int _tileX,int _tileY)
    {
        tileSizeX = _tileX;
        tileSizeY = _tileY;
    }
=======
>>>>>>> origin/master

    public Player(Coordinate position, int varTamShowX, int varTamShowY, int vida, String varNombre, int varStrength, int varArmor) {
        super(position, varNombre, vida, varStrength, varArmor,1);
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
//        SetTamShowX(tamShowX);
//        SetTamShowY(tamShowY);
        tamShowX = varTamShowX;
        tamShowY = varTamShowY;
        saco = new ArrayList();
        SetNombre(varNombre);
        xp = 0;
        currentTop = 100;
    }

    public void SetVidaMaxima(int v) {
        vidaMaxima = v;
    }
    
    public void GetExperience(int cant)
    {
        int var = xp + cant;
        if(var>=currentTop)
        {
            LvlUp();
        }else{
            xp = var;
        }
    }
    
<<<<<<< HEAD
    public void SetMoveDelta()
    {
        
    }
    
=======
>>>>>>> origin/master
    public void LvlUp()
    {
        nivel++;
        // Subimnos de stats ?
        xp = 0;
    }

    public int GetVidaMaxima() {
        return vidaMaxima;
    }

    public int GetTamShowX() {
        return tamShowX;
    }

    final public void SetTamShowX(int tamShow) {
        // Temporal. Aca aunque el tamshow sea mayor pongo el mismo
        if (tamShow >= GetPosition().GetM()) {
            tamShowX = tamShow;
        } else {
            tamShowX = tamShow;
        }
    }

    final public void SetTamShowY(int tamShow) {
        // Temporal. Aca aunque el tamshow sea mayor pongo el mismo
        if (tamShow >= GetPosition().GetN()) {
            tamShowY = tamShow;
        } else {
            tamShowY = tamShow;
        }
    }

    public int GetTamShowY() {
        return tamShowY;
    }

    /// AGREGADO ////////////
    public void AddArtefact(Artefacto a) {
        // Solo añade un artefacto al saco
        saco.add(a);
    }

    public Integer getSizeSaco() {
        return saco.size();
    }

    public boolean EquipItem(int number) {
        if (saco.size() <= number || number < 0) { //Si no es un indice valido devuelve falso
            return false;
        }
        Artefacto selectedObj = saco.get(number);
        // Item a equiparse

        if (selectedObj instanceof Arma) {
            //si es arma se coloca en arma equipada
            arma_equip = (Arma) selectedObj;
        } else if (selectedObj instanceof Armadura) {
            // si es armadura, se coloca en la armadura
            armadura_equip = (Armadura) selectedObj;
        } else {
            //Si es una pocion, se cura la cantidad especificada
            int healCant = ((Pocion) selectedObj).GetVida();
            hp += healCant;
            if (hp > vidaMaxima) //No puede curarse mas del maximo de su vida
            {
                hp = vidaMaxima;
            }
            //Se consume la pocion
            saco.remove(number);
        }
        return true;
    }

    //#Preg 2
    // Para cuestiones de prueba hacemos que aunquesea haga 5 de daño;
    public int GetEquipWeaponDamage() {
        if (arma_equip == null) {
            return GetStrength();
        }
        Random rand = new Random();
        return rand.nextInt(arma_equip.GetDanhoMax() - arma_equip.GetDanhoMin()) + arma_equip.GetDanhoMin() + GetStrength();
    }

    public int GetEquipArmorProtection() {
        if (armadura_equip == null) {
            return GetArmor();
        } else {
            return armadura_equip.GetProteccion();
        }
    }
    
    public synchronized void act(CellInformation[][] dungeonAccess)
    {   
        movePlayer(dungeonAccess);                
    }   
<<<<<<< HEAD
    // LABORATORIO 4 - PREG 1
=======
    
>>>>>>> origin/master
    public void movePlayer(CellInformation[][] dungeonAccess)
    {        
        ArrayList<DIRECTIONS> dirs = GetMovedDirections();
        for(DIRECTIONS dir: dirs)
        {
            Coordinate varCoord;
<<<<<<< HEAD
            currentDirection = dir;
            if((varCoord = CheckDungeonCollision(dungeonAccess, dir, 1))!=null)
            {
                // LABORATORIO 4 - PREG 1
                if(dir!=currentTry) currentTry = dir;              
                // check what is there.
                if(tryTimes>8)
                {
                    SetX(varCoord.GetX());
                    SetY(varCoord.GetY());
                    lastMoved = dir;
                    tryTimes = 0;
                    movXDelta = 0;
                    movYDelta = 0;
                }else{
                    tryTimes++;
                    if(dir==DIRECTIONS.TOP)
                    {
                        movYDelta -= tileSizeY/8;
                    }
                    if(dir==DIRECTIONS.RIGHT)
                    {
                        movXDelta += tileSizeX/8;
                    }
                     if(dir==DIRECTIONS.LEFT)
                    {
                        movXDelta -= tileSizeX/8;
                    }
                      if(dir==DIRECTIONS.BOT)
                    {
                        movYDelta += tileSizeY/8;
                    }
                }
                
            }
            NextPosition(dir);
        }                
    }
    
    // LABORATORIO 4 - PREG 1
=======
            if((varCoord = CheckDungeonCollision(dungeonAccess, dir, 1))!=null)
            {
                // check what is there.
                SetX(varCoord.GetX());
                SetY(varCoord.GetY());
            }
        }                
    }
    
>>>>>>> origin/master
    public ArrayList<DIRECTIONS> GetMovedDirections()
    {
        ArrayList<DIRECTIONS> dirs = new ArrayList<>();
        
        if(keyDownDown)  dirs.add(DIRECTIONS.BOT);
        if(keyLeftDown)  dirs.add(DIRECTIONS.LEFT);
        if(keyRightDown) dirs.add(DIRECTIONS.RIGHT);
        if(keyTopDown)   dirs.add(DIRECTIONS.TOP);
        
        return dirs;
    }
<<<<<<< HEAD
    
    // LABORATORIO 4 - PREG 1
    // Esto es para que cada uno pueda definir sus teclas
    public abstract void createKeys();
    
    // LABORATORIO 4 - PREG 1
=======
    public abstract void createKeys();
    
>>>>>>> origin/master
    public int keyLeft;
    public int keyRight;
    public int keyTop;
    public int keyDown;
    public int keyInteract;

    /* Variables booleanas que indican si las
     * teclas anteriormente nombradas est�n o
     * no pulsadas. */
<<<<<<< HEAD
    // LABORATORIO 4 - PREG 1
=======
>>>>>>> origin/master
    public boolean keyLeftDown = false;
    public boolean keyRightDown = false;
    public boolean keyTopDown = false;
    public boolean keyDownDown = false;
    public boolean keyInteractDown = false;
    
<<<<<<< HEAD
    // LABORATORIO 4 - PREG 1
=======
>>>>>>> origin/master
    public boolean IsWalkingLeft() {return keyLeftDown;};
    public boolean IsWalkingRight() {return keyRightDown;};
    public boolean IsWalkingTop() {return keyTopDown;};
    public boolean IsWalkingDown() {return keyDownDown;};
    public boolean Interacting() {return keyInteractDown;};       
    
<<<<<<< HEAD
    // LABORATORIO 4 - PREG 1
=======
>>>>>>> origin/master
    public synchronized void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == keyTop) {
            keyTopDown = true;
    } else if (e.getKeyCode() == keyRight) {
            keyRightDown = true;
    } else if (e.getKeyCode() == keyLeft) {
            keyLeftDown = true;
    } else if (e.getKeyCode() == keyDown) {
            keyDownDown = true;
    } else if (e.getKeyCode() == keyInteract) {
            keyInteractDown = true;
    }
    }

    public synchronized void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == keyTop) {
            keyTopDown = false;
    } else if (e.getKeyCode() == keyRight) {
            keyRightDown = false;
    } else if (e.getKeyCode() == keyLeft) {
            keyLeftDown = false;
    } else if (e.getKeyCode() == keyDown) {
            keyDownDown = false;
    } else if (e.getKeyCode() == keyInteract) {
            keyInteractDown = false;
        }
    }
    
    //MOVE,EXIT,INTERACT,DEBUG,TELEPORT,ATTACK,HELP,EQUIP,NULA,SAVE
    public synchronized void setFlags(Options c) {
        switch (c.taken) {
            case MOVE:
                if (c.path == DIRECTIONS.TOP) {
                    keyTopDown = true;
                } else if (c.path == DIRECTIONS.RIGHT) {
                    keyRightDown = true;
                } else if (c.path == DIRECTIONS.LEFT) {
                    keyLeftDown = true;
                } else if (c.path == DIRECTIONS.BOT) {
                    keyDownDown = true;
                }
                break;
            case INTERACT:
                keyInteractDown = true;
                break;
        }
    }

    public synchronized void clearFlags() {
        keyLeftDown = false;
        keyRightDown = false;
        keyTopDown = false;
        keyDownDown = false;
        keyInteractDown = false;
    }
    
    

    public void Render() {
        // Son solo impresiones, nada brutal,
        //usa el polimorfismo en artefacto.
        //no imprime indices ni marca los items equipados
        int tamanho = saco.size();
        System.out.println("");
        System.out.println(" ------ STATS ------");
        System.out.print("  ");//Personaje: ");
        System.out.println(this.GetNombre());
        System.out.println("  HP: " + this.hp + "/" + this.vidaMaxima);
        System.out.print("  Arma: ");
        //Si no tiene arma equipada
        if (arma_equip == null) {
            System.out.println("Ninguno");
        } else {
            arma_equip.Render();
            System.out.println();
        }
        //Si no tiene armadura equipada
        System.out.print("  Armadura: ");
        if (armadura_equip == null) {
            System.out.println("Ninguno");
        } else {
            armadura_equip.Render();
            System.out.println();
        }
        Artefacto art;
        System.out.println(" ------ ITEMS ------");
        if (tamanho == 0) {
            System.out.println("  Vacio");
        } else {
            for (int i = 0; i < tamanho; i++) {
                art = saco.get(i);
                //System.out.print("  ");
                System.out.print("  " + (i + 1) + ")");
                //Modificado, ahora si imprime indices xd (Deberia :v)
                art.Render();
                if (saco.get(i) == arma_equip || saco.get(i) == armadura_equip) {
                    System.out.print(" E");
                }
                System.out.println();
            }
        }
    }
    
    public void Render(Graphics g)
    {
<<<<<<< HEAD
        paint(g);
=======
        
>>>>>>> origin/master
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        try {
            fr.write(this.GetNombre() + "\r\n");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.GetVida() + ',' + this.GetVidaMaxima() + ',');
            fr.write("" + this.GetTamShowX() + ',' + this.GetTamShowY() + ',');
            fr.write("" + this.GetStrength() + ',' + this.GetArmor() + "\r\n");
            int tamano = saco.size();
            fr.write("" + tamano + "\r\n");
            int w_index = -1, a_index = -1;
            for (int i = 0; i < tamano; i++) {
                saco.get(i).Save(fr);
                if (saco.get(i) == arma_equip) {
                    w_index = i;
                }
                if (saco.get(i) == armadura_equip) {
                    a_index = i;
                }
            }
            fr.write("" + w_index + ',' + a_index + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    
    public void Load(FileReader flectura, BufferedReader buffer, int[] coordinate)
    {
        try {
            String linea = buffer.readLine();
            this.SetNombre(linea);
            linea = buffer.readLine();
            String[] arr1 = linea.split(",");
            coordinate[0] = Integer.parseInt(arr1[0]);//this.SetX(Integer.parseInt(arr1[0]));
            coordinate[1] = Integer.parseInt(arr1[1]);//this.SetY(Integer.parseInt(arr1[1]));
            this.SetVida(Integer.parseInt(arr1[2]));
            this.SetVidaMaxima(Integer.parseInt(arr1[3]));
            this.SetTamShowX(Integer.parseInt(arr1[4]));
            this.SetTamShowY(Integer.parseInt(arr1[5]));
            this.SetStrength(Integer.parseInt(arr1[6]));
            this.SetArmor(Integer.parseInt(arr1[7]));
            //Termino de leer la segunda linea
            linea = buffer.readLine();
            int sizeMochila = Integer.parseInt(linea);
            for (int i = 0; i < sizeMochila; i++) {
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //arma
                if (arr2[0].equals("W")) {
                    Arma auxArma = new Arma(arr2[1],
                            Integer.parseInt(arr2[2]), Integer.parseInt(arr2[3]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("A")) {
                    Armadura auxArma = new Armadura(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("P")) {
                    Pocion auxArma = new Pocion(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                }
            }
            linea = buffer.readLine();
            String[] arr3 = linea.split(",");
            int auxIndice = Integer.parseInt(arr3[0]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
            auxIndice = Integer.parseInt(arr3[1]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        try {
            String linea = buffer.readLine();
            this.SetNombre(linea);
            linea = buffer.readLine();
            String[] arr1 = linea.split(",");
            this.SetX(Integer.parseInt(arr1[0]));
            this.SetY(Integer.parseInt(arr1[1]));
            this.SetVida(Integer.parseInt(arr1[2]));
            this.SetVidaMaxima(Integer.parseInt(arr1[3]));
            this.SetTamShowX(Integer.parseInt(arr1[4]));
            this.SetTamShowY(Integer.parseInt(arr1[5]));
            this.SetStrength(Integer.parseInt(arr1[6]));
            this.SetArmor(Integer.parseInt(arr1[7]));
            //Termino de leer la segunda linea
            linea = buffer.readLine();
            int sizeMochila = Integer.parseInt(linea);
            for (int i = 0; i < sizeMochila; i++) {
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //arma
                if (arr2[0].equals("W")) {
                    Arma auxArma = new Arma(arr2[1],
                            Integer.parseInt(arr2[2]), Integer.parseInt(arr2[3]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("A")) {
                    Armadura auxArma = new Armadura(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("P")) {
                    Pocion auxArma = new Pocion(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                }
            }
            linea = buffer.readLine();
            String[] arr3 = linea.split(",");
            int auxIndice = Integer.parseInt(arr3[0]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
            auxIndice = Integer.parseInt(arr3[1]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    public void Mostrar_BarraInfo(Graphics g, int x) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
        g.drawString(" ------ STATS ------ ", x, 50);
        g.setColor(Color.WHITE);
        //g.drawString("Q1 se la come", 20, 70);
        g.drawString(this.GetNombre(), x, 70);
        g.drawString("  HP: " + this.hp + "/" + this.vidaMaxima, x, 90);
        if (arma_equip == null) {
            g.drawString("  Arma: Ninguno", x, 110);
        } else {
            g.drawString("  Arma: " + arma_equip.GetNombre(), x, 110);
            //g.drawString("  Arma: " + arma_equip.GetNombre() + " (" + arma_equip.GetDanhoMin() + " - " + arma_equip.GetDanhoMax() + " ATK)", x, 110);
        }
        if (armadura_equip == null) {
            g.drawString("  Armadura: Ninguno", x, 130);
        } else {
            g.drawString("  Armadura: " + armadura_equip.GetNombre(), x, 130);
            //g.drawString("  Armadura: " + armadura_equip.GetNombre() + " (" + armadura_equip.GetProteccion() + " DEF)", x, 130);
        }
        Artefacto art;
        int tamanho = saco.size();
        g.drawString(" ------ ITEMS ------", x, 150);
        if (tamanho == 0) {
            g.drawString("  Vacio", x, 170);
        } else {
            for (int i = 0; i < tamanho; i++) {
                art = saco.get(i);
                if (art == arma_equip || art == armadura_equip) { //puse art en lugar de saco.get(i)
                    g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " E", x, 170 + (20 * i));
                } else {
                    g.drawString("  " + (i + 1) + ") " + art.GetNombre(), x, 170 + (20 * i));
                }
//                if (art instanceof Arma) {
//                    if (art == arma_equip) {
//                        g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " (" + ((Arma) art).GetDanhoMin() + " - " + ((Arma) art).GetDanhoMax() + " ATK) E", x, 170 + (20 * i));
//                    } else {
//                        g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " (" + ((Arma) art).GetDanhoMin() + " - " + ((Arma) art).GetDanhoMax() + " ATK)", x, 170 + (20 * i));
//                    }
//                } else if (art instanceof Armadura) {
//                    if (art == armadura_equip) {
//                        g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " (" + ((Armadura) art).GetProteccion() + " DEF) E", x, 170 + (20 * i));
//                    } else {
//                        g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " (" + ((Armadura) art).GetProteccion() + " DEF)", x, 170 + (20 * i));
//                    }
//                } else { //art instanceof Pocion
//                    g.drawString("  " + (i + 1) + ") " + art.GetNombre() + " (" + ((Pocion) art).GetVida() + " HP)", x, 170 + (20 * i));
//                }
            }
        }
    }
    
    public void guardar_personaje(FileWriter fr) {
        try {
            fr.write(this.GetNombre() + "\r\n");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.GetVida() + ',' + this.GetVidaMaxima() + ',');
            fr.write("" + this.GetTamShowX() + ',' + this.GetTamShowY() + ',');
            fr.write("" + this.GetStrength() + ',' + this.GetArmor() + "\r\n");
            int tamano = saco.size();
            fr.write("" + tamano + "\r\n");
            int w_index = -1, a_index = -1;
            for (int i = 0; i < tamano; i++) {
                saco.get(i).guardar_artefacto(fr);
                if (saco.get(i) == arma_equip) {
                    w_index = i;
                }
                if (saco.get(i) == armadura_equip) {
                    a_index = i;
                }
            }
            fr.write("" + w_index + ',' + a_index + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    @Override
    public void Dispose() {

    }

}
