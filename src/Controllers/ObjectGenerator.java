/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import java.util.List;
import Models.Arma;
import Models.Armadura;
import Models.Pocion;
import java.util.Random;

public class ObjectGenerator {

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

        //Creo los objetos base
        Arma xd = new Arma("Espada Corta", 2, 5);
        armas.add(xd);
        xd = new Arma("Espada Mediana", 4, 10);
        armas.add(xd);
        xd = new Arma("Espada Suprema del Dolor Supremo", 50, 85);
        armas.add(xd);

        Armadura yd = new Armadura("Armadura Liviana", 5);
        armaduras.add(yd);
        yd = new Armadura("Armadura Mediana", 12);
        armaduras.add(yd);
        yd = new Armadura("Armadura Impenetrable de la Maxima Proteccion", 70);
        armaduras.add(yd);

        Pocion zd = new Pocion("Pocion Pequeña", 10);
        pociones.add(zd);
        zd = new Pocion("Pocion Mediana", 20);
        pociones.add(zd);
        zd = new Pocion("Pocion de la Regeneración Hipocrática", 1000);
        pociones.add(zd);
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
        return  armadura_en_lista.copiar();        
    }
    
    public Pocion generar_pocion() {
        Pocion pocion_en_lista;
        int i = randomManager.nextInt(pociones.size());
        pocion_en_lista = pociones.get(i);
        return pocion_en_lista.copiar();        
    }
    
}