/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;
/**
 *
 * @author Jauma
 */
public class CellInformation {
    
    //Preg 1 Lab2
    public enum CELLTYPE{
        ADENTRO,PARED,ENEMY,ARTIFACT,FRIEND
    }
    
    public enum CELLMODE{
        SIGUENTE,ANTERIOR,NORMAL,PARED
        //Podemos agregar diferentes tipos especiales de celdas o Laberintos.
    }
    
    public enum CELLOBJECT{
        POTION,WEAPON,EMPTY,ARMOR
    }

    public CellInformation() {
    }
            
    private CELLMODE MODE = CELLMODE.PARED;
    private CELLTYPE TYPE = CELLTYPE.PARED; 
    private CELLOBJECT OBJECT = CELLOBJECT.EMPTY;

    public boolean isNext(){
        return MODE == CELLMODE.SIGUENTE;
    }
    
    public boolean isPrevious(){
        return MODE==CELLMODE.ANTERIOR;
    }
    
    public boolean isWall()
    {
        return TYPE == CELLTYPE.PARED;
    }
    
    public void SetMode(CELLMODE cellMode){
        MODE = cellMode;
    }
    
    public void SetType(CELLTYPE cellType){
        TYPE = cellType;                
    }
    

    //// agregado /////////////
    public void SetObject(CELLOBJECT cellObject){
        OBJECT = cellObject;                
    }
    /////

    public CELLMODE GetMode(){
        return MODE;
    }
    
    public CELLTYPE GetType(){
        return TYPE;
    }

    public CELLOBJECT GetObject(){
        return OBJECT;
    }
    
    public Coordinate position;                        
}
