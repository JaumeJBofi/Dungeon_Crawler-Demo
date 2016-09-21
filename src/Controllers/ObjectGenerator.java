/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import java.util.List;
import Artefactos.Arma;
import Artefactos.Armadura;
import Artefactos.Pocion;
import java.util.Random;
import java.io.IOException;
///////////////////////

import java.io.BufferedReader;
import java.io.*;

public class ObjectGenerator {

    final private Random randomManager;
    int baseLvl;
    private List<Arma> armas;
    private List<Armadura> armaduras;
    private List<Pocion> pociones;

//    public ObjectGenerator(int baseLvl) {
//        randomManager = new Random();
//        armas = new ArrayList();
//        armaduras = new ArrayList();
//        pociones = new ArrayList();
//
//        //Creo los objetos base
//        Arma xd = new Arma("Espada Corta", 2, 5);
//        armas.add(xd);
//        xd = new Arma("Espada Mediana", 4, 10);
//        armas.add(xd);
//        xd = new Arma("Espada del Dolor Supremo", 50, 85);
//        armas.add(xd);
//
//        Armadura yd = new Armadura("Armadura Liviana", 5);
//        armaduras.add(yd);
//        yd = new Armadura("Armadura Mediana", 12);
//        armaduras.add(yd);
//        yd = new Armadura("Armadura de la Maxima Proteccion", 70);
//        armaduras.add(yd);
//
//        Pocion zd = new Pocion("Pocion Pequeña", 10);
//        pociones.add(zd);
//        zd = new Pocion("Pocion Mediana", 20);
//        pociones.add(zd);
//        zd = new Pocion("Pocion Regeneración Hipocrática", 1000);
//        pociones.add(zd);
//    }
    public ObjectGenerator(int baseLvl) {

        randomManager = new Random();
        armas = new ArrayList();
        armaduras = new ArrayList();
        pociones = new ArrayList();

        //// leo el archivo de armas y ?????
        try {
            FileReader fr = new FileReader("armas.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Arma weapon = new Arma(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                armas.add(weapon);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fr = new FileReader("armaduras.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Armadura armor = new Armadura(arr[0], Integer.parseInt(arr[1]));

                armaduras.add(armor);

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fr = new FileReader("pociones.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Pocion potion = new Pocion(arr[0], Integer.parseInt(arr[1]));

                pociones.add(potion);

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Arma generar_arma() {
        Arma arma_en_lista;
        int i = randomManager.nextInt(armas.size());
        arma_en_lista = armas.get(i);
        return arma_en_lista.copiar();
    }

    public Armadura generar_armadura() {
        Armadura armadura_en_lista;
        int i = randomManager.nextInt(armaduras.size());
        armadura_en_lista = armaduras.get(i);
        return armadura_en_lista.copiar();
    }

    public Pocion generar_pocion() {
        Pocion pocion_en_lista;
        int i = randomManager.nextInt(pociones.size());
        pocion_en_lista = pociones.get(i);
        return pocion_en_lista.copiar();
    }

}
