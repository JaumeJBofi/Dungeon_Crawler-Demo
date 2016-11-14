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
public class ObjectConverter {
    private String value;
    private String delimiter;
    private int currentCount;    
    private String[] tokens;
    
    public ObjectConverter(String _value) {
        value = _value;
        delimiter = "";
    }
    
    public int GetInt()
    {
        return Integer.parseInt(value);
    }
    
    public double GetDouble()
    {
        return Double.parseDouble(value);
    }        
    
    public String GetString()
    {
        return value;
    }
    
    public boolean GetBool()
    {
        return "TRUE".equals(value);
    }
    
    public void SetDelimiter(String delimString)
    {
        delimiter = delimString;
        String buffer = value;        
        tokens = buffer.split(delimiter); 
        currentCount = 0;
    }
    
    public String GetNextPart()
    {        
        if(currentCount==tokens.length) return null;
        return tokens[currentCount++];
    }
    
    public String PeekNextPart()
    {
        if(currentCount==tokens.length) return null;
        return tokens[currentCount];
    }
    
    public String[] GetTokens(String delim)
    {
        return value.split(delim);
    }
    
    public CellInformation.CELLMODE GetNextPartMODE()
    {
        String valueString = GetNextPart();
        
        if(valueString.equals("PARED"))
        {
            return CellInformation.CELLMODE.PARED;
        }
        if(valueString.equals("NORMAL"))
        {
            return CellInformation.CELLMODE.NORMAL;
        }
        if(valueString.equals("ANTERIOR"))
        {
            return CellInformation.CELLMODE.ANTERIOR;
        }
        if(valueString.equals("SIGUIENTE"))
        {
            return CellInformation.CELLMODE.SIGUENTE;
        }
        return CellInformation.CELLMODE.NORMAL;
    }    
}
