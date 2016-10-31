/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import Controllers.Game;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Welcome Screen ?
        
        //Game Screnn
        Game severLayers = new Game();
        severLayers.GetWindow().setVisible(true);        
        severLayers.startGame();        
    }

}
