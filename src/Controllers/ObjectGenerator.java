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
import com.thoughtworks.xstream.XStream;




///////////////////////

import java.io.BufferedReader;
import java.io.*;

final public class ObjectGenerator {
    
    final private Random randomManager;
    int baseLvl;
    private List<Arma> armas;
    private List<Armadura> armaduras;
    private List<Pocion> pociones;
    private ArrayList<Artefacto> artifactsPool;

    public ObjectGenerator(int baseLvl) {

        randomManager = new Random();
        armas = new ArrayList();
        armaduras = new ArrayList();
        pociones = new ArrayList();
        artifactsPool = new ArrayList();

        //// leo el archivo de armas y ?????
        //LoadTxtObjects();         // Already Loaded
        try {
        XStream xs = new XStream();
        FileReader fr = new FileReader("Artefacts_XML.txt");
        artifactsPool = (ArrayList<Artefacto>)xs.fromXML(fr);       
        fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }    

        // HardCoded for the damage..
        for(Artefacto art:artifactsPool)
        {
            if(art instanceof Arma)
            {
                armas.add(new Arma(art,15,30));
            }else if(art instanceof Armadura)
            {
                armaduras.add(new Armadura(art));
            }else
            {
                pociones.add(new Pocion(art));
            }
        }
        artifactsPool = null;
    }    
        
    public void LoadTxtObjects()
    {        
        // FORMATO ESTANDAR!
        // NOMBRE,PRCAPARICION,
        // String _nombre,double _prcAparicion,int _nivel,int _vida,int _ataque,int _proteccion,int precio,int _x,int _y
        try {
            FileReader fr = new FileReader("armas.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Arma weapon = new Arma(arr[0], Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),
                        Integer.parseInt(arr[5]),Integer.parseInt(arr[6]),Integer.parseInt(arr[7]),Integer.parseInt(arr[8]));
                armas.add(weapon);                         

                artifactsPool.add(weapon);
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
                Armadura armor = new Armadura(arr[0], Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),
                        Integer.parseInt(arr[5]),Integer.parseInt(arr[6]),Integer.parseInt(arr[7]),Integer.parseInt(arr[8]));

                armaduras.add(armor);               
                artifactsPool.add(armor);                                
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fr = new FileReader("pociones.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String linea;
                while((linea=br.readLine())!=null&&linea.equals(""));
                if (linea == null) {
                    break;
                }
                String[] arr = linea.split(",");
                Pocion potion = new Pocion(arr[0], Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),
                        Integer.parseInt(arr[5]),Integer.parseInt(arr[6]),Integer.parseInt(arr[7]),Integer.parseInt(arr[8]));

                pociones.add(potion);                
                artifactsPool.add(potion);

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        XStream xs = new XStream();
        try {
        FileWriter fw = new FileWriter("Artefacts_XML.txt");        
        String temp = xs.toXML(artifactsPool);
        fw.write(temp);
        fw.close();
        } catch (IOException e) {
        System.out.println(e.toString());
        }        
    }

    public Arma generar_arma(int levelCap) {
        Arma arma_en_lista;
        double prcToPass = Math.random();
        int searchTimes = 10;
        
        int i = randomManager.nextInt(armas.size());
        
        while((searchTimes!=0)&&armas.get(i).GetPrcAparition()<prcToPass&&armas.get(i).GetNivel()>levelCap)
        {
            searchTimes--;
            i = randomManager.nextInt(armas.size());
        }
        
        arma_en_lista = armas.get(i);
        return arma_en_lista.copiar();
    }

    public Armadura generar_armadura(int levelCap) {
        Armadura armadura_en_lista;
        double prcToPass = Math.random();
        int searchTimes = 10;
        
        int i = randomManager.nextInt(armaduras.size());
        
        while((searchTimes!=0)&&armaduras.get(i).GetPrcAparition()<prcToPass&&armaduras.get(i).GetNivel()>levelCap)
        {
            searchTimes--;
            i = randomManager.nextInt(armaduras.size());
        }
        
        armadura_en_lista = armaduras.get(i);
        return armadura_en_lista.copiar();
    }


    public Pocion generar_pocion(int levelCap) {
        Pocion pocion_en_lista;        
        double prcToPass = Math.random();
        int searchTimes = 10;
        
        int i = randomManager.nextInt(pociones.size());
        
        while((searchTimes!=0)&&pociones.get(i).GetPrcAparition()<prcToPass&&pociones.get(i).GetNivel()>levelCap)
        {
            searchTimes--;
            i = randomManager.nextInt(pociones.size());
        }
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
    
    public Artefacto GetRandomObject(CellInformation.CELLOBJECT objType, int level,int _x,int _y)
    {
        Artefacto art;
        switch(objType)
        {
            case WEAPON:
            {
                art = generar_arma(level);                                
            }break;
            case ARMOR:
            {
                art = generar_armadura(level);                
            }break;
            case POTION:
            {
                art = generar_pocion(level);                
            }break;
            default:
            {
                art = generar_pocion(level);
            }break;
        }
        art.x = _x;
        art.y = _y;
        return  art;
    }
}
