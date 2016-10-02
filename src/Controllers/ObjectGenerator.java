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
import Artefactos.Artefacto;
import Artefactos.Pocion;
import Foundation.CellInformation;
import java.util.Random;
import java.io.IOException;
///////////////////////

import java.io.BufferedReader;
import java.io.*;

final public class ObjectGenerator {

    final private Random randomManager;
    int baseLvl;
    private List<Arma> armas;
    private List<Armadura> armaduras;
    private List<Pocion> pociones;

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
    
    public CellInformation.CELLOBJECT GetRandomArtefactType(double prcItem)
    {
        if (Math.random() <= prcItem) {
            int randomObject = randomManager.nextInt(3);
            switch (randomObject) {
                case 0: {
                    return CellInformation.CELLOBJECT.POTION;
                }            
                case 1: {
                    return CellInformation.CELLOBJECT.WEAPON;
                }            
                case 2: {
                    return CellInformation.CELLOBJECT.ARMOR;
                }            
            }
        }
        return CellInformation.CELLOBJECT.EMPTY;
    }
    
    public Artefacto GetRandomObject()
    {
        Artefacto art;
        switch(randomManager.nextInt(3))
        {
            case 0:
            {
                art = generar_arma();                
            }break;
            case 1:
            {
                art = generar_armadura();                
            }break;
            case 2:
            {
                art = generar_pocion();                
            }break;
            default:
            {
                art = generar_pocion();
            }break;
        }
        return  art;
    }
}
