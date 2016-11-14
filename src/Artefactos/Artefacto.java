/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Artefactos;

import Foundation.ISavable;
import Models.IDibujable;
import Models.Sprite;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Arthuro
 */
public abstract class Artefacto extends Sprite implements ISavable,IDibujable{

    protected String nombre;
    private double prcAparition;
    private int nivel;
    private int vida;
    private int ataque;
    private int proteccion;
    private int precio;    
    
    // Tiene sentido ponerlo publico ya que cualquiera puede mover un objeto.
    // Negativo significa que alguien lo esta llevando
    public int x;
    public int y;

    public Artefacto(String varNombre) {
        nombre = varNombre;
        nivel = 1;       
        vida = 100;
        ataque = 50;
        proteccion = 60;
        precio = 15;
        x = -1;
        y = -1;
    }
    
    public Artefacto(Artefacto artefacto)
    {
        super(artefacto.width, artefacto.height);        
        SetNombre(artefacto.nombre);
        SetPrcAparition(artefacto.prcAparition);
        SetNivel(artefacto.nivel);
        SetVida(artefacto.vida);
        SetProteccion(artefacto.proteccion);
        SetPosition(artefacto.x, artefacto.y);
        SetPrecio(artefacto.precio);        
    }
    
    public Artefacto(String _nombre,double _prcAparicion,int _nivel,int _vida,int _ataque,int _proteccion,int precio,int _x,int _y,
            int _SizeX,int _SizeY)   { 
        super(_SizeX, _SizeY);      
        LoadArtefacto(_nombre, _prcAparicion, _nivel, _vida, _ataque, _proteccion, precio, _x, _y);
    }
    
    final public void LoadArtefacto(String _nombre,double _prcAparicion,int _nivel,int _vida,int _ataque,int _proteccion,int precio,int _x,int _y){       
        SetNombre(_nombre);
        SetPrcAparition(_prcAparicion);
        SetNivel(_nivel);
        SetAtaque(_ataque);
        SetProteccion(_proteccion);
        SetPosition(_x, _y);
        SetPrecio(precio);
        SetVida(_vida);
    }
    
    final public void SetPrecio(int _precio)
    {
        precio = _precio;
    }
    
    public int GetPrecio(int precio)
    {
        return precio;
    }
    
    final public void SetPosition(int _x,int _y)
    {
        // No Coordenadas
        x = _x;
        y = _y;
    }

    // agregado 27/08/16 
    public void Render() {
        System.out.println("Nombre = " + nombre);
    }

    final public void SetNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void guardar_artefacto(FileWriter fr)
    {
        
    }
    
    public double GetPrcAparition(){
        return prcAparition;
    }
    
    final public void SetPrcAparition(double  varPrcAparition){
        prcAparition = varPrcAparition;
    }
    
    public int GetNivel(){
        return nivel;
    }
    
    final public void SetNivel(int varNivel){
        nivel = varNivel;
    }
    
    final public void SetVida(int varVida){
        vida = varVida;
    }
    
    public int GetVida(){
        return vida;
    }
    
    final public void SetAtaque(int varAtaque){
        ataque = varAtaque;
    }
    
    public int GetAtaque(){
        return ataque;         
    }
    
    final public void SetProteccion(int varProteccion){
        proteccion = varProteccion;
    }
    
    public int GetProteccion(){
        return proteccion;
    }
    
    public void RenderStats()
    {
        
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        
    }
    
    @Override
    public  void Load(FileReader flectura, BufferedReader buffer)
    {
        
    }

    public String GetNombre() {
        return nombre;
    }    
    
    @Override
    public void Render(Graphics g)
    {
        paint(g);
    }
  
}
