/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import Controllers.Game;
import Facilidades.Aliado;
import Foundation.Options;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Game game = new Game();      
        Scanner in = new Scanner(System.in);        

       System.out.println("Presione enter para empezar\n\nEscribe Load para cargar una partida anterior.");        
        if (in.nextLine().compareToIgnoreCase("load") == 0) {
             try {
                XStream xs = new XStream();
                FileReader fr = new FileReader("SaveFile.txt");
                game = (Game)xs.fromXML(fr);       
                fr.close();
                } catch (IOException e) {
                    System.out.println(e.toString());          
                }
             game.LoadedStandar();
            // No necesariamente se llega aca            
        } else {
            
            game.LoadNewGame();                        
        }
        Options.ACTION taken;
        while((taken = game.Run())!=Options.ACTION.EXIT)
            {                
                if(taken==Options.ACTION.SAVE)
                {
                    XStream xs = new XStream();
                    try {
                    FileWriter fw = new FileWriter("SaveFile.txt");        
                    String temp = xs.toXML(game);
                    fw.write(temp);
                    fw.close();
                    } catch (IOException e) {
                    System.out.println(e.toString());
                    }        
                }
            }
    }

}
