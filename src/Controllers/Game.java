/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaz.Dibujador;
import Mundo.DungeonManager;
import Foundation.CellInformation;
import Foundation.Coordinate;
import Models.Avatar;
import Interfaz.Lore;
import Foundation.Options;
import Facilidades.Aliado;
import Foundation.DIRECTIONS;
import Foundation.ObjectConverter;
import Models.Entity;
import Models.IDibujable;
import Models.Sprite;
import java.util.Random;
import java.util.Scanner;
import Models.Stage;
import static Models.Stage.SCREENMODE;
import Foundation.Options;
import Interfaz.CharacterCreation;

// Graphics imports begin
import com.sun.media.sound.AudioFileSoundbankReader;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.security.auth.login.Configuration;
import javax.swing.*;
import org.omg.CORBA.Environment;

// Graphics imports end
/**
 *
 * @author Jauma
 */
public final class Game extends Stage {

    private Random randManager;
    public static Sprite chosenPlayer = null;
    //Lore nos ayuda a narrar la historia
    public Lore historia;
    private CharacterCreation cCreation;
    private DungeonManager myManager;
    public int varM;
    public int varN;
    private boolean battleFlag = false;
    
    // LABORATORIO 4 - PREG 2
    private Sprite buttomSalir;

    Dibujador Renderer;
    Options choiceTaken;
    CellInformation nextCellInformation;
    // Puede ser un arreglo
    Avatar player;
    IDibujable currentScene;
    HashMap<String, ObjectConverter> configuration = new HashMap<>();

    private boolean introScreen;

    public CellInformation.CELLTYPE GetWall(int i, int j) {
        return myManager.GetActiveDungeon().GetCellInformation(i, j).GetType();
    }

    public void LoadNewGame() {
        // No utilizado... 

        randManager = new Random();

        myManager = new DungeonManager(randManager.nextInt(7 - 3) + 3, WIDTH, HEIGHT);
        varM = randManager.nextInt(50 - 25) + 25;
        varN = randManager.nextInt(35 - 25) + 25;

        Renderer = new Dibujador();

        choiceTaken = new Options(Options.ACTION.INTERACT);

        String name = " ";
        //if(!name.equalsIgnoreCase("skip")&&!name.equalsIgnoreCase("")) historia.nacer();             
        //player = new Avatar(myManager.CreateDungeonDistribution(varM, varN, 0.15, 5, 0.3,1), 10, 6, 100, name, 10, 5);                   
    }

    public void GameStart() {
        ChangeStage(cCreation);
    }

    public void LoadedStandar() {
        Renderer.scanner.close();
        Renderer.scanner = new Scanner(System.in);
        Entity.generator = new Random();
    }

    public Game() {
        super(SCREENMODE.JFRAME);

        Renderer = new Dibujador();
        choiceTaken = new Options(Options.ACTION.INTERACT);
        nextCellInformation = new CellInformation();

        loadConfigurationFile(configuration);
        SetFPS((configuration.get("FPS").GetInt()));
        setSize((configuration.get("WIDTH").GetInt()), (configuration.get("HEIGHT").GetInt()));
        randManager = new Random();

        loadMainSheets();

        historia = new Lore(this);
        cCreation = new CharacterCreation(this);

        myManager = new DungeonManager(randManager.nextInt(configuration.get("MAXDUNGEONS").GetInt()
                - configuration.get("MINDUNGEONS").GetInt()) + configuration.get("MINDUNGEONS").GetInt(), WIDTH - 200, HEIGHT);
        varM = randManager.nextInt((configuration.get("MAXMAP_X").GetInt()) - (configuration.get("MINMAP_X").GetInt()))
                + (configuration.get("MAXMAP_Y").GetInt());
        varN = randManager.nextInt((configuration.get("MAXMAP_Y").GetInt()) - (configuration.get("MINMAP_Y").GetInt()))
                + (configuration.get("MINMAP_Y").GetInt());

        String name = " ";
        // Poner historia?? Opciones.
        // Var TamShow 10 y 6 anterior
        player = new Avatar(null, configuration.get("TAMSHOW_X").GetInt(), configuration.get("TAMSHOW_Y").GetInt(),
                configuration.get("HP_INI").GetInt(), name, configuration.get("STRENGTH_INI").GetInt(), configuration.get("ARMOR_INI").GetInt());
        player.SetTileSize((int) ((WIDTH - 200) / player.GetTamShowX()), (int) (HEIGHT) / player.GetTamShowY());
//        player.SetWidth((int)(WIDTH-200)/player.GetTamShowX());
//        player.SetHeight((int)(HEIGHT-200)/player.GetTamShowY());
//        
//        player.resize = true;
        player.ProcessSpriteInfo(configuration.get("PLAYER_1").GetString());
        player.resize = true;
        player.SetWidth((int) (WIDTH - 200) / player.GetTamShowX());
        player.SetHeight((int) (HEIGHT) / player.GetTamShowY());
        player.setImage("ST1");
        GetWindow().setResizable(false);
        
        // LABORATORIO 4. PREGUNTA 2
        buttomSalir = new Sprite();
        buttomSalir.ProcessSpriteInfo(configuration.get("BUTOM_SALIR").GetString());
    }

    @Override
    public synchronized void InitStage() {
        // 5 Level, 0.3 ItemsPRC, 1 Player Level
        player.SetPosition(myManager.CreateDungeonDistribution(varM, varN, configuration.get("PRCENEMY").GetDouble(),
                configuration.get("WORLDLEVEL").GetInt(), configuration.get("ITEMPRC").GetDouble(), configuration.get("PLAYERLVL").GetInt(),
                player.GetTamShowX(), player.GetTamShowY()));
        myManager.GetActiveDungeon().AddPlayer(player);
        //currentScene = myManager.GetActiveDungeon(); 
        _movements = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (_movementsRun) {
                        Thread.sleep(1000);
                        myManager.GetActiveDungeon().MoveEnemiesInteligente(player.GetX(), player.GetY());
                        myManager.GetActiveDungeon().MoveAllies(player.GetX(), player.GetY());
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        _movements.start();
    }

    @Override
    public synchronized void UpdateStage() {
        // Now with Graphics

        myManager.GetActiveDungeon().act();
        // LABORATORIO 4 - PREG 2
//        if (choiceTaken.taken != Options.ACTION.NULA) {
//            if (choiceTaken.taken == Options.ACTION.MOVE) {
//                myManager.GetActiveDungeon().act();
//            }
//            if (choiceTaken.taken == Options.ACTION.INTERACT) {
//                if (!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(), choiceTaken.path)).isWall()) {
//                    //myManager.GetActiveDungeon().act();  
//                    switch (nextCellInformation.GetType()) {
//                        case ARTIFACT: {
//                            myManager.GetActiveDungeon().Interactuar(player, nextCellInformation.position); // borra el artefacto de la matriz                                
//                        }
//                        break;
//                        case ENEMY: {
//                            battleFlag = true;
//                        }
//                        break;
//                        case FRIEND: {
//                            myManager.GetActiveDungeon().GetFriendAdvice(nextCellInformation.position);
//                        }
//                        break;
//
//                        default: {
//                            System.out.println("\n\nNo puedes interactuar con esa casilla");
//
//                        }
//                        break;
//                    }
//                    System.out.println("");
//                } else {
//                    System.out.println("\n\nNo puedes interactuar con esa pared");
//                }
//            }
//        }
    }

    @Override
    public synchronized void RenderStage(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (battleFlag == true) {
            //player.Mostrar_BarraInfo(g, 20);
            if (myManager.GetActiveDungeon().BattleGraphic(player, nextCellInformation.position, g)) {
            } else {
                System.out.println("Fin del juego. Ha muerto. Presione Enter\n");
                Exit();
                System.exit(0);
            }
            battleFlag = false;
        }
        myManager.GetActiveDungeon().Render(g);
        player.Mostrar_BarraInfo(g, 1200);
        buttomSalir.paint(g);
        int count = myManager.GetActiveDungeon().TimeCount;
        int sec = count % 60;
        int min = (int) (count / 60);

        g.drawString("T Transcurrido: " + "Min: " + String.valueOf(min) + " Seg: " + String.valueOf(sec), 1175, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if ((keyCode == KeyEvent.VK_ENTER)) {  
          
             if (!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(), player.currentDirection)).isWall()) {
                    //myManager.GetActiveDungeon().act();  
                    switch (nextCellInformation.GetType()) {
                        case ARTIFACT: {
                            myManager.GetActiveDungeon().Interactuar(player, nextCellInformation.position); // borra el artefacto de la matriz                                
                        }
                        break;
                        case ENEMY: {
                            battleFlag = true;
                        }
                        break;
                        case FRIEND: {
                            myManager.GetActiveDungeon().GetFriendAdvice(nextCellInformation.position);
                        }
                        break;

                        default: {
                            System.out.println("\n\nNo puedes interactuar con esa casilla");

                        }
                        break;
                    }
                    System.out.println("");
                }                                     
        }
        
        myManager.GetActiveDungeon().MovePlayersPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        myManager.GetActiveDungeon().MovePlayersReleased(e);
    }

    // LABORATORIO 4 - PREG 2
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(buttomSalir.CheckClicked(e))
        {
            running = false;
            _movementsRun = false;
            try {
                _animator.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }            
            try {
                _movements.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }                        
           System.exit(0);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        gameOver = true;
        running = false;
        try {
            _animator.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void loadConfigurationFile(HashMap<String, ObjectConverter> configHash) {
        try {
            FileReader fr = new FileReader("Config.txt");
            BufferedReader in = new BufferedReader(fr);

            String buffer;
            while ((buffer = in.readLine()) != null) {
                String[] tokens = buffer.split(";");
                String key = tokens[0];
                String value = tokens[1];
                configHash.put(key, new ObjectConverter(value));
            }
        } catch (Exception e) {
            System.err.println("Error Archivo: " + "Config.txt" + " no encontrado");
        }
    }

    public void loadMainSheets() {
        try {
            FileReader fr = new FileReader("MainSheets.txt");
            BufferedReader in = new BufferedReader(fr);

            String buffer;
            while ((buffer = in.readLine()) != null) {
                Sprite.putInMainSheet(buffer);
            }
        } catch (Exception e) {
            System.err.println("Error Archivo: " + "Config.txt" + " no encontrado");
        }
    }

    // No esta tan generico para n Players.
    public void guardar() {
        try {
            FileWriter fr = new FileWriter("partida.txt");
            player.Save(fr);
            myManager.Save(fr);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nGuardado Exitoso!\nPresione Enter para continuar");
    }

    public void equipar(int n) {
        if (n > 0) {
            if (n <= player.getSizeSaco()) {
                player.EquipItem(n - 1);
            } else {
                System.out.println("\nNo tienes ese item \n\n");
            }
        } else {
            System.out.println("\nNo tienes item con numero negativo\n\n");
        }
    }

//    @Override
//    public synchronized void SetInteraccion(Options c) {
//        if (c.taken == Options.ACTION.SAVE) {
//            guardar();
//        } else if (c.taken == Options.ACTION.EQUIP) {
//            int n = c.indice_item;
//            equipar(n);
//        } else {
//            player.setFlags(c);
//        }
//    }
    @Override
    public synchronized void clearInteraccion() {
        player.clearFlags();
    }

    private boolean modificarDireccion(String input, Options choice) {
        //Revisa en que direccion debe ir
        if (input.toLowerCase().endsWith("derecha")) {
            choice.SetPath(DIRECTIONS.RIGHT);
        } else if (input.toLowerCase().endsWith("izquierda")) {
            choice.SetPath(DIRECTIONS.LEFT);
        } else if (input.toLowerCase().endsWith("abajo")) {
            choice.SetPath(DIRECTIONS.BOT);
        } else if (input.toLowerCase().endsWith("arriba")) {
            choice.SetPath(DIRECTIONS.TOP);
        } //En caso que no de ninguna direccion devuelve falso
        else {
            return false;
        }
        //Se llego a definir una direccion
        return true;
    }

    public static String[] alternativas = {"Interactuar izquierda", "Interactuar derecha", "Interactuar abajo", "Interactuar arriba",
        "Usar", "Guardar", "Salir", "Deshabilitar"};

    public synchronized void SetInteraccion() {
        //choiceTaken.taken = c.taken;
        if (choiceTaken.taken == Options.ACTION.NULA) {
            //nothing
        } else if (choiceTaken.taken == Options.ACTION.SAVE) {
            guardar();
        } else if (choiceTaken.taken == Options.ACTION.EQUIP) {
            int n = choiceTaken.indice_item;
            equipar(n);
            choiceTaken.SetAction(Options.ACTION.NULA);
        } else {
            player.setFlags(choiceTaken);
        }
    }

    public static int deshabilitado = 0;

    @Override
    public void interactionUserBox() {
        //Options choice = new Options(Options.ACTION.NULA);
        String input = null;
        if (deshabilitado == 0) { // si es falso
            input = (String) JOptionPane.showInputDialog(frame,
                    "Que deseas hacer?",
                    "Acciones",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    alternativas,
                    alternativas[0]);

            if (input == null) {
                choiceTaken.SetAction(Options.ACTION.NULA);

            } else if (input.equalsIgnoreCase("Deshabilitar")) {
                clearInteraccion();
                deshabilitado = 1;
                choiceTaken.SetAction(Options.ACTION.NULA);
            } //Si la instruccion comienza com mover
            else if (input.equalsIgnoreCase("Salir")) {
                System.out.println("\n\nSesionFinalizada");
                Exit();
                return;
            } //Si la instruccion comienza com mover
            else if (input.toLowerCase().startsWith("mover")) {
                choiceTaken.SetAction(Options.ACTION.MOVE);
                if (!modificarDireccion(input, choiceTaken)) //Si no se modifico la direccion se entiende
                //que no se dio bien la instruccion
                {
                    choiceTaken.SetAction(Options.ACTION.NULA);
                }
            } else if (input.toLowerCase().startsWith("interactuar")) {
                choiceTaken.SetAction(Options.ACTION.INTERACT);
                //No es necesario ver si en verdad la direccion que se
                //da es correcta
                modificarDireccion(input, choiceTaken);
            } //En caso que se desee debugear
            //        else if (input.compareToIgnoreCase("sudo debug") == 0) {
            //            choice.SetAction(Options.ACTION.DEBUG);
            //        } //En caso que use teleport
            //        else if (input.toLowerCase().startsWith("sudo teleport")) {
            //            choice.SetAction(Options.ACTION.TELEPORT);
            //        } //En caso que se desee equipar un objeto
            else if (input.toLowerCase().startsWith("usar")) {

                String numero = JOptionPane.showInputDialog(
                        frame2,
                        "Ingresa el numero del item a usar",
                        "Escoger item",
                        JOptionPane.WARNING_MESSAGE
                );
                int num;
                try {
                    num = Integer.parseInt(numero);
                    choiceTaken.indice_item = num;
                    choiceTaken.SetAction(Options.ACTION.EQUIP);
                } catch (NumberFormatException except) {
                    System.out.println("Indice no valido.");
                    choiceTaken.SetAction(Options.ACTION.NULA);
                    SetInteraccion();
                }

            } else if (input.toLowerCase().startsWith("guardar")) {
                choiceTaken.SetAction(Options.ACTION.SAVE);
            } else {
                choiceTaken.SetAction(Options.ACTION.NULA);
            }
        }
        SetInteraccion();
        interactionEnable = false;
    }

}
