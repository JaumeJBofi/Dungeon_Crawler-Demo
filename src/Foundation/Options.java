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
public class Options {
    
    public enum ACTION{
        MOVE,EXIT,INTERACT,NULA
    }    
    
    public DIRECTIONS path = DIRECTIONS.LEFT;    
    public ACTION taken;                
    
    public Options(ACTION taken){
        this.taken = taken;
    }
    
    public Options(ACTION taken,DIRECTIONS dir){
        this.taken = taken;
        path = dir;
    }        
}
