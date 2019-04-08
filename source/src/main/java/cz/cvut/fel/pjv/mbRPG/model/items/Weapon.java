/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model.items;

import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Weapon {
    private final int damage;
    private final Image image;
    
    public Weapon(Image image, int damage){
        this.image = image;
        this.damage = damage;
    }
    
    /**
     * Calculates the price of itself
     * @return the price of itself
     */
    public int getPrice(){
        return 0;
    }
    
    /**
     * Returns the damage value of itself
     * @return returns the damage value of itself
     */
    public int use(){
        return damage;
    }
}
