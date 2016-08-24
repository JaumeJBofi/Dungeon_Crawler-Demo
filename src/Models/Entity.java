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
public class Entity {

    int x, y;
    int vida; // vida actual

    public void SetX(int x1) {
        x = x1;
    }

    public void SetY(int y1) {
        y = y1;
    }

    public void SetVida(int v) {
        vida = v;
    }

    public int GetX() {
        return x;
    }

    public int GetVida() {
        return vida;
    }

    public int GetY() {
        return y;
    }
}
