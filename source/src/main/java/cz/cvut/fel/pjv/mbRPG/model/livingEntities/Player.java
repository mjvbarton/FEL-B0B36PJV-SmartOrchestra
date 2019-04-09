/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model.livingEntities;

import cz.cvut.fel.pjv.mbRPG.Constants.ItemType;
import cz.cvut.fel.pjv.mbRPG.model.Inventory;
import cz.cvut.fel.pjv.mbRPG.model.LivingEntity;
import cz.cvut.fel.pjv.mbRPG.model.items.Weapon;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Player extends LivingEntity<Monster>{
    private Weapon activeWeapon;
    private Inventory inventory;
    private int score;
    
    public Player(Dimension2D position, Image image) {
        super(position, image);
    }
    
    /**
     * Attack the monster
     * @param entity - pointer to the monster
     * @return - returns true if the attack was successful
     */
    @Override
    public boolean attack(Monster entity) {
        return false;
    }
    
    /**
     * Change weapon of the player
     * @param weapon 
     */
    public void changeWeapon(Weapon weapon){
       
    }
    
    /**
     * Uses item type from the inventory (if available)
     * @param itemType 
     * @return true if there is such item
     */
    public boolean useItem(ItemType itemType){
        return false;
    }
    
    /**
     * updates player's score after killing a monster
     */
    private void updateScore(){}

    public int getMaxLives() {
        return maxLives;
    }

    public int getScore() {
        return score;
    }
        
}
