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
    final private int M;
    final private int N;
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
    
    public void SetX(int xVar){
        if(InRangeX(xVar)){
            x = xVar;            
        }else{
            //throw new IndexOutOfBoundsException();
        }        
    }
    
    public int GetY(){
        return y;
    }
    
    public void SetY(int yVar){
        if(InRangeY(yVar)){
            y = yVar;
        }else{
            //throw new IndexOutOfBoundsException();
        }        
    }
            
    public Coordinate GetPoint(){
        Coordinate newCoord = new Coordinate(M, N);
        newCoord.SetX(x);
        newCoord.SetY(y);
        return newCoord;
    }
    
}
