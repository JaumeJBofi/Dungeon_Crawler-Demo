/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Artefactos.Arma;
import Artefactos.Pocion;
import Artefactos.Artefacto;
import Artefactos.Armadura;
import Foundation.Coordinate;
import Foundation.ISavable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Arthuro
 */
public class Avatar extends Entity implements IDibujable,ISavable {

    private int vidaMaxima;
    private Arma arma_equip;
    private Armadura armadura_equip;
    private List<Artefacto> saco;    
    private int xp;
    private int currentTop;

    public Avatar(Coordinate position, String varNombre) {
        super(position, varNombre, 100, 5, 5,1);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        tamShowX = 15;
        tamShowY = 15;
        saco = new ArrayList();
        SetNombre(varNombre);
        xp = 0;
        currentTop = 100;
    }

    public Avatar(Coordinate position, int varTamShowX, int varTamShowY, int vida, String varNombre, int varStrength, int varArmor) {
        super(position, varNombre, vida, varStrength, varArmor,1);
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
//        SetTamShowX(tamShowX);
//        SetTamShowY(tamShowY);
        tamShowX = varTamShowX;
        tamShowY = varTamShowY;
        saco = new ArrayList();
        SetNombre(varNombre);
        xp = 0;
        currentTop = 100;
    }

    public void SetVidaMaxima(int v) {
        vidaMaxima = v;
    }
    
    public void GetExperience(int cant)
    {
        int var = xp + cant;
        if(var>=currentTop)
        {
            LvlUp();
        }else{
            xp = var;
        }
    }
    
    public void LvlUp()
    {
        nivel++;
        // Subimnos de stats ?
        xp = 0;
    }

    public int GetVidaMaxima() {
        return vidaMaxima;
    }

    public int GetTamShowX() {
        return tamShowX;
    }

    final public void SetTamShowX(int tamShow) {
        // Temporal. Aca aunque el tamshow sea mayor pongo el mismo
        if (tamShow >= GetPosition().GetM()) {
            tamShowX = tamShow;
        } else {
            tamShowX = tamShow;
        }
    }

    final public void SetTamShowY(int tamShow) {
        // Temporal. Aca aunque el tamshow sea mayor pongo el mismo
        if (tamShow >= GetPosition().GetN()) {
            tamShowY = tamShow;
        } else {
            tamShowY = tamShow;
        }
    }

    public int GetTamShowY() {
        return tamShowY;
    }

    /// AGREGADO ////////////
    public void AddArtefact(Artefacto a) {
        // Solo añade un artefacto al saco
        saco.add(a);
    }

    public Integer getSizeSaco() {
        return saco.size();
    }

    public boolean EquipItem(int number) {
        if (saco.size() <= number || number < 0) { //Si no es un indice valido devuelve falso
            return false;
        }
        Artefacto selectedObj = saco.get(number);
        // Item a equiparse

        if (selectedObj instanceof Arma) {
            //si es arma se coloca en arma equipada
            arma_equip = (Arma) selectedObj;
        } else if (selectedObj instanceof Armadura) {
            // si es armadura, se coloca en la armadura
            armadura_equip = (Armadura) selectedObj;
        } else {
            //Si es una pocion, se cura la cantidad especificada
            int healCant = ((Pocion) selectedObj).GetVida();
            hp += healCant;
            if (hp > vidaMaxima) //No puede curarse mas del maximo de su vida
            {
                hp = vidaMaxima;
            }
            //Se consume la pocion
            saco.remove(number);
        }
        return true;
    }

    //#Preg 2
    // Para cuestiones de prueba hacemos que aunquesea haga 5 de daño;
    public int GetEquipWeaponDamage() {
        if (arma_equip == null) {
            return GetStrength();
        }
        Random rand = new Random();
        return rand.nextInt(arma_equip.GetDanhoMax() - arma_equip.GetDanhoMin()) + arma_equip.GetDanhoMin() + GetStrength();
    }

    public int GetEquipArmorProtection() {
        if (armadura_equip == null) {
            return GetArmor();
        } else {
            return armadura_equip.GetProteccion();
        }
    }

    public void Render() {
        // Son solo impresiones, nada brutal,
        //usa el polimorfismo en artefacto.
        //no imprime indices ni marca los items equipados
        int tamanho = saco.size();
        System.out.println("");
        System.out.println(" ------ STATS ------");
        System.out.print("  ");//Personaje: ");
        System.out.println(this.GetNombre());
        System.out.println("  HP: " + this.hp + "/" + this.vidaMaxima);
        System.out.print("  Arma: ");
        //Si no tiene arma equipada
        if (arma_equip == null) {
            System.out.println("Ninguno");
        } else {
            arma_equip.Render();
            System.out.println();
        }
        //Si no tiene armadura equipada
        System.out.print("  Armadura: ");
        if (armadura_equip == null) {
            System.out.println("Ninguno");
        } else {
            armadura_equip.Render();
            System.out.println();
        }
        Artefacto art;
        System.out.println(" ------ ITEMS ------");
        if (tamanho == 0) {
            System.out.println("  Vacio");
        } else {
            for (int i = 0; i < tamanho; i++) {
                art = saco.get(i);
                //System.out.print("  ");
                System.out.print("  " + (i + 1) + ")");
                //Modificado, ahora si imprime indices xd (Deberia :v)
                art.Render();
                if (saco.get(i) == arma_equip || saco.get(i) == armadura_equip) {
                    System.out.print(" E");
                }
                System.out.println();
            }
        }
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        try {
            fr.write(this.GetNombre() + "\r\n");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.GetVida() + ',' + this.GetVidaMaxima() + ',');
            fr.write("" + this.GetTamShowX() + ',' + this.GetTamShowY() + ',');
            fr.write("" + this.GetStrength() + ',' + this.GetArmor() + "\r\n");
            int tamano = saco.size();
            fr.write("" + tamano + "\r\n");
            int w_index = -1, a_index = -1;
            for (int i = 0; i < tamano; i++) {
                saco.get(i).Save(fr);
                if (saco.get(i) == arma_equip) {
                    w_index = i;
                }
                if (saco.get(i) == armadura_equip) {
                    a_index = i;
                }
            }
            fr.write("" + w_index + ',' + a_index + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    
    public void Load(FileReader flectura, BufferedReader buffer, int[] coordinate)
    {
        try {
            String linea = buffer.readLine();
            this.SetNombre(linea);
            linea = buffer.readLine();
            String[] arr1 = linea.split(",");
            coordinate[0] = Integer.parseInt(arr1[0]);//this.SetX(Integer.parseInt(arr1[0]));
            coordinate[1] = Integer.parseInt(arr1[1]);//this.SetY(Integer.parseInt(arr1[1]));
            this.SetVida(Integer.parseInt(arr1[2]));
            this.SetVidaMaxima(Integer.parseInt(arr1[3]));
            this.SetTamShowX(Integer.parseInt(arr1[4]));
            this.SetTamShowY(Integer.parseInt(arr1[5]));
            this.SetStrength(Integer.parseInt(arr1[6]));
            this.SetArmor(Integer.parseInt(arr1[7]));
            //Termino de leer la segunda linea
            linea = buffer.readLine();
            int sizeMochila = Integer.parseInt(linea);
            for (int i = 0; i < sizeMochila; i++) {
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //arma
                if (arr2[0].equals("W")) {
                    Arma auxArma = new Arma(arr2[1],
                            Integer.parseInt(arr2[2]), Integer.parseInt(arr2[3]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("A")) {
                    Armadura auxArma = new Armadura(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("P")) {
                    Pocion auxArma = new Pocion(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                }
            }
            linea = buffer.readLine();
            String[] arr3 = linea.split(",");
            int auxIndice = Integer.parseInt(arr3[0]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
            auxIndice = Integer.parseInt(arr3[1]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        try {
            String linea = buffer.readLine();
            this.SetNombre(linea);
            linea = buffer.readLine();
            String[] arr1 = linea.split(",");
            this.SetX(Integer.parseInt(arr1[0]));
            this.SetY(Integer.parseInt(arr1[1]));
            this.SetVida(Integer.parseInt(arr1[2]));
            this.SetVidaMaxima(Integer.parseInt(arr1[3]));
            this.SetTamShowX(Integer.parseInt(arr1[4]));
            this.SetTamShowY(Integer.parseInt(arr1[5]));
            this.SetStrength(Integer.parseInt(arr1[6]));
            this.SetArmor(Integer.parseInt(arr1[7]));
            //Termino de leer la segunda linea
            linea = buffer.readLine();
            int sizeMochila = Integer.parseInt(linea);
            for (int i = 0; i < sizeMochila; i++) {
                linea = buffer.readLine();
                String[] arr2 = linea.split(",");
                //arma
                if (arr2[0].equals("W")) {
                    Arma auxArma = new Arma(arr2[1],
                            Integer.parseInt(arr2[2]), Integer.parseInt(arr2[3]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("A")) {
                    Armadura auxArma = new Armadura(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                } else if (arr2[0].equals("P")) {
                    Pocion auxArma = new Pocion(arr2[1],
                            Integer.parseInt(arr2[2]));
                    saco.add(auxArma);
                }
            }
            linea = buffer.readLine();
            String[] arr3 = linea.split(",");
            int auxIndice = Integer.parseInt(arr3[0]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
            auxIndice = Integer.parseInt(arr3[1]);
            if (auxIndice != -1) {
                this.EquipItem(auxIndice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    public void guardar_personaje(FileWriter fr) {
        try {
            fr.write(this.GetNombre() + "\r\n");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.GetVida() + ',' + this.GetVidaMaxima() + ',');
            fr.write("" + this.GetTamShowX() + ',' + this.GetTamShowY() + ',');
            fr.write("" + this.GetStrength() + ',' + this.GetArmor() + "\r\n");
            int tamano = saco.size();
            fr.write("" + tamano + "\r\n");
            int w_index = -1, a_index = -1;
            for (int i = 0; i < tamano; i++) {
                saco.get(i).guardar_artefacto(fr);
                if (saco.get(i) == arma_equip) {
                    w_index = i;
                }
                if (saco.get(i) == armadura_equip) {
                    a_index = i;
                }
            }
            fr.write("" + w_index + ',' + a_index + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void LoadComponents() {

    }

    @Override
    public void Dispose() {

    }

}
