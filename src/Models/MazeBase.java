/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *Clase que representa las dimensiones y atributos del laberinto en si.
 * Es abstracta para poder generar el dungeon o el mundo.
 * @author Jauma
 */
public  class MazeBase {
    
    private int M;
    private int N;
    
    // El comportamiento de estos sera diferente. Para un Dungeon estos miden la probabilidad
    // de una Celda pero estos tambien estan basados en el valor del mundo en si.
    // Por eso esta funcion esta sobrecargada para tomar el parametro del mundo 
    // Tambien ayuda a que podamos setar un mundo virtual anterior al nuestro 
    private double prcEnemies;
    private double lvlEnemies;
    
    public MazeBase(int varM,int varN,double varprcEnemies,double varlvlEnemies){
        SetM(varM);
        SetN(varN);
        SetLvlEnemies(varlvlEnemies);
        SetPrcEnemies(varprcEnemies);                        
    }
    
    public int GetM(){
        return M;
    }
    
    // Recordar que las dimensiones deben ser impares
    final public void SetM(int varM){
        if(varM%2==0){
            M = varM +1;
        }else{
            M = varM;
        }
    }
    
    final public int GetN(){
        return N;
    }
    
    final public void SetN(int varN){
        if(varN%2==0){
            N = varN +1;
        }else
        {
            N = varN;
        }
    }
    
    final public double GetPrcEnemies(){
        return prcEnemies;
    }
    
    final public double GetPrcEnemies(double worldPrc){
        return prcEnemies;
    }
    
    final public void SetPrcEnemies(double varPrcEnemies){
        if(varPrcEnemies>=1){
            // Lanzamos exepcion, la cantiddad del porcentajes de enemigos
            // no puede ser mas del 100%
            //throw new UnknownError();
        }else{
            prcEnemies = varPrcEnemies;
        }
    }
    
    final public double GetLvlEnemies(){
        return lvlEnemies;
    }
    
      final public double GetLvlEnemies(double worldLvl){
          // Implementamos un nivel que dependiendo del nivel del mundo aumente
          // el nivel que le corresponde
        return lvlEnemies;
    }
    
    final public void SetLvlEnemies(double varLvlEnemies){
        //Logica de validacion para nivel de Enemigos
        lvlEnemies = varLvlEnemies;
    }   
   
}
