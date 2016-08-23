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
        ADENTRO,AFUERA
    }
    
    public enum CELLMODE{
        SIGUENTE,ANTERIOR,NORMAL
        //Podemos agregar diferentes tipos especiales de celdas o Laberintos.
    }
    
    private CELLMODE MODE = CELLMODE.NORMAL;
    private CELLTYPE TYPE = CELLTYPE.AFUERA;    

    public boolean isNext(){
        return MODE == CELLMODE.SIGUENTE;
    }
    
    public boolean isPrevious(){
        return !isNext();
    }
    
    public boolean isOut()
    {
        return TYPE == CELLTYPE.AFUERA;
    }
    
    public void SetMode(CELLMODE cellMode){
        MODE = cellMode;
    }
    
    public void SetType(CELLTYPE cellType){
        TYPE = cellType;
    }
}
