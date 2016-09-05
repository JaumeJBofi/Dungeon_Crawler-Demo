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
import java.util.Random;

public class EnemyGenerator {

    final private Random randomManager;
    private List<Enemy> enemigos;

    public EnemyGenerator() {
        randomManager = new Random();
        
        enemigos = new ArrayList();
        Coordinate nulo = new Coordinate(0, 0);
        //Creo los objetos base
        Enemy xd = new Enemy(nulo, "xd", 100, 1);
        enemigos.add(xd);
        xd = new Enemy(nulo, "yd", 200, 2);
        enemigos.add(xd);
        xd = new Enemy(nulo, "zd", 300, 3);
        enemigos.add(xd);
    }

    public Enemy generar_enemigo() {
        Enemy enemigo_en_lista, copia_enemigo;
        int i = randomManager.nextInt(enemigos.size());
        enemigo_en_lista = enemigos.get(i);
        copia_enemigo = enemigo_en_lista.copiar();
        return copia_enemigo;
    }
}
