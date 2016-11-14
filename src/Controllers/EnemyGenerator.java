/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Artefactos.Artefacto;
import Foundation.Coordinate;
import java.util.ArrayList;
import java.util.List;
import Models.Enemy;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

final public class EnemyGenerator {

    final private Random randomManager;
    private List<Enemy> enemigos;
    private int tileSizeX;
    private int tileSizeY;

    public EnemyGenerator() {
        randomManager = new Random();
        enemigos = new ArrayList();
        
        LoadEnemyTxt();
        
//        try {
//        XStream xs = new XStream();
//        FileReader fr = new FileReader("Enemy_XML.xml");
//        enemigos = (ArrayList<Enemy>)xs.fromXML(fr);       
//        fr.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }                           
    }
    
    public EnemyGenerator(int _tileSizeX,int _tileSizeY) {
        randomManager = new Random();
        enemigos = new ArrayList();
        
        LoadEnemyTxt();
        
        tileSizeX = _tileSizeX;
        tileSizeY = _tileSizeY;
        
//        try {
//        XStream xs = new XStream();
//        FileReader fr = new FileReader("Enemy_XML.xml");
//        enemigos = (ArrayList<Enemy>)xs.fromXML(fr);       
//        fr.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }                           
    }
    
    public void LoadEnemyTxt()
    {
        //Enemy(Coordinate varPosition, String nomb, int vida, int lvl, int initStrength, String varDescription, int varArmor)
         try {
            FileReader fr = new FileReader("enemigos.txt");
            BufferedReader br = new BufferedReader(fr);
            Coordinate nulo = new Coordinate(0, 0);
            while (true) {
                String linea;
                while((linea=br.readLine())!=null&&linea.equals(""));
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Enemy enemigo = new Enemy(nulo, arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]), arr[4], Integer.parseInt(arr[5]),arr[6],tileSizeX,tileSizeY);
                enemigos.add(enemigo);                
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
         
//        XStream xs = new XStream();
//        try {
//        FileWriter fw = new FileWriter("Enemy_XML.xml");        
//        String temp = xs.toXML(enemigos);
//        fw.write(temp);
//        fw.close();
//        } catch (IOException e) {
//        System.out.println(e.toString());
//        }        
    }

    public Enemy generar_enemigo() {
        Enemy enemigo_en_lista;
        int i = randomManager.nextInt(enemigos.size());        
        enemigo_en_lista = enemigos.get(i).copiar();
        enemigo_en_lista.SetHeight(tileSizeY);
        enemigo_en_lista.SetWidth(tileSizeX);
        return enemigo_en_lista;
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
