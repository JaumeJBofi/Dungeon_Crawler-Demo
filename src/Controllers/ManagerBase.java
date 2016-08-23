/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.AbstractMap;

/**
 *
 * @author Jauma
 */
public class ManagerBase {
    
    public enum DIRECTIONS{
        TOP,RIGHT,BOT,LEFT
    }
    
    public AbstractMap.SimpleEntry<Integer,Integer> advanceInDirection(AbstractMap.SimpleEntry<Integer,Integer> coord, DIRECTIONS dir,int steps)
    {
        int xFactor = 0, yFactor = 0;
        switch(dir) {
                    case BOT:{ yFactor+=steps;                        
                    }break;
                    case TOP:{ yFactor-=steps;                        
                    }break;
                    case LEFT:{ xFactor-=steps;                        
                    }break;
                    case RIGHT:{ xFactor+=steps;                        
                    }
                }

        return new AbstractMap.SimpleEntry(coord.getKey()+xFactor,coord.getValue()+yFactor);
    }
}
