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
public class Coordinate {
    private int M;
    private int N;
    private int x;
    private int y;
    
    public Coordinate(int varM,int varN){
        M = varM;
        N = varN;        
    }
    
    public boolean InRangeX(int xVar){
        return (xVar>=0&&xVar<N);
    }
    
    public boolean InRangeY(int yVar){
        return (yVar>=0&&yVar<M);
    }
    public boolean InRange()
    {
        return InRangeX(x)&&InRangeY(y);
    }
    
    public int GetM(){
        return M;
    }
    
    public int GetN(){
        return N;
    }
    
    public int GetX(){
        return x;
    }
    
    public boolean SetX(int xVar){
        if(InRangeX(xVar)){
            x = xVar;
            return true;
        }else{
            return false;
        }        
    }
    
    public int GetY(){
        return y;
    }
    
    public boolean SetY(int yVar){
        if(InRangeY(yVar)){
            x = yVar;
            return true;
        }else{
            return false;
        }        
    }
            
    public Coordinate GetPoint(){
        Coordinate newCoord = new Coordinate(M, N);
        newCoord.SetX(x);
        newCoord.SetY(y);
        return newCoord;
    }
    
}
