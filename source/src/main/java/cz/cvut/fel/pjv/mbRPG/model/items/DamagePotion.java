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
public class DamagePotion extends Item<Player> {
    private final int damageIncrease;
    private final int increaseLast; // miliseconds
    
    private static final String IMAGE_PATH = "resources/items/DamagePotion.png";
    
    /**
     * 
     * @param position - position in the game field
     * @param damageIncrease - value from [1, 100]
     * @param increaseLast - the time in miliseconds which determines the last of the efect
     */
    public DamagePotion(Dimension2D position, int damageIncrease, int increaseLast) {
        super(position, new Image(IMAGE_PATH));
        this.damageIncrease = damageIncrease;
        this.increaseLast = increaseLast;
    }
    
    /**
     * Use action of the item by Player
     * @param player 
     */
    @Override
    public void use(Player player) {
        
    }
    
}
