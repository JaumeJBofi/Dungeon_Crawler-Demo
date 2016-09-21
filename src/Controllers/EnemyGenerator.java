/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Foundation.Coordinate;
import java.util.ArrayList;
import java.util.List;
import Models.Enemy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class EnemyGenerator {

    final private Random randomManager;
    private List<Enemy> enemigos;

    public EnemyGenerator() {
        randomManager = new Random();
        enemigos = new ArrayList();

//        Coordinate nulo = new Coordinate(0, 0);
//        //Creo los objetos base
//        Enemy xd = new Enemy(nulo, "xd", 5, 1, 10, "Un Enemigo", 5);
//        enemigos.add(xd);
//        xd = new Enemy(nulo, "yd", 10, 1, 15, "Un Enemigo", 5);
//        enemigos.add(xd);
//        xd = new Enemy(nulo, "zd", 15, 1, 20, "Un Enemigo", 5);
//        enemigos.add(xd);
        try {
            FileReader fr = new FileReader("enemigos.txt");
            BufferedReader br = new BufferedReader(fr);
            Coordinate nulo = new Coordinate(0, 0);
            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Enemy enemigo = new Enemy(nulo, arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4], Integer.parseInt(arr[5]));
                enemigos.add(enemigo);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Enemy generar_enemigo() {
        Enemy enemigo_en_lista;
        int i = randomManager.nextInt(enemigos.size());
        return enemigos.get(i).copiar();
    }

    /// agregada para obtener un enemigo en base al nombre 
    public Enemy obtener_enemigo_generado(String enemyName) {
        Enemy e = new Enemy(new Coordinate(0, 0));
        for (int i = 0; i < enemigos.size(); i++) {
            e = enemigos.get(i);
            if (e.GetNombre().compareTo(enemyName) == 0) {
                return e.copiar();
            }
        }
        return null; // si da es por error

    }
}
