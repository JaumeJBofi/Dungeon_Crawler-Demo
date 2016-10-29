/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

/**
 *
 * @author alulab14
 */

// Peg 2
public class Tip {
    
    private String _advice;
    private int _nivel;
    
    public Tip(String advice,int nivel)
    {
        _advice = advice;
        _nivel = nivel;
    }
    
    public String GetAdvice()
    {
        return _advice;
    }
    
    public int GetNivel()
    {
        return _nivel;
    }
    
    public Tip copiar()
    {
        return new Tip(_advice,_nivel);
    }
    
}
