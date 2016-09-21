/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Jauma
 */
public interface ISavable {
    
    public void Save(FileWriter fr);
    
    public void Load(FileReader flectura, BufferedReader buffer);    
}
