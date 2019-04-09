/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.view.ui;

import cz.cvut.fel.pjv.mbRPG.model.Inventory;
import cz.cvut.fel.pjv.mbRPG.model.items.Weapon;

/**
 *
 * @author Matěj Bartoň
 */
public class WeaponButton extends ItemButton{
    
    public WeaponButton(Weapon item) {
        super(item);
    }
    
    /**
     * This is unsupported state, it shall throw exception, or LOG this state as severe
     * @param inventory 
     */
    @Override
    public void update(Inventory inventory){
        // EXCEPTIONAL BEHAVIOR HERE
    }
    
    /**
     * Implements the weapon change for GUI
     * @param item 
     */
    public void update(Weapon item){
       this.item = item; 
    }
    
    /**
     * Updates weapon appearance when the player is boosted
     * 
     * @param isBoosted
     */
    public void update(boolean isBoosted){
        
    }
}
