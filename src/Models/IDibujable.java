/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Jauma
 */
public interface IDibujable {
    // Maneja la implementacion de poder ser renderizado
    public void Render();
    public void Render(int x,int y);
    public void LoadComponents();
    public void Dispose();    
}
