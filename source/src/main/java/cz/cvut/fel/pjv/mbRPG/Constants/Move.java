/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.Constants;

/**
 *
 * @author Matěj Bartoň
 */
public enum Move {
    LEFT(-1),
    RIGHT(1);
    
    public final int value;
    
    private Move(int value){
        this.value = value;
    }
}
