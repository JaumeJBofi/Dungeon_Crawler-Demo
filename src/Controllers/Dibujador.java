/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.util.*;

/**
 *
 * @author alulab14
 */
public class Dibujador {
    private int[][] mapa;
    private int posX;
    private int posY;
    private int maxX;
    private int maxY;
    private int minshowY;
    private int maxshowY;
    private int minshowX; 
    private int maxshowX;
    private int tamshowX;
    private int tamshowY;
    private char direccion;
    
    public Dibujador(int[][] mapa, int maxX, int maxY, int posX, int posY, int tamshowX, int tamshowY){
        this.mapa = mapa;
        this.maxX = maxX;
        this.maxY = maxY;
        this.posX = posX;
        this.posY = posY;
        this.tamshowX = tamshowX;
        this.tamshowY = tamshowY;
        this.direccion = '0';
    }
    
    public void showWindown(){
        while( mostrarPantalla() )
        {}
    }
    
    private boolean mostrarPantalla(){
        while( true ){
            mostrarMapa();
            System.out.println("\nDefinir accion:\t");
            Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            realizarAccion accion = new realizarAccion();
            int resultado = accion.modificar(this, sentence);
            if( resultado == 1 ){
                System.out.println("\n\n");
                return true;
            }
        }
        
    }
    private void mostrarMapa(){
        inicializarDatosMostrarMapa();
        
        for( int y = minshowY ; y <= maxshowY ; y++ ){
            for( int x = minshowX ; x <= maxshowX ; x++ ){
                if( (x == posX) && (y == posY) ){
                    System.out.print("H");
                }
                else{
                    int factor = mapa[y][x];
                    switch (factor){
                        case 0:
                            System.out.print("#");
                            break;
                        case 1:
                            System.out.print(" ");
                            break;
                        case 2:
                            System.out.print("A");
                            break;
                        case 3:
                            System.out.print("E");
                            break;
                        case 4:
                            System.out.print("+");
                            break;
                        case 5:
                            System.out.print("-");
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
    private void inicializarDatosMostrarMapa(){
        if( (posY - tamshowY) > 0 )
            minshowY = posY - tamshowY;
        else
            minshowY = 0;
        if( (posX - tamshowX) > 0 )
            minshowX = posX - tamshowX;
        else
            minshowX = 0;
        if( (posY + tamshowY) < maxY )
            maxshowY = posY + tamshowY;
        else
            maxshowY = maxY;
        if( (posX + tamshowX) < maxX )
            maxshowX = posX + tamshowX;
        else
            maxshowX = maxX;
    }
    public boolean modificarPos( int aumentoY, int aumentoX ){
        if( posicionPosible( posX + aumentoX, posY + aumentoY ) ){
            posX += aumentoX;
            posY += aumentoY;
        }
        else
            return false;
        return true;
    }
    private boolean posicionPosible( int posX, int posY ){
        if( (posX < 0) || (posX > maxX) )
            return false;
        if( (posY < 0) || (posY > maxY) )
            return false;
        if( mapa[posY][posX] != 1 )
            return false;
        return true;
    }
    public void setDireccion(char valor){
        direccion = valor;
    }
    public char getDireccion(){
        return direccion;
    }
    public int objetoMapa( int aumentoY, int aumentoX ){
        return mapa[posY + aumentoY][posX + aumentoX];
    }
    public void destruirObjMapa( int aumentoY, int aumentoX ){
        //3 es monstruo
        if( mapa[posY + aumentoY][posX + aumentoX] == 3 )
            mapa[posY + aumentoY][posX + aumentoX] = 4;
        else
            mapa[posY + aumentoY][posX + aumentoX] = 1;
    }
}
