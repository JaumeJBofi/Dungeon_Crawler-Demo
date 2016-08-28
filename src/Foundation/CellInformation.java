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
    public enum CELLTYPE{
        ADENTRO,PARED
    }
    
    public enum CELLMODE{
        SIGUENTE,ANTERIOR,NORMAL
        //Podemos agregar diferentes tipos especiales de celdas o Laberintos.
    }
    
    public enum CELLOBJECT{
        ENEMY,POTION,WEAPON,EMPTY
    }

    public CellInformation() {
    }
            
    private CELLMODE MODE = CELLMODE.NORMAL;
    private CELLTYPE TYPE = CELLTYPE.PARED; 
    private CELLOBJECT OBJECT = CELLOBJECT.EMPTY;

    public boolean isNext(){
        return MODE == CELLMODE.SIGUENTE;
    }
    
    public boolean isPrevious(){
        return !isNext();
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
}
