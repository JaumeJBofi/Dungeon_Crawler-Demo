/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.awt.Desktop;

/**
 *
 * @author Jauma
 */
public class Options {
    
    public enum ACTION{
        MOVE,EXIT,INTERACT,NULA
    }    
    
    public DIRECTIONS path;    
    public ACTION taken;                
    
    public Options(ACTION taken){
        this.taken = taken;
    }
    
    public Options(ACTION taken,DIRECTIONS dir){
        this.taken = taken;
        path = dir;
    }        
    
    public void SetAction(ACTION act){
        taken = act;
    }
    
    public void SetPath(DIRECTIONS dir){        
        path = dir;
    }
}
