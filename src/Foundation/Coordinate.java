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
    
    public Coordinate(int mVar,int nVar)
    {
        M = mVar;
        N = nVar;     
        x = 0;
        y = 0;
    }
    
    public boolean InRange()
    {
        return ((x>=0&&x<M)&&(y>=0&&y<N));
    }
    
    public int GetX()
    {
        return x;
    }
    
    public void SetX(int xVar)
    {
        x = xVar;
    }
    
    public int GetY()
    {
        return y;
    }
    
    public void SetY(int yVar)
    {
        y = yVar;
    }
    
    public Coordinate GetCoordinate()
    {
        Coordinate newCoord = new Coordinate(M, N);
        newCoord.SetX(x);
        newCoord.SetY(y);
        return newCoord;
    }
}
