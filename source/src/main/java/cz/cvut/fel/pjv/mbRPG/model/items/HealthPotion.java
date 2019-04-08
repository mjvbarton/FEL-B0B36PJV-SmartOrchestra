/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model.items;

import cz.cvut.fel.pjv.mbRPG.model.Item;
import cz.cvut.fel.pjv.mbRPG.model.livingEntities.Player;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class HealthPotion extends Item<Player>{
    private final int healthIncrease;
    
    private static final String IMAGE_PATH = "resources/items/DamagePotion.png";
    /**
     * 
     * @param position - position in the game field
     * @param healthIncrease - value from [1,100]
     */
    public HealthPotion(Dimension2D position, int healthIncrease) {
        super(position, new Image(IMAGE_PATH));
        this.healthIncrease = healthIncrease;
    }
    /**
     * Use of the item by the player.
     * @param player 
     */
    @Override
    public void use(Player player) {
        
    }
    
}
